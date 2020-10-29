package com.example.my_closet;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.androdocs.httprequest.HttpRequest.*;

public class Style extends AppCompatActivity {
    String API = "7363e45fdc7cb5ea14d3049b8175c7d0";
    TextView addressTxt, status_mainTxt,tempTxt, plusTxt;
    ImageView img,simg1,simg2,simg3;
    private ArrayList<Drawable> styling=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);


        addressTxt = findViewById(R.id.address);//현재 위치
        status_mainTxt=findViewById(R.id.status_main);//맑음, 구름 표시
        tempTxt = findViewById(R.id.temp);//현재 온도

        img = findViewById(R.id.w_image);//날씨 아이콘
        simg1=findViewById(R.id.simg1);
        simg2=findViewById(R.id.simg2);
        simg3=findViewById(R.id.simg3);

        plusTxt=findViewById(R.id.plus_txt); //오늘 날씨에 대한 코멘트

//AsyncTask는 execute()명령어를 통해 실행함.
        new weatherTask().execute();
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
                Integer temp = main.getInt("temp");
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

//기온에 따라 옷차림 추천
                if(temp>=28){
                    simg1.setImageResource(R.drawable.covernat_shirt);
                    simg2.setImageResource(R.drawable.beige_short);
                    simg3.setImageResource(R.drawable.slide_black);

                }
                else if(temp<28 && temp>=20){
                    simg1.setImageResource(R.drawable.stripe_long_sleeve);
                    simg2.setImageResource(R.drawable.cotton_pant);
                    simg3.setImageResource(R.drawable.sneakers);
                }
                else if(temp<20 && temp>=17){
                    simg1.setImageResource(R.drawable.black_cardigan);
                    simg2.setImageResource(R.drawable.white_shirt);
                    simg3.setImageResource(R.drawable.denim_pant);
                }
                else if(temp<17 && temp>=12){
                    simg1.setImageResource(R.drawable.trench_coat);
                    simg2.setImageResource(R.drawable.cream_knit);
                    simg3.setImageResource(R.drawable.black_slacks);
                }
                else if(temp<12 && temp>=9){
                    simg1.setImageResource(R.drawable.rider_jacket);
                    simg2.setImageResource(R.drawable.cream_sweatshirt);
                    simg3.setImageResource(R.drawable.black_denim);
                }
                else if(temp<9 && temp>=5){
                    simg1.setImageResource(R.drawable.hoodie_zipup);
                    simg2.setImageResource(R.drawable.stripe_shirt);
                    simg3.setImageResource(R.drawable.training_pant);
                }
                else if(temp<5){
                    simg1.setImageResource(R.drawable.puffa_padding);
                    simg2.setImageResource(R.drawable.navy_sweatshirt);
                    simg3.setImageResource(R.drawable.wide_denim);
                }

                /* Populating extracted data into our views */
                addressTxt.setText(address);
                status_mainTxt.setText(weatherMain);
                tempTxt.setText(temper+"도");



                /* Views populated, Hiding the loader, Showing the main design */
                //findViewById(R.id.loader).setVisibility(View.GONE);
                //findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);


            } catch (JSONException e) {
//findViewById(R.id.loader).setVisibility(View.GONE);
//findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }
    }
}