package is.hi.hbv501g.reithi.Persistence.Repositories;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.CourseSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

    Course save(Course course);

    void delete(Course course);

    Page<Course> findAll(Pageable pageable);

    // List<Course> findAll(CourseSpecification spec);
    Page<Course> findByName(String name, Pageable pageable);

    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Course findByID(long ID);

}
