package kr.hs.emirim.flowerbeen.smoking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class check_login extends AppCompatActivity {
    private EditText et_id;
    private Button btn_enter;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);

        et_id = findViewById(R.id.et_id);
        btn_enter = findViewById(R.id.btn_logout);

        btn_enter.setOnClickListener(v -> {
            userID = et_id.getText().toString();

            Toast.makeText(getApplicationContext(),"아이디 통과!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(check_login.this, member.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
        });
    }
}