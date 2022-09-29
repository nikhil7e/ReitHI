package is.hi.hbv501g.reithi.Persistence.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;

    private String number;

    private String course_URL;
    private String type;

    private Double credits;

    private String semester;

    private String level;

    private String professor_Name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Professor professor;

    private Double minimumGrade;

    private String assessment;

    private String finalExam;

    private String school;

    private String faculty;

    private String professor_Email;

    private String professor_URL;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    public Course() {

    }



    public Course(String name, String number, String type, Double credits, String semester, String level, String professor_Name, Professor professor, List<Review> reviews, Double minimumGrade, String assessment, String finalExam, String school, String faculty, String professor_Email, String professor_URL, String course_URL) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.credits = credits;
        this.semester = semester;
        this.level = level;
        this.professor = professor;
        this.reviews = reviews;
        this.minimumGrade = minimumGrade;
        this.assessment = assessment;
        this.professor_Name = professor_Name;
        this.professor_Email = professor_Email;
        this.professor_URL = professor_URL;
        this.course_URL = course_URL;
        this.finalExam = finalExam;
        this.school = school;
        this.faculty = faculty;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(Double minimumGrade) {
        this.minimumGrade = minimumGrade;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(String finalExam) {
        this.finalExam = finalExam;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourse_URL() {
        return course_URL;
    }

    public void setCourse_URL(String course_URL) {
        this.course_URL = course_URL;
    }

    public String getProfessor_Email() {
        return professor_Email;
    }

    public void setProfessor_Email(String professor_Email) {
        this.professor_Email = professor_Email;
    }

    public String getProfessor_URL() {
        return professor_URL;
    }

    public void setProfessor_URL(String professor_URL) {
        this.professor_URL = professor_URL;
    }


    public String getProfessor_Name() {
        return professor_Name;
    }

    public void setProfessor_Name(String professor_Name) {
        this.professor_Name = professor_Name;
    }

}
