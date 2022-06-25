package com.example.project_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.renderscript.Matrix4f;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class Custom_gust_view_allprojects extends BaseAdapter {
    private final Context context;
    String[] title,pname,sname,c_name,p_year,pid,status,rating;

    public Custom_gust_view_allprojects(Context applicationContext, String[] title, String[]pname, String[]s_name, String[]c_name, String[]p_year, String[]pid, String[]status, String[]rating) {
        this.context=applicationContext;

        this.title=title;
        this.pname=pname;
        this.p_year=p_year;
        this.c_name=c_name;
        this.sname=s_name;
        this.pid=pid;
        this.status=status;
        this.rating=rating;


    }

    @Override
    public int getCount() {
        return p_year.length;
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
            gridView=inflator.inflate(R.layout.custom_gust_view_projects,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.t17);
        TextView tv2=(TextView)gridView.findViewById(R.id.t14);
        TextView tv3=(TextView)gridView.findViewById(R.id.t2);
        TextView tv4=(TextView)gridView.findViewById(R.id.t3);
        ImageView imgrate=(ImageView) gridView.findViewById(R.id.rating);
        imgrate.setTag(i);
        imgrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = aa.edit();
                ed.putString("p_id", pid[pos]);
                ed.commit();

                    Intent in = new Intent(context, Send_rating.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);

            }
        });


        Button btmore=(Button) gridView.findViewById(R.id.btmore);
        btmore.setTag(i);
        btmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed = aa.edit();
                ed.putString("p_id", pid[pos]);
                ed.commit();
                if(status[i].equalsIgnoreCase("yes")) {
//
                    Intent in = new Intent(context, Gust_view_project_details.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                }
                else
                {
                    Intent in = new Intent(context, Gust_razorPayments.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(in);
                }
            }
        });
        Button addfav=(Button) gridView.findViewById(R.id.add_fav);
        addfav.setTag(i);
        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos= (int) view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/gust_inset_fav";
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
                                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(context, Gust_view_all_project.class);
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
                        params.put("lid",sh.getString("lid",""));

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
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
//        Toast.makeText(context, "----"+title[i], Toast.LENGTH_SHORT).show();
        tv1.setText(pname[i]+"\n\n"+title[i]);
        tv2.setText("Published By"+sname[i]);
        tv3.setText(c_name[i]);
        tv4.setText(p_year[i]);
        String rstt= rating[i];
        float f=Float.parseFloat(rstt);

        RatingBar r1=(RatingBar)gridView.findViewById(R.id.ratingBar4);
        r1.setRating(f);
        r1.setEnabled(false);
        return gridView;


    }

}
