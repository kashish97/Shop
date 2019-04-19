package in.codecorp.ssgcp.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import in.codecorp.ssgcp.shop.Payment.MainActivity;

public class CheckoutActivity extends AppCompatActivity {

    RadioGroup radiogrp;
    RadioButton r1,r2,r3;
    TextView t1,t2,t3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        System.out.println("Subtotal1 is"+getIntent().getStringExtra("sub"));

        init();
        final Intent rcv = getIntent();
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio1Clicked();
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio2Clicked();
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio3Clicked();
            }
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentpay=new Intent(CheckoutActivity.this,MainActivity.class);
                intentpay.putExtra("sub", rcv.getStringExtra("sub"));
                startActivity(intentpay);

            }
        });
    }
    public void init()
    {
        radiogrp=findViewById(R.id.radiocheck);
        r1=findViewById(R.id.rb1);
        r2=findViewById(R.id.rb2);
        r3=findViewById(R.id.rb3);
        t1=findViewById(R.id.tv1);
        t2=findViewById(R.id.tv2);
        t3=findViewById(R.id.tv3);
        b1=findViewById(R.id.btn1);
    }
    public void radio1Clicked()
    {
        // Note that I have unchecked  radiobuttons except the one
        // which is clicked/checked by user
        r2.setChecked(false);
        r3.setChecked(false);
    }
    public void radio2Clicked()
    {
        // Note that I have unchecked  radiobuttons except the one
        // which is clicked/checked by user
        r1.setChecked(false);
        r3.setChecked(false);
    }
    public void radio3Clicked()
    {
        // Note that I have unchecked  radiobuttons except the one
        // which is clicked/checked by user
        r1.setChecked(false);
        r2.setChecked(false);
    }
}
