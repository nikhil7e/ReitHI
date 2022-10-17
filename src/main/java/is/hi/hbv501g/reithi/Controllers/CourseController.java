package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller handles HTTP requests from the landing page when the user uses the search engine.
 */
@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Return the landing pages' template on root HTTP request
     *
     * @return The landing pages' template
     */
    @RequestMapping("/")
    public String landingPage() {
        return "landingPage";
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/addcourse", method = RequestMethod.GET)
    public String addCourseGET() {
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
     * Update the model with the users' course search input and return the search results
     * page template
     *
     * @param name  The users' course name search input
     * @param model The applications' model
     * @return The search results page template
     */
    @RequestMapping(value = "/searchcourses", method = RequestMethod.POST)
    public String searchCoursesPOST(@RequestParam("name") String name, Model model) {
        List<Course> courseSearchResults = courseService.findByNameContainingIgnoreCase(name);
        model.addAttribute("courseSearchResults", courseSearchResults);
        return "searchResults";
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourseGET(@PathVariable("id") long id, Model model) {
        Course courseToDelete = courseService.findByID(id);
        courseService.delete(courseToDelete);
        return "redirect:/";
    }

    @RequestMapping(value = "/viewcourse/{id}", method = RequestMethod.GET)
    public String viewCourseGET(@PathVariable("id") long id, Model model) {
        model.addAttribute("selectedCourse", courseService.findByID(id));
        return "viewCourse";
    }


}
