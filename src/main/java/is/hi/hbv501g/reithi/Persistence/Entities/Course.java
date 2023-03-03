package is.hi.hbv501g.reithi.Persistence.Entities;

import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("0")
    private Integer totalOverallScore;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews;

    @ColumnDefault("0")
    private double totalOverall;

    @ColumnDefault("0")
    private double totalDifficulty;

    @ColumnDefault("0")
    private double totalWorkload;

    @ColumnDefault("0")
    private double totalTeachingQuality;

    @ColumnDefault("0")
    private double totalCourseMaterial;

    @ColumnDefault("0")
    private int nrReviews;

    public Course() {

    }

    public Course(String name, String number, String course_URL, String type, Double credits, String semester, String level, String professor_Name, Professor professor, Double minimumGrade, String assessment, String finalExam, String school, String faculty, String professor_Email, String professor_URL, Integer totalOverallScore, List<Review> reviews, double totalOverall, double totalDifficulty, double totalWorkload, double totalTeachingQuality, double totalCourseMaterial, int nrReviews) {
        this.name = name;
        this.number = number;
        this.course_URL = course_URL;
        this.type = type;
        this.credits = credits;
        this.semester = semester;
        this.level = level;
        this.professor_Name = professor_Name;
        this.professor = professor;
        this.minimumGrade = minimumGrade;
        this.assessment = assessment;
        this.finalExam = finalExam;
        this.school = school;
        this.faculty = faculty;
        this.professor_Email = professor_Email;
        this.professor_URL = professor_URL;
        this.totalOverallScore = totalOverallScore;
        this.reviews = reviews;
        this.totalOverall = totalOverall;
        this.totalDifficulty = totalDifficulty;
        this.totalWorkload = totalWorkload;
        this.totalTeachingQuality = totalTeachingQuality;
        this.totalCourseMaterial = totalCourseMaterial;
        this.nrReviews = nrReviews;
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

    public String getCourse_URL() {
        return course_URL;
    }

    public void setCourse_URL(String course_URL) {
        this.course_URL = course_URL;
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

    public String getProfessor_Name() {
        return professor_Name;
    }

    public void setProfessor_Name(String professor_Name) {
        this.professor_Name = professor_Name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

    public Integer getTotalOverallScore() {
        return totalOverallScore;
    }

    public void setTotalOverallScore(Integer totalOverallScore) {
        this.totalOverallScore = totalOverallScore;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getTotalOverall() {
        return totalOverall;
    }

    public void setTotalOverall(double totalOverall) {
        this.totalOverall = totalOverall;
    }

    public double getTotalDifficulty() {
        return totalDifficulty;
    }

    public void setTotalDifficulty(double totalDifficulty) {
        this.totalDifficulty = totalDifficulty;
    }

    public double getTotalWorkload() {
        return totalWorkload;
    }

    public void setTotalWorkload(double totalWorkload) {
        this.totalWorkload = totalWorkload;
    }

    public double getTotalTeachingQuality() {
        return totalTeachingQuality;
    }

    public void setTotalTeachingQuality(double totalTeachingQuality) {
        this.totalTeachingQuality = totalTeachingQuality;
    }

    public double getTotalCourseMaterial() {
        return totalCourseMaterial;
    }

    public void setTotalCourseMaterial(double totalCourseMaterial) {
        this.totalCourseMaterial = totalCourseMaterial;
    }

    public int getNrReviews() {
        return nrReviews;
    }

    public void setNrReviews(int nrReviews) {
        this.nrReviews = nrReviews;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
