package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    private Review review;

    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;


    public Comment() {

    }

    public Comment(User user, String text, Course course) {
        this.user = user;
        this.text = text;
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
