package com.example.s2_2023_2024_2;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
     String imp1=null;

    private EditText edit;
    private TextView ask, binary, decimal, octal, hexa, binaryAns, decimalAns, octalAns, haxaAns;

    private RadioGroup Group;
    private RadioButton bi, dec, oct, hex;
    private int previousSelectedId = -1;


    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Brownsh)));
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Numeric systems converter");
        bar.setHomeAsUpIndicator(R.drawable.up);



        edit = findViewById(R.id.edit);
        ask = findViewById(R.id.ask);

        binaryAns = findViewById(R.id.BinaryAns);
        decimalAns = findViewById(R.id.DecimalAns);
        octalAns = findViewById(R.id.OctalAns);
        haxaAns = findViewById(R.id.HexaAns);

        Group = findViewById(R.id.radioGroup);
        bi = findViewById(R.id.BinaryR);
        dec = findViewById(R.id.DecimalR);
        oct = findViewById(R.id.OctalR);
        hex = findViewById(R.id.HexaR);

        bi.setOnClickListener(this);
        dec.setOnClickListener(this);
        oct.setOnClickListener(this);
        hex.setOnClickListener(this);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


               if(bi.isChecked()) {
                   bi.callOnClick();
                }
                else if (dec.isChecked()) {
                    dec.callOnClick();
               }
                else if(oct.isChecked()) {
                    oct.callOnClick();
                }
                else if(hex.isChecked()) {
                   hex.callOnClick();
                }
            }

        });
        Group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (previousSelectedId != -1) {
                    RadioButton previousRadioButton = findViewById(previousSelectedId);
                    RadioButton CurrentRadioButton = findViewById(checkedId);
                    String previousText = previousRadioButton.getText().toString();
                    String currentText = CurrentRadioButton.getText().toString();
                    imp1=currentText;

                    if (imp1.equals("Binary")&&!(edit.getText().toString().isEmpty())){
                        String s=binaryAns.getText().toString();
                        edit.setText(s);
                    }
                    else if(imp1.equals("Decimal")&&!(edit.getText().toString().isEmpty())){
                        String s=decimalAns.getText().toString();
                        edit.setText(s);
                    }
                    else if(imp1.equals("Octal")&&!(edit.getText().toString().isEmpty())){
                        String s=octalAns.getText().toString();
                        edit.setText(s);
                    }
                    else if(imp1.equals("Hexa")&&!(edit.getText().toString().isEmpty())){
                        String s=haxaAns.getText().toString();
                        edit.setText(s);
                    }


                }

                previousSelectedId = checkedId;
            }
        });
    }


    @SuppressLint({"SetTextI18n", "ResourceType", "SuspiciousIndentation"})
    @Override

    public void onClick(View v) {

        if (v == bi) {

            edit.setKeyListener(DigitsKeyListener.getInstance("01"));
               String s = edit.getText().toString();
                if (s.isEmpty())
                    return;

                else {

                    long decimalAnswer = Long.parseLong(s, 2);
                    long n = Long.parseLong(s);


                    String decAns = String.valueOf(decimalAnswer);
                    decimalAns.setText(decAns);


                    long temp = decimalAnswer;
                    StringBuilder octalAnswer = new StringBuilder();

                    while (temp != 0) {
                        long remainder = temp % 8;
                        octalAnswer.insert(0, remainder);
                        temp = temp / 8;
                    }
                    octalAns.setText(octalAnswer);

                    long temp1 = decimalAnswer;
                    StringBuilder hexAnswer = new StringBuilder();
                    char[] hexDigits = "0123456789ABCDEF".toCharArray();

                    while (temp1 != 0) {
                        int remainder = (int) (temp1 % 16);
                        hexAnswer.insert(0, hexDigits[remainder]);
                        temp1 = temp1 / 16;
                    }

                    haxaAns.setText(hexAnswer);

                    String biAns = String.valueOf(n);
                    binaryAns.setText(biAns);

            }



        }
        else if (v == dec) {

            edit.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
            String s = edit.getText().toString();
            if (s.isEmpty())
                return;

            else {

                long n = Long.parseLong(s);
                long temp = n;
                StringBuilder binaryAnswer = new StringBuilder();

                while (temp != 0) {
                    int remainder = (int) (temp % 2);
                    binaryAnswer.insert(0, remainder);
                    temp = temp / 2;
                }
                binaryAns.setText(binaryAnswer);

                long temp1 = n;
                StringBuilder octalAnswer = new StringBuilder();

                while (temp1 != 0) {
                    int remainder = (int) (temp1 % 8);
                    octalAnswer.insert(0, remainder);
                    temp1 = temp1 / 8;
                }
                octalAns.setText(octalAnswer);

                long temp2 = n;
                StringBuilder hexAnswer = new StringBuilder();
                char[] hexDigits = "0123456789ABCDEF".toCharArray();

                while (temp2 != 0) {
                    int remainder = (int) (temp2 % 16);
                    hexAnswer.insert(0, hexDigits[remainder]);
                    temp2 = temp2 / 16;
                }

                haxaAns.setText(hexAnswer);
                String decAns=String.valueOf(n);

                decimalAns.setText(decAns);


            }
        } else if (v == oct) {

            edit.setKeyListener(DigitsKeyListener.getInstance("01234567"));
            String s = edit.getText().toString();

            if (s.isEmpty())
                return;

            else {
                long n = Long.parseLong(s);

                long decimalAnswer = Long.parseLong(s, 8);
                String decAns=String.valueOf(decimalAnswer);
                decimalAns.setText(decAns);

                int temp = (int) decimalAnswer;
                StringBuilder hexAnswer = new StringBuilder();
                char[] hexDigits = "0123456789ABCDEF".toCharArray();

                while (temp != 0) {
                    int remainder = temp % 16;
                    hexAnswer.insert(0, hexDigits[remainder]);
                    temp = temp / 16;
                }
                haxaAns.setText(hexAnswer);

                long temp1 = decimalAnswer;
                StringBuilder binaryAnswer = new StringBuilder();

                while (temp1 != 0) {
                    int remainder = (int) (temp1 % 2);
                    binaryAnswer.insert(0, remainder);
                    temp1 = temp1 / 2;
                }
                binaryAns.setText(binaryAnswer);

                octalAns.setText(s);


            }
        } else if (v == hex) {

            edit.setKeyListener(DigitsKeyListener.getInstance("0123456789ABCDEF"));
            String s = edit.getText().toString();
            if (s.isEmpty())
                return;

            else {

                long decimalAnswer = Long.parseLong(s, 16);
                String decAns=String.valueOf(decimalAnswer);
                decimalAns.setText(decAns);

                long temp = decimalAnswer;
                StringBuilder binaryAnswer = new StringBuilder();

                while (temp != 0) {
                    int remainder = (int) (temp % 2);
                    binaryAnswer.insert(0, remainder);
                    temp = temp / 2;
                }
                binaryAns.setText(binaryAnswer);

                long temp1 = decimalAnswer;
                StringBuilder octalAnswer = new StringBuilder();

                while (temp1 != 0) {
                    long remainder = temp1 % 8;
                    octalAnswer.insert(0, remainder);
                    temp1 = temp1 / 8;
                }
                octalAns.setText(octalAnswer);

                haxaAns.setText(s);

            }
        }

    }
}
