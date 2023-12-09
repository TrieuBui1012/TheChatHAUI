package dhcn.nhom1.thechathaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SignInActivity extends AppCompatActivity {
    EditText edtMaSV, edtMatKhau;
    CheckBox chbNhoDN;
    Button btnDangNhap, btnNhapHoc;
    MyDatabaseHelper db = new MyDatabaseHelper(SignInActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtMaSV = findViewById(R.id.edtMaSV);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        chbNhoDN = findViewById(R.id.chbNhoDN);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnNhapHoc = findViewById(R.id.btnNhapHoc);

        String[] SignInData = readData();
        if(SignInData != null){
            if(SignInData[1].equals("true")){
                try{
                    Student s = db.getAccountById(Integer.parseInt(SignInData[0]));
                    edtMaSV.setText(String.valueOf(s.getStudent_code()));
                    edtMatKhau.setText(s.getPassword());
                    chbNhoDN.setChecked(true);
                }
                catch (Exception e){
                    Toast.makeText(SignInActivity.this,"Dữ liệu đăng nhập đã lưu bị sai định dạng", Toast.LENGTH_SHORT).show();
                }
            }
        }

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student s = new Student();
                if(edtMaSV.getText().toString().trim().length() == 0){
                    Toast.makeText(SignInActivity.this,"Không thể để trống Mã sinh viên!", Toast.LENGTH_SHORT).show();
                } else if (edtMatKhau.getText().toString().trim().length() == 0) {
                    Toast.makeText(SignInActivity.this,"Không thể để trống Mật khẩu!", Toast.LENGTH_SHORT).show();
                }else{
                    try{
                        s.setStudent_code(Integer.parseInt(edtMaSV.getText().toString()));
                        s.setPassword(edtMatKhau.getText().toString());
                        int check = db.checkSignIn(s);
                        if(check != -1){
                            if(chbNhoDN.isChecked() == true){
                                try {
                                    FileOutputStream out= openFileOutput("SignIn.txt",0);
                                    OutputStreamWriter writer= new OutputStreamWriter(out);
                                    writer.write(check + "\n" + "true");
                                    writer.close();
                                } catch (FileNotFoundException e) {
                                    Toast.makeText(SignInActivity.this,"Không thể lưu dữ liệu đăng nhập", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Toast.makeText(SignInActivity.this,"Không thể lưu dữ liệu đăng nhập", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                try {
                                    FileOutputStream out= openFileOutput("SignIn.txt",0);
                                    OutputStreamWriter writer= new OutputStreamWriter(out);
                                    writer.write(check + "\n" + "false");
                                    writer.close();
                                } catch (FileNotFoundException e) {
                                    Toast.makeText(SignInActivity.this,"Không thể lưu dữ liệu đăng nhập", Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    Toast.makeText(SignInActivity.this,"Không thể lưu dữ liệu đăng nhập", Toast.LENGTH_SHORT).show();
                                }
                            }
                            Intent intent = new Intent(SignInActivity.this, WarningActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(SignInActivity.this,"Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (NumberFormatException e){
                        Toast.makeText(SignInActivity.this,"Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnNhapHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://nhaphoc.haui.edu.vn/mobile/nhap-hoc/nhap-hoc-dai-hoc-cong-nghiep-ha-noi.htm";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    //Hàm này trả về 1 mảng String có phần tử đầu là id của sinh viên
    public String[] readData(){
        String rs[] = null;

        try {
            FileInputStream in= openFileInput("SignIn.txt");
            BufferedReader reader=new BufferedReader(new InputStreamReader(in));
            String data="";
            StringBuilder builder=new StringBuilder();
            while((data=reader.readLine())!=null)
            {
                builder.append(data);
                builder.append("\n");
            }
            in.close();
            rs = builder.toString().split("\n");
            return rs;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            Toast.makeText(SignInActivity.this,"Không thể đọc dữ liệu đăng nhập đã lưu", Toast.LENGTH_SHORT).show();
        }
        return rs;
    }
}