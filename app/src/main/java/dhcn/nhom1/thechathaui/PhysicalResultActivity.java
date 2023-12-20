package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhysicalResultActivity extends AppCompatActivity {
    TextView txvCreditsComplete, txvCreditsMissing;
    ListView lvPhysicalResult;
    MyDatabaseHelper db;
    ArrayList<PhysicalResult> listPhysicalResult;
    PhysicalResultAdapter adapter;
    ImageButton imbBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_result);
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
        lvPhysicalResult = findViewById(R.id.lvPhysicalResult);
        listPhysicalResult = db.getPhysicalResults(Long.parseLong(db.getStudentIdSignIn()));
        adapter = new PhysicalResultAdapter(this, R.layout.physical_result_listview, listPhysicalResult);
        lvPhysicalResult.setAdapter(adapter);

    }


}