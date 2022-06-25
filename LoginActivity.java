package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_user;
    EditText ed_password;
    Button bu_login;
    TextView tvguest;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),ip_address.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ed_user = (EditText) findViewById(R.id.editTextUser);
        ed_password = (EditText) findViewById(R.id.editTextPass);
        tvguest = (TextView) findViewById(R.id.tvguest);
        bu_login = (Button) findViewById(R.id.button2);
        bu_login.setOnClickListener(this);
        tvguest.setOnClickListener(this);
    }
    @Override
      public void onClick(View view) {
        if(view==tvguest){
            startActivity(new Intent(getApplicationContext(),Gust_reg.class));
        }
        else if(view==bu_login) {
            final String username = ed_user.getText().toString();
            if (username.length() == 0) {
                ed_user.setError("Field Cannot be empty!");
            }
            final String password = ed_password.getText().toString();
            if (password.length() == 0) {
                ed_password.setError("Field Cannot be empty!");
            } else {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/andlogin_post";
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    String status = jsonObj.getString("status");

                                    if (status.equalsIgnoreCase("ok")) {
                                        String lid = jsonObj.getString("lid");
                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor ed = sh.edit();
                                        ed.putString("lid", lid);
                                        ed.commit();


                                        String tye = jsonObj.getString("type");
                                        if (tye.equalsIgnoreCase("gust")) {
                                            startActivity(new Intent(getApplicationContext(), Gust_Home.class));
                                        } else if (tye.equalsIgnoreCase("student")) {

                                            Intent i = new Intent(getApplicationContext(), StudenthomeActivity.class);
                                            startActivity(i);
                                        }


                                    }

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("uname", username);
                        params.put("passw", password);

//                params.put("mac",maclis);

                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);
            }
        }

    }
}