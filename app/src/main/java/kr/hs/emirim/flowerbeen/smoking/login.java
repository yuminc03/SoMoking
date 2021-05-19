package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    private EditText et_id, et_pw;
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_id = findViewById(R.id.id);
        et_pw = findViewById(R.id.pw);
        btn_login = findViewById(R.id.button);
        btn_register = findViewById(R.id.btn_enter);

        //로그인 화면전환
        btn_login.setOnClickListener(v -> {
            // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
            String userID = et_id.getText().toString();
            String password = et_pw.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // TODO : 인코딩 문제때문에 한글 DB인 경우 로그인 불가
                        System.out.println("smoking" + response);
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if (success) { // 로그인에 성공한 경우
                            String userID = jsonObject.getString("userID");
                            String password = jsonObject.getString("password");

                            Toast.makeText(getApplicationContext(),"로그인 성공!",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this, alarm.class);
                            intent.putExtra("userID", userID);
                            intent.putExtra("password", password);
                            startActivity(intent);
                        } else { // 로그인에 실패한 경우
                            Toast.makeText(getApplicationContext(),"로그인 실패.. 다시 시도해주세요",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            LoginRequest loginRequest = new LoginRequest(userID, password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(login.this);
            queue.add(loginRequest);
        });

        //회원가입 화면전환
        btn_register.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),activity_join.class);
            startActivity(intent);
        });

    }
}