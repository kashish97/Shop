package in.codecorp.ssgcp.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.codecorp.ssgcp.shop.Adapters.adapterproduct;
import in.codecorp.ssgcp.shop.Fragments.HomeFragment;
import in.codecorp.ssgcp.shop.Utils.product;
import in.codecorp.ssgcp.shop.Utils.util;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class ProductActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    Spinner sp1;
    ArrayList<product> id;
    GridViewWithHeaderAndFooter lv1;
    int current_page = 1;
    String cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        //tv1=findViewById(R.id.textshop);
        //sp1=findViewById(R.id.listspinner);
        lv1=findViewById(R.id.lv);
        BannerSlider bannerSlider= (BannerSlider) findViewById(R.id.banner_slider1);
        List<Banner> banners=new ArrayList<>();
        //add banner using image url
        //banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.mipmap.slide1));
        banners.add(new DrawableBanner(R.mipmap.slide2));
        banners.add(new DrawableBanner(R.mipmap.slide3));
        banners.add(new DrawableBanner(R.mipmap.slide4));
        banners.add(new DrawableBanner(R.mipmap.slide5));
        banners.add(new DrawableBanner(R.mipmap.slide6));
        banners.add(new DrawableBanner(R.mipmap.slide7));

        banners.add(new DrawableBanner(R.mipmap.slide8));

        bannerSlider.setBanners(banners);
        Intent rcv=  getIntent();
        cid = rcv.getStringExtra("id");
        init();
    }

    public void retrievedata()
    {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout listFooterView = (LinearLayout)inflater.inflate(
                R.layout.footer_layout, null);
        final Button loadmore = listFooterView.findViewById(R.id.button);
        loadmore.setText("Load More....");
       // lv1.addFooterView(listFooterView);
        RequestQueue requestQueue= Volley.newRequestQueue(ProductActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, util.base_url+util.datahome+util.ckey+util.csecret+"per_page=100&page="+current_page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response is"+ response);
                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        JSONArray img = jsonObject.optJSONArray("images");
                        JSONObject im = img.getJSONObject(0);
                        JSONArray cat = jsonObject.optJSONArray("categories");
                        JSONObject idd = cat.getJSONObject(0);
                        if(jsonObject.optString("stock_status").equalsIgnoreCase("instock")) {
                            if(idd.optString("id").equalsIgnoreCase(cid)){
                            product p = new product(jsonObject.optString("id"), jsonObject.optString("name"), jsonObject.optString("description")
                                    , im.optString("src"), jsonObject.optString("regular_price"), jsonObject.optString("sale_price"), jsonObject.optString("average_rating"));
                            id.add(p);
                            System.out.println("Added");
                            }
                        }
                    }


                    // Adding Load More button to lisview at bottom
                    adapterproduct adapterproduct=new adapterproduct(ProductActivity.this,R.layout.product,id);
                    lv1.setAdapter(adapterproduct);
                    lv1.getViewTreeObserver()
                            .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                                @Override
                                public void onScrollChanged() {
                                    if (!lv1.canScrollVertically(1)) {
                                        // Toast.makeText(getActivity(), " bottom", Toast.LENGTH_SHORT).show();
                                        System.out.println("Bottom");
                                        new loadMoreListView().retrievedata();
                                    }


                                }
                            });

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    void init(){
        id=new ArrayList<>();
        retrievedata();
    }


    private class loadMoreListView {


        public void retrievedata()
        {
            ++current_page;
            RequestQueue requestQueue= Volley.newRequestQueue(ProductActivity.this);
            StringRequest stringRequest=new StringRequest(Request.Method.GET, util.base_url+util.datahome+util.ckey+util.csecret+"per_page=100&page="+current_page, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("Response is"+ response);
                    try
                    {
                        JSONArray jsonArray=new JSONArray(response);
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);

                            JSONArray img = jsonObject.optJSONArray("images");
                            JSONObject im = img.getJSONObject(0);
                            JSONArray cat = jsonObject.optJSONArray("categories");
                            JSONObject idd = cat.getJSONObject(0);
                            if(jsonObject.optString("stock_status").equalsIgnoreCase("instock")) {
                                if(idd.optString("id").equalsIgnoreCase(cid)){
                                    product p = new product(jsonObject.optString("id"), jsonObject.optString("name"), jsonObject.optString("description")
                                            , im.optString("src"), jsonObject.optString("regular_price"), jsonObject.optString("sale_price"), jsonObject.optString("average_rating"));
                                    id.add(p);
                                }
                            }
                        }
                        int currentPosition = lv1.getFirstVisiblePosition();

                        adapterproduct adapterproduct=new adapterproduct(ProductActivity.this,R.layout.product,id);
                        lv1.setAdapter(adapterproduct);
                        lv1.setSelectionFromTop(currentPosition + 1, 0);

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.toString());
                }
            });
            requestQueue.add(stringRequest);
        }


    }

}
