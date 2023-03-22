package is.hi.hbv501g.reithi.Controllers;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseRating;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseSpecification;
import is.hi.hbv501g.reithi.Persistence.Entities.SearchCriteria;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This controller handles HTTP requests for searching for courses
 */
@Controller
public class LandingPageController {

    private CourseService courseService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public LandingPageController(CourseService courseService, UserService userService, ReviewService reviewService) {
        this.courseService = courseService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    /**
     * Return the landing pages' template on root HTTP request
     *
     * @return The landing pages' template
     */
    @RequestMapping("/")
    public String landingPage(HttpSession session) {
        session.setAttribute("currentPage", "landingPage");
        return "landingPage";
    }

    /**
     * Update the model with the users' course search input and return the search results
     * page template
     *
     * @param name  The users course name search input
     * @param model The applications model
     * @return The search results page template
     */
    @RequestMapping(value = "/searchcourses", method = RequestMethod.GET)
    public String searchCoursesGET(@RequestParam("name") String name, @RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        Page<Course> courseSearchResultsPage = courseService.findByNameContainingIgnoreCase(name, page - 1);
        List<Course> courseSearchResults = courseSearchResultsPage.getContent();

        List<CourseRating> courseRatingList = new ArrayList<>();
        for (Course course : courseSearchResults) {
            long id = course.getID();

            DecimalFormat df = new DecimalFormat("0.00");
            double avgOverall;
            double avgDifficulty;
            double avgWorkload;
            double avgTeachingQuality;
            double avgCourseMaterial;

            if (course.getNrReviews() == 0) {
                avgOverall = 0;
                avgDifficulty = 0;
                avgWorkload = 0;
                avgTeachingQuality = 0;
                avgCourseMaterial = 0;
            } else {
                avgOverall = Double.parseDouble(df.format(course.getTotalOverall() / course.getNrReviews()));
                avgDifficulty = Double.parseDouble(df.format(course.getTotalDifficulty() / course.getNrReviews()));
                avgWorkload = Double.parseDouble(df.format(course.getTotalWorkload() / course.getNrReviews()));
                avgTeachingQuality = Double.parseDouble(df.format(course.getTotalTeachingQuality() / course.getNrReviews()));
                avgCourseMaterial = Double.parseDouble(df.format(course.getTotalCourseMaterial() / course.getNrReviews()));
            }
            CourseRating courseRating = new CourseRating(
                    course.getID(),
                    avgOverall,
                    avgDifficulty,
                    avgWorkload,
                    avgTeachingQuality,
                    avgCourseMaterial
            );

            courseRating.setID(id);
            courseRatingList.add(courseRating);
        }

        model.addAttribute("searchTerm", name);
        model.addAttribute("courseSearchResults", courseSearchResults);
        model.addAttribute("courseRatingList", courseRatingList);
        model.addAttribute("courseSearchResultsPage", courseSearchResultsPage);
        session.setAttribute("currentPage", "searchResults");
        return "searchResults";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public String filterGET(@RequestParam("name") String name, @RequestParam(defaultValue = "1") int page, Model model, HttpSession session) {
        CourseSpecification spec =
                new CourseSpecification(new SearchCriteria("name", ":", name));
        CourseSpecification spec2 =
                new CourseSpecification(new SearchCriteria("nrReviews", ">", "1"));

        Page<Course> courseSearchResultsPage = courseService.findAll(Specification.where(spec).and(spec2), page - 1);
        List<Course> courseSearchResults = courseSearchResultsPage.getContent();

        List<CourseRating> courseRatingList = new ArrayList<>();
        for (Course course : courseSearchResults) {
            long id = course.getID();

            DecimalFormat df = new DecimalFormat("0.00");
            double avgOverall;
            double avgDifficulty;
            double avgWorkload;
            double avgTeachingQuality;
            double avgCourseMaterial;

            if (course.getNrReviews() == 0) {
                avgOverall = 0;
                avgDifficulty = 0;
                avgWorkload = 0;
                avgTeachingQuality = 0;
                avgCourseMaterial = 0;
            } else {
                avgOverall = Double.parseDouble(df.format(course.getTotalOverall() / course.getNrReviews()));
                avgDifficulty = Double.parseDouble(df.format(course.getTotalDifficulty() / course.getNrReviews()));
                avgWorkload = Double.parseDouble(df.format(course.getTotalWorkload() / course.getNrReviews()));
                avgTeachingQuality = Double.parseDouble(df.format(course.getTotalTeachingQuality() / course.getNrReviews()));
                avgCourseMaterial = Double.parseDouble(df.format(course.getTotalCourseMaterial() / course.getNrReviews()));
            }
            CourseRating courseRating = new CourseRating(
                    course.getID(),
                    avgOverall,
                    avgDifficulty,
                    avgWorkload,
                    avgTeachingQuality,
                    avgCourseMaterial
            );

            courseRating.setID(id);
            courseRatingList.add(courseRating);
        }

        model.addAttribute("searchTerm", name);
        model.addAttribute("courseSearchResults", courseSearchResults);
        model.addAttribute("courseRatingList", courseRatingList);
        model.addAttribute("courseSearchResultsPage", courseSearchResultsPage);
        session.setAttribute("currentPage", "searchResults");
        return "searchResults";



//        return courseService.findAll(Specification.where(spec).and(spec2), 0).toList();
//        //return courseService.findByNameContainingIgnoreCase(name, 20);
    }

}