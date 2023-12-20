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

public class PhysicalCoursesAdapter extends ArrayAdapter {
    Activity context;
    int layoutID;
    ArrayList<Course> list = null;

    public PhysicalCoursesAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.layoutID = resource;
        this.list = (ArrayList<Course>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        if (list.size() > 0 && position >= 0) {
            final TextView txvCourseName = convertView.findViewById(R.id.txvCourseName);
            final TextView txvCourseCode = convertView.findViewById(R.id.txvCourseCode);
            final TextView txvCredits = convertView.findViewById(R.id.txvCredits);

            txvCourseName.setText(list.get(position).getCourse_name() + "");
            txvCourseCode.setText(list.get(position).getCourse_code() + "");
            txvCredits.setText(list.get(position).getCredits() + "");
        }
        return convertView;
    }
}
