package in.codecorp.ssgcp.shop;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import in.codecorp.ssgcp.shop.Utils.utils;

public class DetailsActivity extends AppCompatActivity {
    String id;
    String name;
    String descrip;
    String price;
    String regprice;
    String saleprice;
    Context cntx;
    String rating;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    ListView list;
    Button button;
    ImageView iv;
    String uid;
    SessionLogin sessionLogin;
    ContentResolver contentResolver;
    //data store locally
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
        sessionLogin = new SessionLogin(getApplicationContext());
        HashMap<String, String> user = sessionLogin.getUserDetails();
        uid = user.get(SessionLogin.KEY_PHONE);
        if(uid==null){
            Intent intent = new Intent(DetailsActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        id=getIntent().getExtras().getString("id");
        name=getIntent().getExtras().getString("name");
        descrip=getIntent().getExtras().getString("description");
        price=getIntent().getExtras().getString("price");
        Glide.with(getApplicationContext())
                .load(price)
                .into(iv);
        regprice=getIntent().getExtras().getString("regular_price");
        saleprice=getIntent().getExtras().getString("sale_price");
        rating=getIntent().getExtras().getString("rating");

        tv1.setText(id);
        tv2.setText(name);
        tv3.setText(Html.fromHtml(descrip));
        //iv.setI(price);
        tv5.setText(regprice);
        tv6.setText(saleprice);
        tv7.setText(rating);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }
    public void init()
    {
        tv1=findViewById(R.id.id);
        tv2=findViewById(R.id.name);
        tv3=findViewById(R.id.desc);
        iv=findViewById(R.id.price);
        tv5=findViewById(R.id.regprice);
        tv6=findViewById(R.id.saleprice);
        tv7=findViewById(R.id.rating);
       // list=findViewById(R.id.listview);
        button=findViewById(R.id.btn);
        contentResolver=getContentResolver();
    }
    public void insert()
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(utils.COL_ID, tv1.getText().toString().trim());
        contentValues.put(utils.COL_NAME, tv2.getText().toString().trim());
        contentValues.put(utils.COL_SALEPRICE, tv6.getText().toString().trim());
        contentValues.put(utils.COL_IMG, price.trim());
        contentValues.put(utils.COL_DESC, "1");
        contentValues.put(utils.COL_REGPRICE,  price);

        // contentValues.put(com.example.basleenkaur.shoppingcart.utils.COL_ID, tv1.getText().toString().trim());
        Uri uri=contentResolver.insert(utils.USER_URI,contentValues);
        //Toast.makeText(DetailsActivity.this, tv1.getText()+ uri.getLastPathSegment(), Toast.LENGTH_LONG).show();
        button.setText("Go To Cart");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}
