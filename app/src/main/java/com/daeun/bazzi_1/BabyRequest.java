package com.daeun.bazzi_1;

import android.support.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BabyRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private  String URL ="http://bazzi.dothome.co.kr/BabyRegister.php";
    private Map<String, String> map;

    public BabyRequest(String babyName,String babyMW, String babyAge, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("babyName",babyName);
        map.put("babyMW",babyMW);
        map.put("babyAge",babyAge);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
