package is.hi.hbv501g.reithi.Controllers.Rest;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseRating;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseSpecification;
import is.hi.hbv501g.reithi.Persistence.Entities.SearchCriteria;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * This REST controller handles HTTP requests for searching for courses
 */
@RestController
public class CourseRESTController {

    private CourseService courseService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public CourseRESTController(CourseService courseService, UserService userService, ReviewService reviewService) {
        this.courseService = courseService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    /**
     * Update the model with the users' course search input and return the search results
     * page template
     *
     * @param name The users course name search input
     * @return The search results page template
     */
    @RequestMapping(value = "/api/searchcourses", method = RequestMethod.GET)
    public Page<Course> searchCoursesGET(@RequestParam("name") String name, @RequestParam(defaultValue = "1") int page) {
        return courseService.findByNameContainingIgnoreCase(name, page - 1);
    }

    // FOR TESTING PURPOSES, ADJUST
    @RequestMapping(value = "/api/filter", method = RequestMethod.GET)
    public List<Course> filterGET(@RequestParam("name") String name) {
        CourseSpecification spec =
                new CourseSpecification(new SearchCriteria("name", ":", name));
        CourseSpecification spec2 =
                new CourseSpecification(new SearchCriteria("nrReviews", ">", "1"));

        return courseService.findAll(Specification.where(spec).and(spec2), 0).toList();
        //return courseService.findByNameContainingIgnoreCase(name, 20);
    }

}