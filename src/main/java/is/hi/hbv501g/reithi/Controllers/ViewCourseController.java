package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ViewCourseController {

    private CourseService courseService;

    @Autowired
    public ViewCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/ratecourse", method = RequestMethod.GET)
    public String rateCourseGET(Model model) {
        return "rateCourse";
    }





}
