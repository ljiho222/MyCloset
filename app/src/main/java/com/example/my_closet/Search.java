package com.example.my_closet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Search extends AppCompatActivity {
    ArrayAdapter <CharSequence> adspin1,adspin2;
    private Button search_btn;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private CharSequence color, type;
    private User user;
    private SearchAdapter adapter;
    private RecyclerView recyclerview;
    private LinearLayoutManager layoutManager;

    private ArrayList<SearchedData> arrayList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Spinner spin1 = (Spinner)findViewById(R.id.what_color);//색상 선택 스피너
        final Spinner spin2 = (Spinner)findViewById(R.id.what_type);//카테고리 선택 스피너
        search_btn=findViewById(R.id.search_btn);
        arrayList = new ArrayList<>();

        recyclerview = findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        adapter = new SearchAdapter(this, arrayList);
        recyclerview.setAdapter(adapter);
        user=(User)getIntent().getSerializableExtra("userInfo");

        //색상 스피너
        adspin1= ArrayAdapter.createFromResource(this,R.array.color,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin1.setAdapter(adspin1);//어댑터 값들을 스피너에 넣기

        //카테고리 스피너
        adspin2=ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin2.setAdapter(adspin2);//어댑터 값들을 스피너에 넣기

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.clear();
                color = spin1.getSelectedItem().toString();
                type = spin2.getSelectedItem().toString();

                databaseReference.child("Closets").child(user.getUserName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            String t1 = dataSnapshot1.getKey();
                            Log.e("!!!", dataSnapshot1.toString());
                            for(DataSnapshot dataSnapshot2 : dataSnapshot1.child("Clothes").getChildren()) {
                                String t2, t3;
                                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot2.getValue();
                                if(color.toString().equals(hashMap.get("color")) && type.toString().equals(hashMap.get("type1"))) {
                                    t2 = hashMap.get("style");
                                    t3 = hashMap.get("url");
                                    arrayList.add(new SearchedData(t1,t2,t3));
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                        for(SearchedData searchedData : arrayList) Log.e("?", searchedData.closet+","+searchedData.style+","+searchedData.url);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}