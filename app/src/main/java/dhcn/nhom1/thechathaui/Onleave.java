package dhcn.nhom1.thechathaui;

public class Onleave {
    private long id;
    private long status;
    private String reason;
    private String date;
    private String created_at;
    private String updated_at;
    private long class_id;
    private long student_id;

    public Onleave() {
    }

    public Onleave(long id, long status, String reason, String date, String created_at, String updated_at, long class_id, long student_id) {
        this.id = id;
        this.status = status;
        this.reason = reason;
        this.date = date;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.class_id = class_id;
        this.student_id = student_id;
    }

    public Onleave(long status, String reason, String date, String created_at, String updated_at, long class_id, long student_id) {
        this.status = status;
        this.reason = reason;
        this.date = date;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.class_id = class_id;
        this.student_id = student_id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public long getClass_id() {
        return class_id;
    }

    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
}
