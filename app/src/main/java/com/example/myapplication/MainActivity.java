package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button scanbtn;
    public static TextView scanText;
    Button main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanText=(TextView)findViewById(R.id.scanText);
        scanbtn=(Button)findViewById(R.id.scanbtn);
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), scannerView.class));
            }
        });
        main=(Button) findViewById(R.id.main);
        main.setOnClickListener(this);
    }
    


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main:
                Intent intent=new Intent(this, ChoiceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
