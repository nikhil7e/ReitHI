package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImplementation implements CourseService {

    private int id_counter = 0;

    @Autowired
    public CourseServiceImplementation() {
    }

    @Override
    public Course findByName(String name) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course findByID(long ID) {
        return null;
    }

    @Override
    public Course save(Course course) {
        return null;
    }

    @Override
    public void delete(Course course) {

    }
}
