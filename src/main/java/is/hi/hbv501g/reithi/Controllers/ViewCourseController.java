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
        boolean removed = false;

        for (int i = 0; i<review.getUpvoters().size(); i++){
            if(review.getUpvoters().get(i).getUserName().equals(currentUser.getUserName())){
                review.removeUpvote(review.getUpvoters().get(i));
                removed = true;
                break;
            }
        }

        if (!removed){
            review.addUpvote(currentUser);

            for (int i = 0; i<review.getDownvoters().size(); i++){
                if(review.getDownvoters().get(i).getUserName().equals(currentUser.getUserName())){
                    review.removeDownvote(review.getDownvoters().get(i));
                    break;
                }
            }
        }
        reviewService.save(review);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        return "viewCourse";
    }

    @RequestMapping(value = "/downvote/{id}", method = RequestMethod.GET)
    public String downvotePOST(@PathVariable("id") long id, HttpSession session){
        Review review = reviewService.findByID(id);
        User currentUser = (User) session.getAttribute("LoggedInUser");
        boolean removed = false;

        for (int i = 0; i<review.getDownvoters().size(); i++){
            if(review.getDownvoters().get(i).getUserName().equals(currentUser.getUserName())){
                review.removeDownvote(review.getDownvoters().get(i));
                removed = true;
                break;
            }
        }

        if (!removed){
            review.addDownvote(currentUser);
            for (int i = 0; i<review.getUpvoters().size(); i++){
                if(review.getUpvoters().get(i).getUserName().equals(currentUser.getUserName())){
                    review.removeUpvote(review.getUpvoters().get(i));
                    break;
                }
            }
        }

        reviewService.save(review);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        return "viewCourse";
    }

    public void refreshViewCourse(HttpSession session, long id){
        ReviewCourseController.setScores(session, id, reviewService);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) session.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
    }

    @RequestMapping(value = "/deletereview/{id}", method = RequestMethod.GET)
    public String deleteReviewGET(@PathVariable("id") long id,HttpSession session){
        Review review = reviewService.findByID(id);
        User user = review.getUser();
        List<Review> allReviews = user.getReviews();
        allReviews.remove(review);
        user.setReviews(allReviews);

        reviewService.delete(review);
        session.setAttribute("hasReviewedCourse", false);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        return "viewCourse";
    }





}
