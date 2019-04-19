package in.codecorp.ssgcp.shop.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

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

import in.codecorp.ssgcp.shop.Adapters.CategoryAdapter;
import in.codecorp.ssgcp.shop.Adapters.adapterproduct;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.Utils.Category;
import in.codecorp.ssgcp.shop.Utils.util;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    GridView listView;
    ArrayList<Category> data;
    ProgressDialog progressDialog;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        listView = v.findViewById(R.id.list);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("LOADING");
        progressDialog.show();
        retrievedata();
        return v;
    }

    public void retrievedata()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, util.base_url+util.URL_CAT+util.ckey+util.csecret+util.ctperpage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response is"+ response);
                progressDialog.dismiss();
                data = new ArrayList<>();
                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Category category = new Category(jsonObject.optString("id"), jsonObject.optString("name"));
                        data.add(category);
                    }


                    // Adding Load More button to lisview at bottom
                    CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), R.layout.layout_category, data);
                    listView.setAdapter(categoryAdapter);
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
