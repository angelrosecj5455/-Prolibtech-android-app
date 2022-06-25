package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentProjectsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView list;
    String[]pname,pid,ab,rep,j,demo,p_year,gitlink;
    FloatingActionButton f1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_projects);
        list=(ListView) findViewById(R.id.L1);
        list.setOnItemClickListener(this);
        f1=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        f1.setOnClickListener(this);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/view_myprojects";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("users");
                                pname=new String[js.length()];
                                pid=new String[js.length()];
                                ab=new String[js.length()];
                                rep=new String[js.length()];
                                j=new String[js.length()];
                                demo=new String[js.length()];
                                p_year=new String[js.length()];
                                gitlink=new String[js.length()];
                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("title");
                                    pid[i]=u.getString("project_id");
                                    ab[i]=u.getString("abstract");
                                    rep[i]=u.getString("report");
                                    j[i]=u.getString("journal");
                                    demo[i]=u.getString("demo");
                                    p_year[i]=u.getString("p_year");
                                    gitlink[i]=u.getString("githublink");


                                }
//

                                list.setAdapter(new Custom_student_project(getApplicationContext(),pname,pid,ab,rep,j,demo,p_year,gitlink));
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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

                String id=sh.getString("lid","");
                params.put("login_id",id);
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

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),projectuploadActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String pr = pid[i];
        SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed = aa.edit();
        ed.putString("p_id", pr);
        ed.commit();
//
        Intent in = new Intent(getApplicationContext(),ProjectdetailsActivity.class);
        startActivity(in);
    }
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        String pr = pid[i];
//        SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor ed = aa.edit();
//        ed.putString("p_id", pr);
//        ed.commit();
////
//        Intent in = new Intent(getApplicationContext(),ProjectdetailsActivity.class);
//        startActivity(in);
//    }
}