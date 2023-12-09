package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HealthFragment healthFragment = new HealthFragment();
        TodoFragment todoFragment = new TodoFragment();
        LeaveReportFragment leaveReportFragment = new LeaveReportFragment();
        PhysicalStudyFragment physicalStudyFragment = new PhysicalStudyFragment();
        SettingFragment settingFragment = new SettingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, healthFragment).commit();

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navSucKhoe) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, healthFragment).commit();
                return true;
            } else if (itemId == R.id.navRenLuyen) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, todoFragment).commit();
                return true;
            } else if (itemId == R.id.navBaoNghi) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, leaveReportFragment).commit();
                return true;
            } else if (itemId == R.id.navHocPhanTheChat) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, physicalStudyFragment).commit();
                return true;
            } else if (itemId == R.id.navCaiDat) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingFragment).commit();
                return true;
            }
            return true;
        });
    }
}