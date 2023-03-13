package is.hi.hbv501g.reithi.Controllers.Rest;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseRating;
import is.hi.hbv501g.reithi.Services.CourseService;
import is.hi.hbv501g.reithi.Services.ReviewService;
import is.hi.hbv501g.reithi.Services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
     * @param name  The users course name search input
     * @return The search results page template
     */
    @RequestMapping(value = "/api/searchcourses", method = RequestMethod.POST)
    public List<Course> searchCoursesPOST(@RequestBody JSONObject jsonBody) throws JSONException {
        String name = jsonBody.getString("name");
        return courseService.findByNameContainingIgnoreCase(name);
    }

}