package is.hi.hbv501g.reithi.Services;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourseService {

    Page<Course> findByName(String name, int page);

    Page<Course> findByNameContainingIgnoreCase(String name, int page);

    Page<Course> findByNameContainingIgnoreCaseWithSpec(String name, int page, Specification<Course> spec);

    Page<Course> findAll(int page);

    Page<Course> findAll(Specification<Course> spec, int page);

    Course findByID(long ID);

    Course save(Course course);

    void delete(Course course);

}
