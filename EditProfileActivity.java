//package com.example.project_management;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class EditProfileActivity extends AppCompatActivity {
//    TextView edit_name;
//    EditText edit_name1;
//    TextView edit_email;
//    EditText edit_email1;
//    TextView edit_phone;
//    EditText edit_phone1;
//    TextView edit_department;
//    EditText edit_department1;
//    TextView edit_branch;
//    EditText edit_branch1;
//    Button submit;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.edit_profile);
//        edit_name = (TextView) findViewById(R.id.textView17);
//        edit_name1 = (EditText) findViewById(R.id.e1);
//        edit_email = (TextView) findViewById(R.id.textView19);
//        edit_email1 = (EditText) findViewById(R.id.e2);
//        edit_phone = (TextView) findViewById(R.id.textView24);
//        edit_phone1 = (EditText) findViewById(R.id.e3);
//        edit_department = (TextView) findViewById(R.id.textView25);
//        edit_department1 = (EditText) findViewById(R.id.e4);
//        edit_branch = (TextView) findViewById(R.id.textView27);
//        edit_branch1 = (EditText) findViewById(R.id.e5);
//        submit = (Button) findViewById(R.id.button4);
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String hu = sh.getString("ip", "");
//        String url = "http://" + hu + ":5000/view_studentprofile_post";
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//
//                        // response
//                        try {
//                            JSONObject jsonObj = new JSONObject(response);
//                            String status=jsonObj.getString("status");
//
//                            if (status.equalsIgnoreCase("ok")) {
//
//                                String name= jsonObj.getString("name");
//                                String email= jsonObj.getString("email");
//                                String phone=jsonObj.getString("phone");
//                                String department= jsonObj.getString("department");
//                                String branch= jsonObj.getString("branch");
//                                edit_name1.setText(name);
//                                edit_email1.setText(email);
//                                edit_phone1.setText(phone);
//                                edit_department1.setText(department);
//                                edit_branch1.setText(branch);
//
//
//
//
//                            }
//
//                        }    catch (Exception e) {
//                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // error
//                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                Map<String, String> params = new HashMap<String, String>();
//
//
//
//
////                params.put("mac",maclis);
//
//                return params;
//            }
//        };
//
//        int MY_SOCKET_TIMEOUT_MS=100000;
//
//        postRequest.setRetryPolicy(new DefaultRetryPolicy(
//                MY_SOCKET_TIMEOUT_MS,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(postRequest);
//
//
//
//
//    }
//}
