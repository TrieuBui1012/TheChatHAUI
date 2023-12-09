package dhcn.nhom1.thechathaui;

public class Health {
    private long id;
    private double BMI;
    private String disease;
    private String created_at;
    private String updated_at;
    private long student_id;

    public Health() {
    }

    public Health(long id, double BMI, String disease, String created_at, String updated_at, long student_id) {
        this.id = id;
        this.BMI = BMI;
        this.disease = disease;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.student_id = student_id;
    }

    public Health(double BMI, String disease, String created_at, String updated_at, long student_id) {
        this.BMI = BMI;
        this.disease = disease;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.student_id = student_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
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
}
