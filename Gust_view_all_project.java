package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Gust_view_all_project extends AppCompatActivity implements TextWatcher {
    EditText searchbox;
    ListView projects_list;
    RatingBar r1;
    SharedPreferences sh;
    String[] title,pname,project_id,s_name,c_name,p_year,ab,report,demo,journal,gitlink,status,rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gust_view_all_project);
        r1=(RatingBar) findViewById(R.id.ratingBar4);
        searchbox=(EditText) findViewById(R.id.editTextsearch);
        projects_list=(ListView) findViewById(R.id.list);
        searchbox.addTextChangedListener(this);

//        projects_list.setOnItemClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/gust_view_all_project";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                project_id=new String[js.length()];
                                title=new String[js.length()];
                                pname=new String[js.length()];
                                s_name=new String[js.length()];
                                c_name=new String[js.length()];
                                p_year=new String[js.length()];
                                status=new String[js.length()];
                                rating=new String[js.length()];
//                                ab=new String[js.length()];
//                                report=new String[js.length()];
//                                journal=new String[js.length()];
//                                demo=new String[js.length()];
//                                gitlink=new String[js.length()];

//[{'title': 'Project Management', 's_name': 'Devika', 'c_name': 'Government Engineering College, Thrissur', 'p_year': 2021},
//
                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("title");
                                    project_id[i]=u.getString("project_id");
                                    title[i]=u.getString("abstract");
                                    s_name[i]=u.getString("s_name");
                                    c_name[i]=u.getString("c_name");
                                    p_year[i]=u.getString("p_year");
                                    status[i]=u.getString("status");
                                    rating[i]=u.getString("rat");
//                                    ab[i]=u.getString("ab");
//                                    report[i]=u.getString("report");
//                                    journal[i]=u.getString("journal");
//                                    demo[i]=u.getString("demo");
//                                    gitlink[i]=u.getString("gitlink");
//                                    Toast.makeText(ViewprojectActivity.this, "aa"+s_name[i], Toast.LENGTH_SHORT).show();
                                }
//



                                projects_list.setAdapter(new Custom_gust_view_allprojects(getApplicationContext(),title,pname,s_name,c_name,p_year,project_id,status,rating));
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
                params.put("lid",id);
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/andview_gust_project_search";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js= jsonObj.getJSONArray("data");
                                project_id=new String[js.length()];
                                title=new String[js.length()];
                                pname=new String[js.length()];
                                s_name=new String[js.length()];
                                c_name=new String[js.length()];
                                p_year=new String[js.length()];
                                status=new String[js.length()];
                                rating=new String[js.length()];
//                                ab=new String[js.length()];
//                                report=new String[js.length()];
//                                journal=new String[js.length()];
//                                demo=new String[js.length()];
//                                gitlink=new String[js.length()];

//[{'title': 'Project Management', 's_name': 'Devika', 'c_name': 'Government Engineering College, Thrissur', 'p_year': 2021},
//
                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    pname[i]=u.getString("title");
                                    project_id[i]=u.getString("project_id");
                                    title[i]=u.getString("abstract");
                                    s_name[i]=u.getString("s_name");
                                    c_name[i]=u.getString("c_name");
                                    p_year[i]=u.getString("p_year");
                                    status[i]=u.getString("status");
                                    rating[i]=u.getString("rat");
//                                    ab[i]=u.getString("ab");
//                                    report[i]=u.getString("report");
//                                    journal[i]=u.getString("journal");
//                                    demo[i]=u.getString("demo");
//                                    gitlink[i]=u.getString("gitlink");
//                                    Toast.makeText(ViewprojectActivity.this, "aa"+s_name[i], Toast.LENGTH_SHORT).show();
                                }
//

                                projects_list.setAdapter(new Custom_gust_view_allprojects(getApplicationContext(),title,pname,s_name,c_name,p_year,project_id,status,rating));
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

//                String id=sh.getString("uid","");
                params.put("search_project",searchbox.getText().toString());
                params.put("lid",sh.getString("lid",""));

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

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        String pr = project_id[i];
//        SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        SharedPreferences.Editor ed = aa.edit();
//        ed.putString("p_id", pr);
//        ed.commit();
////
//        Intent in = new Intent(getApplicationContext(),Gust_view_project_details.class);
//        startActivity(in);
//    }
}