package in.codecorp.ssgcp.shop.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import in.codecorp.ssgcp.shop.Adapters.adapterproduct;
import in.codecorp.ssgcp.shop.LoginActivity;
import in.codecorp.ssgcp.shop.R;
import in.codecorp.ssgcp.shop.SessionLogin;
import in.codecorp.ssgcp.shop.Utils.product;
import in.codecorp.ssgcp.shop.Utils.util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView fname, lname, uname, mobile;
    String email;
    SessionLogin sessionLogin;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        sessionLogin = new SessionLogin(getActivity());
        HashMap<String, String> user = sessionLogin.getUserDetails();
        email = user.get(SessionLogin.KEY_PHONE);
        fname = v.findViewById(R.id.txtFname);
        lname = v.findViewById(R.id.txtLname);
        uname = v.findViewById(R.id.txtEmail);
        mobile = v.findViewById(R.id.txtMobile);
        if(email!=null){

        }
        else {
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        getUser();
        return v;
    }

    public void getUser()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, util.BASE_URL+util.URL_PROFILE+email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response is"+ response);
                try
                {
                    JSONObject jsonObject=new JSONObject(response);
                    fname.setText(jsonObject.getString("first_name"));
                    lname.setText(jsonObject.getString("last_name"));
                    uname.setText(jsonObject.getString("user_email"));
                    mobile.setText(jsonObject.getString("mobile_number"));


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