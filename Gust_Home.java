package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gust_Home extends AppCompatActivity implements View.OnClickListener {
ImageView btall_project,btfav,btlogout;

TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gust_home);
        btall_project=(ImageView) findViewById(R.id.imageView7);
        t1=(TextView) findViewById(R.id.textView22);
        btfav=(ImageView) findViewById(R.id.imageView8);
        btlogout=(ImageView) findViewById(R.id.imageView9);
        t2=(TextView) findViewById(R.id.textView24);
        t3=(TextView) findViewById(R.id.textView25);
        btall_project.setOnClickListener(this);
        btfav.setOnClickListener(this);
        btlogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==btall_project){
startActivity(new Intent(getApplicationContext(),Gust_view_all_project.class));
        }if(view==btfav){
startActivity(new Intent(getApplicationContext(),gust_view_favourite.class));
        }if(view==btlogout){
startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }
    }
}