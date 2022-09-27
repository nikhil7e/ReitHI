package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/addcourse", method = RequestMethod.GET)
    public String addCourseGET(Course course){

        return "newCourse.html";
    }

    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
    public String addCoursePOST(Course course, BindingResult result, Model model){
        if(result.hasErrors()){
            return "newCourse.html";
        }
        courseService.save(course);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") long id, Model model) {
        Course courseToDelete = courseService.findByID(id);
        courseService.delete(courseToDelete);
        return "redirect:/";
    }

}
