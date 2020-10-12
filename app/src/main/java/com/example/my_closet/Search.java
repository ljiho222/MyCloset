package com.example.my_closet;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
    ArrayAdapter <CharSequence> adspin1,adspin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Spinner spin1 = (Spinner)findViewById(R.id.what_color);//색상 선택 스피너
        final Spinner spin2 = (Spinner)findViewById(R.id.what_type);//카테고리 선택 스피너

        //색상 스피너
        adspin1= ArrayAdapter.createFromResource(this,R.array.color,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin1.setAdapter(adspin1);//어댑터 값들을 스피너에 넣기

        //카테고리 스피너
        adspin2=ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin2.setAdapter(adspin2);//어댑터 값들을 스피너에 넣기
    }
}