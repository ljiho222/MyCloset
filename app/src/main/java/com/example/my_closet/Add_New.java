package com.example.my_closet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Add_New extends AppCompatActivity {
    ArrayAdapter <CharSequence> adspin1,adspin2,adspin3;//어댑터 선언
    private Button button;
    private ImageButton imgbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Spinner spin1 = (Spinner)findViewById(R.id.color_spinner);//색상 선택 스피너
        final Spinner spin2 = (Spinner)findViewById(R.id.type_spinner);//카테고리 선택 스피너
        final Spinner spin3 = (Spinner)findViewById(R.id.sub_spinner);//옷 종류 선택 스피너

        //색상 스피너-독립적으로 작용
        adspin1=ArrayAdapter.createFromResource(this,R.array.color,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin1.setAdapter(adspin1);//어댑터 값들을 스피너에 넣기

        //카테고리 스피너->종류 스피너 영향 줌
        adspin2=ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin2.setAdapter(adspin2);//어댑터 값들을 스피너에 넣기
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adspin2.getItem(i).equals("아우터")){
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.outer_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);//아우터의 세부 종류 스피너
                }
                else if(adspin2.getItem(i).equals("상의")){
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.top_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                }
                else if(adspin2.getItem(i).equals("하의")){
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.bottom_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                }
               //원피스,기타 선택시 스피너3(종류)에 아예 안뜨도록 구현하는 거는 나중에,,

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}