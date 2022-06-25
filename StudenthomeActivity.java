package com.example.project_management;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_management.databinding.StudenthomeBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudenthomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private StudenthomeBinding binding;
ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = StudenthomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
//        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profiles, R.id.navigation_projects, R.id.navigation_logout)
                .setOpenableLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        im=(ImageView) findViewById(R.id.imageView6);
        //Picasso.with(getApplicationContext()).load(R.drawable.applogo).transform(new CircleTransform()).into(im);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.studenthome, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main2);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.nav_home)
        {
            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(i);

        }
        if(id==R.id.nav_slideshow)
        {
            Intent i = new Intent(getApplicationContext(), StudentProjectsActivity.class);
            startActivity(i);

        }
        if(id==R.id.nav_home1)
        {
            Intent i = new Intent(getApplicationContext(), StudenthomeActivity.class);
            startActivity(i);

        }
//        if(id==R.id.nav_favourite)
//        {
//            Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
//            startActivity(i);
//
//        }
//       if(id==R.id.nav_department)
//       {
//           Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
//          startActivity(i);
//
//       }
       if(id==R.id.nav_gallery)
        {
           Intent i = new Intent(getApplicationContext(),view_departments .class);
            startActivity(i);
        }
        if(id==R.id.nav_logout)
        {
            Intent i = new Intent(getApplicationContext(), ip_address.class);
            startActivity(i);

        }
        if(id==R.id.nav_upload)
        {
            Intent i = new Intent(getApplicationContext(), projectuploadActivity.class);
            startActivity(i);

        }
        return true;
    }
   }