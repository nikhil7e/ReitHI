package is.hi.hbv501g.reithi.Controllers.Rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
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

    @RequestMapping(value = "/api/getcoursebyid", method = RequestMethod.GET)
    public Course genericGET(@RequestParam("id") int id ) {
        return courseService.findByID(id);
    }

    // FOR TESTING PURPOSES, ADJUST
    @RequestMapping(value = "/api/filter", method = RequestMethod.POST)
    public Page<Course> filterPOST(@RequestBody Map<String, String> json, @RequestParam(defaultValue = "1") int page) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json.get("filter"), Map.class);
        boolean scores = false;
        List<Specification<Course>> specs = new ArrayList<>();


        Object undergrad = map.get("undergraduate");
        if (undergrad!=null){
            System.out.println("undergraduate wasn't null");
            specs.add(new CourseSpecification(new SearchCriteria("level", ":", "undergraduate")));
        }
        Object grad = map.get("graduate");
        if (grad!=null){
            System.out.println("graduate wasn't null");
            specs.add(new CourseSpecification(new SearchCriteria("level", "!:", "undergraduate")));
        }
        ArrayList<Integer> creditsRange = (ArrayList<Integer>) map.get("creditsRange");
        if (creditsRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("credits", "<", creditsRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("credits", ">", creditsRange.get(0))));
        }

        ArrayList<Integer> overallRange = (ArrayList<Integer>) map.get("overallRange");
        if (overallRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("totalOverall", "sum<", overallRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("totalOverall", "sum>", overallRange.get(0))));
            specs.add(new CourseSpecification(new SearchCriteria("totalOverall", ">", 1)));
        }

        ArrayList<Integer> totalDifficultyRange = (ArrayList<Integer>) map.get("difficultyRange");
        if (totalDifficultyRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("totalDifficulty", "sum<", totalDifficultyRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("totalDifficulty", "sum>", totalDifficultyRange.get(0))));
            specs.add(new CourseSpecification(new SearchCriteria("totalDifficulty", ">", 1)));
        }

        ArrayList<Integer> totalWorkloadRange = (ArrayList<Integer>) map.get("workloadRange");
        if (totalWorkloadRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("totalWorkload", "sum<", totalWorkloadRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("totalWorkload", "sum>", totalWorkloadRange.get(0))));
            specs.add(new CourseSpecification(new SearchCriteria("totalWorkload", ">", 1)));
        }

        ArrayList<Integer> totalTeachingQualityRange = (ArrayList<Integer>) map.get("teachingQualityRange");
        if (totalTeachingQualityRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("totalTeachingQuality", "sum<", totalTeachingQualityRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("totalTeachingQuality", "sum>", totalTeachingQualityRange.get(0))));
            specs.add(new CourseSpecification(new SearchCriteria("totalTeachingQuality", ">", 1)));
        }

        ArrayList<Integer> totalCourseMaterialRange = (ArrayList<Integer>) map.get("courseMaterialRange");
        if (totalCourseMaterialRange != null) {
            specs.add(new CourseSpecification(new SearchCriteria("totalCourseMaterial", "sum<", totalCourseMaterialRange.get(1))));
            specs.add(new CourseSpecification(new SearchCriteria("totalCourseMaterial", "sum>", totalCourseMaterialRange.get(0))));
            specs.add(new CourseSpecification(new SearchCriteria("totalCourseMaterial", ">", 1)));
        }
        CourseSpecification spec2 = new CourseSpecification(new SearchCriteria("ID", ">", 0));


        Specification<Course> combinedSpec = spec2;

        for (Specification<Course> spec : specs) {
            combinedSpec = combinedSpec.and(spec);
        }



        if (combinedSpec != spec2) {
            return courseService.findAll(combinedSpec, page-1);
        } else {
            return courseService.findAll(page-1);
        }
        //return courseService.findByNameContainingIgnoreCase(name, 20);
    }

}