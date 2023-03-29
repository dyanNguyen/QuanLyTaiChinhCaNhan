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
    int newTienValue;
    EditText nhaptien;
    Button rewrite, confirm;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhaptien);
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
                Intent intent = new Intent();
                intent.putExtra("key1",newTienValue);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }


}
