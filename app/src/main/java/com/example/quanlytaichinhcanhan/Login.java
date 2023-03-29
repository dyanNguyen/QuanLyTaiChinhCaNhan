package com.example.quanlytaichinhcanhan;

import static android.app.SearchManager.QUERY;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.SavedStateRegistry;

public class Login extends Activity{
    public EditText LoginUsername;
    public EditText LoginPassword;
    public Button LoginButton;
    public TextView regis ;
    public String username, password;
    public SQLiteDatabase db;
    public static final String dtbase = "QLCT.db";
    public static final String EXTRA_MESSAGE = "com.example.quanlytaichinhcanhan.MESSAGE";
    public void initDB(){
        db= openOrCreateDatabase(dtbase,MODE_PRIVATE,null);
        String sql;
        try{
            if(!isTableExists(db,"dbUser"))
            {
                sql = "CREATE TABLE dbUser(id_user INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,Username TEXT NOT NULL, PassWord TEXT NOT NULL, Tien INT)";
                db.execSQL(sql);
                sql = "INSERT INTO dbUser(Username, PassWord, Tien) values ('admin','1','0')";
                db.execSQL(sql);
                db.close();
            }
        }catch (Exception ex){
            db.close();
            Toast.makeText(this, "Khởi tạo cơ sở dữ liệu không thành công!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public boolean isUser(String username, String password){
        try {
            db = openOrCreateDatabase(dtbase,MODE_PRIVATE,null);
            Cursor c = db.rawQuery("select * from dbUser where Username = ? and PassWord = ?", new String[]{username, password});
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
            Toast.makeText(this,"Bug nek", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        LoginUsername = findViewById(R.id.loginusernameEditText);
        LoginPassword = findViewById(R.id.loginpasswordEditText);
        LoginButton = findViewById(R.id.loginButton);
        regis = findViewById(R.id.Signup1TextView);
        initDB();
        regis.setAutoLinkMask(Linkify.WEB_URLS);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = LoginUsername.getText().toString();
                password = LoginPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Vui lòng điền thông tin tài khoản, mật khẩu", Toast.LENGTH_LONG).show();
                } else {
                    if (isUser(username, password)) {
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        sendMessage();
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                    }
                }
            }
            });
    }

    public void sendMessage() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        String message = LoginUsername.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);
    }

}

