package is.hi.hbv501g.reithi.Controllers.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    public Review createReviewFromJson(Map<String, String> json, ObjectMapper objectMapper) throws JsonProcessingException {
        Map<String,Object> jsonObject = objectMapper.readValue(json.get("review"), Map.class);
        Review review = new Review();
        review.setComment(jsonObject.get("comment").toString());
        review.setID(Long.parseLong((jsonObject.get("ID")).toString()));
        review.setUser(userService.findByID(Long.parseLong(( jsonObject.get("user_id")).toString())));
        review.setCourse(courseService.findByID(Long.parseLong(( jsonObject.get("course_id")).toString())));
        List<User> upvoters = new ArrayList<>();
        List<User> downvoters = new ArrayList<>();
        if (jsonObject.containsKey("upvoters")){
            upvoters = objectMapper.readValue(objectMapper.writeValueAsString(
                    jsonObject.get("upvoters")),
                    new TypeReference<List<User>>() {});
        }
        if(jsonObject.containsKey("downvoters")){
            downvoters = objectMapper.readValue(objectMapper.writeValueAsString(
                    jsonObject.get("downvoters")),
                    new TypeReference<List<User>>() {});
        }
        review.setUpvoters(upvoters);
        review.setDownvoters(downvoters);
        return review;
    }


    @RequestMapping(value = "/api/upvote/", method = RequestMethod.POST)
    public int upvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        Review review = createReviewFromJson(json, objectMapper);

        if (review.getUpvoters() != null && review.getUpvoters().contains(user)) {
            review.removeUpvote(user);
        } else if (review.getDownvoters() != null  && review.getDownvoters().contains(user)) {
            review.removeDownvote(user);
            review.addUpvote(user);
        }
        else {
            review.addUpvote(user);
        }
        reviewService.save(review);
        return review.getUpvotes();
    }

    @RequestMapping(value = "/api/downvote/", method = RequestMethod.POST)
    public int downvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        Review review = createReviewFromJson(json, objectMapper);
        if (review.getDownvoters() != null && review.getDownvoters().contains(user)) {
            review.removeDownvote(user);
        } else if (review.getUpvoters() != null  && review.getUpvoters().contains(user)) {
            review.removeUpvote(user);
            review.addDownvote(user);
        } else {
            review.addDownvote(user);
        }
        reviewService.save(review);
        return review.getUpvotes();
    }

    @RequestMapping(value = "/api/deletereview/", method = RequestMethod.POST)
    public void deleteReviewPOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Review review = objectMapper.readValue(json.get("review"), Review.class);
        Course selectedCourse = courseService.findByID(review.getCourseId());

        selectedCourse.setTotalOverall(selectedCourse.getTotalOverall() - review.getOverallScore());
        selectedCourse.setTotalDifficulty(selectedCourse.getTotalDifficulty() - review.getDifficulty());
        selectedCourse.setTotalWorkload(selectedCourse.getTotalWorkload() - review.getWorkload());
        selectedCourse.setTotalTeachingQuality(selectedCourse.getTotalTeachingQuality() - review.getTeachingQuality());
        selectedCourse.setTotalCourseMaterial(selectedCourse.getTotalCourseMaterial() - review.getCourseMaterial());
        selectedCourse.setNrReviews(selectedCourse.getNrReviews() - 1);

        courseService.save(selectedCourse);
        reviewService.delete(review);
    }

}