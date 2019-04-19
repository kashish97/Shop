package in.codecorp.ssgcp.shop.Adapters;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.codecorp.ssgcp.shop.CartActivity;
import in.codecorp.ssgcp.shop.DetailsActivity;
import in.codecorp.ssgcp.shop.Fragments.CartFragment;
import in.codecorp.ssgcp.shop.MainActivity;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.Utils.cart;
import in.codecorp.ssgcp.shop.Utils.util;
import in.codecorp.ssgcp.shop.Utils.utils;

/**
 * Created by NavdeepAhuja on 2018-12-21.
 */

public class cartadapter extends ArrayAdapter<cart>{
    public ContentResolver resolver;
    Context cntx1;
    int resource;
    ArrayList<cart> cart1;
    ArrayList<cart> cart2;
    int a=0;

    public cartadapter(@NonNull Context context, int resource, @NonNull ArrayList<cart> objects) {
        super(context, resource, objects);
        this.cntx1=context;
        this.resource=resource;
        cart1=objects;
        cart2=new ArrayList<>();
        cart2.addAll(cart1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        view = LayoutInflater.from(cntx1).inflate(resource, parent, false);

        final cart p = cart1.get(position);
        final CartActivity cartActivity = new CartActivity();
        TextView txt_name = view.findViewById(R.id.name1);

        ImageView txt_img = view.findViewById(R.id.img1);

        a =1;
        TextView txt_saleprice = view.findViewById(R.id.saleprice1);
        final TextView qty = view.findViewById(R.id.qty);
        final Button pls = view.findViewById(R.id.button3);
        Button sub = view.findViewById(R.id.button4);
        qty.setText(""+a);




        pls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ++a;
                qty.setText("" + a);
                resolver = getContext().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(utils.COL_ID, p.getId());
                contentValues.put(utils.COL_NAME, p.getName());
                contentValues.put(utils.COL_SALEPRICE, p.getSale_price());
                contentValues.put(utils.COL_IMG, p.getImg());
                contentValues.put(utils.COL_DESC, "" + a);
                contentValues.put(utils.COL_REGPRICE, "" + (a * Double.parseDouble(p.getSale_price())));

                String where = utils.COL_ID + " = " + p.getId();
                int i = resolver.update(utils.USER_URI, contentValues, where, null);
                double sub=Double.parseDouble(util.subtotal)+Double.parseDouble(p.getSale_price());
                util.subtotal = ""+ sub;
                //  cartActivity.re();
//                cartActivity.recreate();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(a>1) {
                --a;
                qty.setText("" + a);
                resolver = getContext().getContentResolver();
                ContentValues contentValues = new ContentValues();
                contentValues.put(utils.COL_ID, p.getId());
                contentValues.put(utils.COL_NAME, p.getName());
                contentValues.put(utils.COL_SALEPRICE, p.getSale_price());
                contentValues.put(utils.COL_IMG, p.getImg());
                contentValues.put(utils.COL_DESC, "" + a);
                contentValues.put(utils.COL_REGPRICE, "" + (a * Double.parseDouble(p.getSale_price())));
                String where = utils.COL_ID + " = " + p.getId();
                int i = resolver.update(utils.USER_URI, contentValues, where, null);
                if(Integer.parseInt(qty.getText().toString())>0){
                    double sub=Double.parseDouble(util.subtotal)-Double.parseDouble(p.getSale_price());
                    util.subtotal = ""+ sub;
                }
                if(qty.getText().toString().equalsIgnoreCase("0")){
                    remove(position);
                    System.out.println("position"+ position);
                    if(position==0){
                        Intent intent = new Intent(cntx1, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        cntx1.startActivity(intent);
                    }
                    int j = resolver.delete(utils.USER_URI,where, null );

                }
//
                // cartActivity.recreate();
                //  }
                //else if(a==0){
                //  remove(position);

                //}

            }
        });




        txt_name.setText(p.getName());

        Glide.with(cntx1)
                .load(p.getImg())
                .into(txt_img);
        //txt_price.setText(p.getPrice());

        txt_saleprice.setText(p.getSale_price());
        return view;

    }

    public void remove(int position){
        cart1.remove(cart1.get(position));;
    }


}