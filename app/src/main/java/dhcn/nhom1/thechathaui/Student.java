package dhcn.nhom1.thechathaui;

public class Student {
    private long id;
    private long student_code;
    private String password;
    private String fullname;
    private String avatar;
    private double height;
    private double weight;
    private String underlying_disease;
    private long gender;
    private String dob;
    private String created_at;
    private String updated_at;

    public Student() {
    }

    public Student(long id, long student_code, String password, String fullname, String avatar, double height, double weight, String underlying_disease, long gender, String dob, String created_at, String updated_at) {
        this.id = id;
        this.student_code = student_code;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.height = height;
        this.weight = weight;
        this.underlying_disease = underlying_disease;
        this.gender = gender;
        this.dob = dob;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Student(long student_code, String password, String fullname, String avatar, double height, double weight, String underlying_disease, long gender, String dob, String created_at, String updated_at) {
        this.student_code = student_code;
        this.password = password;
        this.fullname = fullname;
        this.avatar = avatar;
        this.height = height;
        this.weight = weight;
        this.underlying_disease = underlying_disease;
        this.gender = gender;
        this.dob = dob;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudent_code() {
        return student_code;
    }

    public void setStudent_code(long student_code) {
        this.student_code = student_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUnderlying_disease() {
        return underlying_disease;
    }

    public void setUnderlying_disease(String underlying_disease) {
        this.underlying_disease = underlying_disease;
    }

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
