package com.example.quanlytaichinhcanhan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NaptienCucmanh extends Activity {

    public SQLiteDatabase db;
    String username;
    int newTienValue;
    EditText nhaptien;
    Button rewrite, confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nhaptien = findViewById(R.id.money);
        rewrite = findViewById(R.id.btnnhaplai);
        confirm = findViewById(R.id.btnConfirm);
        String tien = nhaptien.getText().toString();
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhaptien.setText("");
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void insertMoney(String username, int newTienValue) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            String sql = "UPDATE dbUser SET Tien = ? WHERE Username = ?";
            db.execSQL(sql, new String[]{String.valueOf(newTienValue), username});
            db.close();
            Toast.makeText(this, "Nạp tiền thành công", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đây", Toast.LENGTH_SHORT).show();
        }
    }
}
