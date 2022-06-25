package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Gust_reg extends AppCompatActivity implements View.OnClickListener {
EditText edname,edemail,edpass;
Button btbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gust_reg);
        edname=(EditText) findViewById(R.id.gstname);
        edemail=(EditText) findViewById(R.id.gstemail);
        edpass=(EditText) findViewById(R.id.gstpassword);
        btbutton=(Button) findViewById(R.id.gbt);
        btbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name=edname.getText().toString();
        String email=edemail.getText().toString();
        String pass=edpass.getText().toString();

        if(name.length()==0){
            edname.setError("Missing");
        }
        else if(email.length()==0){
            edemail.setError("Missing");
        }else if(pass.length()==0){
            edpass.setError("Missing");
        }
        else {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/gust_reg";
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
                                    Toast.makeText(Gust_reg.this, "Success", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(i);


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

                    params.put("name", name);
                    params.put("email", email);
                    params.put("passw", pass);

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