package is.hi.hbv501g.reithi.Controllers.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * This controller handles HTTP requests for user functionality, such as logging in, signing up and logging out.
 */
@RestController
public class ViewCourseRESTController {

    private UserService userService;
    private ReviewService reviewService;

    private CourseService courseService;

    @Autowired
    public ViewCourseRESTController(UserService userService, ReviewService reviewService, CourseService courseService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.courseService = courseService;
    }


    @RequestMapping(value = "/api/upvote/", method = RequestMethod.GET)
    public Review upvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        Review review = objectMapper.readValue(json.get("review"), Review.class);

        if (review.getUpvoters().contains(user)) {
            review.removeUpvote(user);
        } else if (review.getDownvoters().contains(user)) {
            review.removeDownvote(user);
            review.addUpvote(user);
        } else {
            review.addUpvote(user);
        }
        reviewService.save(review);
        return review;
    }

    @RequestMapping(value = "/api/downvote/", method = RequestMethod.GET)
    public Review downvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        Review review = objectMapper.readValue(json.get("review"), Review.class);

        if (review.getDownvoters().contains(user)) {
            review.removeDownvote(user);
        } else if (review.getUpvoters().contains(user)) {
            review.removeUpvote(user);
            review.addDownvote(user);
        } else {
            review.addDownvote(user);
        }
        reviewService.save(review);
        return review;
    }

    @RequestMapping(value = "/api/deletereview/", method = RequestMethod.GET)
    public void deleteReviewGET(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Course selectedCourse = objectMapper.readValue(json.get("selectedCourse"), Course.class);
        Review review = objectMapper.readValue(json.get("review"), Review.class);

        selectedCourse.setTotalOverall(selectedCourse.getTotalOverall() - review.getOverallScore());
        selectedCourse.setTotalDifficulty(selectedCourse.getTotalDifficulty() - review.getDifficulty());
        selectedCourse.setTotalWorkload(selectedCourse.getTotalWorkload() - review.getWorkload());
        selectedCourse.setTotalTeachingQuality(selectedCourse.getTotalTeachingQuality() - review.getTeachingQuality());
        selectedCourse.setTotalCourseMaterial(selectedCourse.getTotalCourseMaterial() - review.getCourseMaterial());
        selectedCourse.setNrReviews(selectedCourse.getNrReviews() - 1);

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
    }

}