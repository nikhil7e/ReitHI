package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseRating;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This controller handles HTTP requests for searching for courses
 */
@Controller
public class LandingPageController {

    private CourseService courseService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public LandingPageController(CourseService courseService, UserService userService, ReviewService reviewService) {
        this.courseService = courseService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    /**
     * Return the landing pages' template on root HTTP request
     *
     * @return The landing pages' template
     */
    @RequestMapping("/")
    public String landingPage(HttpSession session) {
        session.setAttribute("currentPage", "landingPage");
        return "landingPage";
    }

    /**
     * Update the model with the users' course search input and return the search results
     * page template
     *
     * @param name  The users course name search input
     * @param model The applications model
     * @return The search results page template
     */
    @RequestMapping(value = "/searchcourses", method = RequestMethod.POST)
    public String searchCoursesPOST(@RequestParam("name") String name, Model model, HttpSession session) {
        List<Course> courseSearchResults = courseService.findByNameContainingIgnoreCase(name);
        List<CourseRating> courseRatingList = new ArrayList<>();
        for (int i = 0; i < courseSearchResults.size(); i++) {
            Course course = courseSearchResults.get(i);
            long id = course.getID();

            DecimalFormat df = new DecimalFormat("0.00");

            double avgOverall = Double.parseDouble(df.format(reviewService.getAverageOverallScore(id)));
            double avgDifficulty = Double.parseDouble(df.format(reviewService.getAverageDifficulty(id)));
            double avgWorkload = Double.parseDouble(df.format(reviewService.getAverageWorkload(id)));
            double avgTeachingQuality = Double.parseDouble(df.format(reviewService.getAverageTeachingQuality(id)));
            double avgCourseMaterial = Double.parseDouble(df.format(reviewService.getAverageCourseMaterial(id)));

            CourseRating courseRating = new CourseRating(
                    course.getID(),
                    avgOverall,
                    avgDifficulty,
                    avgWorkload,
                    avgTeachingQuality,
                    avgCourseMaterial
            );

            courseRating.setID(id);
            courseRatingList.add(courseRating);
            courseRatingList.get(courseSearchResults.indexOf(course)).getAvgOverall();
        }
        session.setAttribute("courseSearchResults", courseSearchResults);
        session.setAttribute("courseRatingList", courseRatingList);
        session.setAttribute("currentPage", "searchResults");
        return "searchResults";
    }

}