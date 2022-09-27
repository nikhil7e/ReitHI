package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Repositories.CourseRepository;
import is.hi.hbv501g.reithi.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByID(long ID) {
        return courseRepository.findByID(ID);
    }

    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }
}
