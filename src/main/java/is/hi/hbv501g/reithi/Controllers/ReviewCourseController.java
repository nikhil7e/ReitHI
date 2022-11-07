package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.*;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReviewCourseController {

    private CourseService courseService;
    private ReviewService reviewService;

    @Autowired
    public ReviewCourseController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    // TODO: For testing purposes only, remove eventually
    @RequestMapping(value = "/addreview", method = RequestMethod.POST)
    public String addReviewPOST(Comment comment, Rating rating, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "newCourse";
        }

        reviewService.save(new Review((User) session.getAttribute("LoggedInUser"), rating, comment, (Course) session.getAttribute("selectedCourse")));
        //Course c = (Course) session.getAttribute("selectedCourse");
        //courseService.save(c);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) session.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        return "viewCourse";
    }
    @RequestMapping(value = "/deletereview", method = RequestMethod.POST)
    public String deleteReviewPOST(Review review){
        reviewService.delete(review);
        return "viewCourse";
    }




}
