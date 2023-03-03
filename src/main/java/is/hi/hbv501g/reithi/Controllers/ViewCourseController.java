package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This controller handles HTTP requests for the view course page, such as voting reviews, requesting
 * the review page and deleting reviews.
 */
@Controller
public class ViewCourseController {

    private CourseService courseService;
    private ReviewService reviewService;

    private UserService userService;


    @Autowired
    public ViewCourseController(CourseService courseService, ReviewService reviewService, UserService userService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    /**
     * Gets the review course page template
     *
     * @param model The applications model
     * @return The review course page template
     */
    @RequestMapping(value = "/reviewcourse", method = RequestMethod.GET)
    public String reviewCourseGET(Model model, HttpSession session) {
        session.setAttribute("currentPage", "reviewCourse");
        return "reviewCourse";
    }

    /**
     * Upvotes a review
     *
     * @param id      The id of the review to be upvoted
     * @param session The applications session
     * @return The view course page template
     */
    @RequestMapping(value = "/upvote/{id}", method = RequestMethod.GET)
    public String upvotePOST(@PathVariable("id") long id, HttpSession session) {
        Review review = reviewService.findByID(id);
        User currentUser = (User) session.getAttribute("LoggedInUser");
        boolean removed = false;

        for (int i = 0; i < review.getUpvoters().size(); i++) {
            if (review.getUpvoters().get(i).getUserName().equals(currentUser.getUserName())) {
                review.removeUpvote(review.getUpvoters().get(i));
                removed = true;
                break;
            }
        }

        if (!removed) {
            for (int i = 0; i < review.getDownvoters().size(); i++) {
                if (review.getDownvoters().get(i).getUserName().equals(currentUser.getUserName())) {
                    review.removeDownvote(review.getDownvoters().get(i));
                    break;
                }
            }
            review.addUpvote(currentUser);
        }
        reviewService.save(review);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        session.setAttribute("currentPage", "viewCourse");
        return "viewCourse";
    }

    /**
     * Downvotes a review
     *
     * @param id      The id of the review to be downvoted
     * @param session The applications session
     * @return The view course page template
     */
    @RequestMapping(value = "/downvote/{id}", method = RequestMethod.GET)
    public String downvotePOST(@PathVariable("id") long id, HttpSession session) {
        Review review = reviewService.findByID(id);
        User currentUser = (User) session.getAttribute("LoggedInUser");
        boolean removed = false;

        for (int i = 0; i < review.getDownvoters().size(); i++) {
            if (review.getDownvoters().get(i).getUserName().equals(currentUser.getUserName())) {
                review.removeDownvote(review.getDownvoters().get(i));
                removed = true;
                break;
            }
        }

        if (!removed) {
            for (int i = 0; i < review.getUpvoters().size(); i++) {
                if (review.getUpvoters().get(i).getUserName().equals(currentUser.getUserName())) {
                    review.removeUpvote(review.getUpvoters().get(i));
                    break;
                }
            }
            review.addDownvote(currentUser);
        }

        reviewService.save(review);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        session.setAttribute("currentPage", "viewCourse");
        return "viewCourse";
    }

    /**
     * Refreshes model and session attributes to be displayed for the course
     *
     * @param session The applications session
     * @param id      The courses id
     */
    public void refreshViewCourse(HttpSession session, long id) {
        ReviewCourseController.setScores(session, id, reviewService, courseService);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name(((Course) session.getAttribute("selectedCourse")).getName());
        session.setAttribute("reviewsForCourse", reviewSearchResults);
    }

    /**
     * Deletes a review
     *
     * @param id      The id of the review to be deleted
     * @param session The applications session
     * @return The view course page template
     */
    @RequestMapping(value = "/deletereview/{id}", method = RequestMethod.GET)
    public String deleteReviewGET(@PathVariable("id") long id, HttpSession session) {
        Review review = reviewService.findByID(id);
        Course selectedCourse = (Course) session.getAttribute("selectedCourse");
        selectedCourse.setNrReviews(selectedCourse.getNrReviews() - 1);

        selectedCourse.setTotalOverall(selectedCourse.getTotalOverall() - review.getRating().getOverallScore());
        selectedCourse.setTotalDifficulty(selectedCourse.getTotalDifficulty() - review.getRating().getDifficulty());
        selectedCourse.setTotalWorkload(selectedCourse.getTotalWorkload() - review.getRating().getWorkload());
        selectedCourse.setTotalTeachingQuality(selectedCourse.getTotalTeachingQuality() - review.getRating().getTeachingQuality());
        selectedCourse.setTotalCourseMaterial(selectedCourse.getTotalCourseMaterial() - review.getRating().getCourseMaterial());

        courseService.save(selectedCourse);
        User user = review.getUser();
        List<Review> allReviews = user.getReviews();
        allReviews.remove(review);
        user.setReviews(allReviews);
        for (int i = 0; i < review.getUpvoters().size(); i++) {
            review.removeUpvote(review.getUpvoters().get(i));
        }
        for (int i = 0; i < review.getDownvoters().size(); i++) {
            review.removeDownvote(review.getDownvoters().get(i));
        }
        reviewService.save(review);
        reviewService.delete(review);
        session.setAttribute("hasReviewedCourse", false);
        refreshViewCourse(session, ((Course) session.getAttribute("selectedCourse")).getID());
        session.setAttribute("currentPage", "viewCourse");
        return "viewCourse";
    }

}
