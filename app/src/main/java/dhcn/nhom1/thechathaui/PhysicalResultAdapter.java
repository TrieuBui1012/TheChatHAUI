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
            final TextView txvSummary = convertView.findViewById(R.id.txvSummary);
            final TextView txvWordScore = convertView.findViewById(R.id.txvWordScore);
            final TextView txvGraded = convertView.findViewById(R.id.txvGraded);

            txvCourseName.setText(list.get(position).getCourse_name() + "");
            txvCredits.setText("Số tín: " + list.get(position).getCredits() + "");
            txvCourseCode.setText("Mã học phần: " + list.get(position).getCourse_code() + "");
            txvClassCode.setText("Mã lớp: " + list.get(position).getClass_code() + "");
            txvTest1.setText("Điểm thường xuyên: " + list.get(position).getTest1() + "");
            txvMidtermScore.setText("Điểm giữa kì: " + list.get(position).getMidterm_score() + "");
            txvExamScore.setText("Điểm thi: " + list.get(position).getExam_score() + "");

            double summary = resSummary(list.get(position).getTest1(), list.get(position).getMidterm_score(), list.get(position).getExam_score());
            String wordScore = resWordScore(summary);
            double graded = resGraded(wordScore);
            String wordGraded = resWordGraded(graded);

            txvSummary.setText("Tổng kết: " + summary);
            txvWordScore.setText("Điểm chữ: " + wordScore);
            txvGraded.setText("Xếp loại: " + wordGraded);
        }

        return convertView;
    }

    private double resSummary(double test1, double midterm, double exam) {
        return (double) (test1 * 0.2 + midterm * 0.3 + midterm * 0.5);
    }

    private String resWordScore(double summary) {
        if (summary >= 8.5) return "A";
        if (summary < 8.5 && summary >= 7.7) return "B+";
        if (summary < 7.7 && summary >= 7.0) return "B";
        if (summary < 7.0 && summary >= 6.2) return "C+";
        if (summary < 6.2 && summary >= 5.5) return "C";
        if (summary < 5.5 && summary >= 4.7) return "D+";
        if (summary < 4.7 && summary >= 4.0) return "D";
        return "F";
    }

    private double resGraded(String wordScore) {
        if (wordScore.equals("A")) return 4;
        if (wordScore.equals("B+")) return 3.5;
        if (wordScore.equals("B")) return 3;
        if (wordScore.equals("C+")) return 2.5;
        if (wordScore.equals("C")) return 2;
        if (wordScore.equals("D+")) return 1.5;
        if (wordScore.equals("D")) return 1;
        return 0;
    }

    private String resWordGraded(double graded) {
        if (graded <= 4 && graded >= 3.6) return "Xuất sắc";
        if (graded < 3.6 && graded >= 3.2) return "Giỏi";
        if (graded < 3.2 && graded >= 2.5) return "Khá";
        if (graded < 2.5 && graded >= 2.0) return "Trung bình";
        if (graded < 2.0 && graded >= 1) return "Yếu";
        return "Kém";
    }


}
