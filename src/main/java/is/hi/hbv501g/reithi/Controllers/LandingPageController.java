package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
/*
Landing Page controller is responsible for the front page of the website.
*/

/**
 * This controller handles HTTP requests from the landing page when the user uses the search engine.
 */
@Controller
public class LandingPageController {

    private CourseService courseService;

    @Autowired
    public LandingPageController(CourseService courseService) {
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

}