package is.hi.hbv501g.reithi.Persistence.Entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ID")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private long ID;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = false)
    //@Column(nullable = true)

    //@JsonIdentityReference(alwaysAsId = true)
    @JsonManagedReference("userReference")
    private List<Review> reviews = new ArrayList<>();
    private String deviceToken;
    private String enrolledSchoolOrFaculty;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String deviceToken) {
        this.userName = userName;
        this.password = password;
        this.deviceToken = deviceToken;
    }

    public String getEnrolledSchoolOrFaculty() {
        return enrolledSchoolOrFaculty;
    }

    public void setEnrolledSchoolOrFaculty(String enrolledSchoolOrFaculty) {
        this.enrolledSchoolOrFaculty = enrolledSchoolOrFaculty;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
