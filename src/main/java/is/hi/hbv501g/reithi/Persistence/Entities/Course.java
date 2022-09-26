package is.hi.hbv501g.reithi.Persistence.Entities;

import java.util.List;

public class Course {
    private long ID;
    private String name;
    private String number;
    private String type;
    private double credits;
    private String semester;
    private String level;
    private String professor;
    private List<Review> reviews;
    private double minimumGrade;
    private String assessment;
    private String lessons;
    private String finalExam;
    private String school;
    private String faculty;

    public Course() {

    }

    public Course(String name, String number, String type, double credits, String semester, String level, String professor, List<Review> reviews, double minimumGrade, String assessment, String lessons, String finalExam, String school, String faculty) {
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
        this.lessons = lessons;
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

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(double minimumGrade) {
        this.minimumGrade = minimumGrade;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getLessons() {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
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

}
