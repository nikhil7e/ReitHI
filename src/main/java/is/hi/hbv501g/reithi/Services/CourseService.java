package is.hi.hbv501g.reithi.Services;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;

import java.util.List;

public interface CourseService {

    Course findByName(String name);
    List<Course> findAll();
    Course findByID(long ID);
    Course save(Course course);
    void delete(Course course);


}
