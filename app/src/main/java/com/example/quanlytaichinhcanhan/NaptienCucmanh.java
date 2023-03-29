package com.example.quanlytaichinhcanhan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
    public static final String KEY_SHOW_WHAT = "show_what";
    int newTienValue;
    EditText nhaptien;
    Button rewrite, confirm;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhaptien);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String valueShow = bundle.getString(KEY_SHOW_WHAT, "");
        }
        nhaptien = findViewById(R.id.money);
        rewrite = findViewById(R.id.btnnhaplai);
        confirm = findViewById(R.id.btnConfirm);
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhaptien.setText("");
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tien = nhaptien.getText().toString();
                newTienValue = Integer.parseInt(tien);
                Intent intent = new Intent(NaptienCucmanh.this, MainActivity.class);
                intent.putExtra("key1",newTienValue);
                startActivity(intent);
            }
        });
    }


}
