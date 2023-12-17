package dhcn.nhom1.thechathaui;

public class History {
    private String date;
    private String BMI;
    private String status;
    private String disease;
    private String is_enough_water;
    private String liter;
    private String is_doing_exercise;
    private String exercise_span;

    public History() {
    }

    public History(String date, String BMI, String status, String disease, String is_enough_water, String liter, String is_doing_exercise, String exercise_span) {
        this.date = date;
        this.BMI = BMI;
        this.status = status;
        this.disease = disease;
        this.is_enough_water = is_enough_water;
        this.liter = liter;
        this.is_doing_exercise = is_doing_exercise;
        this.exercise_span = exercise_span;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getIs_enough_water() {
        return is_enough_water;
    }

    public void setIs_enough_water(String is_enough_water) {
        this.is_enough_water = is_enough_water;
    }

    public String getLiter() {
        return liter;
    }

    public void setLiter(String liter) {
        this.liter = liter;
    }

    public String getIs_doing_exercise() {
        return is_doing_exercise;
    }

    public void setIs_doing_exercise(String is_doing_exercise) {
        this.is_doing_exercise = is_doing_exercise;
    }

    public String getExercise_span() {
        return exercise_span;
    }

    public void setExercise_span(String exercise_span) {
        this.exercise_span = exercise_span;
    }
}
