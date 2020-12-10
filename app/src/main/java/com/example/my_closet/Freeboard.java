package com.example.my_closet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.androdocs.httprequest.HttpRequest.excuteGet;

public class Freeboard extends AppCompatActivity implements View.OnClickListener{
    String API = "7363e45fdc7cb5ea14d3049b8175c7d0";
    TextView addressTxt, status_mainTxt,tempTxt, plusTxt;
    ImageView img,simg1,simg2,simg3;

    //파이어베이스
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private SharedPreferences auto;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    //뷰
    private RecyclerView recyclerViewFreeBoard;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static Context context;
    public User user;
    private Intent intent;

    //리사이클러뷰에 필요한 것들
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Article> arrayList;
    private ArticleAdapter articleAdapter;
    private int thiscp,thisgp;
    public String mUniv="";

    private SeekBar seekBar;
    private TextView tempera;

    private boolean isFabOpen=false;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard);

        addressTxt = findViewById(R.id.address);//현재 위치
        status_mainTxt=findViewById(R.id.status_main);//맑음, 구름 표시
        tempTxt = findViewById(R.id.temp);//현재 온도

        img = findViewById(R.id.w_image);//날씨 아이콘

        plusTxt=findViewById(R.id.plus_txt); //오늘 날씨에 대한 코멘트

        seekBar = findViewById(R.id.seekBar);

        tempera = findViewById(R.id.tempera);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int temperature;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tempera.setText(String.valueOf(seekBar.getProgress()));
                temperature = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tempera.setText(String.valueOf(temperature));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempera.setText(String.valueOf(temperature));
            }
        });

        //AsyncTask는 execute()명령어를 통해 실행함.
        new Freeboard.weatherTask().execute();


        context = this;
        user=(User)getIntent().getSerializableExtra("userInfo");


        initPalette();
        func();


        fab=(FloatingActionButton)findViewById(R.id.mypage_fab);

        fab.setOnClickListener(this);

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



    }
    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
//제일 먼저 실행 이미지 로딩작업 등 스레드 작업 이전 수행할 동작 구현

        protected void onPreExecute() {
            super.onPreExecute();

            /* Showing the ProgressBar, Making the main design GONE */
// findViewById(R.id.loader).setVisibility(View.VISIBLE);
// findViewById(R.id.mainContainer).setVisibility(View.GONE);
// findViewById(R.id.errorText).setVisibility(View.GONE);
        }
        protected String doInBackground(String... args) {

            String response = excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + "Daegu" + "&units=metric&appid=" + API);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject main = jsonObj.getJSONObject("main");
                JSONObject sys = jsonObj.getJSONObject("sys");
                JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);


                //Date currentTime= Calendar.getInstance().getTime();
                //String updatedAtText = new SimpleDateFormat("yyyy/MM/dd hh:mm", Locale.getDefault()).format(currentTime);

//main object에 접근
                Integer temper = main.getInt("temp");


                String weatherMain = weather.getString("main");
                String weatherDescription = weather.getString("description");
                String address = jsonObj.getString("name") + ", " + sys.getString("country");

                if (weatherMain.equals("Clear")) {
                    img.setImageResource(R.drawable.img1);
                    weatherMain="맑음";
                    plusTxt.setText("오늘 하루 날씨 좋습니다!");
                }
                else if(weatherMain.equals("Clouds")){
                    if(weatherDescription.equals("few clouds") || weatherDescription.equals("scatterd clouds"))
                        img.setImageResource(R.drawable.img2);
                    else
                        img.setImageResource(R.drawable.img3);
                    weatherMain="구름";
                    plusTxt.setText("오늘은 다소 흐리네요ㅠㅠ");
                }
                else if(weatherMain.equals("Drizzle")){
                    img.setImageResource(R.drawable.img5);
                    weatherMain="약한 비";
                    plusTxt.setText("오늘은 비가 오니 우산 챙겨 나가세요!");

                }
                else if(weatherMain.equals("Rain")){
                    img.setImageResource(R.drawable.img6);
                    weatherMain="비";
                    plusTxt.setText("오늘은 비가 오니 우산 챙겨 나가세요!");

                }
                else if(weatherMain.equals("Thunderstorm")){
                    img.setImageResource(R.drawable.img7);
                    weatherDescription="천둥";
                    plusTxt.setText("오늘은 외출을 자제하는게 좋겠어요");

                }
                else if(weatherMain.equals("Snow")){
                    img.setImageResource(R.drawable.img8);
                    weatherMain="눈";
                    plusTxt.setText("오늘은 외출을 자제하는게 좋겠어요");

                }
                else {
                    img.setImageResource(R.drawable.img9);
                    plusTxt.setText("오늘은 외출을 자제하는게 좋겠어요");

                }

                /* Populating extracted data into our views */
                addressTxt.setText(address);
                status_mainTxt.setText(weatherMain);
                tempTxt.setText(temper+"도");
                tempera.setText(temper.toString());


                /* Views populated, Hiding the loader, Showing the main design */
                //findViewById(R.id.loader).setVisibility(View.GONE);
                //findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);


            } catch (JSONException e) {
            //findViewById(R.id.loader).setVisibility(View.GONE);
                // findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }
    }

    private void initPalette() {
        recyclerViewFreeBoard = (RecyclerView)findViewById(R.id.recyclerViewFreeBoard);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewFreeBoard.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_layout);
    }

    private void func() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("정보를 불러오는 중입니다");
        progressDialog.show();

        arrayList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(arrayList);
        recyclerViewFreeBoard.setAdapter(articleAdapter);

        databaseReference.child("Articles").child(mUniv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Article article = snapshot.getValue(Article.class);
                    arrayList.add(article);
                }
                Log.e("freeboardarraylist",""+arrayList.size());
                Collections.reverse(arrayList);
                articleAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch(view.getId()){
            case R.id.mypage_fab:
                FreeBoardshowDialog();
                break;
        }
    }


    public void FreeBoardshowDialog() {
        FreeBoardDialog freeBoardDialog = new FreeBoardDialog(this);
        freeBoardDialog.show();
    }

    class FreeBoardDialog extends Dialog {

        Button buttonComplete;

        public FreeBoardDialog(@NonNull Context context) {
            super(context);
            setContentView(R.layout.free_board_dialog);

            initPalette();
        }

        private void initPalette() {
            buttonComplete = (Button)findViewById(R.id.buttonComplete);
            buttonComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(getApplicationContext(), writeArticleActivity.class);
                    intent.putExtra("userInformation", user);
                    startActivity(intent);
                    dismiss();
                }
            });


        }
    }


}