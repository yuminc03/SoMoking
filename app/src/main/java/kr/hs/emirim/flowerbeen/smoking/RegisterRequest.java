package kr.hs.emirim.flowerbeen.smoking;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://127.0.0.1/Register.php";
    private Map<String, String> map;


    public RegisterRequest(String email, String name, String password, String phone, String address, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("email",email);
        map.put("name",name);
        map.put("password", password);
        map.put("phone", phone);
        map.put("address", address);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
