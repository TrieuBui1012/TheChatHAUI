package dhcn.nhom1.thechathaui;

public class Result {
    private long id;
    private long status;
    private double exam_score;
    private double midterm_score;
    private double test1;
    private double test2;
    private String created_at;
    private String updated_at;
    private long student_id;
    private long class_id;

    public Result() {
    }

    public Result(long id, long status, double exam_score, double midterm_score, double test1, double test2, String created_at, String updated_at, long student_id, long class_id) {
        this.id = id;
        this.status = status;
        this.exam_score = exam_score;
        this.midterm_score = midterm_score;
        this.test1 = test1;
        this.test2 = test2;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.student_id = student_id;
        this.class_id = class_id;
    }

    public Result(long status, double exam_score, double midterm_score, double test1, double test2, String created_at, String updated_at, long student_id, long class_id) {
        this.status = status;
        this.exam_score = exam_score;
        this.midterm_score = midterm_score;
        this.test1 = test1;
        this.test2 = test2;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.student_id = student_id;
        this.class_id = class_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public double getExam_score() {
        return exam_score;
    }

    public void setExam_score(double exam_score) {
        this.exam_score = exam_score;
    }

    public double getMidterm_score() {
        return midterm_score;
    }

    public void setMidterm_score(double midterm_score) {
        this.midterm_score = midterm_score;
    }

    public double getTest1() {
        return test1;
    }

    public void setTest1(double test1) {
        this.test1 = test1;
    }

    public double getTest2() {
        return test2;
    }

    public void setTest2(double test2) {
        this.test2 = test2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }
}
