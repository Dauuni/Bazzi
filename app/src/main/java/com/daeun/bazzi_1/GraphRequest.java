package com.daeun.bazzi_1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GraphRequest extends StringRequest {

    // 서버 url 설정 (php 파일 연동)
    final static private  String URL ="http://bazzi.dothome.co.kr/graphRegister1.php";
    private Map<String, String> map;

    public GraphRequest(String babyDate, Float babyCM, Float babyKG, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("babyDate",babyDate);
        map.put("babyCM",babyCM+"");
        map.put("babyKG",babyKG+"");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}