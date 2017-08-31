package zhou.com.theold.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonOldManData;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.CurrentTimeUtil;
import zhou.com.theold.utils.DES3Util;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.Md5Util;
import zhou.com.theold.utils.SpUtil;
import zhou.com.theold.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    private ImageView im_model;
    private EditText et_login_name;
    private EditText et_login_password;
    private Button bt_login;
    private String mobile;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        initLogin();
    }

    /**
     * 用户登录
     */
    private void initLogin() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取输入的帐号密码
                mobile = et_login_name.getText().toString().trim();
                pwd = et_login_password.getText().toString().trim();
                // 判断输入框是否为空
                if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(pwd)) {
                    ToastUtil.show(getApplicationContext(), "输入不能为空");
                } else {
                    // 请求网络获取信息
                    requestNetwork();
                }
            }
        });
    }

    /**
     * 请求网络获取信息
     */
    private void requestNetwork() {
        // 对密码的加密
        pwd = DES3Util.encrypt3DES(pwd, ConstantValue.ENCRYPTION_KEY, Charset.forName("UTF-8"));
        String _t = CurrentTimeUtil.nowTime();
        String joint = "_t=" + _t + "&mobile=" + mobile + "&opt=" + "2"
                + "&pwd=" + pwd + ConstantValue.APP_ENCRYPTION_KEY;
        String _s = Md5Util.encoder(joint);

        //进行post请求 使用的是okhttp框架
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("mobile", mobile)
                .add("pwd", pwd)
                .add("opt", "2")
                .add("_t", _t)
                .add("_s", _s)
                .build();
        Request request = new Request.Builder().url(ConstantValue.URL_ONE).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                FileDataUtil.saveDataToFile(getApplicationContext(), str, "old_man_data");
                Gson gson = new Gson();
                JsonOldManData jsonOldManData = gson.fromJson(str, JsonOldManData.class);
                String error = jsonOldManData.getError();
                String cityName = jsonOldManData.getCityName();
                //System.out.println("cityName="+cityName);
                SpUtil.putString(getApplicationContext(),ConstantValue.CITY_NAME,cityName);//记住该城市的名称
                if (error.equals("-1")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(LoginActivity.this, "登录成功");
                            Intent intent = new Intent(LoginActivity.this, LifeRecordActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.show(LoginActivity.this, "系统出错");
                        }
                    });
                }

                //Log.i("LoginActivity", str);
            }
        });
    }


    /**
     * 获取各孔控件
     */
    private void initUI() {
        im_model = (ImageView) findViewById(R.id.im_model);
        et_login_name = (EditText) findViewById(R.id.et_login_name);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        bt_login = (Button) findViewById(R.id.bt_login);
    }
}
