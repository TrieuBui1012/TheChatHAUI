package dhcn.nhom1.thechathaui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HistoryActivity extends AppCompatActivity {
    final long MILLIS_IN_A_DAY = 1000*60*60*24;
    Toolbar toolbar;
    TextView txvLichSu;
    ListView lsvLichSu;

    ArrayList<History> list = new ArrayList<History>();
    ArrayAdapter<History> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar = findViewById(R.id.toolbarLichSu);
        txvLichSu = findViewById(R.id.txvLichSu);
        lsvLichSu = findViewById(R.id.lsvLichSu);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("StartEndData");
        String start = bundle.getString("start");
        String end = bundle.getString("end");
        Long studentId = bundle.getLong("studentId");

        txvLichSu.setText("Từ " + start + " đến " + end);

        Date startDate;
        try {
            startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        startDate = new Date(startDate.getTime() - MILLIS_IN_A_DAY);
        start = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        Date endDate;
        try {
            endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        endDate = new Date(endDate.getTime() + MILLIS_IN_A_DAY);
        end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        ArrayList<Tracker> trackers = db.getTrackerInDateRange(start, end, studentId);

        for(int i = 0; i < trackers.size(); i++){
            History history = new History();
            Date d;
            try {
                d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(trackers.get(i).getCreated_at());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            history.setDate(new SimpleDateFormat("dd/MM/yyyy").format(d));
            double BMI = db.getHealthByDate(new SimpleDateFormat("yyyy-MM-dd").format(d), studentId).getBMI();
            history.setBMI("BMI: " + BMI);
            if(BMI < 18.5){
                history.setStatus("Tình trạng: Cân nặng thấp (gầy)");
            } else if (BMI < 23) {
                history.setStatus("Tình trạng: Bình thường");
            } else if (BMI < 25) {
                history.setStatus("Tình trạng: Tiền béo phí");
            } else if (BMI < 30) {
                history.setStatus("Tình trạng: Béo phì độ I");
            } else{
                history.setStatus("Tình trạng: Béo phì độ II");
            }
            String benh = db.getHealthByDate(new SimpleDateFormat("yyyy-MM-dd").format(d), studentId).getDisease();
            history.setDisease((benh == null) ? "Bệnh: không" : ("Bệnh: " + benh));
            history.setIs_enough_water((trackers.get(i).getIs_enough_water() == 1) ? "Uống đủ nước: có" : "Uống đủ nước: không");
            history.setLiter("Lượng nước cần: " + trackers.get(i).getLiter() + " lít");
            history.setIs_doing_exercise((trackers.get(i).getIs_doing_exercise() == 1) ? "Tập thể dục: có" : "Tập thể dục: không");
            history.setExercise_span("Thời gian: " + trackers.get(i).getExercise_span() + " phút");
            list.add(history);
        }

        adapter = new HistoryAdapter(HistoryActivity.this, R.layout.list_item_history, list);
        lsvLichSu.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}