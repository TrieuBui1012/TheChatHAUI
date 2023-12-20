package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class PhysicalCoursesActivity extends AppCompatActivity {
    MyDatabaseHelper db;
    ArrayList<Course> listPhysicalCourses;
    PhysicalCoursesAdapter adapter;
    ListView lvPhysical = null;
    ImageButton imbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_courses);
        getWidget();

        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void getWidget() {
        db = new MyDatabaseHelper(this);
        imbBack = findViewById(R.id.imbBack);
        lvPhysical = findViewById(R.id.lvPhysical);
        listPhysicalCourses = db.getPhysicalCourses();
        adapter = new PhysicalCoursesAdapter(this, R.layout.physical_listview, listPhysicalCourses);
        lvPhysical.setAdapter(adapter);
    }

}