package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Repositories.CourseRepository;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {

    private CourseRepository courseRepository;
    private int id_counter = 0;

    @Autowired
    public CourseServiceImplementation(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseServiceImplementation() {
    }

    /**
     * Finds courses whose names exactly match the name parameter
     *
     * @param name The course name to search for
     * @return A list of courses that exactly match the name parameter
     */
    @Override
    public Page<Course> findByName(String name, int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return courseRepository.findByName(name, pageable);
    }

    /**
     * Finds courses whose names contain the name parameter
     *
     * @param name The course name to search for
     * @return A list of courses that contain the name parameter
     */
    @Override
    public Page<Course> findByNameContainingIgnoreCase(String name, int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return courseRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    /**
     * Returns all courses stored in the database
     *
     * @return A list of all courses stored in the database
     */
    @Override
    public Page<Course> findAll(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        return courseRepository.findAll(pageable);
    }

    /**
     * Returns the course with the provided ID
     *
     * @param ID The desired courses' ID
     * @return The course with the provided ID
     */
    @Override
    public Course findByID(long ID) {
        return courseRepository.findByID(ID);
    }

    /**
     * Stores a course in the database
     *
     * @param course The course to be stored
     * @return The stored course
     */
    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    /**
     * Removes the desired course from the database
     *
     * @param course The course to be removed from the database
     */
    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }

}
