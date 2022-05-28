package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class NumberActivity extends AppCompatActivity implements View.OnClickListener {

    Button Choice1;
    EditText EditNumbers;
    Button GoMain;
    ImageView QRcodeIm;
    private String[] CodeCountry={"Алжир","Белоруссия","Египет","Казахстан","Китай","Россия","США"};
    EditText Code;
    Button GenerateQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        EditNumbers=(EditText) findViewById(R.id.editNumber);
        GenerateQR=(Button) findViewById(R.id.generate1);
        GenerateQR.setOnClickListener(this);
        init();
        Code=(EditText) findViewById(R.id.Code);
        Choice1=(Button) findViewById(R.id.Choice1);
        Choice1.setOnClickListener(this);
        GoMain=(Button) findViewById(R.id.generatorNumber);
        GoMain.setOnClickListener(this);
        QRcodeIm=(ImageView) findViewById(R.id.qr_code1);
        ArrayAdapter<String> Country=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CodeCountry);
        Country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spCountry=(Spinner) findViewById(R.id.ChCount);
        spCountry.setAdapter(Country);
        Code.setText("");
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (CodeCountry[position] == "Алжир"){
                    Code.setText("+21");
                }
                if (CodeCountry[position] == "Белоруссия"){
                    Code.setText("+375");
                }
                if (CodeCountry[position] == "Египет"){
                    Code.setText("+20");
                }
                if (CodeCountry[position] == "Казахстан"){
                    Code.setText("+7");
                }
                if (CodeCountry[position] == "Китай"){
                    Code.setText("+86");
                }
                if (CodeCountry[position] == "Россия"){
                    Code.setText("+7");
                }
                if (CodeCountry[position] == "США"){
                    Code.setText("+1");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.generatorNumber:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        switch (v.getId()) {
            case R.id.Choice1:
                Intent intent = new Intent(this, ChoiceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
    private void init(){
        Code=findViewById(R.id.Code);
        EditNumbers= findViewById(R.id.editNumber);
        QRcodeIm=findViewById(R.id.qr_code1);
        GenerateQR= findViewById(R.id.generate1);
        GenerateQR.setOnClickListener(new View.OnClickListener() {
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
        BitMatrix bitMatrix =multiFormatWriter.encode((Code.getText().toString()+EditNumbers.getText().toString()), BarcodeFormat.QR_CODE,350,300);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmapQrCode = barcodeEncoder.createBitmap(bitMatrix);
        QRcodeIm.setImageBitmap(bitmapQrCode);
    }
}