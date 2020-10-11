package com.example.my_closet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ClosetMain extends AppCompatActivity {

    private Button btn_add;
    private ImageButton btn_closet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closetmain);

        btn_closet = findViewById(R.id.btn_closet);
        btn_closet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClosetMain.this, Closet.class);
                startActivity(intent); // 옷장 추가 인텐트
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClosetMain.this, Add_Closet.class);
                startActivity(intent); // 옷장 추가 인텐트
            }
        });

    }
}