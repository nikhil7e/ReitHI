package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Comment comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Rating rating;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH}, orphanRemoval = true)
    private List<User> upvoters;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH}, orphanRemoval = true)
    private List<User> downvoters;

    public Review() {
    }

    public Review(User user, Rating rating, Comment comment, Course course) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.course = course;
        this.upvoters = new ArrayList<>();
        this.downvoters = new ArrayList<>();
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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getUpvotes() {
        return upvoters.size() - downvoters.size();
    }

    public void addUpvote(User user){
        upvoters.add(user);
    }

    public void addDownvote(User user){
        downvoters.add(user);
    }

    public List<User> getUpvoters() {
        return upvoters;
    }

    public void removeUpvote(User user){
        upvoters.remove(user);
    }

    public List<User> getDownvoters() {
        return downvoters;
    }

    public void setComment(String content) {
        this.comment = comment;
    }


    public void removeDownvote(User currentUser) {
        downvoters.remove(user);
    }
}
