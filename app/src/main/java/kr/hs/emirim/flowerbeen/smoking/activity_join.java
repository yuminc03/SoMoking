package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class activity_join extends AppCompatActivity {
    private EditText et_id, et_name, et_password, et_phone, et_address; //회원가입 입력 정보
    private Button btn_id_check, btn_register; //회원가입 버튼
    private AlertDialog dialog;//다이얼로그 창
    private boolean confirm = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //        액션바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //아이디 값 찾기
        et_id = findViewById(R.id.id); //아이디
        et_id.setText("gradio07");
        et_name = findViewById(R.id.textView2); //이름
        et_name.setText("gradio");
        et_password = findViewById(R.id.pw); //비번
        et_password.setText("gradio*11");
        et_phone = findViewById(R.id.editTextPhone); //전화번호
        et_phone.setText("010-7989-4656");
        et_address = findViewById(R.id.address); //주소
        et_address.setText("01802");

        //아이디 중복 체크
        btn_id_check = findViewById(R.id.btn_id_check);
        btn_id_check.setOnClickListener(v -> {
            String UserEmail = et_id.getText().toString();
            if (confirm) {
                return; //검증 완료
            }

            if (UserEmail.equals("")) {//아이디 입력을 안했을 경우
                AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);//알림창 띄우기
                dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
                dialog.show();
                return;
            }

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);
                            dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                            dialog.show();
                            et_id.setEnabled(false); //아이디값 고정
                            confirm = true; //검증 완료
                            btn_id_check.setBackgroundColor(getResources().getColor(R.color.colorGray));//버튼 색 바꾸기
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);
                            dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                            dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            ConfirmIdRequest confirmIdRequest = new ConfirmIdRequest(userID, responseListener);
            RequestQueue queue = Volley.newRequestQueue(activity_join.this);
            queue.add(confirmIdRequest);
        });

        // 회원가입 버튼 클릭 시 수행
        btn_register = findViewById(R.id.button2);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = et_id.getText().toString();
                String name = et_name.getText().toString();
                String password = et_password.getText().toString();
                String phone = et_phone.getText().toString();
                String address = et_address.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("mytag", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(),"회원 가입 성공! 환영합니다!",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(activity_join.this, alarm.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(),"회원 가입 실패.. 다시 시도해주세요..!",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                // 서버로 Volley를 이용해서 요청을 함.
                RegisterRequest registerRequest = new RegisterRequest(userID,name,password,phone,address, responseListener);
                RequestQueue queue = Volley.newRequestQueue(activity_join.this);
                queue.add(registerRequest);

            }
        });

//        btn_id_check = findViewById(R.id.btn_id_check);//이메일 중복 확인
//        btn_id_check.setOnClickListener(view -> {
//            String UserEmail = et_id.getText().toString();
//            if (validate) {
//                return; //검증 완료
//            }
//
//            if (UserEmail.equals("")) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);
//                dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인", null).create();
//                dialog.show();
//                return;
//            }
//
//            Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    try {
//
//                        JSONObject jsonResponse = new JSONObject(response);
//                        boolean success = jsonResponse.getBoolean("success");
//
//                        if (success) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                            dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
//                            dialog.show();
//                            join_email.setEnabled(false); //아이디값 고정
//                            validate = true; //검증 완료
//                        }
//                        else {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);
//                            dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
//                            dialog.show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            ValidateRequest validateRequest = new ValidateRequest(UserEmail, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
//            queue.add(validateRequest);
//        });
//        btn_register = findViewById(R.id.button2);
//        btn_register.setOnClickListener(v -> { //회원가입 버튼을 눌렀을 때
//            try{
//                String result;
//                String email = et_id.getText().toString();
//                String name = et_name.getText().toString();
//                String password = et_password.getText().toString();
//                String phone = et_phone.getText().toString();
//                String address = et_address.getText().toString();
//
//                if (email.equals("") || name.equals("") || password.equals("") || phone.equals("")
//                        || address.equals("")) {//모든 데이터를 입력했는지 확인
//                    AlertDialog.Builder builder = new AlertDialog.Builder(activity_join.this);
//                    dialog = builder.setMessage("모든 정보를 입력해주세요!").setNegativeButton("확인", null).create();
//                    dialog.show();
//                    return;
//                }
//
//                Communication comm = new Communication();
//                result = comm.execute(email, name, password, phone, address).get();
//                //System.out.println(result);
//                if(result.equals("회원 가입 성공!")){
//                    Intent intent = new Intent(getApplicationContext(), alarm.class);
//                    startActivity(intent);
//                    Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
//                }
//                else if(result.equals("이미 존재하는 이메일 입니다..")){
//                    Toast.makeText(this, "이미 가입한 이메일입니다!", Toast.LENGTH_LONG).show();
//                    et_id.setText("");
//                    et_name.setText("");
//                    et_password.setText("");
//                    et_phone.setText("");
//                    et_address.setText("");
//                }
//                else{
//                    Toast.makeText(this, "회원가입에 실패하였습니다...", Toast.LENGTH_LONG).show();
//                    et_id.setText("");
//                    et_name.setText("");
//                    et_password.setText("");
//                    et_phone.setText("");
//                    et_address.setText("");
//                }
//            }catch (Exception e){
//                Log.i("DBtest", "..error..");
//            }
//        });

    }
}