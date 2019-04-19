package in.codecorp.ssgcp.shop.Fragments;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.codecorp.ssgcp.shop.Adapters.cartadapter;
import in.codecorp.ssgcp.shop.CartActivity;
import in.codecorp.ssgcp.shop.CheckoutActivity;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.Utils.cart;
import in.codecorp.ssgcp.shop.Utils.util;
import in.codecorp.ssgcp.shop.Utils.utils;

public class CartFragment extends Fragment {

    ArrayList<cart> cartlist;
    ContentResolver contentResolver;
    ListView linear;
    public double subtotal = 0;
    Thread thread;

   // Button checkout;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        linear=v.findViewById(R.id.l1);
      //  checkout = v.findViewById(R.id.btncot);
        contentResolver=getActivity().getContentResolver();
        retrive();
        return v;
    }



    public void retrive()
    {
        String[] cartaaray={utils.COL_ID,utils.COL_NAME,utils.COL_IMG,utils.COL_SALEPRICE};
        Cursor cursor=contentResolver.query(utils.USER_URI,cartaaray,null,null,null);
        cartlist=new ArrayList<>();
        if(cursor!=null){
            while(cursor.moveToNext())
            {
                try {


                    cartlist.add(new cart(cursor.getString(cursor.getColumnIndex(utils.COL_ID)),
                            cursor.getString(cursor.getColumnIndex(utils.COL_NAME)),
                            cursor.getString(cursor.getColumnIndex(utils.COL_IMG)),
                            cursor.getString(cursor.getColumnIndex(utils.COL_SALEPRICE))));
                    subtotal+= Double.parseDouble(cursor.getString(cursor.getColumnIndex(utils.COL_SALEPRICE)));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            LayoutInflater inflater = getActivity().getLayoutInflater();
            LinearLayout listFooterView = (LinearLayout)inflater.inflate(
                    R.layout.footer, null);
            final TextView amount = listFooterView.findViewById(R.id.subtotal);
            final Button checkout = listFooterView.findViewById(R.id.button);
            checkout.setText("Checkout");
            linear.addFooterView(listFooterView);
            cartadapter c = new cartadapter(getActivity(), R.layout.cart, cartlist);
            linear.setAdapter(c);
            util.subtotal = ""+ subtotal;

            thread = new Thread() {

                @Override
                public void run() {
                    try {
                        while (!thread.isInterrupted()) {
                            Thread.sleep(1000);
                            if(getActivity()!=null)
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    amount.setText(util.subtotal);
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                    }
                }
            };

            thread.start();
            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                    subtotal+=40.00;
                    util.subtotal = ""+ subtotal;
                    System.out.println("Subtotal is"+subtotal);
                    intent.putExtra("sub", util.subtotal);
                    startActivity(intent);
                }
            });
            //for (int i=0; i<cartlist.size(); i++){
            // View v = View.inflate(getApplicationContext(), R.layout.cart, null);
            //linear.addView(c.getView(i, v, null));
            //}




        }
        else {
            Toast.makeText(getActivity(), "bhhbbbhbh", Toast.LENGTH_SHORT).show();
        }
    }
}
