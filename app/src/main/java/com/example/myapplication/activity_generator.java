package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;

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
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+ "/Camera";
        FileOutputStream outputStream;
        File file =Environment.getExternalStorageDirectory();
        File dir=new File(root);
        dir.mkdirs();
        String filename = String.format("%d.png",System.currentTimeMillis());
        File outFile=new File(dir,filename);
        System.out.println(outFile.getAbsolutePath());
        if (file.exists()) file.delete();
        Log.i("LOAD", root + filename);
        try {
            outputStream = new FileOutputStream(outFile);
            if (bitmapQrCode != null) {
            bitmapQrCode.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
