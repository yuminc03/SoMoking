package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class menu extends AppCompatActivity {
    private ImageButton ibtn_somoking, ibtn_alarm, ibtn_map, ibtn_setting;
    private Button btn_attend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //        액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ibtn_somoking = findViewById(R.id.ibtn_somoking);//소모킹
        ibtn_alarm = findViewById(R.id.ibtn_alarm);//알람
        ibtn_map = findViewById(R.id.ibtn_map);//지도
        ibtn_setting = findViewById(R.id.ibtn_setting);//설정

        btn_attend = findViewById(R.id.btn_attend);//출석

        ibtn_somoking.setOnClickListener(v -> {
            Intent intent = new Intent(menu.this, smoking.class);
            startActivity(intent);
        });
        ibtn_alarm.setOnClickListener(v -> {
            Intent intent = new Intent(menu.this, StartSmokeActivity.class);
            startActivity(intent);
        });
        ibtn_map.setOnClickListener(v -> {
            Intent intent = new Intent(menu.this, map.class);
            startActivity(intent);
        });
        ibtn_setting.setOnClickListener(v -> {
            Intent intent = new Intent(menu.this, setting.class);
            startActivity(intent);
        });
        btn_attend.setOnClickListener(v -> {
            //Intent intent = new Intent(menu.this, menu.class);
        });

    }
}