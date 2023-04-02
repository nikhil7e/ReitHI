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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.SendResponse;
import com.google.firebase.messaging.TopicManagementResponse;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;
import com.google.firebase.messaging.WebpushNotification;

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

    private final FirebaseMessaging fcm;

    @Autowired
    public ViewCourseRESTController(UserService userService, ReviewService reviewService, CourseService courseService, FirebaseMessaging fcm) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.courseService = courseService;
        this.fcm = fcm;
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
    public int upvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException, FirebaseMessagingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        String deviceToken = objectMapper.readValue(json.get("deviceToken"), String.class);
        String courseName = objectMapper.readValue(json.get("courseName"), String.class);
        Review review = createReviewFromJson(json, objectMapper);
        review = reviewService.findByID(review.getID());

        boolean alreadyVoted = false;
        for (User alreadyVotedUser: review.getUpvoters()) {
            if (alreadyVotedUser.getID() == user.getID()){
                review.removeUpvote(alreadyVotedUser);
                alreadyVoted = true;
                break;
            }
        }
        if (!alreadyVoted){
            for (User alreadyVotedUser: review.getDownvoters()) {
                if (alreadyVotedUser.getID() == user.getID()){
                    review.removeDownvote(alreadyVotedUser);
                    break;
                }
            }
            review.addUpvote(user);
            Message msg = Message.builder().setNotification(Notification.builder()
                            .setTitle("ReitHÍ - Review upvoted")
                            .setBody("Your review for " + courseName + " has been upvoted!").build())
                            //.setToken(deviceToken)
                            .setTopic(user.getUserName())
                            .putData("body", "Upvote")
                            .build();
            String id = fcm.send(msg);
            ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
        }

        reviewService.save(review);
        return review.getUpvotes();
    }

    @RequestMapping(value = "/api/downvote/", method = RequestMethod.POST)
    public int downvotePOST(@RequestBody Map<String, String> json) throws JsonProcessingException, FirebaseMessagingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        String deviceToken = objectMapper.readValue(json.get("deviceToken"), String.class);
        String courseName = objectMapper.readValue(json.get("courseName"), String.class);
        Review review = createReviewFromJson(json, objectMapper);
        review = reviewService.findByID(review.getID());

        boolean alreadyVoted = false;
        for (User alreadyVotedUser: review.getDownvoters()) {
            if (alreadyVotedUser.getID() == user.getID()){
                review.removeDownvote(alreadyVotedUser);
                alreadyVoted = true;
                break;
            }
        }
        if (!alreadyVoted){
            for (User alreadyVotedUser: review.getUpvoters()) {
                if (alreadyVotedUser.getID() == user.getID()){
                    review.removeUpvote(alreadyVotedUser);
                    break;
                }
            }
            review.addDownvote(user);
            Message msg = Message.builder().setNotification(Notification.builder()
                            .setTitle("ReitHÍ - Review downvoted")
                            .setBody("Your review for " + courseName + " has been downvoted!").build())
                    .setTopic(user.getUserName())
                    .putData("body", "Downvote")
                    .build();
            String id = fcm.send(msg);
            ResponseEntity.status(HttpStatus.ACCEPTED).body(id);
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

    public void sendToToken() throws FirebaseMessagingException {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "YOUR_REGISTRATION_TOKEN";

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        // [END send_to_token]
    }


}