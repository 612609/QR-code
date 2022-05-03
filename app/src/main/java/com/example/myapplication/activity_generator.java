package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class activity_generator extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_code;
    Button generator;
    private Button save;
    private ImageView qrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator2);
        generator=(Button) findViewById(R.id.generator);
        generator.setOnClickListener(this);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.generator:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    private void init(){
        edit_code=findViewById(R.id.edit_code);
        qrCode=findViewById(R.id.qr_code);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
    }
    private void getCode() {
        try {
            qrcode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    MultiFormatWriter multiFormatWriter= new MultiFormatWriter();
    public void qrcode() throws WriterException {
        BitMatrix bitMatrix =multiFormatWriter.encode(edit_code.getText().toString(), BarcodeFormat.QR_CODE,350,300);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        qrCode.setImageBitmap(bitmap);
    }
}