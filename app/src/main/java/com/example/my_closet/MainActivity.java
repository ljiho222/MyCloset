package com.example.my_closet;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        user = (User)this.getIntent().getSerializableExtra("userInfo");

        //첫번째 탭
       tabHost.addTab(tabHost.newTabSpec("Closet").setIndicator("CLOSET").setContent(new Intent(this,ClosetMain.class).putExtra("userInfo", user)));

        //두번째 탭
        tabHost.addTab(tabHost.newTabSpec("Style").setIndicator("STYLING").setContent(new Intent(this,Style.class)));


    }
}