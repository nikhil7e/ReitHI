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

    private HashMap<Long, Boolean> changesOverall = new HashMap();

    private HashMap<Long, Boolean> changesDifficulty = new HashMap();

    private HashMap<Long, Boolean> changesWorkload = new HashMap();

    private HashMap<Long, Boolean> changesTeachingQuality = new HashMap();

    private HashMap<Long, Boolean> changesCourseMaterial = new HashMap();


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

    /**
     * Finds average overall score for course with ID
     *
     * @param ID ID for the course being searched in
     * @return The average overall score in the form of a Double
     */
    @Override
    public Double getAverageOverallScore(long ID) {
        if (latestOverall.containsKey(ID) && !(changesOverall.get(ID).booleanValue())) {
            return latestOverall.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getOverallScore();
            }
            if (list.size() == 0) {
                double overall = 0.0;
                changesOverall.put(ID, false);
                latestOverall.put(ID, overall);
                return overall;
            }
            double overall = sum / list.size();
            changesOverall.put(ID, false);
            latestOverall.put(ID, overall);
            return overall;
        }
    }

    /**
     * Finds average difficulty for course with ID
     *
     * @param ID ID for the course being searched in
     * @return The average difficulty in the form of a Double
     */
    @Override
    public Double getAverageDifficulty(long ID) {
        if (latestDifficulty.containsKey(ID) && !(changesDifficulty.get(ID).booleanValue())) {
            return latestDifficulty.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getDifficulty();
            }
            if (list.size() == 0) {
                double difficulty = 0.0;
                changesDifficulty.put(ID, false);
                latestDifficulty.put(ID, difficulty);
                return difficulty;
            }
            double difficulty = sum / list.size();
            changesDifficulty.put(ID, false);
            latestDifficulty.put(ID, difficulty);
            return difficulty;
        }
    }

    /**
     * Finds average workload for course with ID
     *
     * @param ID ID for the course being searched in
     * @return The average workload in the form of a Double
     */
    @Override
    public Double getAverageWorkload(long ID) {
        if (latestWorkload.containsKey(ID) && !(changesWorkload.get(ID).booleanValue())) {
            return latestWorkload.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getWorkload();
            }
            if (list.size() == 0) {
                double workload = 0.0;
                changesWorkload.put(ID, false);
                latestWorkload.put(ID, workload);
                return workload;
            }
            double workload = sum / list.size();
            changesWorkload.put(ID, false);
            latestWorkload.put(ID, workload);
            return workload;
        }
    }

    /**
     * Finds average teaching quality for course with ID
     *
     * @param ID ID for the course being searched in
     * @return The average teaching quality in the form of a Double
     */
    @Override
    public Double getAverageTeachingQuality(long ID) {
        if (latestTeachingQuality.containsKey(ID) && !(changesTeachingQuality.get(ID).booleanValue())) {
            return latestTeachingQuality.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getTeachingQuality();
            }
            if (list.size() == 0) {
                double teachingQuality = 0.0;
                changesTeachingQuality.put(ID, false);
                latestTeachingQuality.put(ID, teachingQuality);
                return teachingQuality;
            }
            double teachingQuality = sum / list.size();
            changesTeachingQuality.put(ID, false);
            latestTeachingQuality.put(ID, teachingQuality);
            return teachingQuality;
        }
    }

    /**
     * Finds average course material for course with ID
     *
     * @param ID ID for the course being searched in
     * @return The average course material in the form of a Double
     */
    @Override
    public Double getAverageCourseMaterial(long ID) {
        if (latestCourseMaterial.containsKey(ID) && !(changesCourseMaterial.get(ID).booleanValue())) {
            return latestCourseMaterial.get(ID).doubleValue();
        } else {
            List<Review> list = reviewRepository.findByCourse_ID(ID);
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += list.get(i).getRating().getCourseMaterial();
            }
            if (list.size() == 0) {
                double courseMaterial = 0.0;
                changesCourseMaterial.put(ID, false);
                latestCourseMaterial.put(ID, courseMaterial);
                return courseMaterial;
            }
            double courseMaterial = sum / list.size();
            changesCourseMaterial.put(ID, false);
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
        changesOverall.put(review.getCourse().getID(), true);
        changesDifficulty.put(review.getCourse().getID(), true);
        changesWorkload.put(review.getCourse().getID(), true);
        changesTeachingQuality.put(review.getCourse().getID(), true);
        changesCourseMaterial.put(review.getCourse().getID(), true);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        changesOverall.put(review.getCourse().getID(), true);
        changesDifficulty.put(review.getCourse().getID(), true);
        changesWorkload.put(review.getCourse().getID(), true);
        changesTeachingQuality.put(review.getCourse().getID(), true);
        changesCourseMaterial.put(review.getCourse().getID(), true);
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
