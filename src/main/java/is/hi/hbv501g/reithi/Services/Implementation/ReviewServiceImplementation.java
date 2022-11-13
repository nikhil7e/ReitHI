package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Repositories.CourseRepository;
import is.hi.hbv501g.reithi.Persistence.Repositories.ReviewRepository;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private HashMap<Long, Boolean> changes = new HashMap();

    private HashMap<Long, Double> latestOverall = new HashMap();

    private HashMap<Long, Double> latestDifficulty = new HashMap();

    private HashMap<Long, Double> latestWorkload = new HashMap();

    private HashMap<Long, Double> latestTeachingQuality = new HashMap();

    private HashMap<Long, Double> latestCourseMaterial = new HashMap();


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
        if (latestOverall.containsKey(ID) && !(changes.get(ID).booleanValue())) {
            return latestOverall.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getDifficulty();
            }
            if (list.size() == 0) {
                double overall = 0.0;
                changes.put(ID, false);
                latestOverall.put(ID, overall);
                return overall;
            }
            double overall = sum / list.size();
            changes.put(ID, false);
            latestDifficulty.put(ID, overall);
            return overall;
        }
    }

    @Override
    public Double getAverageDifficulty(long ID) {
        if (latestDifficulty.containsKey(ID) && !(changes.get(ID).booleanValue())) {
            return latestDifficulty.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getDifficulty();
            }
            if (list.size() == 0) {
                double difficulty = 0.0;
                changes.put(ID, false);
                latestDifficulty.put(ID, difficulty);
                return difficulty;
            }
            double difficulty = sum / list.size();
            changes.put(ID, false);
            latestDifficulty.put(ID, difficulty);
            return difficulty;
        }
    }

    @Override
    public Double getAverageWorkload(long ID) {
        if (latestWorkload.containsKey(ID) && !(changes.get(ID).booleanValue())) {
            return latestWorkload.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getWorkload();
            }
            if (list.size() == 0) {
                double workload = 0.0;
                changes.put(ID, false);
                latestTeachingQuality.put(ID, workload);
                return workload;
            }
            double workload = sum / list.size();
            changes.put(ID, false);
            latestTeachingQuality.put(ID, workload);
            return workload;
        }
    }

    @Override
    public Double getAverageTeachingQuality(long ID) {
        if (latestTeachingQuality.containsKey(ID) && !(changes.get(ID).booleanValue())) {
            return latestTeachingQuality.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getTeachingQuality();
            }
            if (list.size() == 0) {
                double teachingQuality = 0.0;
                changes.put(ID, false);
                latestTeachingQuality.put(ID, teachingQuality);
                return teachingQuality;
            }
            double teachingQuality = sum / list.size();
            changes.put(ID, false);
            latestTeachingQuality.put(ID, teachingQuality);
            return teachingQuality;
        }
    }

    @Override
    public Double getAverageCourseMaterial(long ID) {
        if (latestCourseMaterial.containsKey(ID) && !(changes.get(ID).booleanValue())) {
            return latestCourseMaterial.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getTeachingQuality();
            }
            if (list.size() == 0) {
                double courseMaterial = 0.0;
                changes.put(ID, false);
                latestCourseMaterial.put(ID, courseMaterial);
                return courseMaterial;
            }
            double courseMaterial = sum / list.size();
            changes.put(ID, false);
            latestCourseMaterial.put(ID, courseMaterial);
            return courseMaterial;
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review save(Review review) {
        changes.put(review.getID(), true);
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
    public List<Review> findByCourse_ID(long ID) {
        return reviewRepository.findByCourse_ID(ID);
    }

    @Override
    public Review findByID(long ID) {
        return reviewRepository.findByID(ID);
    }
}
