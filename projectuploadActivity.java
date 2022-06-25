package com.example.project_management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class projectuploadActivity extends AppCompatActivity implements View.OnClickListener {

    EditText ed_project,parea;
    EditText ed_year;
    EditText ab;
    TextView rep;
    TextView dem;
    TextView j;

    EditText git;
    Button upload;
    Button bu_rep;
    Button bu_dem;
    Button bu_j;

    String path, atype, fname, attach1, attatch2,attatch3,attatch4;
    byte[] byteArray = null;
    String url3 = "";
    String[] dis = {"--select--", "Architect", "furniture_designer", "interiors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projectupload);

        ed_project = (EditText) findViewById(R.id.pname);
        ed_year = (EditText) findViewById(R.id.editTextTextPersonName2);
        ab = (EditText) findViewById(R.id.multiab);
        parea = (EditText) findViewById(R.id.area);
        rep = (TextView) findViewById(R.id.textView4);
        dem = (TextView) findViewById(R.id.textView8);
        j = (TextView) findViewById(R.id.textView5);

        git = (EditText) findViewById(R.id.editTextTextPersonName3);
        upload = (Button) findViewById(R.id.button15);

        bu_dem = (Button) findViewById(R.id.button14);
        bu_j = (Button) findViewById(R.id.button13);
        bu_rep = (Button) findViewById(R.id.button12);
        upload.setOnClickListener(this);
        bu_rep.setOnClickListener(this);
        bu_j.setOnClickListener(this);
        bu_dem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == bu_rep) {
            showfilechooser(2);
        }
        if (view == bu_j) {
            showfilechooser(3);
        }
        if (view == bu_dem) {
            showfilechooser(4);
        }

        if (view == upload) {
            Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT).show();
            final String githublink = git.getText().toString();
            Toast.makeText(this, "HELLO2", Toast.LENGTH_SHORT).show();
            final String project = ed_project.getText().toString();
            Toast.makeText(this, "HELLO1", Toast.LENGTH_SHORT).show();
            final String y = ed_year.getText().toString();
            final String area = parea.getText().toString();

            if (githublink.length() == 0) {
                git.setError("Field Cannot be empty!");
            }

            else if (y.length() == 0) {
                ed_year.setError("Field Cannot be empty!");
            }

            else  if (project.length() == 0) {
                ed_project.setError("Field Cannot be empty!");
            }  else  if (area.length() == 0) {
                parea.setError("Field Cannot be empty!");
            }
            else if(attatch2.equalsIgnoreCase(""))
            {
                Toast.makeText(getApplicationContext(),"attach2---null",Toast.LENGTH_LONG).show();
            }
            else if(attatch3.equalsIgnoreCase(""))
            {
                Toast.makeText(getApplicationContext(),"attach3---null",Toast.LENGTH_LONG).show();
            }
            else if(attatch4.equalsIgnoreCase(""))
            {
                Toast.makeText(getApplicationContext(),"attach4---null",Toast.LENGTH_LONG).show();
            }

            else {
                Toast.makeText(this, "-----------------", Toast.LENGTH_SHORT).show();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":5000/projectupload_post";
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
                                        Toast.makeText(projectuploadActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), StudenthomeActivity.class);
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


                        params.put("login_id", sh.getString("lid", ""));
                        params.put("pname", project);
                        params.put("year", y);
                        params.put("ab",ab.getText().toString());
                        params.put("git", githublink);
                        params.put("rep", attatch2);
                        params.put("j1", attatch3);
                        params.put("demo", attatch4);
                        params.put("area", area);



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
            Toast.makeText(this, "HELLO3", Toast.LENGTH_SHORT).show();
            }
        }


        void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
//            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);

                    Toast.makeText(this, "-----------------------------"+requestCode, Toast.LENGTH_SHORT).show();
//                    if(requestCode==1) {
//                        Toast.makeText(this, "recode1 enter", Toast.LENGTH_SHORT).show();
//                        ab.setText(fname);
//                    }

                    if(requestCode==2) {
                        rep.setText(fname);
                    }
                    if(requestCode==3){
                    j.setText(fname);
                    }
                    if(requestCode==4) {
                        dem.setText(fname);
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        //i1.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    if(requestCode==2) {
                        attatch2 = str;
                    }
                    if(requestCode==3) {
                        attatch3 = str;
                    }
                    if(requestCode==4) {
                        attatch4 = str;
                    }


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

//            }
        }
    }
}







