package com.example.quanlytaichinhcanhan;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Locale;

public class TienTe extends Activity {
    public TextView textUSD, textCNY, textRUB, textJPY, textEUR, tien;
    public Button Click;
    public SQLiteDatabase db;
    public String Cusd, Cjpy, Ceur, Ccny, Crub, Ctien, Tienne;
    public int tienValue, tienIndex = -1;
    public double tienusd, tienjpy, tieneur, tiencny, tienrub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiente);
        textUSD = findViewById(R.id.currency1);
        textJPY = findViewById(R.id.currency2);
        textEUR = findViewById(R.id.currency3);
        textCNY = findViewById(R.id.currency4);
        textRUB = findViewById(R.id.currency5);
        tien = findViewById(R.id.tien);
        Click = findViewById(R.id.btnClick);
        Ctien = getIntent().getStringExtra("key2");
        Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TraTien(Ctien);
                tienusd=tienValue / 23490;
                tienjpy=tienValue * 0.00525;
                tieneur=0.92 * tienValue / 23490;
                tiencny=6.89 * tienValue / 23490;
                tienrub=77.03 * tienValue / 23490;
                Cusd = String.format(Locale.US, "$%,.2f", tienusd);
                Cjpy = String.format(Locale.US, "¥%,.2f", tienjpy);
                Ceur = String.format(Locale.US, "€%,.2f", tieneur);
                Ccny = String.format(Locale.US, "¥%,.2f", tiencny);
                Crub = String.format(Locale.US, "₽%,.2f", tienrub);
                textUSD.setText(Cusd);
                textJPY.setText(Cjpy);
                textEUR.setText(Ceur);
                textCNY.setText(Ccny);
                textRUB.setText(Crub);
            }
        });
    }

    public void TraTien(String username) {
        try {
            db = openOrCreateDatabase(Login.dtbase, MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT Tien FROM dbUser WHERE Username=?", new String[]{username});
            if (cursor.moveToFirst()) {
                tienIndex = cursor.getColumnIndex("Tien");
                tienValue = Integer.parseInt(cursor.getString( tienIndex));
                Tienne = getString(R.string.Tienne, tienValue);
                tien.setText(Tienne);
            }
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "lỗi đây", Toast.LENGTH_SHORT).show();
        }
    }
}

