package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Hashtable;

public class activity_generator extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_code;
    Button generator;
    private Button save;
    private ImageView qrCode;
    Button Choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator2);
        generator=(Button) findViewById(R.id.generatorText);
        generator.setOnClickListener(this);
        init();
        qrCode=(ImageView) findViewById(R.id.qr_code);
        Choice=(Button) findViewById(R.id.Choice);
        Choice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.generatorText:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        switch (v.getId()){
            case R.id.Choice:
                Intent intent=new Intent(this, ChoiceActivity.class);
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
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix =multiFormatWriter.encode(edit_code.getText().toString(), BarcodeFormat.QR_CODE,350,300,hints);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmapQrCode = barcodeEncoder.createBitmap(bitMatrix);
        qrCode.setImageBitmap(bitmapQrCode);
    }
}
