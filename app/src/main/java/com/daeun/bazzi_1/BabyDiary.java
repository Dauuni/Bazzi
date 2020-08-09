package com.daeun.bazzi_1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BabyDiary extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadList();  //작성된 일기 항목 가져오기

        writeBtn = (Button)findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.Diary2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }

    public void loadList(){ //작성된 일기 항목 가져오는 작업
        LinearLayout layout = (LinearLayout)findViewById(R.id.list);
        layout.setBackgroundColor(Color.argb(100,255,255,255));
        String[] lists = fileList();
        if (lists.equals(null) || lists.length == 0) {
            layout.addView(makeTextView("※ 작성된 일기가 없습니다.","nt"));
        }else{
            layout.removeAllViews();
            for (int i=0;i<lists.length;i++){
                if (lists[i].endsWith(".txt"))
                    layout.addView(makeTextView(lists[i],"t"+i));
            }
            if (layout.getChildCount()==0)
                layout.addView(makeTextView("※ 작성된 일기가 없습니다.","nt"));
        }
    }
    public TextView makeTextView(String label, String tag){ //TextView 생성
        TextView tv = new TextView(this);
        tv.setText(label);
        tv.setTag(tag);
        tv.setOnClickListener(this);
        tv.setTextColor(Color.argb(200,255,255,255));
        tv.setBackground(ContextCompat.getDrawable(this,R.drawable.diary_text));
        tv.setTextSize(25);
        tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        return tv;
    }
    @Override
    public void onClick(View v){ //[작성] 버튼 또는 일기 목록을 클릭하면 자동 실행
        Intent it = new Intent(BabyDiary.this,DiaryWork.class);
        if (v==writeBtn){
            it.putExtra("fName","new");
        }else{
            TextView tv = (TextView)v;
            String tLabel = (String)tv.getText();
            it.putExtra("fName",tLabel);
        }
        startActivity(it);
        finish();
    }
//    @Override
//    public boolean onCreateOptionsItemSelected(MenuItem item){ //옵션 메뉴 항목 클릭 시 자동 실행
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        ///noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//
//
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.Baby);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.Diary2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
