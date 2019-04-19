package in.codecorp.ssgcp.shop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.codecorp.ssgcp.shop.ProductActivity;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.Utils.Category;


public class CategoryAdapter extends ArrayAdapter<Category> {
    Context cntx;
    int resource;
    ArrayList<Category> product1;
    ArrayList<Category> product2;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Category> objects) {
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

        final Category p = product1.get(position);


        TextView txt_sno = view.findViewById(R.id.textView);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cntx,ProductActivity.class);
                intent.putExtra("id",p.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                cntx.startActivity(intent);
            }
        });


        txt_sno.setText(p.getName());

        return view;

    }}
