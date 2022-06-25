package com.example.project_management;

import static java.lang.Character.getName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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

public class ViewprojectActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, TextWatcher {
    EditText searchbox;
    ListView projects_list;
    Spinner sp;
    ImageView im;
    SharedPreferences sh;
    String[] title,title1;
    String[] pname,pname1;
    String[] project_id,project_id1;
    String[] s_name,s_name1;
    String[] c_name,c_name1;
    String[] p_year,p_year1;
    String[] ab;
    String[] report;
    String[] demo;
    String[] journal;
    String[] gitlink;
    String dept_id;
    String sel_year="";
int flg,steps,flg2;
ImageView iw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewproject);
        searchbox=(EditText) findViewById(R.id.editTextsearch);
        iw=(ImageView)findViewById(R.id.imageView4);
        im=(ImageView)findViewById(R.id.imageView3);
        sp=findViewById(R.id.spinner2);
        iw.setOnClickListener(this);
        projects_list=(ListView) findViewById(R.id.list);
        searchbox.addTextChangedListener(this);
flg=0;
im.setOnClickListener(this);
        projects_list.setOnItemClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        dept_id=sh.getString("pid","");
        String url = "http://" + hu + ":5000/andview_projectguest1";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

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
//                                p_year=new String[js.length()];
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
                                    title[i]=u.getString("title");
                                    s_name[i]=u.getString("s_name");
                                    c_name[i]=u.getString("c_name");
                                    p_year[i]=u.getString("p_year");
//                                    p_year[i]=u.getString("year");
//                                    ab[i]=u.getString("ab");
//                                    report[i]=u.getString("report");
//                                    journal[i]=u.getString("journal");
//                                    demo[i]=u.getString("demo");
//                                    gitlink[i]=u.getString("gitlink");
//                                    Toast.makeText(ViewprojectActivity.this, "aa"+s_name[i], Toast.LENGTH_SHORT).show();
                                }
                                int p=pname.length;
                                if (p<10) {
                                    SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor edd=sh.edit();
                                    edd.putInt("length",p);
                                    edd.commit();
                                   // Toast.makeText(getApplicationContext(),"hoi",Toast.LENGTH_LONG).show();
                                    projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title, pname, s_name, c_name, p_year));
                                 //   Toast.makeText(getApplicationContext(),"noooi",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    steps=p/10;
                                    int last=p%10;


                                    SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor edd=sh.edit();
                                    edd.putInt("length",p);
                                    edd.putInt("steps",steps);
                                    edd.putInt("end",last);
                                    edd.putInt("flag",1);
                                    edd.putInt("count",10);
                                    edd.commit();



                                        project_id1 = new String[10];
                                        title1 = new String[10];
                                        pname1 = new String[10];
                                        s_name1 = new String[10];
                                        c_name1 = new String[10];
                                        p_year1 = new String[10];

                                        for (int j = 0; j < 10; j++) {
                                            pname1[j] = pname[j];
                                            project_id1[j] = project_id[j];

                                            title1[j] = title[j];
                                            s_name1[j] = s_name[j];

                                            c_name1[j] = c_name[j];
                                            p_year1[j] = p_year[j];

//                                   Toast.makeText(getApplicationContext(),p_year1[j],Toast.LENGTH_LONG).show();
                                            Log.d("p--------------------p",p_year1[j]+""+pname1[j]+""+project_id1[j]+""+title1[j]+""+s_name1[j]+""+c_name1);
                                        }
try {
    projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title1, pname1, s_name1, c_name1, p_year1));

}catch (Exception ex) {
    Log.d("-----------------ex",ex.getMessage().toString());
    //Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
}
}



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
                       // Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                String id=sh.getString("uid","");
                params.put("uid",id);
                params.put("did",sh.getString("did",""));
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

        if (view == im) {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_LONG).show();

            int length = sh.getInt("length", 0);
            int count = sh.getInt("count", 0);
            int steps = sh.getInt("steps", 0);
            int last = sh.getInt("end", 0);
            int flag = sh.getInt("flag", 0);
            Log.d("length",length+"");
            Log.d("count",count+"");
            Log.d("steps",steps+"");
            Log.d("last",last+"");
            Log.d("flag",flag+"");

            if (count < length) {
//Toast.makeText(getApplicationContext(),"l<<<<c",Toast.LENGTH_LONG).show();
                if (flag < steps) {
                    Log.d("------------------flag",flag+"");

                    Toast.makeText(getApplicationContext(),"s<<<<f",Toast.LENGTH_LONG).show();
                    project_id1 = new String[10];
                    title1 = new String[10];
                    pname1 = new String[10];
                    s_name1 = new String[10];
                    c_name1 = new String[10];
                    p_year1 = new String[10];
int k=0;
                    for (int j = count; j < count + 10; j++) {
                        pname1[k] = pname[j];
                        project_id1[k] = project_id[j];
                        title1[k] = title[j];
                        s_name1[k] = s_name[j];
                        c_name1[k] = c_name[j];
                        p_year1[k] = p_year[j];
                        k++;
                    }
                    SharedPreferences.Editor edd = sh.edit();
    int f=flag+1;
    if ((f==steps) && (last==0)){
        edd.putInt("length",0);
        edd.putInt("steps",0);
        edd.putInt("end",0);
        edd.putInt("flag",0);
        edd.putInt("count",count+10);

        edd.putInt("end", 0);
        edd.commit();
    }
else {
        edd.putInt("flag", flag + 1);
        edd.putInt("count", count + 10);
        edd.commit();
    }
Toast.makeText(getApplicationContext(),count+10+"",Toast.LENGTH_LONG).show();

                    projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title1, pname1, s_name1, c_name1, p_year1));

                } else {
                    project_id1 = new String[last];
                    title1 = new String[last];
                    pname1 = new String[last];
                    s_name1 = new String[last];
                    c_name1 = new String[last];
                    p_year1 = new String[last];
                    int k=0;
                    for (int j = count; j < count+last; j++) {
                        pname1[k] = pname[j];
                        project_id1[k] = project_id[j];

                        title1[k] = title[j];
                        s_name1[k] = s_name[j];

                        c_name1[k] = c_name[j];
                        p_year1[k] = p_year[j];
                        k++;
                      //  Toast.makeText(this, p_year1[j], Toast.LENGTH_SHORT).show();
                    }
                    SharedPreferences.Editor edd = sh.edit();

                    edd.putInt("flag",0);
                    edd.putInt("count",count+last);

                    edd.putInt("end", 0);
//                    edd.putInt("count", count + last);
                    edd.commit();
                    projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title1, pname1, s_name1, c_name1, p_year1));


                }
            } else {
                Toast.makeText(getApplicationContext(), "End", Toast.LENGTH_LONG);
            }
        }

        else{
            String yr = sp.getSelectedItem().toString();
            // Toast.makeText(this, "aaaa"+yr, Toast.LENGTH_SHORT).show();



            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/andview_studnetproject_search_year";



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

                                    JSONArray js= jsonObj.getJSONArray("data");
                                    project_id=new String[js.length()];
                                    title=new String[js.length()];
                                    pname=new String[js.length()];
                                    s_name=new String[js.length()];
                                    c_name=new String[js.length()];
                                    p_year=new String[js.length()];
//                                p_year=new String[js.length()];
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
                                        title[i]=u.getString("title");
                                        s_name[i]=u.getString("s_name");
                                        c_name[i]=u.getString("c_name");
                                        p_year[i]=u.getString("p_year");
//                                    p_year[i]=u.getString("year");
//                                    ab[i]=u.getString("ab");
//                                    report[i]=u.getString("report");
//                                    journal[i]=u.getString("journal");
//                                    demo[i]=u.getString("demo");
//                                    gitlink[i]=u.getString("gitlink");
//                                    Toast.makeText(ViewprojectActivity.this, "aa"+s_name[i], Toast.LENGTH_SHORT).show();
                                    }

                                    projects_list.setAdapter(new CustomviewActivity(getApplicationContext(),title,pname,s_name,c_name,p_year));

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
                            //Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    String id=sh.getString("pid","");
                    params.put("search_project",yr);
                    params.put("dept_id",id);
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
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences aa = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
int k=aa.getInt("length",0);

//int s=aa.getInt("steps",0);
//Toast.makeText(getApplicationContext(),k+",,,"+i+"----",Toast.LENGTH_LONG).show();
//int m=k%10;
//int n;
//if (m==0){
//    n=k/10;
//    Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_LONG).show();
//}
//else {
//    n = m;
//}
        String pr;
if(k<10)
{
    pr = project_id[i];
}
else {
     pr = project_id1[i];
}
    Log.d("prrr",pr);
    SharedPreferences.Editor ed = aa.edit();
    ed.putString("p_id", pr);
    ed.commit();

    Intent in = new Intent(getApplicationContext(), ProjectdetailsActivity.class);
    startActivity(in);


}












    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        final String search1= searchbox.getText().toString();


            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String hu = sh.getString("ip", "");
            String url = "http://" + hu + ":5000/andview_studnetproject_search";



            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        //      Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                            // response
                            try {
                                JSONObject jsonObj = new JSONObject(response);
                                if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                    JSONArray js = jsonObj.getJSONArray("data");
                                    project_id = new String[js.length()];
                                    title = new String[js.length()];
                                    pname = new String[js.length()];
                                    s_name = new String[js.length()];
                                    c_name = new String[js.length()];
                                    p_year = new String[js.length()];
//                                p_year=new String[js.length()];
//                                ab=new String[js.length()];
//                                report=new String[js.length()];
//                                journal=new String[js.length()];
//                                demo=new String[js.length()];
//                                gitlink=new String[js.length()];

//[{'title': 'Project Management', 's_name': 'Devika', 'c_name': 'Government Engineering College, Thrissur', 'p_year': 2021},
//

                                    for (int i = 0; i < js.length(); i++) {
                                        JSONObject u = js.getJSONObject(i);
                                        pname[i] = u.getString("title");
                                        project_id[i] = u.getString("project_id");
                                        title[i] = u.getString("title");
                                        s_name[i] = u.getString("s_name");
                                        c_name[i] = u.getString("c_name");
                                        p_year[i] = u.getString("p_year");
//                                    p_year[i]=u.getString("year");
//                                    ab[i]=u.getString("ab");
//                                    report[i]=u.getString("report");
//                                    journal[i]=u.getString("journal");
//                                    demo[i]=u.getString("demo");
//                                    gitlink[i]=u.getString("gitlink");
                                        //   Toast.makeText(ViewprojectActivity.this, "aa" + s_name[i], Toast.LENGTH_SHORT).show();
                                    }

                                    int p=pname.length;
                                    if (p<10) {
                                        // Toast.makeText(getApplicationContext(),"hoi",Toast.LENGTH_LONG).show();
                                        projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title, pname, s_name, c_name, p_year));
                                        //   Toast.makeText(getApplicationContext(),"noooi",Toast.LENGTH_LONG).show();
                                    }
                                    else
                                    {
                                        steps=p/10;
                                        int last=p%10;


                                        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        SharedPreferences.Editor edd=sh.edit();
                                        edd.putInt("length",p);
                                        edd.putInt("steps",steps);
                                        edd.putInt("end",last);
                                        edd.putInt("flag",1);
                                        edd.putInt("count",10);
                                        edd.commit();



                                        project_id1 = new String[10];
                                        title1 = new String[10];
                                        pname1 = new String[10];
                                        s_name1 = new String[10];
                                        c_name1 = new String[10];
                                        p_year1 = new String[10];

                                        for (int j = 0; j < 10; j++) {
                                            pname1[j] = pname[j];
                                            project_id1[j] = project_id[j];

                                            title1[j] = title[j];
                                            s_name1[j] = s_name[j];

                                            c_name1[j] = c_name[j];
                                            p_year1[j] = p_year[j];

//                                   Toast.makeText(getApplicationContext(),p_year1[j],Toast.LENGTH_LONG).show();
                                            Log.d("p--------------------p",p_year1[j]+""+pname1[j]+""+project_id1[j]+""+title1[j]+""+s_name1[j]+""+c_name1);
                                        }
                                        try {
                                            projects_list.setAdapter(new CustomviewActivity(getApplicationContext(), title1, pname1, s_name1, c_name1, p_year1));

                                        }catch (Exception ex) {
                                            Log.d("-----------------ex",ex.getMessage().toString());
                                           // Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                                        }
                                    }

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
                           // Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Map<String, String> params = new HashMap<String, String>();

                    String id=sh.getString("pid","");
                    params.put("search_project",search1);
                    params.put("dept_id",id);
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
    public void afterTextChanged(Editable editable) {

    }


}