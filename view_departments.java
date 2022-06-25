package com.example.project_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class view_departments extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView li;
    SharedPreferences sh;
    String ip, url, lid;
    String[] did,clgname,dptname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_departments);

        li = (ListView) findViewById(R.id.list);
        li.setOnItemClickListener(this);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip = sh.getString("url", "");
        url = ip + "/and_view_department";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                JSONArray js = jsonObj.getJSONArray("data");//from python
                                did = new String[js.length()];
                                clgname= new String[js.length()];
                                dptname= new String[js.length()];
//                                post= new String[js.length()];
//                                post= new String[js.length()];



                                for (int i = 0; i < js.length(); i++) {
                                    JSONObject u = js.getJSONObject(i);
                                    did[i] = u.getString("depidd");//dbcolumn name in double quotes
                                    clgname[i] = u.getString("c_name");
                                    dptname[i] = u.getString("dept_name");
//                                    post[i] = u.getString("post");


                                }
                                li.setAdapter(new custom_view_department(getApplicationContext(),did, clgname, dptname));//custom_view_service.xml and li is the listview object


                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", sh.getString("lid",""));//passing to python
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        final String id=did[i];

        AlertDialog.Builder builder = new AlertDialog.Builder(view_departments.this);
        builder.setTitle("options");
        builder.setItems(new CharSequence[]
                        {"View Projects","Cancel"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:

                            {
                                Intent i =new Intent(getApplicationContext(),ViewprojectActivity.class);
//                                i.putExtra("pid",id);
                                Toast.makeText(getApplicationContext(),id+"-------",Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("pid",id);
                                ed.putString("did",id);
                                ed.commit();
//                                i.putExtra("pid",id);
                                startActivity(i);
//                                startActivity(i);
//                                requestQueue.add(postRequest);
                            }


                            break;

                            case 1:
                            {
                                Intent i =new Intent(getApplicationContext(),StudenthomeActivity.class);
                                startActivity(i);
//                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                                        new Response.Listener<String>() {
//                                            @Override
//                                            public void onResponse(String response) {
//                                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                                                try {
//                                                    JSONObject jsonObj = new JSONObject(response);
//                                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                                        Toast.makeText(view_departments.this, "post deleted", Toast.LENGTH_SHORT).show();
//                                                        Intent i =new Intent(getApplicationContext(),view_departments.class);
////                                                        i.putExtra("pid",id);
//                                                        startActivity(i);
//                                                    } else {
//                                                        Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                                    }
//
//                                                } catch (Exception e) {
//                                                    Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        },
//                                        new Response.ErrorListener() {
//                                            @Override
//                                            public void onErrorResponse(VolleyError error) {
//                                                // error
//                                                Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                ) {
//
//                                    //                value Passing android to python
//                                    @Override
//                                    protected Map<String, String> getParams() {
//                                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                        Map<String, String> params = new HashMap<String, String>();
//
//                                        params.put("pid", id);//passing to python
//
//
//
//                                        return params;
//                                    }
//                                };
//
//
//                                int MY_SOCKET_TIMEOUT_MS = 100000;
//
//                                postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                                        MY_SOCKET_TIMEOUT_MS,
//                                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                                requestQueue.add(postRequest);


                            }
                            break;

                            case 2:

                                break;


                        }
                    }
                });
        builder.create().show();
    }
}