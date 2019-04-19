package in.codecorp.ssgcp.shop;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import in.codecorp.ssgcp.shop.Utils.UtilLog;
import in.codecorp.ssgcp.shop.Utils.util;
import in.codecorp.ssgcp.shop.Utils.utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText editEmail;
    private EditText editPassword;
    private Context mContext;
    private TextView textRegister;
    SessionLogin sessionLogin;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        sessionLogin = new SessionLogin(getApplicationContext());
        progressDialog = new ProgressDialog(LoginActivity.this);
        initUI();
    }

    private void initUI() {
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textRegister = (TextView) findViewById(R.id.textRegister);
        btnLogin.setOnClickListener(this);
        textRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textRegister:
                startActivity(new Intent(LoginActivity.this,RgisterActivity.class));
                finish();
                break;
            case R.id.btnLogin:
                loginFunctionality();

                break;
        }
    }

    void loadBooks(final String userName, String Password){
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        String URL = util.BASE_URL + util.URL_LOGIN+userName+"&user_password="+Password;
        final String requestBody = null;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Log.i("VOLLEY", response);
               // loader.dismiss();

                System.out.println();
                System.out.println("Response is "+ response);
                progressDialog.dismiss();
                if (response != null && !response.isEmpty()) {
                    try {
                        JSONObject object = new JSONObject(response);
                        String status = object.optString("UserStatus");
                        if (status.equals("1")) {

                           // Util.setUserLoggedIn(LoginActivity.this, true);
                            UtilLog.showToast(mContext, "You are logged in successfully ");
                            sessionLogin.createLoginSession(userName);
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            UtilLog.showToast(mContext, " Please enter valid email and password ");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                progressDialog.dismiss();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }


        };

        requestQueue.add(stringRequest);
    }


    private void loginFunctionality() {

        String userId = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        if (userId.equals("") || userId.equals(null)) {
            UtilLog.showToast(LoginActivity.this, "Please enter Username");
            editEmail.requestFocus();
        } else if (password.equals("") || password.equals(null)) {
            UtilLog.showToast(LoginActivity.this, "Please enter Password");
            editPassword.requestFocus();
        } else {
            progressDialog.setMessage("LOADING");
            progressDialog.show();
                loadBooks(userId, password);
                //   LoginAsyncTask loginAsyncTask = new LoginAsyncTask(userId, password);
                // loginAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        }

    }


}
