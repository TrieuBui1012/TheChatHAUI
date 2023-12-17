package dhcn.nhom1.thechathaui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SettingFragment extends Fragment {
    View view;
    String id;
    TextView txvId, txvName, txvBirthday, txvGender;
    EditText edtHeight, edtWeight, edtDisease;
    Button btnSave, btnLogout;
    ImageView imgAvatar;
    Spinner spnDisease;
    ArrayList<String> listDisease = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    MyDatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        getWidget(view);
        id = db.getStudentIdSignIn();
        showStudentInfo(id);
        return view;
    }

    private void showStudentInfo(String id) {
        Student st = db.getStudentById(Long.parseLong(id));
        txvId.setText(st.getStudent_code() + "");
        txvName.setText(st.getFullname());
        txvGender.setText(st.getGender() == 1 ? "Nam" : "Nữ");
        txvBirthday.setText(st.getDob());
        edtHeight.setText(st.getHeight() + "");
        edtWeight.setText(st.getWeight() + "");
        edtDisease.setText(st.getUnderlying_disease());
        displayImageFromAssets("images/" + st.getAvatar(), imgAvatar);
        int pos = listDisease.indexOf(st.getUnderlying_disease());
        spnDisease.setSelection(pos);
    }

    private void displayImageFromAssets(String fileName, ImageView imageView) {
        AssetManager assetManager = getActivity().getAssets();
        InputStream inputStream = null;
        try {
            // Mở ảnh từ thư mục assets
            inputStream = assetManager.open(fileName);

            // Chuyển InputStream thành Bitmap
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            // Hiển thị Bitmap trong ImageView
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Đóng InputStream
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getWidget(View view) {
        db = new MyDatabaseHelper(view.getContext());
        txvId = view.findViewById(R.id.txvId);
        txvName = view.findViewById(R.id.txvName);
        txvBirthday = view.findViewById(R.id.txvBirthday);
        txvGender = view.findViewById(R.id.txvGender);
        edtHeight = view.findViewById(R.id.edtHeight);
        edtWeight = view.findViewById(R.id.edtWeight);
        edtDisease = view.findViewById(R.id.edtDisease);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnSave = view.findViewById(R.id.btnSave);
        spnDisease = view.findViewById(R.id.spnDisease);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        listDisease.add("Không");
        listDisease.add("Nhóm 1: Rối loạn chuyển hoá");
        listDisease.add("Nhóm 2: Hô hấp");
        listDisease.add("Nhóm 3: Tim mạch");
        adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, listDisease);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spnDisease.setAdapter(adapter);
        spnDisease.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtDisease.setText(listDisease.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btnSave.setOnClickListener(new EventHandle());
        btnLogout.setOnClickListener(new EventHandle());
    }

    class EventHandle implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnSave) {
                String txtHeight = (edtHeight.getText() + "").trim();
                String txtWeight = (edtWeight.getText() + "").trim();
                String txtDisease = (edtDisease.getText() + "").trim();
                Double height, weight;
                if (txtHeight.isEmpty()) {
                    Toast.makeText(view.getContext(), "Chiều cao không được để trống!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtWeight.isEmpty()) {
                    Toast.makeText(view.getContext(), "Cân nặng không được để trống!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (txtDisease.isEmpty()) {
                    Toast.makeText(view.getContext(), "Bệnh nền không được để trống!", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    height = Double.parseDouble(txtHeight);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Chiều cao không hợp lệ!", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    weight = Double.parseDouble(txtWeight);
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Cân nặng không hợp lệ!", Toast.LENGTH_LONG).show();
                    return;
                }
                Student st = new Student();
                st.setWeight(weight);
                st.setHeight(height);
                st.setUnderlying_disease(txtDisease);
                if (db.updateStudent(id, st)) {
                    Toast.makeText(view.getContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(view.getContext(), "Đã có lỗi xảy ra!", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            if (v.getId() == R.id.btnLogout) {
                AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
                b.setTitle("Đăng Xuất");
                b.setMessage("Bạn có muốn đăng xuất?");
                b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logout = new Intent(view.getContext(), SignInActivity.class);
                        startActivity(logout);
                    }
                });
                b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        }
    }
}