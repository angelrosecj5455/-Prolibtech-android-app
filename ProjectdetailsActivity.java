package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class ProjectdetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView title1;
    TextView report1;
    TextView abst1;
    TextView journal1;
    TextView demo1;
    TextView githublink1;
    String report,demo,journal;
    Button download2;
    Button download3;
    Button download4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectdetails);
        title1=(TextView)  findViewById(R.id.textView2);
        report1=(TextView) findViewById(R.id.textView15);
        abst1=(TextView) findViewById(R.id.textView12);
        journal1=(TextView) findViewById(R.id.textView23);
        demo1=(TextView) findViewById(R.id.textView9);
        githublink1=(TextView) findViewById(R.id.textView21);
        githublink1.setOnClickListener(this);


        download2=(Button) findViewById(R.id.button6);
        download3=(Button) findViewById(R.id.button7);
        download4=(Button) findViewById(R.id.button8);
        download2.setOnClickListener(this);
        download3.setOnClickListener(this);
        download4.setOnClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":5000/projectdetails";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            Toast.makeText(ProjectdetailsActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                            String status=jsonObj.getString("status");

                            if (status.equalsIgnoreCase("ok")) {
                                String title= jsonObj.getString("title");
                                String abst= jsonObj.getString("abst");
                                report=jsonObj.getString("report");
                                demo= jsonObj.getString("demo");
                                journal= jsonObj.getString("journal");
                                String githublink= jsonObj.getString("githublink");


                                Toast.makeText(ProjectdetailsActivity.this, "aaa", Toast.LENGTH_SHORT).show();
                                title1.setText(title);
                                abst1.setText(abst);
//                                report1.setText(report);
//                                demo1.setText(demo);
//                                journal1.setText(journal);
                                githublink1.setText(githublink);




                                String lid=jsonObj.getString("lid");
                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("lid",lid);
                                ed.commit();
                                Intent i  = new Intent(getApplicationContext(),HomePageActivity.class);
                                startActivity(i);



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
                params.put("pid",sh.getString("p_id",""));


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
        if(view==githublink1)
        {
            String url = githublink1.getText().toString();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
        else if (view == download2) {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip=sh.getString("ip","");
            String url="http://" + ip + ":5000"+report;
            Log.d("url------------",url);
            Log.d("---***","..**..%%..");

            Uri uri = Uri.parse(url);
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Check what kind of file you are trying to open, by comparing the url with extensions.
            // When the if condition is matched, plugin sets the correct intent (mime) type,
            // so Android knew what application to use to open the file
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if(url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
                // WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if(url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if(url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if(url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else {
                //if you want you can also define the intent type for any other file

                //additionally use else clause below, to manage other unknown extensions
                //in this case, Android will show all applications installed on the device
                //so you can choose which application to use
                intent.setDataAndType(uri, "*/*");
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);

        }
        if (view == download3) {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip=sh.getString("ip","");
            String url="http://" + ip + ":5000"+journal;
            Log.d("url------------",url);
            Log.d("---***","..**..%%..");

            Uri uri = Uri.parse(url);
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            // Check what kind of file you are trying to open, by comparing the url with extensions.
            // When the if condition is matched, plugin sets the correct intent (mime) type,
            // so Android knew what application to use to open the file
            if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(uri, "application/msword");
            } else if(url.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(uri, "application/pdf");
            } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                // Powerpoint file
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
            } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                // Excel file
                intent.setDataAndType(uri, "application/vnd.ms-excel");
            } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
                // WAV audio file
                intent.setDataAndType(uri, "application/x-wav");
            } else if(url.toString().contains(".rtf")) {
                // RTF file
                intent.setDataAndType(uri, "application/rtf");
            } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                // WAV audio file
                intent.setDataAndType(uri, "audio/x-wav");
            } else if(url.toString().contains(".gif")) {
                // GIF file
                intent.setDataAndType(uri, "image/gif");
            } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                // JPG file
                intent.setDataAndType(uri, "image/jpeg");
            } else if(url.toString().contains(".txt")) {
                // Text file
                intent.setDataAndType(uri, "text/plain");
            } else {
                //if you want you can also define the intent type for any other file

                //additionally use else clause below, to manage other unknown extensions
                //in this case, Android will show all applications installed on the device
                //so you can choose which application to use
                intent.setDataAndType(uri, "*/*");
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);

        }
        if (view == download4) {
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String ip=sh.getString("ip","");
            String url="http://" + ip + ":5000"+demo;
            Log.d("url------------",url);
            Log.d("---***","..**..%%..");

            SharedPreferences.Editor ed=sh.edit();

            ed.putString("vurl",url);
            ed.commit();
            Intent i = new Intent(getApplicationContext(), VideopageActivity.class);
            startActivity(i);

        }



    }

}