package dhcn.nhom1.thechathaui;

public class Course {
    private long id;
    private String course_code;
    private String course_name;
    private long is_physical;
    private double credits;
    private String created_at;
    private String updated_at;

    public Course() {
    }

    public Course(long id, String course_code, String course_name, long is_physical, double credits, String created_at, String updated_at) {
        this.id = id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.is_physical = is_physical;
        this.credits = credits;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Course(String course_code, String course_name, long is_physical, double credits, String created_at, String updated_at) {
        this.course_code = course_code;
        this.course_name = course_name;
        this.is_physical = is_physical;
        this.credits = credits;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public long getIs_physical() {
        return is_physical;
    }

    public void setIs_physical(long is_physical) {
        this.is_physical = is_physical;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
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
}
