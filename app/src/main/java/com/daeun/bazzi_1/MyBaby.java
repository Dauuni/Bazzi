package com.daeun.bazzi_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MyBaby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_baby);

        Button buttonSave = (Button) findViewById(R.id.buttonSave) ;
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                EditText editTextNo = (EditText) findViewById(R.id.editTextNo) ;
                intent.putExtra("contact_no", editTextNo.getText().toString()) ;

                // Name 입력 값을 String 값으로 그대로 전달.
                EditText editTextName = (EditText) findViewById(R.id.editTextName) ;
                intent.putExtra("contact_name", editTextName.getText().toString()) ;

                // Phone 입력 값을 String 값으로 그대로 전달.
                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone) ;
                intent.putExtra("contact_phone", editTextPhone.getText().toString()) ;

                setResult(RESULT_OK, intent) ;
                finish() ;

            }
        });
    }

}
