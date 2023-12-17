package dhcn.nhom1.thechathaui;

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

public class LeaveReportAdapter extends ArrayAdapter {
    Activity context;
    int layoutID;
    ArrayList<LeaveReport> list=null;

    public LeaveReportAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.list = (ArrayList<LeaveReport>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if((list.size() > 0) && (position >= 0)){
            //Lay dong thu i
            final TextView txvMonLR = convertView.findViewById(R.id.txvMonLR);
            final TextView txvTietLR = convertView.findViewById(R.id.txvTietLR);
            final TextView txvGiangVienLR = convertView.findViewById(R.id.txvGiangVienLR);
            final TextView txvTinhTrangLR = convertView.findViewById(R.id.txvTinhTrangLR);
            final TextView txvLiDoLR = convertView.findViewById(R.id.txvLiDoLR);

            //Lay phan tu thu position de hien thi
            txvMonLR.setText(list.get(position).getCourse_name());
            txvTietLR.setText("Tiết: " + list.get(position).getTime_in_day());
            txvGiangVienLR.setText("Giảng viên: " + list.get(position).getInstructor_name());
            if(list.get(position).getStatus() == null){
                txvTinhTrangLR.setText("Tình trạng: Bình thường");
            } else if (list.get(position).getStatus() == 1) {
                txvTinhTrangLR.setText("Tình trạng: Báo ốm");
            } else if (list.get(position).getStatus() == 2) {
                txvTinhTrangLR.setText("Tình trạng: Báo nghỉ");
            } else {
                txvTinhTrangLR.setText("Tình trạng: Lỗi dữ liệu");
            }
            txvLiDoLR.setText((list.get(position).getReason() == null) ? "" : ("Lí do: " + list.get(position).getReason()));
        }
        return convertView;
    }
}
