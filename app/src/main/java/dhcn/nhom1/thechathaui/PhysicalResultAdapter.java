package dhcn.nhom1.thechathaui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PhysicalResultAdapter extends ArrayAdapter {

    Activity context;
    int layoutID;

    ArrayList<PhysicalResult> list = null;

    public PhysicalResultAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        this.context = (Activity) context;
        this.layoutID = resource;
        this.list = (ArrayList<PhysicalResult>) objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        if (list.size() > 0 && position >= 0) {
            final TextView txvCourseName = convertView.findViewById(R.id.txvCourseName);
            final TextView txvCredits = convertView.findViewById(R.id.txvCredits);
            final TextView txvCourseCode = convertView.findViewById(R.id.txvCourseCode);
            final TextView txvClassCode = convertView.findViewById(R.id.txvClassCode);
            final TextView txvTest1 = convertView.findViewById(R.id.txvTest1);
            final TextView txvMidtermScore = convertView.findViewById(R.id.txvMidtermScore);
            final TextView txvExamScore = convertView.findViewById(R.id.txvExamScore);

            txvCourseName.setText(list.get(position).getCourse_name() + "");
            txvCredits.setText("Số tín: " + list.get(position).getCredits() + "");
            txvCourseCode.setText("Mã học phần: " + list.get(position).getClass_code() + "");
            txvClassCode.setText("Mã lớp: " + list.get(position).getClass_code() + "");
            txvTest1.setText("Điểm thường xuyên: " + list.get(position).getTest1() + "");
            txvMidtermScore.setText("Điểm giữa kì: " + list.get(position).getMidterm_score() + "");
            txvExamScore.setText("Điểm thi: " + list.get(position).getExam_score() + "");
        }

        return convertView;
    }
}
