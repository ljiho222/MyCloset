package com.example.my_closet;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Add_Closet extends AppCompatActivity {


    private ArrayList<Newcloset> arrayList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private User user;

    private EditText closet_name;
    private RadioGroup radioGroup;
    private RadioButton cls1;
    private RadioButton cls2;
    private RadioButton cls3;
    private RadioButton cls4;
    private RadioButton cls5;
    private Button addcloset;
    private int clstype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_closet);

        closet_name = findViewById(R.id.closet_name);
        cls1 = findViewById(R.id.cls1);
        cls2 = findViewById(R.id.cls2);
        cls3 = findViewById(R.id.cls3);
        cls4 = findViewById(R.id.cls4);
        cls5 = findViewById(R.id.cls5);
        radioGroup=findViewById(R.id.radio_cls);
        addcloset=findViewById(R.id.addcloset);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.cls1){
                    clstype=1;
                }
                else if(i==R.id.cls2){
                    clstype=2;
                }
                else if(i==R.id.cls3){
                    clstype=3;
                }
                else if(i==R.id.cls4){
                    clstype=4;
                }
                else if(i==R.id.cls5){
                    clstype=5;
                }
            }
        });

        user = (User)getIntent().getSerializableExtra("userInfo");

        addcloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Newcloset newcloset = new Newcloset(closet_name.getText().toString(), clstype);
                databaseReference.child("Closets").child(user.getUserName()).child(newcloset.getName()).setValue(newcloset);
                finish();
            }
        });
    }
}