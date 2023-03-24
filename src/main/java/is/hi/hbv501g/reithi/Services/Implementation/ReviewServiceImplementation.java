package is.hi.hbv501g.reithi.Services.Implementation;

import is.hi.hbv501g.reithi.Persistence.Entities.Review;
import is.hi.hbv501g.reithi.Persistence.Repositories.ReviewRepository;
import is.hi.hbv501g.reithi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private HashMap<Long, Boolean> changesOverall = new HashMap<>();

    private HashMap<Long, Boolean> changesDifficulty = new HashMap<>();

    private HashMap<Long, Boolean> changesWorkload = new HashMap<>();

    private HashMap<Long, Boolean> changesTeachingQuality = new HashMap<>();

    private HashMap<Long, Boolean> changesCourseMaterial = new HashMap<>();

    private HashMap<Long, Double> latestOverall = new HashMap<>();

    private HashMap<Long, Double> latestDifficulty = new HashMap<>();

    private HashMap<Long, Double> latestWorkload = new HashMap<>();

    private HashMap<Long, Double> latestTeachingQuality = new HashMap<>();

    private HashMap<Long, Double> latestCourseMaterial = new HashMap<>();

    private ReviewRepository reviewRepository;

    private int id_counter = 0;

    @Autowired
    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewServiceImplementation() {
    }

    private Double calculateAverage(long ID, List<Review> list, double sum, HashMap<Long, Boolean> changes, HashMap<Long, Double> latest) {
        if (list.size() == 0) {
            double workload = 0.0;
            changes.put(ID, false);
            latest.put(ID, workload);
            return workload;
        }
        double workload = sum / list.size();
        changes.put(ID, false);
        latest.put(ID, workload);
        return workload;
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
            for (Review review : list) {
                sum += review.getOverallScore();
            }
            return calculateAverage(ID, list, sum, changesOverall, latestOverall);
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
            for (Review review : list) {
                sum += review.getDifficulty();
            }
            return calculateAverage(ID, list, sum, changesDifficulty, latestDifficulty);
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
            for (Review review : list) {
                sum += review.getWorkload();
            }
            return calculateAverage(ID, list, sum, changesWorkload, latestWorkload);
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
            for (Review review : list) {
                sum += review.getTeachingQuality();
            }
            return calculateAverage(ID, list, sum, changesTeachingQuality, latestTeachingQuality);
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
            for (Review review : list) {
                sum += review.getCourseMaterial();
            }
            return calculateAverage(ID, list, sum, changesCourseMaterial, latestCourseMaterial);
        }
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    private void updateChangesHashmaps(Review review) {
        changesOverall.put(review.getCourse().getID(), true);
        changesDifficulty.put(review.getCourse().getID(), true);
        changesWorkload.put(review.getCourse().getID(), true);
        changesTeachingQuality.put(review.getCourse().getID(), true);
        changesCourseMaterial.put(review.getCourse().getID(), true);
    }

    @Override
    public Review save(Review review) {
        //updateChangesHashmaps(review);
        return reviewRepository.save(review);
    }

    @Override
    public void delete(Review review) {
        //updateChangesHashmaps(review);
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
