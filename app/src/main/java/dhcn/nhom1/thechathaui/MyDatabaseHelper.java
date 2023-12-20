package dhcn.nhom1.thechathaui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "HealthTracker.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENTS = "students";
    private static final String STUDENTS_ID = "id";
    private static final String STUDENTS_STUDENT_CODE = "student_code";
    private static final String STUDENTS_PASSWORD = "password";
    private static final String STUDENTS_FULLNAME = "fullname";
    private static final String STUDENTS_AVATAR = "avatar";
    private static final String STUDENTS_HEIGHT = "height";
    private static final String STUDENTS_WEIGHT = "weight";
    private static final String STUDENTS_UNDERLYING_DISEASE = "underlying_disease";
    private static final String STUDENTS_GENDER = "gender";
    private static final String STUDENTS_DOB = "dob";
    private static final String STUDENTS_CREATED_AT = "created_at";
    private static final String STUDENTS_UPDATED_AT = "updated_at";

    private static final String TABLE_COURSES = "courses";
    private static final String COURSES_ID = "id";
    private static final String COURSES_COURSE_CODE = "course_code";
    private static final String COURSES_COURSE_NAME = "course_name";
    private static final String COURSES_IS_PHYSICAL = "is_physical";
    private static final String COURSES_CREDITS = "credits";
    private static final String COURSES_CREATED_AT = "created_at";
    private static final String COURSES_UPDATED_AT = "updated_at";

    private static final String TABLE_INSTRUCTORS = "instructors";
    private static final String INSTRUCTORS_ID = "id";
    private static final String INSTRUCTORS_INSTRUCTOR_CODE = "instructor_code";
    private static final String INSTRUCTORS_FULLNAME = "fullname";
    private static final String INSTRUCTORS_CREATED_AT = "created_at";
    private static final String INSTRUCTORS_UPDATED_AT = "updated_at";


    private static final String TABLE_CLASSES = "classes";
    private static final String CLASSES_ID = "id";
    private static final String CLASSES_CLASS_CODE = "class_code";
    private static final String CLASSES_DAYS_IN_WEEK = "days_in_week";
    private static final String CLASSES_TIME_IN_DAY = "time_in_day";
    private static final String CLASSES_STARTED_AT = "started_at";
    private static final String CLASSES_FINISHED_AT = "finished_at";
    private static final String CLASSES_CREATED_AT = "created_at";
    private static final String CLASSES_UPDATED_AT = "updated_at";
    private static final String CLASSES_COURSE_ID = "course_id";
    private static final String CLASSES_INSTRUCTOR_ID = "instructor_id";

    private static final String TABLE_TRACKERS = "trackers";
    private static final String TRACKERS_ID = "id";
    private static final String TRACKERS_IS_ENOUGH_WATER = "is_enough_water";
    private static final String TRACKERS_LITER = "liter";
    private static final String TRACKERS_IS_DOING_EXERCISE = "is_doing_exercise";
    private static final String TRACKERS_EXERCISE_SPAN = "exercise_span";
    private static final String TRACKERS_CREATED_AT = "created_at";
    private static final String TRACKERS_UPDATED_AT = "updated_at";
    private static final String TRACKERS_STUDENT_ID = "student_id";

    private static final String TABLE_HEALTHS = "healths";
    private static final String HEALTHS_ID = "id";
    private static final String HEALTHS_BMI = "BMI";
    private static final String HEALTHS_DISEASE = "disease";
    private static final String HEALTHS_CREATED_AT = "created_at";
    private static final String HEALTHS_UPDATED_AT = "updated_at";
    private static final String HEALTHS_STUDENT_ID = "student_id";

    private static final String TABLE_RESULTS = "results";
    private static final String RESULTS_ID = "id";
    private static final String RESULTS_STATUS = "status";
    private static final String RESULTS_EXAM_SCORE = "exam_score";
    private static final String RESULTS_MIDTERM_SCORE = "midterm_score";
    private static final String RESULTS_TEST1 = "test1";
    private static final String RESULTS_TEST2 = "test2";
    private static final String RESULTS_CREATED_AT = "created_at";
    private static final String RESULTS_UPDATED_AT = "updated_at";
    private static final String RESULTS_STUDENT_ID = "student_id";
    private static final String RESULTS_CLASS_ID = "class_id";

    private static final String TABLE_ONLEAVES = "onleaves";
    private static final String ONLEAVES_ID = "id";
    private static final String ONLEAVES_STATUS = "status";
    private static final String ONLEAVES_REASON = "reason";
    private static final String ONLEAVES_DATE = "date";
    private static final String ONLEAVES_CREATED_AT = "created_at";
    private static final String ONLEAVES_UPDATED_AT = "updated_at";
    private static final String ONLEAVES_CLASS_ID = "class_id";
    private static final String ONLEAVES_STUDENT_ID = "student_id";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            InputStream inputStream = context.getAssets().open("HealthTracker.sql");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            String createScript = stringBuilder.toString();
            bufferedReader.close();
            String[] statements = createScript.split(";");
            for (String statement : statements) {
                if (statement.trim().length() > 0)
                    db.execSQL(statement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEALTHS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ONLEAVES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKERS);
        onCreate(db);
    }

    public String getStudentIdSignIn() {
        String rs = null;

        try {
            FileInputStream in = context.openFileInput("SignIn.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            rs = reader.readLine();
            if (rs.trim().length() != 0) {
                return rs;
            }
            in.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            Toast.makeText(context, "Không thể đọc dữ liệu sinh viên đăng nhập", Toast.LENGTH_SHORT).show();
        }
        return rs;
    }

    public int checkSignIn(Student s) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_STUDENTS,
                    new String[]{STUDENTS_ID},
                    STUDENTS_STUDENT_CODE + " = ? AND " + STUDENTS_PASSWORD + " = ?",
                    new String[]{String.valueOf(s.getStudent_code()), s.getPassword()},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return -1;
        }

        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return -1;
        } else {
            cursor.moveToFirst();
            int rs = cursor.getInt(0);
            cursor.close();
            db.close();
            return rs;
        }
    }

    public Student getAccountById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_STUDENTS,
                    new String[]{STUDENTS_STUDENT_CODE, STUDENTS_PASSWORD},
                    STUDENTS_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            Student s = new Student();
            s.setStudent_code(cursor.getInt(0));
            s.setPassword(cursor.getString(1));
            cursor.close();
            db.close();
            return s;
        }
    }

    public Student getStudentById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_STUDENTS,
                    new String[]{STUDENTS_STUDENT_CODE, STUDENTS_PASSWORD,
                            STUDENTS_FULLNAME, STUDENTS_AVATAR, STUDENTS_HEIGHT,
                            STUDENTS_WEIGHT, STUDENTS_UNDERLYING_DISEASE, STUDENTS_GENDER,
                            STUDENTS_DOB},
                    STUDENTS_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            Student s = new Student();
            s.setId(id);
            s.setStudent_code(cursor.getInt(0));
            s.setPassword(cursor.getString(1));
            s.setFullname(cursor.getString(2));
            s.setAvatar(cursor.getString(3));
            s.setHeight(Double.parseDouble(cursor.getString(4)));
            s.setWeight(Double.parseDouble(cursor.getString(5)));
            s.setUnderlying_disease(cursor.getString(6));
            s.setGender(Long.parseLong(cursor.getString(7)));
            s.setDob(cursor.getString(8));
            cursor.close();
            db.close();
            return s;
        }
    }

    //Thêm nếu chưa có, cập nhật nếu đã có
    public boolean addHealthRecord(Health health) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "%";
        try {
            cursor = db.query(TABLE_HEALTHS,
                    new String[]{HEALTHS_ID},
                    HEALTHS_CREATED_AT + " LIKE ? AND " + HEALTHS_STUDENT_ID + " = ?",
                    new String[]{date, String.valueOf(health.getStudent_id())},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return false;
        }
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(HEALTHS_BMI, health.getBMI());
            values.put(HEALTHS_DISEASE, health.getDisease());
            values.put(HEALTHS_STUDENT_ID, health.getStudent_id());
            db.insert(TABLE_HEALTHS, null, values);
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put(HEALTHS_BMI, health.getBMI());
            values.put(HEALTHS_DISEASE, health.getDisease());
            values.put(HEALTHS_STUDENT_ID, health.getStudent_id());
            values.put(HEALTHS_UPDATED_AT, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            db.update(TABLE_HEALTHS, values, HEALTHS_ID + " = ?", new String[]{String.valueOf(cursor.getInt(0))});
            cursor.close();
            db.close();
            return true;
        }
    }

    //Thêm nếu chưa có, cập nhật nếu đã có
    public boolean addTrackerRecord(Tracker tracker) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "%";
        try {
            cursor = db.query(TABLE_TRACKERS,
                    new String[]{TRACKERS_ID},
                    TRACKERS_CREATED_AT + " LIKE ? AND " + TRACKERS_STUDENT_ID + " = ?",
                    new String[]{date, String.valueOf(tracker.getStudent_id())},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return false;
        }
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(TRACKERS_IS_ENOUGH_WATER, tracker.getIs_enough_water());
            values.put(TRACKERS_LITER, tracker.getLiter());
            values.put(TRACKERS_IS_DOING_EXERCISE, tracker.getIs_doing_exercise());
            values.put(TRACKERS_EXERCISE_SPAN, tracker.getExercise_span());
            values.put(TRACKERS_STUDENT_ID, tracker.getStudent_id());
            db.insert(TABLE_TRACKERS, null, values);
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put(TRACKERS_IS_ENOUGH_WATER, tracker.getIs_enough_water());
            values.put(TRACKERS_LITER, tracker.getLiter());
            values.put(TRACKERS_IS_DOING_EXERCISE, tracker.getIs_doing_exercise());
            values.put(TRACKERS_EXERCISE_SPAN, tracker.getExercise_span());
            values.put(TRACKERS_UPDATED_AT, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            values.put(TRACKERS_STUDENT_ID, tracker.getStudent_id());
            db.update(TABLE_TRACKERS, values, TRACKERS_ID + " = ?", new String[]{String.valueOf(cursor.getInt(0))});
            cursor.close();
            db.close();
            return true;
        }
    }

    public Tracker getTrackerToday(long studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "%";
        try {
            cursor = db.query(TABLE_TRACKERS,
                    new String[]{TRACKERS_ID, TRACKERS_IS_ENOUGH_WATER, TRACKERS_LITER,
                            TRACKERS_IS_DOING_EXERCISE, TRACKERS_EXERCISE_SPAN, TRACKERS_CREATED_AT,
                            TRACKERS_UPDATED_AT, TRACKERS_STUDENT_ID},
                    TRACKERS_CREATED_AT + " LIKE ? AND " + TRACKERS_STUDENT_ID + " = ?",
                    new String[]{date, String.valueOf(studentId)},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            Tracker tracker = new Tracker();
            tracker.setId(cursor.getLong(0));
            tracker.setIs_enough_water(cursor.getLong(1));
            tracker.setLiter(cursor.getDouble(2));
            tracker.setIs_doing_exercise(cursor.getLong(3));
            tracker.setExercise_span(cursor.getLong(4));
            tracker.setCreated_at(cursor.getString(5));
            tracker.setUpdated_at(cursor.getString(6));
            tracker.setStudent_id(cursor.getLong(7));
            cursor.close();
            db.close();
            return tracker;
        }
    }

    public ArrayList<Tracker> getTrackerInDateRange(String start, String end, long studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_TRACKERS,
                    new String[]{TRACKERS_ID, TRACKERS_IS_ENOUGH_WATER, TRACKERS_LITER,
                            TRACKERS_IS_DOING_EXERCISE, TRACKERS_EXERCISE_SPAN, TRACKERS_CREATED_AT,
                            TRACKERS_UPDATED_AT, TRACKERS_STUDENT_ID},
                    TRACKERS_STUDENT_ID + " = ? AND (" + TRACKERS_CREATED_AT + " BETWEEN ? AND ?)",
                    new String[]{String.valueOf(studentId), start, end},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            ArrayList<Tracker> trackers = new ArrayList<Tracker>();
            do {
                Tracker tracker = new Tracker();
                tracker.setId(cursor.getLong(0));
                tracker.setIs_enough_water(cursor.getLong(1));
                tracker.setLiter(cursor.getDouble(2));
                tracker.setIs_doing_exercise(cursor.getLong(3));
                tracker.setExercise_span(cursor.getLong(4));
                tracker.setCreated_at(cursor.getString(5));
                tracker.setUpdated_at(cursor.getString(6));
                tracker.setStudent_id(cursor.getLong(7));
                trackers.add(tracker);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return trackers;
        }
    }

    public Health getHealthByDate(String date, long studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        date = date + "%";
        try {
            cursor = db.query(TABLE_HEALTHS,
                    new String[]{HEALTHS_ID, HEALTHS_BMI, HEALTHS_DISEASE, HEALTHS_CREATED_AT, HEALTHS_UPDATED_AT, HEALTHS_STUDENT_ID},
                    HEALTHS_CREATED_AT + " LIKE ? AND " + HEALTHS_STUDENT_ID + " = ?",
                    new String[]{date, String.valueOf(studentId)},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            Health health = new Health();
            health.setId(cursor.getLong(0));
            health.setBMI(cursor.getDouble(1));
            health.setDisease(cursor.getString(2));
            health.setCreated_at(cursor.getString(3));
            health.setUpdated_at(cursor.getString(4));
            health.setStudent_id(cursor.getLong(5));
            cursor.close();
            db.close();
            return health;
        }
    }

    public ArrayList<Course> getPhysicalCourses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_COURSES,
                    new String[]{COURSES_ID, COURSES_COURSE_CODE, COURSES_COURSE_NAME,
                            COURSES_IS_PHYSICAL, COURSES_CREDITS, COURSES_CREATED_AT,
                            COURSES_UPDATED_AT},
                    COURSES_IS_PHYSICAL + " = 1",
                    new String[]{},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            ArrayList<Course> courses = new ArrayList<Course>();
            do {
                Course course = new Course();
                course.setId(cursor.getLong(0));
                course.setCourse_code(cursor.getString(1));
                course.setCourse_name(cursor.getString(2));
                course.setIs_physical(cursor.getLong(3));
                course.setCredits(cursor.getLong(4));
                course.setCreated_at(cursor.getString(5));
                course.setUpdated_at(cursor.getString(6));
                courses.add(course);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return courses;
        }
    }

    public ArrayList<PhysicalResult> getPhysicalResults(long studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String query = "SELECT courses.course_code, courses.course_name, courses.credits, " +
                "classes.class_code, results.exam_score, results.midterm_score, results.test1 " +
                "FROM students " +
                "JOIN results ON students.id = results.student_id " +
                "JOIN classes ON results.class_id = classes.id " +
                "JOIN courses ON classes.course_id = courses.id " +
                "WHERE students.id = " + studentId + " AND courses.is_physical = 1";
        try {
            cursor = db.rawQuery(query, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            ArrayList<PhysicalResult> results = new ArrayList<PhysicalResult>();
            do {
                PhysicalResult result = new PhysicalResult();
                result.setCourse_code(cursor.getString(0));
                result.setCourse_name(cursor.getString(1));
                result.setCredits(cursor.getLong(2));
                result.setClass_code(cursor.getString(3));
                result.setExam_score(cursor.getLong(4));
                result.setMidterm_score(cursor.getLong(5));
                result.setTest1(cursor.getLong(6));
                results.add(result);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return results;
        }
    }

    public ArrayList<LeaveReport> getLeaveReportsByDate(String date, long studentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        String dates[] = date.split("/");
        date = dates[2] + "-" + dates[1] + "-" + dates[0];


        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == 1) {
            day_of_week = 6;
        } else {
            day_of_week = day_of_week - 2;
        }


        final String myQuery = String.format("SELECT %s.%s, %s.%s, %s.%s, %s.%s, %s.%s, %s.%s, %s.%s, %s.%s " +
                        "FROM %s " +
                        "JOIN %s ON %s.%s = %s.%s " +
                        "JOIN %s ON %s.%s = %s.%s " +
                        "JOIN %s ON %s.%s = %s.%s " +
                        "LEFT JOIN %s ON (%s.%s = %s.%s AND %s.%s = %s.%s) " +
                        "WHERE %s.%s LIKE '%s' AND ('%s' BETWEEN %s.%s AND %s.%s) AND %s.%s = %s",
                TABLE_ONLEAVES, ONLEAVES_ID, TABLE_RESULTS, RESULTS_CLASS_ID, TABLE_COURSES, COURSES_COURSE_NAME, TABLE_INSTRUCTORS, INSTRUCTORS_FULLNAME, TABLE_CLASSES, CLASSES_TIME_IN_DAY, TABLE_ONLEAVES, ONLEAVES_STATUS, TABLE_ONLEAVES, ONLEAVES_REASON, TABLE_ONLEAVES, ONLEAVES_DATE,
                TABLE_RESULTS,
                TABLE_CLASSES, TABLE_RESULTS, RESULTS_CLASS_ID, TABLE_CLASSES, CLASSES_ID,
                TABLE_COURSES, TABLE_CLASSES, CLASSES_COURSE_ID, TABLE_COURSES, COURSES_ID,
                TABLE_INSTRUCTORS, TABLE_CLASSES, CLASSES_INSTRUCTOR_ID, TABLE_INSTRUCTORS, INSTRUCTORS_ID,
                TABLE_ONLEAVES, TABLE_CLASSES, CLASSES_ID, TABLE_ONLEAVES, ONLEAVES_CLASS_ID, TABLE_RESULTS, RESULTS_STUDENT_ID, TABLE_ONLEAVES, ONLEAVES_STUDENT_ID,
                TABLE_CLASSES, CLASSES_DAYS_IN_WEEK, "%" + day_of_week + "%", date, TABLE_CLASSES, CLASSES_STARTED_AT, TABLE_CLASSES, CLASSES_FINISHED_AT, TABLE_RESULTS, RESULTS_STUDENT_ID, String.valueOf(studentId));

        try {
            cursor = db.rawQuery(myQuery, null);
        } catch (Exception e) {
            db.close();
            return null;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            db.close();
            return null;
        } else {
            cursor.moveToFirst();
            ArrayList<LeaveReport> leaveReports = new ArrayList<LeaveReport>();
            do {
                LeaveReport leaveReport = new LeaveReport();
                leaveReport.setClass_id(cursor.getLong(1));
                leaveReport.setCourse_name(cursor.getString(2));
                leaveReport.setInstructor_name(cursor.getString(3));
                leaveReport.setTime_in_day(cursor.getString(4));
                leaveReports.add(leaveReport);
                if (cursor.getString(7) != null) {
                    if (cursor.getString(7).contains(date)) {
                        leaveReport.setOnleave_id(cursor.getLong(0));
                        leaveReport.setStatus(cursor.getLong(5));
                        leaveReport.setReason(cursor.getString(6));
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
            return leaveReports;
        }
    }

    //Thêm nếu chưa có, cập nhật nếu chưa có
    public boolean addOnleaveRecord(Long onleaveId, long classId, long studentId, String date, String reason) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        Date d;
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        date = new SimpleDateFormat("yyyy-MM-dd").format(d);

        try {
            cursor = db.query(TABLE_ONLEAVES,
                    new String[]{ONLEAVES_ID},
                    ONLEAVES_ID + " = ?",
                    new String[]{String.valueOf(onleaveId)},
                    null, null, null);
        } catch (Exception e) {
            db.close();
            return false;
        }
        if (cursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(ONLEAVES_STATUS, 2);
            values.put(ONLEAVES_REASON, reason);
            values.put(ONLEAVES_DATE, date);
            values.put(ONLEAVES_CLASS_ID, classId);
            values.put(ONLEAVES_STUDENT_ID, studentId);
            db.insert(TABLE_ONLEAVES, null, values);
            cursor.close();
            db.close();
            return true;
        } else {
            cursor.moveToFirst();
            ContentValues values = new ContentValues();
            values.put(ONLEAVES_STATUS, 2);
            values.put(ONLEAVES_REASON, reason);
            db.update(TABLE_ONLEAVES, values, ONLEAVES_ID + " = ?", new String[]{String.valueOf(onleaveId)});
            cursor.close();
            db.close();
            return true;
        }
    }

    public int deleteOnleaveById(Long onleaveId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete(TABLE_ONLEAVES, ONLEAVES_ID + " = ?", new String[]{String.valueOf(onleaveId)});
        db.close();
        return rowAffected;
    }

    public boolean updateStudent(String id, Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues st = new ContentValues();
        st.put(STUDENTS_HEIGHT, student.getHeight());
        st.put(STUDENTS_WEIGHT, student.getWeight());
        st.put(STUDENTS_UNDERLYING_DISEASE, student.getUnderlying_disease());

        if (db.update(TABLE_STUDENTS, st, STUDENTS_ID + " = " + id, null) != 0) {
            return true;
        }
        return false;
    }

}
