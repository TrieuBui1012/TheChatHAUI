package dhcn.nhom1.thechathaui;

public class PhysicalResult {
    private String course_code;
    private String course_name;
    private double credits;
    private String class_code;
    private double exam_score;
    private double midterm_score;
    private double test1;
    private String created_at;
    private String updated_at;

    public PhysicalResult() {
    }

    public PhysicalResult(String course_code, String course_name, double credits, String class_code, double exam_score, double midterm_score, double test1, String created_at, String updated_at) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.credits = credits;
        this.class_code = class_code;
        this.exam_score = exam_score;
        this.midterm_score = midterm_score;
        this.test1 = test1;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public String getCourse_code() {
        return course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public double getCredits() {
        return credits;
    }

    public String getClass_code() {
        return class_code;
    }

    public double getExam_score() {
        return exam_score;
    }

    public double getMidterm_score() {
        return midterm_score;
    }

    public double getTest1() {
        return test1;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public void setExam_score(double exam_score) {
        this.exam_score = exam_score;
    }

    public void setMidterm_score(double midterm_score) {
        this.midterm_score = midterm_score;
    }

    public void setTest1(double test1) {
        this.test1 = test1;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
