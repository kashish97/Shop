package in.codecorp.ssgcp.shop;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class RgisterActivity extends AppCompatActivity {
    private Context mContext;
    private EditText editUsername;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editCityName;
    private EditText editMobileNo;
    private EditText editEmail;
    private EditText editPassword;
    private TextInputLayout tilUsername;
    private TextInputLayout tilFirstName;
    private TextInputLayout tilLastName;
    private TextInputLayout tilCityName;
    private TextInputLayout tilMobileNo;
    private TextInputLayout tilPassword;
    private TextInputLayout tilEmail;
    private Button btnRegister;
    private String userName;
    private String firstName;
    private String lastName;
    private String cityName;
    private String mobileNo;
    private String email;
    SessionLogin sessionLogin;
    private String password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgister);
        mContext = this;
        sessionLogin = new SessionLogin(getApplicationContext());
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();
        initUI();
    }

    private void initUI() {
        editUsername = (EditText) findViewById(R.id.editUsername);
        editFirstName = (EditText) findViewById(R.id.editFirstName);
        editLastName = (EditText) findViewById(R.id.editLastName);
        editCityName = (EditText) findViewById(R.id.editCityName);
        editMobileNo = (EditText) findViewById(R.id.editMobileNo);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
       // tilUsername = (TextInputLayout) findViewById(R.id.tilUsername);
        //tilFirstName = (TextInputLayout) findViewById(R.id.tilFirstName);
        //tilLastName = (TextInputLayout) findViewById(R.id.tilLastName);
        //tilCityName = (TextInputLayout) findViewById(R.id.tilCityName);
        //tilMobileNo = (TextInputLayout) findViewById(R.id.tilMobileNo);
        //tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        //tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);


        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invalidateField();
            }
        });
    }

    private void invalidateField() {
        userName = editUsername.getText().toString().trim();
        firstName = editFirstName.getText().toString().trim();
        lastName = editLastName.getText().toString().trim();
        cityName = editCityName.getText().toString().trim();
        mobileNo = editMobileNo.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();

     /*   tilUsername.setError("");
        tilFirstName.setError("");
        tilLastName.setError("");
        tilCityName.setError("");
        tilMobileNo.setError("");
        tilEmail.setError("");
        tilPassword.setError("");
        if (userName.equals("")) {
            tilUsername.setError("Enter UserName");
            editUsername.requestFocus();
        } else if (firstName.equals("")) {
            tilFirstName.setError("Enter First Name");
            editFirstName.requestFocus();
        } else if (lastName.equals("")) {
            tilLastName.setError("Enter Last Name");
            editLastName.requestFocus();
        } else if (cityName.equals("")) {
            tilCityName.setError("Enter City Name");
            editCityName.requestFocus();
        } else if (mobileNo.equals("")) {
            tilMobileNo.setError("Enter Your Mobile No");
            editMobileNo.requestFocus();
        } else if (email.equals("") ) {
            tilEmail.setError("Enter Valid Email");
            editEmail.requestFocus();
        } else if (password.equals("")) {
            tilPassword.setError("Enter Your Password");
            editPassword.requestFocus();
        } *///else {
            loadBooks();
            // RegisterAsyncTask loginAsyncTask = new RegisterAsyncTask();
            //loginAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }


    void loadBooks(){
        RequestQueue requestQueue = Volley.newRequestQueue(RgisterActivity.this);
        String URL = util.BASE_URL + util.URL_REGISTER+userName+util.URL_PASSWORD+password+"&user_email="+email+
                "&display_name="+userName+"&first_name="+firstName+"&last_name="+lastName+"&city="+cityName+"&mobile="+mobileNo;

        final String requestBody = null;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Log.i("VOLLEY", response);

                System.out.println();
                System.out.println("Response is "+ response);
                progressDialog.dismiss();
                if (response != null && !response.isEmpty()) {
                    try {
                        JSONObject object = new JSONObject(response);
                        if (object.getString("Status").equalsIgnoreCase("ok")){
                            UtilLog.showToast(mContext, "You are Successfully Registered");
                            sessionLogin.createLoginSession(email);
                          //  Util.setUserLoggedIn(RegisterActivity.this, true);
                            finish();
                        }else {
                            UtilLog.showToast(mContext, "User already registerd!");
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


}

