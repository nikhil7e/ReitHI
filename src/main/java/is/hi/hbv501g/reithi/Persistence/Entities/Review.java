package is.hi.hbv501g.reithi.Persistence.Entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
@JsonSerialize(using = ReviewSerializer.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ID")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private long ID;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    @JsonBackReference("userReference")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "course_id")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ID")
    @JsonBackReference("courseReference")
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
    @JsonProperty("comment")
    private String comment;
    @Transient
    private int upvotes;
    @Transient
    @JsonProperty("course_id")
    private long courseID;

    @Transient
    @JsonProperty("user_id")
    private long userID;

    @Transient
    @JsonProperty("course_name")
    private String courseName;

    @Transient
    @JsonProperty("user_name")
    private String userName;

    @Transient
    @JsonProperty("user_token")
    private String userToken;

    @Transient
    @JsonProperty("upvoter_ids")
    private List<String> upvoterIDs;

    @Transient
    @JsonProperty("downvoter_ids")
    private List<String> downvoterIDs;


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

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long courseID) {
        this.courseID = courseID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
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

    public void removeUpvote(User currentUser) {
        upvoters.remove(currentUser);
    }

    public List<User> getDownvoters() {
        return downvoters;
    }

    public List<String> getUpvoterIDs() {
        List<String> upvoterIds = new ArrayList<>();
        for (User user: upvoters) {
            upvoterIds.add(String.valueOf(user.getID()));
        }
        return upvoterIds;
    }

    public void setUpvoterIDs(List<String> upvoterIDs) {
        this.upvoterIDs = upvoterIDs;
    }

    public List<String> getDownvoterIDs() {
        List<String> downvoterIds = new ArrayList<>();
        for (User user: downvoters) {
            downvoterIds.add(String.valueOf(user.getID()));
        }
        return downvoterIds;
    }

    public void setDownvoterIDs(List<String> downvoterIDs) {
        this.downvoterIDs = downvoterIDs;
    }

    public void setComment(String comment) {
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
        downvoters.remove(currentUser);
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setUpvoters(List<User> upvoters) {
        this.upvoters = upvoters;
    }

    public void setDownvoters(List<User> downvoters) {
        this.downvoters = downvoters;
    }




    public long getUserId() {
        return userID;
    }


    public long getCourseId() {
        return courseID;
    }

    @Override
    public String toString() {
        return "Review{" +
                "ID=" + ID +
                ", user=" + user +
                ", course=" + course +
                ", upvoters=" + upvoters +
                ", downvoters=" + downvoters +
                ", overallScore=" + overallScore +
                ", difficulty=" + difficulty +
                ", workload=" + workload +
                ", teachingQuality=" + teachingQuality +
                ", courseMaterial=" + courseMaterial +
                ", comment='" + comment + '\'' +
                '}';
    }
}