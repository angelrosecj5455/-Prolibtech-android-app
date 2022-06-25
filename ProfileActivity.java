package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    TextView name;
    TextView name1;
    TextView email;
    TextView email1;
    TextView phone;
    TextView phone1;
    TextView department;
    TextView department1;
    TextView branch;
    TextView branch1;
    ImageView img1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        name = (TextView) findViewById(R.id.textView11);
        name1 = (TextView) findViewById(R.id.textView31);
        email = (TextView) findViewById(R.id.textView28);
        email1 = (TextView) findViewById(R.id.textView32);
        phone = (TextView) findViewById(R.id.textView26);
        phone1 = (TextView) findViewById(R.id.textView33);
        department = (TextView) findViewById(R.id.textView29);
        department1 = (TextView) findViewById(R.id.textView34);
        branch = (TextView) findViewById(R.id.textView30);
        branch1 = (TextView) findViewById(R.id.textView35);

        img1=(ImageView) findViewById(R.id.imageview1);
        // edit1.setOnClickListener(this);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/view_studentprofile_postt";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            String status=jsonObj.getString("status");

                            if (status.equalsIgnoreCase("ok")) {
                                String img=jsonObj.getString("image");
                                String name= jsonObj.getString("name");
                                String email= jsonObj.getString("email");
                                String phone=jsonObj.getString("phone");
                                String department= jsonObj.getString("department");
                                String branch= jsonObj.getString("branch");
                                name1.setText(name);
                                email1.setText(email);
                                phone1.setText(phone);
                                department1.setText(department);
                                branch1.setText(branch);


                              SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                              String ip=sh.getString("ip","");
                               String url="http://" + ip + ":5000"+img;


                                Picasso.with(getApplicationContext()).load(url). into(img1);

                            }

                        }    catch (Exception e) {
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
                params.put("lid",sh.getString("lid",""));




//                params.put("mac",maclis);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

      //  Intent i = new Intent(getApplicationContext(), EditProfileActivity.class);
       // startActivity(i);



    }

}