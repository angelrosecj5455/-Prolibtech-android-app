package com.example.project_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

public class Custom_student_project extends BaseAdapter {
    private final Context context;
    String[]pname,pid,ab,rep,sc,demo,year,gitlink;

    public Custom_student_project(Context applicationContext, String[] pname, String[] pid,String[] ab,String[] rep,String[] sc,String[] demo,String[] year,String[] gitlink) {
        this.context=applicationContext;
        this.pname=pname;
        this.pid=pid;
        this.ab=ab;
        this.rep=rep;
        this.sc=sc;
        this.demo=demo;
        this.year=year;
        this.gitlink=gitlink;
    }

    @Override
    public int getCount() {
        return pname.length;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_student_project,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView16);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView3);
        ImageView imgdel=(ImageView)gridView.findViewById(R.id.imgdelete);
        imgdel.setTag(i);
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/and_delete_my_project";
                RequestQueue requestQueue = Volley.newRequestQueue(context);
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

                                        Intent i = new Intent(context, StudenthomeActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);


                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("pid", pid[pos]);

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
        });

        ImageView imgrate=(ImageView)gridView.findViewById(R.id.imgrate);
        imgrate.setTag(i);
        imgrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= (int) view.getTag();
                SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = aa.edit();
                ed.putString("p_id", pid[pos]);
                ed.commit();

                Intent i = new Intent(context, Student_view_ratings.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);



            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);


        tv1.setText(pname[i]);
        tv2.setText(year[i]);




        return gridView;


    }
}
