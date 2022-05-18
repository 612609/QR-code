package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.SharedValues;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class activity_generator extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_code;
    Button generator;
    private Button save;
    private ImageView qrCode;
    Button SaveCode;
    private Bitmap bitmapQrCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator2);
        generator=(Button) findViewById(R.id.generator);
        generator.setOnClickListener(this);
        init();
        qrCode=(ImageView) findViewById(R.id.qr_code);
        SaveCode=(Button) findViewById(R.id.SaveCode);
        ActivityCompat.requestPermissions(activity_generator.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(activity_generator.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        SaveCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToGallery();
            }
        });
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
        Bitmap bitmapQrCode = barcodeEncoder.createBitmap(bitMatrix);
        qrCode.setImageBitmap(bitmapQrCode);
    }
    private void SaveToGallery(){
        FileOutputStream outputStream= null;
        File file =Environment.getExternalStorageDirectory();
        File dir=new File(file.getAbsolutePath()+"/MyPics");
        dir.mkdirs();
        String filename = String.format("%d.png",System.currentTimeMillis());
        File outFile=new File(dir,filename);
        try {
            outputStream = new FileOutputStream(outFile);
            if (bitmapQrCode != null) {
                bitmapQrCode.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            } else {
                // show toast!!
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
