package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Comment comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Rating rating;
    private int upvotes;
    private int downvotes;

    public Review() {
    }

    public Review(User user, Rating rating, Comment comment, Course course) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.course = course;
        this.upvotes = 0;
        this.downvotes = 0;
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
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public void setComment(String content) {
        this.comment = comment;
    }
}
