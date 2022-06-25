package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomviewActivity extends BaseAdapter {
    private final Context context;
    String[] title,pname,sname,c_name,p_year;

    public CustomviewActivity(Context applicationContext, String[] title, String[]pname, String[]s_name, String[]c_name, String[]p_year) {
        this.context=applicationContext;

        this.title=title;
        this.pname=pname;
        this.p_year=p_year;
        this.c_name=c_name;
        this.sname=s_name;


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
            gridView=inflator.inflate(R.layout.customview,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.t17);
        TextView tv2=(TextView)gridView.findViewById(R.id.t14);
        TextView tv3=(TextView)gridView.findViewById(R.id.t2);
        TextView tv4=(TextView)gridView.findViewById(R.id.t3);
        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        try {     tv1.setText(pname[i]);
            tv2.setText(sname[i]);
            tv3.setText(c_name[i]);
            tv4.setText(p_year[i]);
            Log.d("-----","*****");
                 }catch (Exception ex) {
            Log.d("-----------------ex",ex.getMessage().toString());

        }

        return gridView;


    }

}


