package dhcn.nhom1.thechathaui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthFragment extends Fragment {
    EditText editText ;
    Student student;
    private ListView listView;
    private Toolbar toolbar ;
    private MyDatabaseHelper dbHelper;
    private ArrayAdapter<String> adapter;
    ArrayList<LeaveReport> list = new ArrayList<LeaveReport>();
    ArrayAdapter<LeaveReport> adapterLV;

    EditText edtBenh;
    Button btn;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        dbHelper = new MyDatabaseHelper(getContext());
        long StudentId = Long.parseLong(dbHelper.getStudentIdSignIn());
        listView = view.findViewById(R.id.lstViewTKB);
        student = dbHelper.getStudentById(StudentId);
        toolbar = view.findViewById(R.id.toolbar4);
        toolbar.setTitle("Xin chào, " + student.getFullname());
        btn = view.findViewById(R.id.btnLuu);
        edtBenh = view.findViewById(R.id.edtBenh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Health health = new Health();
                health.setStudent_id(student.getId());
                if (String.valueOf(edtBenh.getText()).trim().length() == 0) {
                    Toast.makeText(getContext(), "Cần nhập tên bệnh trước khi lưu !!", Toast.LENGTH_SHORT).show();
                } else
                    health.setDisease(String.valueOf(edtBenh.getText()));
                boolean result = dbHelper.addHealthRecord(health);
                if (result == false) {
                    Toast.makeText(getContext(), "Gặp lỗi khi truy cập dữ liệu", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            }

        });
        adapterLV = new HealthAdapter(getActivity(), R.layout.list_item_health,list);
        listView.setAdapter(adapterLV);
        // Get the Spinner reference from the layout
        Spinner spinner = view.findViewById(R.id.spnBenh);

        // Create an ArrayAdapter using a simple spinner layout and your array or list of items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getYourListOfItems());

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        // Optional: Set a listener to respond to item selection events
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected item
                String selectedItem = (String) parentView.getItemAtPosition(position);
                if(position==0){
                    edtBenh.setEnabled(false);
                }
                else{
                    edtBenh.setEnabled(true);
                }
                // Do something with the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Date date = new Date();
                String datestr = new SimpleDateFormat("dd/MM/yyyy").format(date);
                if(String.valueOf(edtBenh.getText()).trim().length() == 0){
                    Toast.makeText(getContext(), "Bạn cần nhập bệnh trước khi báo ốm", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean result1 = dbHelper.addSickReport(list.get(position).getOnleave_id(),list.get(position).getClass_id(),student.getId(),datestr,String.valueOf(edtBenh.getText()));
                    if(result1 == false){
                        Toast.makeText(getContext(), "Lỗi truy vấn", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        list.clear();
                        list.addAll(dbHelper.getLeaveReportsByDate(datestr, student.getId()));
                        adapterLV.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Báo ốm thành công", Toast.LENGTH_SHORT).show();
                    }}
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Date date = new Date();
                String datestr = new SimpleDateFormat("dd/MM/yyyy").format(date);
                int row = dbHelper.deleteOnleaveById(list.get(position).getOnleave_id());
                if(row == 0){
                    Toast.makeText(getContext(), "Bạn không cần phải đặt lại tình trạng bình thường", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(dbHelper.getLeaveReportsByDate(datestr, student.getId()));
                    adapterLV.notifyDataSetChanged();
                }
                return true;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Date date = new Date();
        String datestr = new SimpleDateFormat("dd/MM/yyyy").format(date);
        if(dbHelper.getLeaveReportsByDate(datestr, student.getId())==null){
            Toast.makeText(getContext(), "Không tìm thấy TKB hôm nay", Toast.LENGTH_SHORT).show();
        }
        else{
            list.clear();
            list.addAll(dbHelper.getLeaveReportsByDate(datestr, student.getId()));
            adapterLV.notifyDataSetChanged();
        }
    }

    private List<String> getYourListOfItems() {
        // Return your list of items here
        List<String> items = new ArrayList<>();
        items.add("Bình thường");
        items.add("Ốm");
        // Add more items as needed
        return items;
    }

}