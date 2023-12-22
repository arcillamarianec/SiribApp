package com.bacuyag.SiribApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signinn(View view){
        Intent i = new Intent(this, SignInActivity.class);
        startActivity(i);
    }
    public void signupp(View view){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void References(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
}