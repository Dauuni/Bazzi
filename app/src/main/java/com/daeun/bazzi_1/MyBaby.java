package com.daeun.bazzi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MyBaby extends AppCompatActivity {

    private EditText et_bname, et_mw, et_age;
    private Button baby_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_baby);

        et_bname=findViewById(R.id.et_bname);
        et_mw=findViewById(R.id.et_mw);
        et_age=findViewById(R.id.et_age);
        baby_register=findViewById(R.id.baby_register);


        //아기 등록 버튼 눌렀을 때
        baby_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText에 입력된 현재 입력되어 있는 값을 get 해온다.
                String babyName = et_bname.getText().toString();
                String babyMW = et_mw.getText().toString();
                String babyAge = et_age.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(),"아기 등록에 성공하였습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MyBaby.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(),"아기 등록에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                //서버로 volley를 이용해서 요청을 함.
                BabyRequest babyRequest = new BabyRequest(babyName, babyMW, babyAge,responseListener);
                RequestQueue queue = Volley.newRequestQueue(MyBaby.this);
                queue.add(babyRequest);
            }
        });
    }
}