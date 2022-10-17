package is.hi.hbv501g.reithi.Persistence.Repositories;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course save(Course course);
    void delete(Course course);
    List<Course> findAll();
    List<Course> findByName(String name);
    List<Course> findByNameContainingIgnoreCase(String name);
    Course findByID(long ID);

}
