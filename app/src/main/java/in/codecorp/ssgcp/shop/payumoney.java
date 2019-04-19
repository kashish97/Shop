package in.codecorp.ssgcp.shop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class payumoney extends AppCompatActivity {
    EditText fname, pnumber, emailAddress, rechargeAmt;
    Button Paynow;


    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payumoney);
        fname        = (EditText)findViewById(R.id.fname);
        pnumber      = (EditText)findViewById(R.id.pnumber);
        emailAddress = (EditText)findViewById(R.id.emailAddress);
        rechargeAmt  = (EditText)findViewById(R.id.rechargeAmt);
        Paynow       = (Button)findViewById(R.id.Paynow);

        Paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstname=findViewById(R.id.fname);
                EditText number=findViewById(R.id.pnumber);
                EditText emiladdress=findViewById(R.id.emailAddress);
                EditText rechargeamt=findViewById(R.id.rechargeAmt);
                firstname.setText(""+"Arun");
                number.setText("98898888787");
                emiladdress.setText("arun@gmail.com");
                rechargeamt.setText("10");

                Intent intent = new Intent(getApplicationContext(), Paymentgateway.class);
                startActivity(intent);
              /*  intent.putExtra("FIRST_NAME",getFname);
                intent.putExtra("PHONE_NUMBER",getPhone);
                intent.putExtra("EMAIL_ADDRESS",getEmail);
                intent.putExtra("RECHARGE_AMT",getAmt);
                startActivity(intent);*/
            }
        });

    }

}