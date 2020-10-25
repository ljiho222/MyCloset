package com.example.my_closet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText id;
    private EditText pw;
    private EditText nickname;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);
        nickname = findViewById(R.id.nickname);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                User user = new User(id.getText().toString(), pw.getText().toString(), nickname.getText().toString());
                intent.putExtra("userInfo", user);
                startActivity(intent);
            }
        });
    }
}