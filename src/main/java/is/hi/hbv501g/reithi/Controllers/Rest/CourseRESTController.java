package is.hi.hbv501g.reithi.Controllers.Rest;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return The search results page template
     */
    @RequestMapping(value = "/api/searchcourses", method = RequestMethod.POST)
    public List<Course> searchCoursesPOST(@RequestBody Map<String, String> payload) throws JSONException {
        return courseService.findByNameContainingIgnoreCase(payload.get("name"));
    }

}