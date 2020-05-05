package com.daeun.bazzi_1;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQ_ADD_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton btnInform = (ImageButton) findViewById(R.id.imageButton1);
        ImageButton btnCradle = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton btnSlumber = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton btnSuyu = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton btnGraph = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton btnDiary = (ImageButton) findViewById(R.id.imageButton6);

        Intent intent_loading = new Intent(this, Loading.class);
        startActivity(intent_loading);

        btnInform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),Baby.class);
                startActivity(intent1);
            }
        });

        btnCradle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),Cradle.class);
                startActivity(intent2);
            }
        });

        btnSlumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),Slumber.class);
                startActivity(intent3);
            }
        });

        btnSuyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(),Alarm.class);
                startActivity(intent4);
            }
        });

        btnGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(),Test.class);
                startActivity(intent5);
            }
        });

        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(getApplicationContext(),Diary.class);
                startActivity(intent6);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {

                TextView textViewNo = (TextView) findViewById(R.id.textBox1);
                String no = intent.getStringExtra("contact_no");
                if (no != null)
                    textViewNo.setText(no);

                // Name 값을 String 타입 그대로 표시.
                TextView textViewName = (TextView) findViewById(R.id.textBox2);
                String name = intent.getStringExtra("contact_name");
                if (name != null)
                    textViewName.setText(name);

                // Phone 값을 String 타입 그대로 표시.
                TextView textViewPhone = (TextView) findViewById(R.id.textBox3);
                String phone = intent.getStringExtra("contact_phone");
                if (phone != null)
                    textViewPhone.setText(phone);
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intentBaby = new Intent(MainActivity.this, MyBaby.class);
            startActivityForResult(intentBaby, REQ_ADD_CONTACT);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
