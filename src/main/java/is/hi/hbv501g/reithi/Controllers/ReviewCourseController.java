package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Rating;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewCourseController {

    private CourseService courseService;

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/addreview", method = RequestMethod.POST)
    public String addReviewPOST(Review review, Rating rating, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "newCourse";
        }

        Course c = (Course) session.getAttribute("selectedCourse");
        //courseService.save(course);
        return "landingPage";
    }

}