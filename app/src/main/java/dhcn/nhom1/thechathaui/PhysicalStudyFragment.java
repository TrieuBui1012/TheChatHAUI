package dhcn.nhom1.thechathaui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class PhysicalStudyFragment extends Fragment {
    View view;
    Button btnPhysicalCourses, btnPhysicalResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_physical_study, container, false);
        getWidget(view);
        return view;
    }

    private void getWidget(View view) {
        btnPhysicalCourses = view.findViewById(R.id.btnPhysicalCourses);
        btnPhysicalResult = view.findViewById(R.id.btnPhysicalResult);
        btnPhysicalCourses.setOnClickListener(new EventHandle());
        btnPhysicalResult.setOnClickListener(new EventHandle());
    }

    class EventHandle implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnPhysicalCourses) {
                Intent intent = new Intent(view.getContext(), PhysicalCoursesActivity.class);
                startActivity(intent);
            }
            if (v.getId() == R.id.btnPhysicalResult) {
                Intent intent = new Intent(view.getContext(), PhysicalResultActivity.class);
                startActivity(intent);
            }
        }
    }

    ;

}