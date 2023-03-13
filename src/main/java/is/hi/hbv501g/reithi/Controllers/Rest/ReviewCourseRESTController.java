package is.hi.hbv501g.reithi.Controllers.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.hbv501g.reithi.Persistence.Entities.*;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This REST controller handles HTTP requests for review functionality
 */
@RestController
public class ReviewCourseRESTController {

    private CourseService courseService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public ReviewCourseRESTController(CourseService courseService, UserService userService, ReviewService reviewService) {
        this.courseService = courseService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    // TODO: Dont allow default user

    /**
     * Creates a review and saves it in the database. Returns the review object.
     *
     * @return The course view page template
     */
    @RequestMapping(value = "/api/addreview", method = RequestMethod.POST)
    public Review addReviewPOST(@RequestBody ObjectNode json) throws JsonProcessingException {
        Review review;
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.treeToValue(json.get("user"), User.class);
        String comment = objectMapper.treeToValue(json.get("comment"), String.class);
        int overallScore = objectMapper.treeToValue(json.get("overallScore"), int.class);
        int difficulty = objectMapper.treeToValue(json.get("difficulty"), int.class);
        int workload = objectMapper.treeToValue(json.get("workload"), int.class);
        int teachingQuality = objectMapper.treeToValue(json.get("teachingQuality"), int.class);
        int courseMaterial = objectMapper.treeToValue(json.get("courseMaterial"), int.class);
        Course selectedCourse = objectMapper.treeToValue(json.get("selectedCourse"), Course.class);

        if (user == null) {
            review = reviewService.save(new Review(userService.login(new User("x", "x")), selectedCourse, overallScore, difficulty, workload, teachingQuality, courseMaterial, comment));
        } else {
            review = reviewService.save(new Review(user, selectedCourse, overallScore, difficulty, workload, teachingQuality, courseMaterial, comment));
        }

        selectedCourse.setTotalOverall(selectedCourse.getTotalOverall() + overallScore);
        selectedCourse.setTotalDifficulty(selectedCourse.getTotalDifficulty() + difficulty);
        selectedCourse.setTotalWorkload(selectedCourse.getTotalWorkload() + workload);
        selectedCourse.setTotalTeachingQuality(selectedCourse.getTotalTeachingQuality() + teachingQuality);
        selectedCourse.setTotalCourseMaterial(selectedCourse.getTotalCourseMaterial() + courseMaterial);
        courseService.save(selectedCourse);
//
//        long id = ((Course) session.getAttribute("selectedCourse")).getID();
//        setScores(session, id, reviewService);
//        List<Review> reviewSearchResults = reviewService.findByCourse_Name((selectedCourse.getName()));
//        session.setAttribute("reviewsForCourse", reviewSearchResults);
//        session.setAttribute("currentPage", "viewCourse");

        return review;
    }

    /**
     * Saves the average scores for the viewed course in the session
     *
     * @param session       The applications session
     * @param id            The courses id
     * @param reviewService The review service
     */
    public static void setScores(HttpSession session, long id, ReviewService reviewService) {
        session.setAttribute("avgOAS", reviewService.getAverageOverallScore(id));
        session.setAttribute("avgD", reviewService.getAverageDifficulty(id));
        session.setAttribute("avgW", reviewService.getAverageWorkload(id));
        session.setAttribute("avgTQ", reviewService.getAverageTeachingQuality(id));
        session.setAttribute("avgCM", reviewService.getAverageCourseMaterial(id));
    }

}
