package dhcn.nhom1.thechathaui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public String getStudentIdSignIn(Context context){
        String rs = null;

        try {
            FileInputStream in= context.openFileInput("SignIn.txt");
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            StringBuilder builder=new StringBuilder();
            rs = reader.readLine();
            if(rs.trim().length() != 0){
                return rs;
            }
            in.close();
            return rs;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            Toast.makeText(context,"Không thể đọc dữ liệu sinh viên đăng nhập", Toast.LENGTH_SHORT).show();
        }
        return rs;
    }

    public int checkSignIn(Student s){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try{
            cursor = db.query(TABLE_STUDENTS,
                    new String[]{STUDENTS_ID},
                    STUDENTS_STUDENT_CODE + " = ? AND " + STUDENTS_PASSWORD + " = ?",
                    new String[]{String.valueOf(s.getStudent_code()), s.getPassword()},
                    null, null, null);
        }
        catch (Exception e){
            cursor.close();
            db.close();
            return -1;
        }

        if (cursor.getCount() == 0){
            cursor.close();
            db.close();
            return -1;
        }else{
            cursor.moveToFirst();
            int rs = cursor.getInt(0);
            cursor.close();
            db.close();
            return rs;
        }
    }
    public Student getAccountById(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.query(TABLE_STUDENTS,
                    new String[]{STUDENTS_STUDENT_CODE, STUDENTS_PASSWORD},
                    STUDENTS_ID + " = ?",
                    new String[]{String.valueOf(id)},
                    null, null, null);
        }
        catch (Exception e){
            cursor.close();
            db.close();
            return null;
        }
        if(cursor.getCount() == 0){
            cursor.close();
            db.close();
            return null;
        }else{
            cursor.moveToFirst();
            Student s = new Student();
            s.setStudent_code(cursor.getInt(0));
            s.setPassword(cursor.getString(1));
            cursor.close();
            db.close();
            return s;
        }
    }
}
