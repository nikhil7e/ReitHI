package is.hi.hbv501g.reithi.Persistence.Entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;
    @ManyToMany(cascade = {CascadeType.DETACH})
    private List<User> upvoters;
    @ManyToMany(cascade = {CascadeType.DETACH})
    private List<User> downvoters;
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
    private String comment;

    public Review() {
    }

    public Review(User user, Course course, int overallScore, int difficulty, int workload, int teachingQuality, int courseMaterial, String comment) {
        this.upvoters = new ArrayList<>();
        this.downvoters = new ArrayList<>();
        this.user = user;
        this.course = course;
        this.overallScore = overallScore;
        this.difficulty = difficulty;
        this.workload = workload;
        this.teachingQuality = teachingQuality;
        this.courseMaterial = courseMaterial;
        this.comment = comment;
    }

    public long getID() {
        return ID;
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


    public int getUpvotes() {
        return upvoters.size() - downvoters.size();
    }

    public void addUpvote(User user) {
        upvoters.add(user);
    }

    public void addDownvote(User user) {
        downvoters.add(user);
    }

    public List<User> getUpvoters() {
        return upvoters;
    }

    public void removeUpvote(User user) {
        upvoters.remove(user);
    }

    public List<User> getDownvoters() {
        return downvoters;
    }

    public void setComment(String content) {
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void removeDownvote(User currentUser) {
        downvoters.remove(user);
    }
}
