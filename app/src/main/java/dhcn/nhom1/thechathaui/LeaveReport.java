package dhcn.nhom1.thechathaui;

public class LeaveReport {
   private Long onleave_id;
   private long class_id;
   private String course_name;
   private String instructor_name;
   private String time_in_day;
   private Long status;
   private String reason;

   public LeaveReport() {
   }

   public LeaveReport(Long onleave_id, long class_id, String course_name, String instructor_name, String time_in_day, Long status, String reason) {
      this.onleave_id = onleave_id;
      this.class_id = class_id;
      this.course_name = course_name;
      this.instructor_name = instructor_name;
      this.time_in_day = time_in_day;
      this.status = status;
      this.reason = reason;
   }

   public Long getOnleave_id() {
      return onleave_id;
   }

   public void setOnleave_id(Long onleave_id) {
      this.onleave_id = onleave_id;
   }

   public long getClass_id() {
      return class_id;
   }

   public void setClass_id(long class_id) {
      this.class_id = class_id;
   }

   public String getCourse_name() {
      return course_name;
   }

   public void setCourse_name(String course_name) {
      this.course_name = course_name;
   }

   public String getInstructor_name() {
      return instructor_name;
   }

   public void setInstructor_name(String instructor_name) {
      this.instructor_name = instructor_name;
   }

   public String getTime_in_day() {
      return time_in_day;
   }

   public void setTime_in_day(String time_in_day) {
      this.time_in_day = time_in_day;
   }

   public Long getStatus() {
      return status;
   }

   public void setStatus(Long status) {
      this.status = status;
   }

   public String getReason() {
      return reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
