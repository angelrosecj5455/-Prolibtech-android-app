package com.example.project_management;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_view_rating extends BaseAdapter {
        String []name,image,rating,date;
        private Context context;
    public Custom_view_rating(Context applicationContext, String[] name, String[] image, String[] rating, String[] date) {
        this.context=applicationContext;
        this.name=name;
        this.image=image;
        this.rating=rating;
        this.date=date;

    }


    @Override
    public int getCount() {
        return name.length;
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
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_view_rating, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.uname);
        TextView tv2 = (TextView) gridView.findViewById(R.id.rdate);

        ImageView im = (ImageView) gridView.findViewById(R.id.imageView5);

        RatingBar rate_bar=(RatingBar)gridView.findViewById(R.id.ratingBar2) ;
        rate_bar.setRating(Float.parseFloat(String.valueOf(rating[i])));


        im.setVisibility(View.INVISIBLE);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(date[i]);

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");

        String url = "http://" + ip + ":5000" + image[i] ;

        Log.d("iiiiiii----",url);
        Picasso.with(context).load(url).into(im);

        return gridView;
    }
}
