package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Controllers.Rest.ReviewCourseRESTController;
import is.hi.hbv501g.reithi.Persistence.Entities.*;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * This controller handles HTTP requests for review functionality, such as adding and deleting reviews.
 */
@Controller
public class ReviewCourseController {

    private CourseService courseService;
    private ReviewService reviewService;

    @Autowired
    public ReviewCourseController(CourseService courseService, ReviewService reviewService) {
        this.courseService = courseService;
        this.reviewService = reviewService;
    }

    /**
     * Creates a review and saves it in the database. Redirects to the course view page with updated values.
     *
     * @param comment The reviews optional comment
     * @param result  The binding result
     * @param model   The applications model
     * @param session The applications session
     * @return The course view page template
     */
    @RequestMapping(value = "/addreview", method = RequestMethod.POST)
    public String addReviewPOST(String comment, int overallScore, int difficulty, int workload, int teachingQuality, int courseMaterial, Model model, HttpSession session) {
//        if (result.hasErrors()) {
//            return "reviewCourse";
//        }

        User user = (User) session.getAttribute("LoggedInUser");
        Course selectedCourse = (Course) session.getAttribute("selectedCourse");
        selectedCourse.setNrReviews(selectedCourse.getNrReviews() + 1);
        Review review = reviewService.save(new Review(user, selectedCourse, overallScore, difficulty, workload, teachingQuality, courseMaterial, comment));
        selectedCourse = review.getCourse();
        selectedCourse.setTotalOverall(selectedCourse.getTotalOverall() + overallScore);
        selectedCourse.setTotalDifficulty(selectedCourse.getTotalDifficulty() + difficulty);
        selectedCourse.setTotalWorkload(selectedCourse.getTotalWorkload() + workload);
        selectedCourse.setTotalTeachingQuality(selectedCourse.getTotalTeachingQuality() + teachingQuality);
        selectedCourse.setTotalCourseMaterial(selectedCourse.getTotalCourseMaterial() + courseMaterial);

        courseService.save(selectedCourse);
        session.setAttribute("hasReviewedCourse", true);

        long id = ((Course) session.getAttribute("selectedCourse")).getID();
        setScores(session, id, reviewService, courseService);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name((selectedCourse.getName()));
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        session.setAttribute("currentPage", "viewCourse");
        return "viewCourse";
    }

    /**
     * Saves the average scores for the viewed course in the session
     *
     * @param session       The applications session
     * @param id            The courses id
     * @param reviewService The review service
     */
    public static void setScores(HttpSession session, long id, ReviewService reviewService, CourseService courseService) {
        Course course = courseService.findByID(id);

        if (course.getNrReviews() == 0) {
            session.setAttribute("avgOAS", 0);
            session.setAttribute("avgD", 0);
            session.setAttribute("avgW", 0);
            session.setAttribute("avgTQ", 0);
            session.setAttribute("avgCM", 0);
        } else {
            session.setAttribute("avgOAS", course.getTotalOverall() / course.getNrReviews());
            session.setAttribute("avgD", course.getTotalDifficulty() / course.getNrReviews());
            session.setAttribute("avgW", course.getTotalWorkload() / course.getNrReviews());
            session.setAttribute("avgTQ", course.getTotalTeachingQuality() / course.getNrReviews());
            session.setAttribute("avgCM", course.getTotalCourseMaterial() / course.getNrReviews());
        }
    }

}
