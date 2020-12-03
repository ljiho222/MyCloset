package com.example.my_closet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClosetMain extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private Button btn_add;
    private Button btn_search;
    private ImageButton btn_closet;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private User user;
    private ArrayList<Newcloset> Newclosets = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closetmain);

        viewPager=(ViewPager)findViewById(R.id.view);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);


        func();

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                func();
                swipeRefreshLayout.setRefreshing(false);
            }
        });






        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClosetMain.this, Add_Closet.class);
                intent.putExtra("userInfo", user);
                startActivity(intent); // 옷장 추가 인텐트
            }
        });
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClosetMain.this, Search.class);
                intent.putExtra("userInfo", user);
                startActivity(intent); // 옷 검색
            }
        });
    }
    private void func() {

        user = (User)getIntent().getSerializableExtra("userInfo");
        adapter = new ViewPagerAdapter(this, Newclosets, user);
        viewPager.setAdapter(adapter);

        databaseReference.child("Closets").child(user.getUserName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Newclosets.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Newcloset newcloset = snapshot.getValue(Newcloset.class);
                    Newclosets.add(newcloset);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}