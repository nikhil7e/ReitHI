package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LandingPageController  {

    private CourseService courseService;

    @Autowired
    public LandingPageController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("/")
    public String landingPage(Model model) {
        List<Course> allCourses = courseService.findAll();
        model.addAttribute("courses", allCourses);

        return "landingPage.html";
    }

}
