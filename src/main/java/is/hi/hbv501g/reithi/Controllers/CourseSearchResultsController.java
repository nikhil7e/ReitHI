package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This controller handles HTTP requests for the course search results page, such as selecting
 * a course to be viewed.
 */
@Controller
public class CourseSearchResultsController {

    private ReviewService reviewService;
    private CourseService courseService;

    @Autowired
    public CourseSearchResultsController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourseGET(@PathVariable("id") long id, Model model) {
        Course courseToDelete = courseService.findByID(id);
        courseService.delete(courseToDelete);
        return "redirect:/";
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/addcourse", method = RequestMethod.GET)
    public String addCourseGET(Course course) {
        return "newCourse";
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
    public String addCoursePOST(Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "newCourse";
        }

        courseService.save(course);
        return "searchResults";
    }

    /**
     * Gets the ViewCourse template and sets model and session attributes
     * to course details that will be displayed
     *
     * @param id      The id of the course to be viewed
     * @param model   The applications model
     * @param session The applications session
     * @return the ViewCourse template
     */
    @RequestMapping(value = "/viewcourse/{id}", method = RequestMethod.GET)
    public String viewCourseGET(@PathVariable("id") long id, Model model, HttpSession session) {
        // update html to use session instead of model and delete line 43
        model.addAttribute("selectedCourse", courseService.findByID(id));
        // Get course rating from selected course
        Course selectedCourse = (Course) model.getAttribute("selectedCourse");
        // Include rating data course in HTML
        model.addAttribute("avgOAS", reviewService.getAverageOverallScore(selectedCourse.getID()));
        model.addAttribute("avgD", reviewService.getAverageDifficulty(selectedCourse.getID()));
        model.addAttribute("avgW", reviewService.getAverageWorkload(selectedCourse.getID()));
        model.addAttribute("avgTQ", reviewService.getAverageTeachingQuality(selectedCourse.getID()));
        model.addAttribute("avgCM", reviewService.getAverageCourseMaterial(selectedCourse.getID()));
        session.setAttribute("selectedCourse", courseService.findByID(id));
        session.setAttribute("avgOAS", reviewService.getAverageOverallScore(selectedCourse.getID()));
        session.setAttribute("avgD", reviewService.getAverageDifficulty(selectedCourse.getID()));
        session.setAttribute("avgW", reviewService.getAverageWorkload(selectedCourse.getID()));
        session.setAttribute("avgTQ", reviewService.getAverageTeachingQuality(selectedCourse.getID()));
        session.setAttribute("avgCM", reviewService.getAverageCourseMaterial(selectedCourse.getID()));

        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) model.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        return "viewCourse";
    }

}
