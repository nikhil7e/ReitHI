package is.hi.hbv501g.reithi.Persistence.Repositories;

import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review save(Review review);
    void delete(Review review);
    List<Review> findAll();
    List<Review> findByCourse_Name(String name);
    List<Review> findByCourse_ID(long ID);
    Review findByID(long ID);



}
