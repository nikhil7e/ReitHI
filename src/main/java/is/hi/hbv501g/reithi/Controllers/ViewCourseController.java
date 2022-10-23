package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Comment;
import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Persistence.Repositories.CourseRepository;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewCourseController {

    private CourseService courseService;
    private ReviewService reviewService;

    @Autowired
    public ViewCourseController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    @RequestMapping(value = "/reviewcourse", method = RequestMethod.GET)
    public String reviewCourseGET(Model model) {
        return "reviewCourse";
    }


    @RequestMapping(value = "/upvote/{id}", method = RequestMethod.GET)
    public String upvotePOST(@PathVariable("id") long id, HttpSession session){
        Review review = reviewService.findByID(id);
        User currentUser = (User) session.getAttribute("LoggedInUser");
        if(review.getUpvoters().contains(currentUser)){
            review.removeUpvote(currentUser);
        }
        else{
            review.addUpvote(currentUser);
        }

        reviewService.save(review);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) session.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        return "viewCourse";
    }
    @RequestMapping(value = "/downvote/{id}", method = RequestMethod.GET)
    public String downvotePOST(@PathVariable("id") long id, HttpSession session){
        Review review = reviewService.findByID(id);
        User currentUser = (User) session.getAttribute("LoggedInUser");
        if(review.getUpvoters().contains(currentUser)){
            review.removeDownvote(currentUser);
        }
        else{
            review.addDownvote(currentUser);
        }
        reviewService.save(review);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) session.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        return "viewCourse";
    }



}
