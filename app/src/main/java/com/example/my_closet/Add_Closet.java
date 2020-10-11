package com.example.my_closet;
import android.os.Bundle;
import android.util.Log;

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

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Newcloset> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_closet);

        recyclerView = findViewById(R.id.recyclerView);//아이디 연결
        recyclerView.setHasFixedSize(true);//성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//옷장 객체 담을 어레이 리스트(어댑터쪽으로)

        database = FirebaseDatabase.getInstance();//파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("Newcloset");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열 초기화
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {//반복문으로 데이터리스트를 추출해냄
                    Newcloset newcloset = snapshot.getValue(Newcloset.class);//만들어뒀던 뉴 클래스 객체에 데이터를 담는다.
                    arrayList.add(newcloset);//담을 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //데이터베이스 로드시 에러 발생할 경우
                Log.e("Add", String.valueOf(databaseError.toException()));//에러문 출력
            }
        });

        adapter = new ClosetAdater(arrayList,this);
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어댑터 연결

    }
}