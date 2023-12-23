package dhcn.nhom1.thechathaui;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HealthAdapter extends ArrayAdapter {

    Activity context;
    int layoutID;
    ArrayList<LeaveReport> list=null;

    public HealthAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.list = (ArrayList<LeaveReport>) objects;
    }

    public HealthAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if((list.size() > 0) && (position >= 0)){
            //Lay dong thu i
            final TextView textViewMon = convertView.findViewById(R.id.textViewMon);
            final TextView textViewTietHoc = convertView.findViewById(R.id.textViewTietHoc);
            final TextView textViewGV = convertView.findViewById(R.id.textViewGV);
            final TextView textViewTinhTrang = convertView.findViewById(R.id.textViewTinhTrang);
            final TextView textViewLyDo = convertView.findViewById(R.id.textViewLyDo);

            //Lay phan tu thu position de hien thi
            textViewMon.setText(list.get(position).getCourse_name());
            textViewTietHoc.setText("Tiết: " + list.get(position).getTime_in_day());
            textViewGV.setText("Giảng viên: " + list.get(position).getInstructor_name());
            if(list.get(position).getStatus() == null){
                textViewTinhTrang.setText("Tình trạng: Bình thường");
            } else if (list.get(position).getStatus() == 1) {
                textViewTinhTrang.setText("Tình trạng: Báo ốm");
                textViewLyDo.setText((list.get(position).getReason() == null) ? "" : ("Bệnh: " + list.get(position).getReason()));
            } else if (list.get(position).getStatus() == 2) {
                textViewTinhTrang.setText("Tình trạng: Báo nghỉ");
                textViewLyDo.setText((list.get(position).getReason() == null) ? "" : ("Lí do: " + list.get(position).getReason()));
            } else {
                textViewTinhTrang.setText("Tình trạng: Lỗi dữ liệu");
            }
        }
        return convertView;
    }

}
