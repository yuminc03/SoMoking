package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class alarm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //        액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TimePicker timePicker = (TimePicker) this.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true); // 24H Mode.

        //회원수정 화면 전환
        ImageButton imageButton4 = (ImageButton)findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),activity_join.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton6 = (ImageButton)findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),map.class);
                startActivity(intent);
            }
        });







    }
}