package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button GenText;
    Button GenNumber;
    Button GoStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        GenNumber=(Button) findViewById(R.id.gotoGenNumber);
        GenText=(Button) findViewById(R.id.gotoGenText);
        GoStart=(Button) findViewById(R.id.MainActivity);
        GenNumber.setOnClickListener(this);
        GenText.setOnClickListener(this);
        GoStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MainActivity:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        switch (v.getId()) {
            case R.id.gotoGenText:
                Intent intent = new Intent(this, activity_generator.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        switch (v.getId()) {
            case R.id.gotoGenNumber:
                Intent intent = new Intent(this, NumberActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}