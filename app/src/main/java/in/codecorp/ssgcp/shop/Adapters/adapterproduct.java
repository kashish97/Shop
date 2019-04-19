package in.codecorp.ssgcp.shop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.codecorp.ssgcp.shop.DetailsActivity;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.Utils.product;

/**
 * Created by basleenkaur on 2018-12-20.
 */

public class adapterproduct extends ArrayAdapter<product> {
Context cntx;
int resource;
ArrayList<product> product1;
ArrayList<product> product2;

    public adapterproduct(@NonNull Context context, int resource, @NonNull ArrayList<product> objects) {
        super(context, resource, objects);
        this.cntx=context;
        this.resource=resource;
        product1=objects;
        product2=new ArrayList<>();
        product2.addAll(product1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        view = LayoutInflater.from(cntx).inflate(resource, parent, false);

        final product p = product1.get(position);


        TextView txt_sno = view.findViewById(R.id.id);
        TextView txt_name = view.findViewById(R.id.name);
       // TextView txt_desc = view.findViewById(R.id.desc);
        ImageView txt_img = view.findViewById(R.id.img);
        TextView txt_regprice = view.findViewById(R.id.regprice);
        TextView txt_saleprice = view.findViewById(R.id.saleprice);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cntx,DetailsActivity.class);
                intent.putExtra("id",p.getId());
                intent.putExtra("name",p.getName());
                intent.putExtra("description",p.getDesc());
                intent.putExtra("price",p.getPrice());
                intent.putExtra("regular_price",p.getRegprice());
                intent.putExtra("sale_price",p.getSaleprice());
                intent.putExtra("rating",p.getRating());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntx.startActivity(intent);
            }
        });


        txt_sno.setText(p.getId());
        txt_name.setText(p.getName());
      //  txt_desc.setText(Html.fromHtml(p.getDesc()));
        Glide.with(cntx)
                .load(p.getPrice())
                .into(txt_img);
        //txt_price.setText(p.getPrice());
        txt_regprice.setText(p.getRegprice());
        txt_saleprice.setText(p.getSaleprice());
        return view;

    }}
