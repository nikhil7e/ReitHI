package is.hi.hbv501g.reithi.Controllers.Rest;

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

    /**
     * Creates a review and saves it in the database. Returns the review object.
     *
     * @param comment The reviews optional comment
     * @param rating  The reviews rating
     * @param result  The binding result
     * @return The course view page template
     */
    @RequestMapping(value = "/api/addreview", method = RequestMethod.POST)
    public Review addReviewPOST(@RequestParam("comment") Comment comment, @RequestParam("rating") Rating rating,
                                @RequestParam("user") User user, @RequestParam("selectedCourse") Course selectedCourse,
                                BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        Review review = reviewService.save(new Review(user, rating, comment, selectedCourse));
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