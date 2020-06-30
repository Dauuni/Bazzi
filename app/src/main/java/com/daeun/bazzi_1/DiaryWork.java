package com.daeun.bazzi_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryWork extends Activity implements View.OnClickListener {
    EditText content;
    Button listBtn, saveBtn, delBtn;
    String fName;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_work);
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String str = dayTime.format(new Date(time));

        content = (EditText)findViewById(R.id.edit);
        content.setBackground(ContextCompat.getDrawable(this,R.drawable.diary_jaksung));
        content.setText("\n     ");
        content.setSelection(content.length());

        Bundle params = getIntent().getExtras(); //넘어온 정보를 얻어냄
        fName = params.getString("fName");

        if (fName.equals("new")){ //새로 작성
            fName = "▶ "+ str +".txt";
            Toast.makeText(this,"새로운 일기를 작성하세요.",Toast.LENGTH_SHORT).show();
        }else{ //기존파일 읽어오기
            content.setText(readFile(this));
            Toast.makeText(this,"저장된 일기를 표시합니다.",Toast.LENGTH_SHORT).show();
        }

        listBtn = (Button)findViewById(R.id.listBtn);
        listBtn.setOnClickListener(this);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        delBtn = (Button)findViewById(R.id.delBtn);
        delBtn.setOnClickListener(this);

    }

    public void onBackPressed() {
        returnMain();
    }

    public String readFile(Context c){ //저장된 파일 읽어오기
        FileInputStream in = null; //파일 입력 스트림
        ByteArrayOutputStream out = null; //읽은 내용을 임시로 저장할 버퍼
        int len = 0;  //한 번에 읽을 수 있는 내용의 크기

        byte[] bStr = new byte[1024]; //버퍼 생성

        try{
            //FileInpuStream 객체를 얻어냄
            in = c.openFileInput(fName);
            //읽은 내용을 저장할 버퍼 생성
            out = new ByteArrayOutputStream();

            //파일의 끝이 아닐 때까지 반복 수행
            while ((len = in.read(bStr))!= -1)
                out.write(bStr, 0, len);  //읽은 내용을 버퍼에 저장

            //입출력 스트림 닫음
            out.close();
            in.close();
        }catch (Exception e){
            try{
                if(out != null) out.close();
                if (in != null) in.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        //버퍼의 내용을 문자열로 변환해 리턴
        return out.toString();
    }
    public void saveFile(Context c, String cStr){ // 파일 생성 및 입력된 내용 저장
        FileOutputStream out = null; //파일 출력스트림
        byte[] bStr = cStr.getBytes(); //문자열을 바이트 배열로 변환

        try {
            //FileOutputStream 객체 생성 - 파일 생성
            out = c.openFileOutput(fName,Context.MODE_PRIVATE);
            out.write(bStr, 0,bStr.length); //파일에 내용을 씀
            out.close(); //스트림 닫음 - 리소스 해제
        }catch (Exception e){
            try{
                if(out != null)out.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
    public void returnMain(){
        Intent it = new Intent(this,BabyDiary.class);
        startActivity(it);
        finish();
    }
    @Override
    public void onClick(View v){ //[목록], [저장], [삭제] 버튼을 누르면 자동 실행
        if (v==listBtn){
            returnMain();
        }else if(v ==saveBtn){
            String cStr = content.getText().toString();
            saveFile(this, cStr);
            Toast.makeText(this,"파일이 저장되었습니다.",Toast.LENGTH_SHORT).show();
            returnMain();
        }else if(v==delBtn){
            deleteFile(fName);
            content.setText("");
            Toast.makeText(this,"파일이 삭제되었습니다",Toast.LENGTH_SHORT).show();
            returnMain();
        }

    }
}

