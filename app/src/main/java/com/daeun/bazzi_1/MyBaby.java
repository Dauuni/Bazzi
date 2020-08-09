package com.daeun.bazzi_1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MyBaby extends AppCompatActivity {

    private EditText et_bname, et_mw, et_age;
    private Button baby_register;

    private static String TAG = "bazzi";

    private static final String TAG_JSON="baby";
    private static final String TAG_NAME = "babyName";
    private static final String TAG_MW = "babyMW";
    private static final String TAG_AGE ="babyAge";

    ArrayList<HashMap<String, String>> mArrayList1;
    ListView mlistView1;
    String mJsonString1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_baby);

        et_bname=findViewById(R.id.et_bname);
        et_mw=findViewById(R.id.et_mw);
        et_age=findViewById(R.id.et_age);
        baby_register=findViewById(R.id.baby_register);

        mlistView1 = (ListView) findViewById(R.id.image_box2);
        mArrayList1 = new ArrayList<>();

        GetData task = new GetData();
        task.execute("http://bazzi.dothome.co.kr/BabyGetjson.php");


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

    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MyBaby.this,
                    "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "response  - " + result);

            if (result == null){

            }
            else {

                mJsonString1 = result;
                showResult();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }

    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString1);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            int i=jsonArray.length()-1;

            JSONObject item = jsonArray.getJSONObject(i);

            String name = item.getString(TAG_NAME);
            String mw = item.getString(TAG_MW);
            String age = item.getString(TAG_AGE);

            HashMap<String,String> hashMap = new HashMap<>();

            hashMap.put(TAG_NAME, name);
            hashMap.put(TAG_MW,mw);
            hashMap.put(TAG_AGE, age);

            mArrayList1.add(hashMap);


            ListAdapter adapter = new SimpleAdapter(
                    MyBaby.this, mArrayList1, R.layout.item_list2,
                    new String[]{TAG_NAME,TAG_MW,TAG_AGE},
                    new int[]{R.id.baby_inform1, R.id.baby_inform2, R.id.baby_inform3}
            );

            mlistView1.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }

    }
}