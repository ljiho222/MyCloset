package com.example.my_closet;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_New extends AppCompatActivity {
    ArrayAdapter <CharSequence> adspin1,adspin2,adspin3,adspin4;//어댑터 선언
    private Button plus_btn;
    private ImageButton imgbtn;
    private User user;
    private TextView type;
    private int cls_style;
    private String cls_name;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Spinner spin1 = (Spinner)findViewById(R.id.color_spinner);//색상 선택 스피너
        final Spinner spin2 = (Spinner)findViewById(R.id.type_spinner);//카테고리 선택 스피너
        final Spinner spin3 = (Spinner)findViewById(R.id.sub_spinner);//옷 종류 선택 스피너
        final Spinner spin4 = (Spinner)findViewById(R.id.location_spinner);//위치 선택 스피너

        type = (TextView)findViewById(R.id.type);
        plus_btn = findViewById(R.id.plus_btn);

        user = (User)getIntent().getSerializableExtra("userInfo");
        cls_style = getIntent().getIntExtra("cls_style", cls_style);
        cls_name = getIntent().getStringExtra("cls_name");


        //색상 스피너-독립적으로 작용
        adspin1=ArrayAdapter.createFromResource(this,R.array.color,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin1.setAdapter(adspin1);//어댑터 값들을 스피너에 넣기

       if(cls_style==1){
           adspin4=ArrayAdapter.createFromResource(this,R.array.cls1,android.R.layout.simple_spinner_dropdown_item);

       }
       else if(cls_style==2){
           adspin4=ArrayAdapter.createFromResource(this,R.array.cls2,android.R.layout.simple_spinner_dropdown_item);

        }
       else if(cls_style==3){
           adspin4=ArrayAdapter.createFromResource(this,R.array.cls3,android.R.layout.simple_spinner_dropdown_item);

       }
       else if(cls_style==4){
           adspin4=ArrayAdapter.createFromResource(this,R.array.cls4,android.R.layout.simple_spinner_dropdown_item);

       }
       else if(cls_style==5){
           adspin4=ArrayAdapter.createFromResource(this,R.array.cls5,android.R.layout.simple_spinner_dropdown_item);

       }
        adspin4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin4.setAdapter(adspin4);//어댑터 값들을 스피너에 넣기

        //카테고리 스피너->종류 스피너 영향 줌
        adspin2=ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin2.setAdapter(adspin2);//어댑터 값들을 스피너에 넣기
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                if(adspin2.getItem(i).equals("아우터")){
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.outer_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);//아우터의 세부 종류 스피너
                }
                else if(adspin2.getItem(i).equals("상의")){
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.top_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                }
                else if(adspin2.getItem(i).equals("하의")){
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.bottom_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                }
               //원피스,기타 선택시 스피너3(종류)에 아예 안뜨도록 구현하는 거는 나중에,,
                else{
                    spin3.setVisibility(View.GONE);
                    type.setVisibility(View.GONE);
                }

                plus_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Clothes clothes = new Clothes(adspin1.getItem(i).toString(), adspin2.getItem(i).toString(), adspin3.getItem(i).toString(), getResources().getDrawable(R.drawable.closet_illust1));
                        //databaseReference.child("Closets").child(user.getUserName()).child(cls_name).push().setValue(clothes);
                        finish();
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
}