package com.example.my_closet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Closet extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private LinearLayout linearLayout;
    private int cls_style;
    private TextView cls_name;
    private Button camera;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);


        cls_name=(TextView)findViewById(R.id.cls_name);//사용자가 입력한 옷장 이름
        camera=(Button)findViewById(R.id.camera);//옷 입력을 위함.

        cls_name.setText(getIntent().getStringExtra("cls_name"));

        cls_style = getIntent().getIntExtra("cls_style", cls_style);
        user = (User)getIntent().getSerializableExtra("userInfo");

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Closet.this, Add_New.class);
                intent.putExtra("userInfo", user);
                intent.putExtra("cls_style", cls_style);
                intent.putExtra("cls_name", cls_name.getText().toString());
                startActivity(intent);
            }
        });


    }
}