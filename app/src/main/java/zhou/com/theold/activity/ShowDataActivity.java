package zhou.com.theold.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonOldManNews;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.CurrentTimeUtil;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.Md5Util;
import zhou.com.theold.utils.SpUtil;

public class ShowDataActivity extends AppCompatActivity {

    private TextView tv_back;
    private TextView tv_show_name;
    private TextView tv_gender;
    private TextView tv_photo;
    private TextView tv_birthday;
    private TextView tv_income;
    private TextView tv_payways;
    private TextView tv_diploma;
    private TextView tv_marriage;
    private TextView tv_living;
    private TextView tv_religion;
    private TextView tv_oafish;
    private TextView tv_insanity;
    private TextView tv_illness;
    private TextView tv_accident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        initUI();
        initData();
    }

    /**
     * 获取文件数据
     *
     * 并展示
     */
    private void initData() {
        String show_old_news = FileDataUtil.loadDataFile(getApplicationContext(), "show_old_news");
        if(show_old_news.isEmpty()){
            return;
        }else{
            Gson gson = new Gson();
            JsonOldManNews jsonOldManNews = gson.fromJson(show_old_news, JsonOldManNews.class);
            JsonOldManNews.OldBean old = jsonOldManNews.getOld();
            String username = old.getUsername();
            int genderInt = old.getGender();
            String photoUrl = old.getPhotoUrl();
            String birthday = old.getBirthday();
            int income = old.getIncome();
            String payways = old.getPayways();
            String diploma = old.getDiploma();
            String marry_status = old.getMarry_status();
            String living = old.getLiving();
            int religionInt = old.getReligion();
            String oafish = old.getOafish();
            String insantity = old.getInsantity();
            String insantity_others = old.getInsantity_others();
            String illness = old.getIllness();
            String illness_others = old.getIllness_others();
            String accident = old.getAccident();

            tv_show_name.setText(username);
            if(genderInt == 1){
                tv_gender.setText("男");
            }else{
                tv_gender.setText("女");
            }
            tv_photo.setText(photoUrl);
            tv_birthday.setText(birthday);
            tv_income.setText(income+"");
            tv_payways.setText(payways);
            tv_diploma.setText(diploma);
            tv_marriage.setText(marry_status);
            tv_living.setText(living);
            if(religionInt==1){
                tv_religion.setText("有");
            }else{
                tv_religion.setText("无");
            }
            tv_oafish.setText(oafish);
            tv_insanity.setText(insantity+","+insantity_others);
            tv_illness.setText(illness+","+illness_others);
            tv_accident.setText(accident);
        }
    }



    private void initUI() {
        tv_back = (TextView) findViewById(R.id.tv_back2);
        tv_show_name = (TextView) findViewById(R.id.tv_show_name);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_photo = (TextView) findViewById(R.id.tv_photo);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_income = (TextView) findViewById(R.id.tv_income);
        tv_payways = (TextView) findViewById(R.id.tv_payways);
        tv_diploma = (TextView) findViewById(R.id.tv_diploma);
        tv_marriage = (TextView) findViewById(R.id.tv_marriage);
        tv_living = (TextView) findViewById(R.id.tv_living);
        tv_religion = (TextView) findViewById(R.id.tv_religion);
        tv_oafish = (TextView) findViewById(R.id.tv_oafish);
        tv_insanity = (TextView) findViewById(R.id.tv_insanity);
        tv_illness = (TextView) findViewById(R.id.tv_illness);
        tv_accident = (TextView) findViewById(R.id.tv_accident);

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
