package dhcn.nhom1.thechathaui;

public class Class {
    private long id;
    private String class_code;
    private String days_in_week;
    private String time_in_day;
    private String started_at;
    private String finished_at;
    private String created_at;
    private String updated_at;
    private long course_id;
    private long instructor_id;

    public Class() {
    }

    public Class(long id, String class_code, String days_in_week, String time_in_day, String started_at, String finished_at, String created_at, String updated_at, long course_id, long instructor_id) {
        this.id = id;
        this.class_code = class_code;
        this.days_in_week = days_in_week;
        this.time_in_day = time_in_day;
        this.started_at = started_at;
        this.finished_at = finished_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.course_id = course_id;
        this.instructor_id = instructor_id;
    }

    public Class(String class_code, String days_in_week, String time_in_day, String started_at, String finished_at, String created_at, String updated_at, long course_id, long instructor_id) {
        this.class_code = class_code;
        this.days_in_week = days_in_week;
        this.time_in_day = time_in_day;
        this.started_at = started_at;
        this.finished_at = finished_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.course_id = course_id;
        this.instructor_id = instructor_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getDays_in_week() {
        return days_in_week;
    }

    public void setDays_in_week(String days_in_week) {
        this.days_in_week = days_in_week;
    }

    public String getTime_in_day() {
        return time_in_day;
    }

    public void setTime_in_day(String time_in_day) {
        this.time_in_day = time_in_day;
    }

    public String getStarted_at() {
        return started_at;
    }

    public void setStarted_at(String started_at) {
        this.started_at = started_at;
    }

    public String getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(String finished_at) {
        this.finished_at = finished_at;
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

    public long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(long course_id) {
        this.course_id = course_id;
    }

    public long getInstructor_id() {
        return instructor_id;
    }

    public void setInstructor_id(long instructor_id) {
        this.instructor_id = instructor_id;
    }
}
