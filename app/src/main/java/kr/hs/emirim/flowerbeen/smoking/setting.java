package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TimePicker;

public class setting extends AppCompatActivity {
    private ImageButton btn_update_user, btn_logout;
    private Switch btn_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //        액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_logout = (ImageButton)findViewById(R.id.btn_logout);//로그아웃 버튼

//        TimePicker timePicker = (TimePicker) this.findViewById(R.id.timePicker);
//        timePicker.setIs24HourView(true); // 24H Mode.

        btn_logout.setOnClickListener(v -> {//로그아웃 버튼을 누를 경우
            new AlertDialog.Builder(this/* 해당 액티비티를 가르킴 */)
                    .setTitle("로그아웃").setMessage("지금 로그아웃 하시겠습니까??")
                    .setPositiveButton("예", new DialogInterface.OnClickListener() {//로그아웃 하는 경우
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent intent = new Intent(setting.this/*현재 activity 위치*/ , MainActivity.class/*이동 activity 위치*/);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);//setFlags - 하나의 Flag를 지정한다.
                            //FLAG_ACTIVITY_CLEAR_TOP - 상위 스택 제거
                            //설정되어 있고 시작되는 activity가 현재 작업에서 이미 실행 중이면 해당 activity의
                            // 새 인스턴스를 시작하는 대신 그 위에있는 다른 모든 activity가 닫히고
                            // 이 Intent가 (지금 top)새로운 Intent 로서의 오래된 activity.
                            //쉽게 말하면, 실행하는 액티비티가 스택에 있으면 새로 시작하지 않고 상위 스택 모두 제거.
                            //예> [ABCDE]가 있고, E에서 C를 열면 상위 DE제거

                            //FLAG_ACTIVITY_SINGLE_TOP -하나의 Top
                            //설정된 경우 activity가 이미 기록 스택의 맨 위에서 실행중인 경우 activity가 시작되지 않습니다.
                            //액티비티가 이미 최상단에 있으면 다시 시작하지 않고 재사용.
                            //예> [ABCDE]가 있고, E에서 E를 열면 onPause()->onNewIntent() -> onResume()
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {//로그아웃 안하는 경우
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();//다이얼로그 종료
                        }
                    })
                    .show();
        });

        btn_sound = (Switch)findViewById(R.id.btn_sound);//소리 조정 버튼


        //회원수정 화면 전환
        btn_update_user = (ImageButton)findViewById(R.id.btn_update_user);//회원정보 수정 버튼
        btn_update_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), member.class);
                startActivity(intent);
            }
        });

    }
}