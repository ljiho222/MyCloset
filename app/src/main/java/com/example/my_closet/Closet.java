package com.example.my_closet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;

public class Closet extends AppCompatActivity {
    private int cls_style;
    private TextView cls_name;
    private Button camera;
    private User user;
    private Spinner cls_spinner;
    ArrayAdapter <CharSequence> adspin;
    private ArrayList<Clothes> Clothess = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private ClothesAdapter adapter;
    private RecyclerView recyclerview;
    private GridLayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);


        cls_name=(TextView)findViewById(R.id.cls_name);//사용자가 입력한 옷장 이름
        camera=(Button)findViewById(R.id.camera);//옷 입력을 위함.
        cls_spinner=findViewById(R.id.cls_spinner);

        cls_name.setText(getIntent().getStringExtra("cls_name"));

        cls_style = getIntent().getIntExtra("cls_style", cls_style);
        user = (User)getIntent().getSerializableExtra("userInfo");

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,3);
        recyclerview.setLayoutManager(layoutManager);
        adapter = new ClothesAdapter(this, Clothess);
        recyclerview.setAdapter(adapter);

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

        if(cls_style==1){
            adspin= ArrayAdapter.createFromResource(this,R.array.cls_1,android.R.layout.simple_spinner_dropdown_item);
        }
        else if(cls_style==2){
            adspin= ArrayAdapter.createFromResource(this,R.array.cls_2,android.R.layout.simple_spinner_dropdown_item);
        }
        else if(cls_style==3){
            adspin= ArrayAdapter.createFromResource(this,R.array.cls_3,android.R.layout.simple_spinner_dropdown_item);
        }
        else if(cls_style==4){
            adspin= ArrayAdapter.createFromResource(this,R.array.cls_4,android.R.layout.simple_spinner_dropdown_item);
        }
        else if(cls_style==5){
            adspin= ArrayAdapter.createFromResource(this,R.array.cls_5,android.R.layout.simple_spinner_dropdown_item);
        }
        adspin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        cls_spinner.setAdapter(adspin);
        cls_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(adspin.getItem(i).equals("전체")){
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("행거1")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("행거1"))
                                   Clothess.add(clothes);
                           }
                          // Collections.sort(Clothess);
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("행거2")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("행거2"))
                                   Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("서랍1")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("서랍1"))
                                   Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("서랍2")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("서랍2"))
                                   Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("서랍3")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("서랍3"))
                                   Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
               else if(adspin.getItem(i).equals("서랍4")) {
                   databaseReference.child("Closets").child(user.getUserName()).child(cls_name.getText().toString()).child("Clothes").addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                           Clothess.clear();
                           for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                               Clothes clothes = snapshot.getValue(Clothes.class);
                               if(clothes.getStyle().equals("서랍4"))
                                   Clothess.add(clothes);
                           }
                           adapter.notifyDataSetChanged();
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError databaseError) {

                       }
                   });
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//어댑터 값들을 스피너에 넣기




    }
}