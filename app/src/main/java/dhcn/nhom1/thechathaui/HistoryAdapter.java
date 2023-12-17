package dhcn.nhom1.thechathaui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends ArrayAdapter {
    Activity context;
    int layoutID;
    ArrayList<History> list=null;

    public HistoryAdapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.list = (ArrayList<History>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);
        if((list.size() > 0) && (position >= 0)){
            //Lay dong thu i
            final TextView txvNgayLS = convertView.findViewById(R.id.txvNgayLS);
            final TextView txvBMILS = convertView.findViewById(R.id.txvBMILS);
            final TextView txvTinhTrangLS = convertView.findViewById(R.id.txvTinhTrangLS);
            final TextView txvBenhLS = convertView.findViewById(R.id.txvBenhLS);
            final TextView txvNuocLS = convertView.findViewById(R.id.txvNuocLS);
            final TextView txvLitLS = convertView.findViewById(R.id.txvLitLS);
            final TextView txvTheDucLS = convertView.findViewById(R.id.txvTheDucLS);
            final TextView txvPhutLS = convertView.findViewById(R.id.txvPhutLS);
            //Lay phan tu thu position de hien thi
            txvNgayLS.setText(list.get(position).getDate());
            txvBMILS.setText(list.get(position).getBMI());
            txvTinhTrangLS.setText(list.get(position).getStatus());
            txvBenhLS.setText(list.get(position).getDisease());
            txvNuocLS.setText(list.get(position).getIs_enough_water());
            txvLitLS.setText(list.get(position).getLiter());
            txvTheDucLS.setText(list.get(position).getIs_doing_exercise());
            txvPhutLS.setText(list.get(position).getExercise_span());
        }
        return convertView;
    }
}
