package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Repositories.CourseRepository;
import is.hi.hbv501g.reithi.Persistence.Repositories.ReviewRepository;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;
    private int id_counter = 0;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewServiceImplementation() {
    }

    @Override
    public Double getAverageOverallScore(long ID) {
        List<Review> list = reviewRepository.findByCourse_ID(ID);
        double sum = 0;
        for(int i = 0;i < list.size(); i++ ) {
            sum += list.get(i).getRating().getOverallScore();
        }
        return sum / list.size();
    }

    @Override
    public Double getAverageDifficulty(long ID) {
        List<Review> list = reviewRepository.findByCourse_ID(ID);
        double sum = 0;
        for(int i = 0;i < list.size(); i++ ) {
            sum += list.get(i).getRating().getDifficulty();
        }
        return sum / list.size();
    }

    @Override
    public Double getAverageWorkload(long ID) {
        List<Review> list = reviewRepository.findByCourse_ID(ID);
        double sum = 0;
        for(int i = 0;i < list.size(); i++ ) {
            sum += list.get(i).getRating().getWorkload();
        }
        return sum / list.size();
    }

    @Override
    public Double getAverageTeachingQuality(long ID) {
        List<Review> list = reviewRepository.findByCourse_ID(ID);
        double sum = 0;
        for(int i = 0;i < list.size(); i++ ) {
            sum += list.get(i).getRating().getTeachingQuality();
        }
        return sum / list.size();
    }

    @Override
    public Double getAverageCourseMaterial(long ID) {
        List<Review> list = reviewRepository.findByCourse_ID(ID);
        double sum = 0;
        for(int i = 0;i < list.size(); i++ ) {
            sum += list.get(i).getRating().getCourseMaterial();
        }
        return sum / list.size();
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> findByCourse_Name(String name) {
        return reviewRepository.findByCourse_Name(name);
    }

    @Override
    public Review findByID(long ID) {
        return reviewRepository.findByID(ID);
    }
}
