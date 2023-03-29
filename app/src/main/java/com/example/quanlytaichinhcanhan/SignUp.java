package com.example.quanlytaichinhcanhan;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SignUp extends Activity {
    public EditText SignupUsername, SignupPassword, SignupRepeatPassword;
    public Button SignupButton;
    public TextView loginHereButton;
    public SQLiteDatabase db;
    public Login login = new Login();

    public void insertDB(String username, String password) {
        try {
            db = openOrCreateDatabase(login.dtbase, MODE_PRIVATE, null);
            String sql = "INSERT INTO dbUser(Username, PassWord,Tien) values (?, ?,0)";
            db.execSQL(sql, new String[]{username, password});
            db.close();
            Toast.makeText(this, "Khởi tạo thành công", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi ở đây", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isUserExits(String username){
        try {
            db = openOrCreateDatabase(login.dtbase,MODE_PRIVATE,null);
            Cursor c = db.rawQuery("select * from dbUser where Username = ? ", new String[]{username});
            c.moveToFirst();
            db.close();
            if (c.getCount() > 0)
            {
                c.close();
                return true;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this,"Bug nek 1", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        SignupUsername = findViewById(R.id.usernameEditText);
        SignupPassword = findViewById(R.id.passwordEditText);
        SignupRepeatPassword = findViewById(R.id.repeatPasswordEditText);
        SignupButton = findViewById(R.id.signUpButton);
        loginHereButton = findViewById(R.id.loginHereTextView);
        loginHereButton.setAutoLinkMask(Linkify.WEB_URLS);
        loginHereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });
        SignupButton.setOnClickListener(view -> {
            String username, password, repeatpassword;
            username = SignupUsername.getText().toString();
            password = SignupPassword.getText().toString();
            repeatpassword = SignupRepeatPassword.getText().toString();
            if (username.isEmpty() || password.isEmpty())
                Toast.makeText(SignUp.this, "Vui lòng điền thông tin tài khoản, mật khẩu", Toast.LENGTH_LONG).show();
            else if (!password.equals(repeatpassword)) {
                Toast.makeText(SignUp.this, "Mật khẩu và Xác nhận mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
            } else {
                if(isUserExits(username))
                    Toast.makeText(SignUp.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                else {
                    insertDB(username, password);
                    Toast.makeText(SignUp.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                }
            }
        });
    }
}
