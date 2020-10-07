package com.daeun.bazzi_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Use extends AppCompatActivity implements View.OnTouchListener {
    private ViewFlipper flipper;
    float xAtDown;
    float xAtUp;
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use);

        flipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        flipper.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event){
        if(v!=flipper) return false;
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            xAtDown=event.getX();
        }
        else if(event.getAction()==MotionEvent.ACTION_UP){
            xAtUp=event.getX();
            if(xAtDown>xAtUp){
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.left_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.left_out));
                count++;
                if(count<6)
                    flipper.showNext();
                else{
                    Toast.makeText(this,"마지막 이미지입니다.", Toast.LENGTH_SHORT).show();
                    count--;
                }
            }
            else if(xAtDown<xAtUp){
                flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.right_in));
                flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.right_out));
                count--;
                if(count>-1)
                    flipper.showPrevious();
                else{
                    Toast.makeText(this,"첫번째 페이지입니다.",Toast.LENGTH_SHORT).show();
                    count++;
                }
            }
        }
        return true;
    }
}
