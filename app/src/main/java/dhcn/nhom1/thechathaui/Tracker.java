package dhcn.nhom1.thechathaui;

public class Tracker {
    private long id;
    private long is_enough_water;
    private double liter;
    private long is_doing_exercise;
    private long exercise_span;
    private String created_at;
    private String updated_at;
    private long student_id;

    public Tracker() {
    }

    public Tracker(long id, long is_enough_water, double liter, long is_doing_exercise, long exercise_span, String created_at, String updated_at, long student_id) {
        this.id = id;
        this.is_enough_water = is_enough_water;
        this.liter = liter;
        this.is_doing_exercise = is_doing_exercise;
        this.exercise_span = exercise_span;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.student_id = student_id;
    }

    public Tracker(long is_enough_water, double liter, long is_doing_exercise, long exercise_span, String created_at, String updated_at, long student_id) {
        this.is_enough_water = is_enough_water;
        this.liter = liter;
        this.is_doing_exercise = is_doing_exercise;
        this.exercise_span = exercise_span;
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

    public long getIs_enough_water() {
        return is_enough_water;
    }

    public void setIs_enough_water(long is_enough_water) {
        this.is_enough_water = is_enough_water;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public long getIs_doing_exercise() {
        return is_doing_exercise;
    }

    public void setIs_doing_exercise(long is_doing_exercise) {
        this.is_doing_exercise = is_doing_exercise;
    }

    public long getExercise_span() {
        return exercise_span;
    }

    public void setExercise_span(long exercise_span) {
        this.exercise_span = exercise_span;
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
