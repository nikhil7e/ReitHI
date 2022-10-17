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

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
}
