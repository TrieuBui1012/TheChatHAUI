package dhcn.nhom1.thechathaui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
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
    Toolbar toolbarBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_result);
        getWidget();
        if (!listPhysicalResult.isEmpty()) {
            txvCreditsComplete.setText(listPhysicalResult.size() + "");
            txvCreditsMissing.setText((4 - listPhysicalResult.size()) + "");
        } else {
            txvCreditsComplete.setText("0");
            txvCreditsMissing.setText("4");
        }

    }

    private void getWidget() {
        db = new MyDatabaseHelper(this);
        txvCreditsComplete = findViewById(R.id.txvCreditsComplete);
        txvCreditsMissing = findViewById(R.id.txvCreditsMissing);

        toolbarBack = findViewById(R.id.toolbarBack);
        setSupportActionBar(toolbarBack);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        lvPhysicalResult = findViewById(R.id.lvPhysicalResult);

        listPhysicalResult = db.getPhysicalResults(Long.parseLong(db.getStudentIdSignIn()));
        adapter = new PhysicalResultAdapter(this, R.layout.physical_result_listview, listPhysicalResult);
        lvPhysicalResult.setAdapter(adapter);

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}