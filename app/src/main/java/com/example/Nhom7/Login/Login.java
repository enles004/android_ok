package com.example.Nhom7.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.Nhom7.LOPDAO.NVDao;
import com.example.Nhom7.LOPPRODUCT.NhanVien;
import com.example.Nhom7.MainActivity;
import com.example.Nhom7.R;

public class Login extends AppCompatActivity {
    private AppCompatButton btn_login;
    private EditText ed_user, ed_pass;
    Intent intent;
    NVDao nvdao;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        btn_login = findViewById(R.id.login_btn);
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        nvdao = new NVDao(this);
        nvdao.OPEN();
        if (nvdao.getUserName("admin") < 0) {
            nvdao.ADDNV(new NhanVien("admin", "admin", "admin"));
        }
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        ed_user.setText(preferences.getString("USERNAME", ""));
        ed_pass.setText(preferences.getString("PASSWORD", ""));

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checklogin();
            }
        });


    }

    public void checklogin() {
        String usered = ed_user.getText().toString();
        String passed = ed_pass.getText().toString();
        if (usered.isEmpty() || passed.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (nvdao.getlogin(usered, passed) > 0 || (usered.equalsIgnoreCase("admin") && passed.equalsIgnoreCase("admin"))
                    || (usered.equalsIgnoreCase("user") && passed.equalsIgnoreCase("user"))) {
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                startActivity(intent = new Intent(Login.this, MainActivity.class).putExtra("admintion", usered));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại ! " +
                        "\nSai tài khoản,mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
