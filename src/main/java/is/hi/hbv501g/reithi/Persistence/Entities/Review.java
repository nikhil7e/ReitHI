package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Comment comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @OneToOne(fetch = FetchType.LAZY)
    private Rating rating;
    private int upvotes;
    private int downvotes;



    public Review() {
    }

    public Review(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
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

    public void setComment(String content) {
        this.comment = comment;
    }
}
