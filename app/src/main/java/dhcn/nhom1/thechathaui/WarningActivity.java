package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class WarningActivity extends AppCompatActivity {
    CheckBox chbXacNhan;
    Button btnTiepTheo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);

        chbXacNhan = findViewById(R.id.chbXacNhan);
        btnTiepTheo = findViewById(R.id.btnTiepTheo);

        btnTiepTheo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chbXacNhan.isChecked() == false){
                    Toast.makeText(WarningActivity.this, "Không thể tiếp tục nếu không xác nhận.", Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(WarningActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}