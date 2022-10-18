package is.hi.hbv501g.reithi.Persistence.Entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @ColumnDefault("0")
    private int overallScore;
    @ColumnDefault("0")
    private int difficulty;
    @ColumnDefault("0")
    private int workload;
    @ColumnDefault("0")
    private int teachingQuality;
    @ColumnDefault("0")
    private int courseMaterial;


    public Rating() {
    }

    public Rating(User user, Course course, int overallScore, int difficulty, int workload, int teachingQuality, int courseMaterial) {
        this.user = user;
        this.course = course;
        this.overallScore = overallScore;
        this.difficulty = difficulty;
        this.workload = workload;
        this.teachingQuality = teachingQuality;
        this.courseMaterial = courseMaterial;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(int overallScore) {
        this.overallScore = overallScore;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    public int getTeachingQuality() {
        return teachingQuality;
    }

    public void setTeachingQuality(int teachingQuality) {
        this.teachingQuality = teachingQuality;
    }

    public int getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(int courseMaterial) {
        this.courseMaterial = courseMaterial;
    }
}
