package dhcn.nhom1.thechathaui;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoFragment extends Fragment implements View.OnClickListener {
    Context context;
    MyDatabaseHelper db;
    Student student;
    TextView txvBMI, txvBMITT, txvNuoc, txvTheDuc, txvBatDau, txvKetThuc;
    EditText edtPhutTheDuc;
    CheckBox chbNuoc, chbTheDuc;
    ImageButton imbBatDau, imbKetThuc;
    Button btnLichSu;
    ProgressBar pgbTienTrinh;
    int isChbNuocChecked, isChbTheDucChecked;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }

    //Giúp fragment luôn được cập nhật khi có sự thay đổi trong cơ sở dữ liệu
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getWidget();
        getData();
        calculateBMI();
        calculateWater(Long.parseLong(String.valueOf(edtPhutTheDuc.getText())));
        //Tính lại lượng nước cần uống khi thay đổi thời gian tập thể dục
        edtPhutTheDuc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(String.valueOf(edtPhutTheDuc.getText()).trim().length() != 0){
                    calculateWater(Long.parseLong(String.valueOf(edtPhutTheDuc.getText())));
                }else{
                    calculateWater(0);
                }
            }
        });
        calculateExerciseTime();

        //Cài đặt hiển thị cho thanh tiến trình
        isChbNuocChecked = (chbNuoc.isChecked() == true) ? 1 : 0;
        isChbTheDucChecked = (chbTheDuc.isChecked() == true) ? 1 : 0;
        pgbTienTrinh.setProgress(isChbNuocChecked + isChbTheDucChecked);

        //Thay đổi thanh tiến trình khi các CheckBox thay đổi
        chbNuoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isChbNuocChecked = 1;
                }else{
                    isChbNuocChecked = 0;
                }
                pgbTienTrinh.setProgress(isChbNuocChecked + isChbTheDucChecked);
            }
        });
        chbTheDuc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isChbTheDucChecked = 1;
                }else{
                    isChbTheDucChecked = 0;
                }
                pgbTienTrinh.setProgress(isChbNuocChecked + isChbTheDucChecked);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        saveData();
    }

    @Override
    public void onStop() {
        super.onStop();
        saveData();
    }
    //Lưu dữ liệu vào cơ sở dữ liệu khi fragment bị che khuất hoặc thoát ứng dụng
    private void saveData(){
        String water[] = String.valueOf(txvNuoc.getText()).split(" ");
        Tracker tracker = new Tracker();
        tracker.setIs_enough_water((chbNuoc.isChecked() == true) ? 1 : 0);
        tracker.setLiter(Double.parseDouble(water[0]));
        tracker.setIs_doing_exercise((chbTheDuc.isChecked() == true) ? 1 : 0);
        tracker.setExercise_span(Long.parseLong(String.valueOf(edtPhutTheDuc.getText())));
        tracker.setStudent_id(student.getId());
        db.addTrackerRecord(tracker);
    }
    //Lấy dữ liệu từ cơ sở dữ liệu hiển thị lên màn hình
    private void getData(){
        context = this.getContext();
        db = new MyDatabaseHelper(context);
        long StudentId = Long.parseLong(db.getStudentIdSignIn());
        student = db.getStudentById(StudentId);
        Tracker tracker = db.getTrackerToday(StudentId);
        if(tracker != null){
            edtPhutTheDuc.setText(String.valueOf(tracker.getExercise_span()));
            chbNuoc.setChecked((tracker.getIs_enough_water() == 1) ? true : false);
            chbTheDuc.setChecked((tracker.getIs_doing_exercise() == 1) ? true : false);
        }
    }
    private void getWidget(){
        txvBMI = getView().findViewById(R.id.txvBMI);
        txvBMITT = getView().findViewById(R.id.txvBMITT);
        edtPhutTheDuc = getView().findViewById(R.id.edtPhutTheDuc);
        chbNuoc = getView().findViewById(R.id.chbNuoc);
        chbTheDuc = getView().findViewById(R.id.chbTheDuc);
        txvNuoc = getView().findViewById(R.id.txvNuoc);
        txvTheDuc = getView().findViewById(R.id.txvTheDuc);
        txvBatDau = getView().findViewById(R.id.txvBatDau);
        txvKetThuc = getView().findViewById(R.id.txvKetThuc);
        pgbTienTrinh = getView().findViewById(R.id.pgbTienTrinh);
        View innerLayout = getView().findViewById(R.id.innerLayoutTodo);
        imbBatDau = innerLayout.findViewById(R.id.imbBatDau);
        imbKetThuc = innerLayout.findViewById(R.id.imbKetThuc);
        btnLichSu = innerLayout.findViewById(R.id.btnLichSu);
        

        imbBatDau.setOnClickListener(this);
        imbKetThuc.setOnClickListener(this);
        btnLichSu.setOnClickListener(this);
    }
    //Tính, hiển thị thông tin BMI và lưu vào cơ sở dữ liệu
    private void calculateBMI(){
        double BMI = student.getWeight()/Math.pow(student.getHeight(), 2);
        BMI = Math.round(BMI*10)/10.0;
        txvBMI.setText(String.valueOf(BMI));
        if(BMI < 18.5){
            txvBMITT.setText("Cân nặng thấp (gầy)");
        } else if (BMI < 23) {
            txvBMITT.setText("Bình thường");
        } else if (BMI < 25) {
            txvBMITT.setText("Tiền béo phí");
        } else if (BMI < 30) {
            txvBMITT.setText("Béo phì độ I");
        } else{
            txvBMITT.setText("Béo phì độ II");
        }
        Health health = new Health(BMI, null, null, null, student.getId());
        boolean rsAdd = db.addHealthRecord(health);
        if(rsAdd == true){
            Toast.makeText(context, "Cập nhật dữ liệu BMI!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Có lỗi khi truy cập cơ sở dữ liệu!",Toast.LENGTH_SHORT).show();
        }
    }
    //Tính lượng nước uống cần thiết dựa trên cân nặng, mức độ vận động, hiển thị lên màn hình
    private void calculateWater(long soPhutTheDuc) {
        double liter = (student.getWeight() + (soPhutTheDuc/30.0 * 12)) * 0.03;
        liter = Math.round(liter*10)/10.0;
        txvNuoc.setText(String.valueOf(liter) + " lít");
    }
    //Tính thời gian tập thể dục tham khảo, dựa trên bệnh nền
    private void calculateExerciseTime() {
        String benhNen = student.getUnderlying_disease();
        if(benhNen.equals("Không")){
            txvTheDuc.setText("Thời gian tập thể dục tham khảo:\n" +
                    "- 30 phút tập thể dục vừa phải hoặc 15 phút tập thể dục cường độ cao");
        } else if (benhNen.equals("Nhóm 1: Rối loạn chuyển hoá")) {
            txvTheDuc.setText("Thời gian tập thể dục tham khảo:\n" +
                    "- 30 phút tập thể dục vừa phải\n" +
                    "- Nên tập sau ăn từ 1 - 3 giờ vì đây là khoảng thời gian cơ thể trao đổi chất mạnh mẽ\n" +
                    "- Đối với người bị rối loạn chuyển hoá ở mức quá cao, cần tuân theo tư vấn của bác sĩ trước khi lên kế hoạch tập luyện");
        } else if (benhNen.equals("Nhóm 2: Hô hấp")) {
            txvTheDuc.setText("Thời gian tập thể dục tham khảo:\n" +
                    "- Không tập thể dục quá sức, bắt đầu từ những bước nhỏ (ví dụ: 5 phút), tăng dần thời gian tập khi đã quen\n" +
                    "- Chọn những bài tập phù hợp: những bài tập nhẹ nhàng như bơi lội, đi bộ, đạp xe, yoga, khí công, thái cực quyền\n" +
                    "- Đối với người bị bệnh hô hấp nặng, bắt buộc phải tuân theo chỉ dẫn tập luyện của bác sĩ");
        } else if (benhNen.equals("Nhóm 3: Tim mạch")) {
            txvTheDuc.setText("Thời gian tập thể dục tham khảo:\n" +
                    "- 30 phút tập thể dục vừa phải, ngắt quãng, không cố tập quá sức\n" +
                    "- Chọn những bài tập phù hợp: những bài tập nhẹ nhàng như bơi lội, đi bộ, đạp xe, yoga, khí công, thái cực quyền\n" +
                    "- Cần khởi động kỹ tối thiểu 15 phút để các hệ cơ - xương - khớp, hệ tuần hoàn và hô hấp thích nghi với nhịp độ vận động\n" +
                    "- Đối với người bị bệnh tim mạch mức trung bình và cao, cần tuân theo chỉ dẫn của bác sĩ");
        } else{
            txvTheDuc.setText("Thời gian tập thể dục tham khảo:\n" +
                    "- Bệnh nền của bạn không có trong cơ sở dữ liệu đề xuất\n" +
                    "- Hãy tập luyện tuân theo chỉ dẫn của bác sĩ");
        }
    }

    @Override
    public void onClick(View v) {
        if (v == imbBatDau){
            //Cài đặt DatePicker cho ngày bắt đầu
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Date d = null;
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth + "/" + (month+1) + "/" + year);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    txvBatDau.setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
                }
            };
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog pic = new DatePickerDialog(context, callback, year, month, day);
            pic.setTitle("Chọn ngày");
            pic.show();
        } else if (v == imbKetThuc) {
            //Cài đặt DatePicker cho ngày kết thúc
            DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Date d = null;
                    try {
                        d = new SimpleDateFormat("dd/MM/yyyy").parse(dayOfMonth + "/" + (month+1) + "/" + year);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    txvKetThuc.setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
                }
            };
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog pic = new DatePickerDialog(context, callback, year, month, day);
            pic.setTitle("Chọn ngày");
            pic.show();
        } else if (v == btnLichSu) {
            //Gọi đến HistoryActivity và truyền dữ liệu ngày bắt đầu, ngày kết thúc cho nó
            if((String.valueOf(txvBatDau.getText()).trim().length() == 0) || (String.valueOf(txvKetThuc.getText()).trim().length() == 0)){
                Toast.makeText(context, "Hãy chọn đầy đủ ngày bắt đầu và ngày kết thúc!", Toast.LENGTH_SHORT).show();
            } else{
                Date startDate, endDate;
                try {
                    startDate = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(txvBatDau.getText()));
                    endDate = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(txvKetThuc.getText()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if(startDate.after(endDate)){
                    Toast.makeText(context, "Ngày bắt đầu không thể lớn hơn ngày kết thúc", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    Bundle bundle = new Bundle();

                    String start = String.valueOf(txvBatDau.getText());
                    String end = String.valueOf(txvKetThuc.getText());

                    bundle.putString("start", start);
                    bundle.putString("end", end);
                    bundle.putLong("studentId", student.getId());

                    intent.putExtra("StartEndData",bundle);
                    startActivity(intent);
                }
            }
        }
    }
}