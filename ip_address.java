package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ip_address extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button button_connect;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),ip_address.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_address);
        ed_ip=(EditText) findViewById(R.id.editTextIp);
        button_connect=(Button) findViewById(R.id.button1);

        button_connect.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        final String ip_connect = ed_ip.getText().toString();
        if (ip_connect.length()==0){
            ed_ip.setError("Field Cannot be empty!");
        }
        else{

            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            ed_ip.setText("");
            String url1="http://" + ip_connect + ":5000";
            SharedPreferences.Editor ed=sh.edit();
            ed.putString("ip",ip_connect);
            ed.putString("url",url1);
            ed.commit();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);



        }
    }
}