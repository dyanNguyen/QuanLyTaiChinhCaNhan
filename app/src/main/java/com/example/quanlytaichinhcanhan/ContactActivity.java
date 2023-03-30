package com.example.quanlytaichinhcanhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ContactActivity extends AppCompatActivity {

    public ImageButton imgbtn,facebookLink,githubLink;
    public ImageView backicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        facebookLink = findViewById(R.id.facebook_link);
        githubLink = findViewById(R.id.github_link);
        imgbtn = findViewById(R.id.dis);
        backicon = findViewById(R.id.backicon);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ContactActivity.this, MenuAdapter.class);
//                startActivity(intent);
                finish();
            }
        });
        facebookLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở liên kết Facebook
                Uri uri = Uri.parse("https://www.facebook.com/profile.php?id=100006771705823");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở liên kết Discord
                Uri uri = Uri.parse("https://discord.gg/eJwcgEcvXx");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở liên kết Discord
                Uri uri = Uri.parse("https://github.com/MaiThanhNam141");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
