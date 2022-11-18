package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CourseRating{
    private long ID;

    private double avgOverall;

    private double avgDifficulty;

    private double avgWorkload;

    private double avgTeachingQuality;

    private double avgCourseMaterial;

    public CourseRating(long ID, double avgOverall, double avgDifficulty, double avgWorkload, double avgTeachingQuality, double avgCourseMaterial) {
        this.avgOverall = avgOverall;
        this.avgDifficulty = avgDifficulty;
        this.avgWorkload = avgWorkload;
        this.avgTeachingQuality = avgTeachingQuality;
        this.avgCourseMaterial = avgCourseMaterial;
        this.ID = ID;
    }

    public double getAvgOverall() {
        return avgOverall;
    }

    public void setAvgOverall(double avgOverall) {
        this.avgOverall = avgOverall;
    }

    public double getAvgDifficulty() {
        return avgDifficulty;
    }

    public void setAvgDifficulty(double avgDifficulty) {
        this.avgDifficulty = avgDifficulty;
    }

    public double getAvgWorkload() {
        return avgWorkload;
    }

    public void setAvgWorkload(double avgWorkload) {
        this.avgWorkload = avgWorkload;
    }

    public double getAvgTeachingQuality() {
        return avgTeachingQuality;
    }

    public void setAvgTeachingQuality(double avgTeachingQuality) {
        this.avgTeachingQuality = avgTeachingQuality;
    }

    public double getAvgCourseMaterial() {
        return avgCourseMaterial;
    }

    public void setAvgCourseMaterial(double avgCourseMaterial) {
        this.avgCourseMaterial = avgCourseMaterial;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
