package com.example.my_closet;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        //첫번째 탭
       tabHost.addTab(tabHost.newTabSpec("Closet").setIndicator("옷장").setContent(new Intent(this,ClosetMain.class)));

        //두번째 탭
        tabHost.addTab(tabHost.newTabSpec("Style").setIndicator("코디").setContent(new Intent(this,Style.class)));


    }
}