package dhcn.nhom1.thechathaui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class PhysicalCoursesActivity extends AppCompatActivity {
    MyDatabaseHelper db;
    ArrayList<Course> listPhysicalCourses;
    PhysicalCoursesAdapter adapter;
    ListView lvPhysical = null;
    Toolbar toolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_courses);
        getWidget();
    }

    private void getWidget() {
        db = new MyDatabaseHelper(this);
        toolbarBack = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbarBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lvPhysical = findViewById(R.id.lvPhysical);
        listPhysicalCourses = db.getPhysicalCourses();
        adapter = new PhysicalCoursesAdapter(this, R.layout.physical_listview, listPhysicalCourses);
        lvPhysical.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}