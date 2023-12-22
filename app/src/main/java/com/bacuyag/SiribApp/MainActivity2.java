package com.bacuyag.SiribApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void layuninnn(View view){
        Intent i = new Intent(this, LayuninActivity.class);
        startActivity(i);
    }
    public void aboutusss(View view){
        Intent i = new Intent(this, AboutUsActivity.class);
        startActivity(i);
    }
    public void maglaroo(View view){
        Intent i = new Intent(this, Questions.class);
        startActivity(i);
    }
}