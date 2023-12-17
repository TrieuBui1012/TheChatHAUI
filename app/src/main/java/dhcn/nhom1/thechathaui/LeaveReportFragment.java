package dhcn.nhom1.thechathaui;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class LeaveReportFragment extends Fragment implements View.OnClickListener{
    Context context;
    MyDatabaseHelper db;
    Student student;
    TextView txvNgayNghi;
    ImageButton imbNgayNghi;
    ListView lsvNgayNghi;
    ArrayList<LeaveReport> list = new ArrayList<LeaveReport>();
    ArrayAdapter<LeaveReport> adapter;
    ActivityResultLauncher<Intent> launcher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leave_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        getData();

        adapter = new LeaveReportAdapter((Activity) context, R.layout.list_item_leave_report, list);
        lsvNgayNghi.setAdapter(adapter);

        setDate();

        lsvNgayNghi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        lsvNgayNghi.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
        txvNgayNghi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()) != null){
                    list.clear();
                    list.addAll(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()));
                    adapter.notifyDataSetChanged();
                }else{
                    list.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Không tìm được dữ liệu thời khoá biểu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        registerForContextMenu(lsvNgayNghi);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK){
                        list.clear();
                        list.addAll(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()));
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    private void getWidget() {
        txvNgayNghi = getView().findViewById(R.id.txvNgayNghi);
        imbNgayNghi = getView().findViewById(R.id.imbNgayNghi);
        lsvNgayNghi = getView().findViewById(R.id.lsvNgayNghi);

        imbNgayNghi.setOnClickListener(this);
    }

    private void getData(){
        context = this.getContext();
        db = new MyDatabaseHelper(context);
        long StudentId = Long.parseLong(db.getStudentIdSignIn());
        student = db.getStudentById(StudentId);
    }

    private void setDate() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Date d = null;
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(day + "/" + (month+1) + "/" + year);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        txvNgayNghi.setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
        if(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()) != null){
            list.clear();
            list.addAll(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()));
            adapter.notifyDataSetChanged();
        }else{
            list.clear();
            adapter.notifyDataSetChanged();
            Toast.makeText(context, "Không tìm được dữ liệu thời khoá biểu!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v == imbNgayNghi){
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Date d = null;
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth + "/" + (month+1) + "/" + year);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    txvNgayNghi.setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
                }
            };
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog pic = new DatePickerDialog(context, callback, year, month, day);
            pic.setTitle("Chọn ngày");
            pic.show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_leave_report,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        if(id == R.id.itBaoNghi){
            Intent intent = new Intent(context, LeaveReasonActivity.class);
            Bundle bundle = new Bundle();

            Long onleave_id = list.get(pos).getOnleave_id();
            long class_id = list.get(pos).getClass_id();
            long student_id = student.getId();

            if(onleave_id != null){
                bundle.putLong("onleave_id", onleave_id);
            }
            bundle.putLong("class_id", class_id);
            bundle.putLong("student_id", student_id);
            bundle.putString("date", String.valueOf(txvNgayNghi.getText()));

            intent.putExtra("LeaveReportData",bundle);
            launcher.launch(intent);
        } else if (id == R.id.itDatLai) {
            if(list.get(pos).getOnleave_id() == null){
                Toast.makeText(context, "Không cần phải đặt lại!", Toast.LENGTH_SHORT).show();
            }else{
                int rowAffected = db.deleteOnleaveById(list.get(pos).getOnleave_id());
                if(rowAffected == 0){
                    Toast.makeText(context, "Không thể đặt lại!", Toast.LENGTH_SHORT).show();
                }else{
                    list.clear();
                    list.addAll(db.getLeaveReportsByDate(String.valueOf(txvNgayNghi.getText()), student.getId()));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(context, "Đặt lại thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return super.onContextItemSelected(item);
    }
}