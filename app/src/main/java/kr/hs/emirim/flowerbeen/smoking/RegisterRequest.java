package kr.hs.emirim.flowerbeen.smoking;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://10.0.2.2:81/Register.php";
    private Map<String, String> map;


    public RegisterRequest(String userID, String name, String password, String phone, String address, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
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
