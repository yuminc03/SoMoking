package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //        액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
}