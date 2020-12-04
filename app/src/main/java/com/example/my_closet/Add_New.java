package com.example.my_closet;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

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
    private int position1;
    private int position2;
    private int position3;
    private int position4;
    private String filepath;
    private InputStream in;
    private Uri selectImage;
    private Uri img_download;
    private Clothes clothes;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference("Image");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final Spinner spin1 = (Spinner)findViewById(R.id.color_spinner);//색상 선택 스피너
        final Spinner spin2 = (Spinner)findViewById(R.id.type_spinner);//카테고리 선택 스피너
        final Spinner spin3 = (Spinner)findViewById(R.id.sub_spinner);//옷 종류 선택 스피너
        final Spinner spin4 = (Spinner)findViewById(R.id.location_spinner);//위치 선택 스피너


        //(spin1).setTypeface(externalFont);

        type = (TextView)findViewById(R.id.type);
        plus_btn = findViewById(R.id.plus_btn);
        imgbtn=findViewById(R.id.imgbtn);

        user = (User)getIntent().getSerializableExtra("userInfo");
        Log.v("12345", user.getUserName());


        cls_style = getIntent().getIntExtra("cls_style", cls_style);
        cls_name = getIntent().getStringExtra("cls_name");
        Log.v("4235235", cls_name);


        //색상 스피너-독립적으로 작용
        adspin1=ArrayAdapter.createFromResource(this,R.array.color,android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position1=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });//어댑터 값들을 스피너에 넣기



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
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position4=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //카테고리 스피너->종류 스피너 영향 줌
        adspin2=ArrayAdapter.createFromResource(this,R.array.type,android.R.layout.simple_spinner_dropdown_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spin2.setAdapter(adspin2);//어댑터 값들을 스피너에 넣기
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                if(adspin2.getItem(i).equals("아우터")){
                    position2=i;
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.outer_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            position3=i;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });//아우터의 세부 종류 스피너
                }
                else if(adspin2.getItem(i).equals("상의")){
                    position2=i;
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.top_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            position3=i;
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                else if(adspin2.getItem(i).equals("하의")){
                    position2=i;
                    spin3.setVisibility(View.VISIBLE);
                    type.setVisibility(View.VISIBLE);
                    adspin3=ArrayAdapter.createFromResource(Add_New.this,R.array.bottom_type,android.R.layout.simple_spinner_dropdown_item);
                    spin3.setAdapter(adspin3);
                    spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            position3=i;

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
               //원피스,기타 선택시 스피너3(종류)에 아예 안뜨도록 구현하는 거는 나중에,,
                else{
                    position2=i;
                    spin3.setVisibility(View.GONE);
                    type.setVisibility(View.GONE);
                    position3=10;
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clothes = new Clothes(adspin1.getItem(position1).toString(), adspin2.getItem(position2).toString(), "", adspin4.getItem(position4).toString(), "", "");
                if(position3!=10)clothes.setType2(adspin3.getItem(position3).toString());
                else clothes.setType2("널");
                if(selectImage != null){
                    //업로드 과정
                    final StorageReference uploadRef =  storageReference.child("image"+selectImage.getLastPathSegment()+System.currentTimeMillis());
                    final UploadTask uploadTask =uploadRef.putFile(selectImage);
                    Task<Task<Uri>> uriTask = uploadTask.continueWith(new Continuation<UploadTask.TaskSnapshot,Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if(!task.isSuccessful()){
                                throw task.getException();
                            }

                            Log.d("tag","진입성공");
                            return uploadRef.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Task<Uri>>() {
                        @Override
                        public void onComplete(@NonNull Task<Task<Uri>> task) {
                            if(task.isSuccessful()){
                                //자료형 선언 두번 해서 getResult() 두번... ㅠㅠ
                                task.getResult().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Uri> task) {
                                        img_download = task.getResult();
                                        clothes.setUrl(img_download.toString());
                                        String key = databaseReference.push().getKey();
                                        clothes.setKey(key);
                                        databaseReference.child("Closets").child(user.getUserName()).child(cls_name).child("Clothes").child(key).setValue(clothes);
                                        finish();
                                    }
                                });


                            }
                        }
                    });


                }


            }
        });



        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode == RESULT_OK){
                selectImage = data.getData();
                Glide.with(this).load(selectImage).into(imgbtn);

                try{
                    InputStream in = getContentResolver().openInputStream(data.getData());


                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
}