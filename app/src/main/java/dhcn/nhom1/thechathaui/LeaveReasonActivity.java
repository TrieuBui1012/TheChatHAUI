package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LeaveReasonActivity extends AppCompatActivity{
    MyDatabaseHelper db;
    Button btnLuuBN, btnHuyBN;
    EditText edtLyDoBN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_reason);

        db = new MyDatabaseHelper(this);

        btnLuuBN = findViewById(R.id.btnLuuBN);
        btnHuyBN = findViewById(R.id.btnHuyBN);
        edtLyDoBN = findViewById(R.id.edtLyDoBN);
        //Lấy dữ liệu được truyền
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("LeaveReportData");

        Long onleave_id;
        if(bundle.containsKey("onleave_id")){
            onleave_id = bundle.getLong("onleave_id");
        }else{
            onleave_id = Long.parseLong(String.valueOf(-1));
        }
        long class_id = bundle.getLong("class_id");
        long student_id = bundle.getLong("student_id");
        String date = bundle.getString("date");

        btnLuuBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(edtLyDoBN.getText()).trim().length() == 0){
                    Toast.makeText(LeaveReasonActivity.this, "Cần điền lý do trước khi lưu", Toast.LENGTH_SHORT).show();
                }else{
                    boolean rs = db.addOnleaveRecord(onleave_id, class_id, student_id, date, String.valueOf(edtLyDoBN.getText()));
                    if(rs == true){
                        Toast.makeText(LeaveReasonActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(LeaveReasonActivity.this, LeaveReportFragment.class);
                        setResult(RESULT_OK, intent1);

                        finish();
                    }else{
                        Toast.makeText(LeaveReasonActivity.this, "Lỗi truy cập dữ liệu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuyBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}