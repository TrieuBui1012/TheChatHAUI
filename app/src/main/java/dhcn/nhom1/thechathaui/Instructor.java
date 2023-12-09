package dhcn.nhom1.thechathaui;

public class Instructor {
    private long id;
    private long instructor_code;
    private String fullname;
    private String created_at;
    private String updated_at;

    public Instructor() {
    }

    public Instructor(long id, long instructor_code, String fullname, String created_at, String updated_at) {
        this.id = id;
        this.instructor_code = instructor_code;
        this.fullname = fullname;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Instructor(long instructor_code, String fullname, String created_at, String updated_at) {
        this.instructor_code = instructor_code;
        this.fullname = fullname;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInstructor_code() {
        return instructor_code;
    }

    public void setInstructor_code(long instructor_code) {
        this.instructor_code = instructor_code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
