package is.hi.hbv501g.reithi.Services;

import is.hi.hbv501g.reithi.Persistence.Entities.Review;

import java.util.List;

import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.Review;

public interface ReviewService {
    Double getAverageOverallScore(long ID);
    Double getAverageDifficulty(long ID);
    Double getAverageWorkload(long ID);
    Double getAverageTeachingQuality(long ID);
    Double getAverageCourseMaterial(long ID);

    List<Review> findAll ();
    Review save(Review review);
    void delete(Review review);
    List<Review> findByCourse_Name(String name);
    List<Review> findByCourse_ID(long ID);
    Review findByID(long ID);
}
