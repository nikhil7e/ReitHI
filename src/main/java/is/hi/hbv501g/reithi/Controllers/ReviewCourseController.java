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
            return "reviewCourse";
        }

        User user = (User) session.getAttribute("LoggedInUser");
        Course selectedCourse = (Course) session.getAttribute("selectedCourse");
        reviewService.save(new Review(user, rating, comment, selectedCourse));
        session.setAttribute("hasReviewedCourse", true);

        long id = ((Course) session.getAttribute("selectedCourse")).getID();
        setScores(session, id, reviewService);
        List<Review> reviewSearchResults = reviewService.findByCourse_Name((selectedCourse.getName()));
        session.setAttribute("reviewsForCourse", reviewSearchResults);
        return "viewCourse";
    }

    public static void setScores(HttpSession session, long id, ReviewService reviewService) {
        session.setAttribute("avgOAS", reviewService.getAverageOverallScore(id));
        session.setAttribute("avgD", reviewService.getAverageDifficulty(id));
        session.setAttribute("avgW", reviewService.getAverageWorkload(id));
        session.setAttribute("avgTQ", reviewService.getAverageTeachingQuality(id));
        session.setAttribute("avgCM", reviewService.getAverageCourseMaterial(id));
    }

}
