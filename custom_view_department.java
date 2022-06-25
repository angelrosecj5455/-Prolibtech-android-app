package com.example.project_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_view_department extends BaseAdapter {
    String []did,clg_name,dpt_name;
    private Context context;
    public custom_view_department(Context applicationContext, String[] did, String[] clgname, String[] dptname) {
        this.context=applicationContext;
        this.did=did;
        this.clg_name=clgname;
        this.dpt_name=dptname;
//        this.date=date;

    }


    @Override
    public int getCount() {
        return clg_name.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_department,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView14);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView18);
//        TextView tv3=(TextView)gridView.findViewById(R.id.t3);
//        ImageView im=(ImageView) gridView.findViewById(R.id.imageView3);



        tv1.setTextColor(Color.RED);//color setting
        tv2.setTextColor(Color.BLACK);
//        tv3.setTextColor(Color.BLACK);


        tv1.setText(clg_name[i]);
        tv2.setText(dpt_name[i]);
//        tv3.setText(post[i]);

//            ImageView im=(ImageView) gridView.findViewById(R.id.imageView101);
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
//        String ip=sh.getString("ip","");
//        String url="http://" + ip + ":4000"+pho[i];
//        Picasso.with(context).load(url). into(im);//circle

        return gridView;

    }
}