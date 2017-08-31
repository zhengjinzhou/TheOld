package zhou.com.theold.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonCityTown;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.ToastUtil;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int IMAGE_OPEN = 1;//打开照片的标记
    private ImageView iv_more;
    private TextView rl_username;
    private TextView rl_gender;
    private TextView rl_birthday;
    private TextView rl_town_id;
    private TextView rl_diploma;
    private TextView rl_religion;
    private TextView rl_marriage;
    private TextView rl_living;
    private TextView rl_income;
    private TextView rl_incomeways;
    private TextView rl_payways;
    private TextView rl_oafish;
    private TextView rl_insanity;
    private TextView rl_illness;
    private TextView rl_accident;
    private TextView tv_upload;
    private List<Integer> idList;
    private List<String> townNameList;

    //要上传的数据
    private String username;
    private int town_id;
    private int gender;
    private int religion;
    private String birthday;
    private String diploma;
    private String marriage;
    private String income;
    private String living;
    private String incomeways;
    private String payways;
    private String oafish;
    private String insanity;
    private String illness;
    private String accident;
    private TextView tv_data_back;
    private String pathImage;
    private String pictureName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        initCityTown();//获取城镇信息
        initUI();
    }

    /**
     *  获取城镇信息
     */
    private void initCityTown() {
        String city_town_name = FileDataUtil.loadDataFile(getApplicationContext(), "city_town_name");
        idList = new ArrayList<>();
        townNameList = new ArrayList<>();
        Gson gson = new Gson();
        JsonCityTown jsonCityTown = gson.fromJson(city_town_name, JsonCityTown.class);
        List<JsonCityTown.TownsBean> towns = jsonCityTown.getTowns();
        for(int i=0;i < towns.size();i++){
            JsonCityTown.TownsBean townsBean = towns.get(i);
            int id = townsBean.getId();
            String townName = townsBean.getTownName();
            idList.add(id);
            townNameList.add(townName);
        }
    }

    private void initUI() {
        tv_data_back = (TextView) findViewById(R.id.tv_data_back);
        tv_data_back.setOnClickListener(this);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_more.setOnClickListener(this);
        tv_upload = (TextView) findViewById(R.id.tv_upload);
        tv_upload.setOnClickListener(this);
        rl_username = (TextView) findViewById(R.id.rl_username);
        rl_username.setOnClickListener(this);
        rl_gender = (TextView) findViewById(R.id.rl_gender);
        rl_gender.setOnClickListener(this);
        rl_birthday = (TextView) findViewById(R.id.rl_birthday);
        rl_birthday.setOnClickListener(this);
        rl_town_id = (TextView) findViewById(R.id.rl_town_id);
        rl_town_id.setOnClickListener(this);
        rl_diploma = (TextView) findViewById(R.id.rl_diploma);
        rl_diploma.setOnClickListener(this);
        rl_religion = (TextView) findViewById(R.id.rl_religion);
        rl_religion.setOnClickListener(this);
        rl_marriage = (TextView) findViewById(R.id.rl_marriage);
        rl_marriage.setOnClickListener(this);
        rl_living = (TextView) findViewById(R.id.rl_living);
        rl_living.setOnClickListener(this);
        rl_income = (TextView) findViewById(R.id.rl_income);
        rl_income.setOnClickListener(this);
        rl_incomeways = (TextView) findViewById(R.id.rl_incomeways);
        rl_incomeways.setOnClickListener(this);
        rl_payways = (TextView) findViewById(R.id.rl_payways);
        rl_payways.setOnClickListener(this);
        rl_oafish = (TextView) findViewById(R.id.rl_oafish);
        rl_oafish.setOnClickListener(this);
        rl_insanity = (TextView) findViewById(R.id.rl_insanity);
        rl_insanity.setOnClickListener(this);
        rl_illness = (TextView) findViewById(R.id.rl_illness);
        rl_illness.setOnClickListener(this);
        rl_accident = (TextView) findViewById(R.id.rl_accident);
        rl_accident.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            //返回
            case R.id.tv_data_back:
                finish();
                break;
            // 上传
            case R.id.tv_upload:
                System.out.println(username +"-"+ pathImage+"-" + gender +"-"+ birthday+"-" + town_id+"-"
                        + diploma +"-"+ religion +"-"+ marriage+"-" + living+"-" + income+"-"
                        + incomeways+"-" + payways+"-" + oafish+"-" + insanity+"-"
                        + illness+"-" + accident);
                String gender2 = gender+"";
                String town_id2 = town_id+"";
                String religion2 = religion+"";

                if(username==null || pathImage==null || gender2==null || birthday ==null||town_id2==null||diploma==null
                        || religion2 ==null||marriage==null||living==null||income==null||incomeways==null||payways==null
                        ||oafish==null||insanity==null||illness==null||accident==null){
                    ToastUtil.show(getApplicationContext(),"您没有完成所有信息");
                }else{
                    OkHttpClient okHttpClient = new OkHttpClient();
                    File file = new File(pathImage);
                    MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
                    RequestBody fileBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, file);

                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM).addFormDataPart("photo",pictureName,fileBody)
                            .addFormDataPart("username", username).addFormDataPart("gender",gender2)
                            .addFormDataPart("birthday",birthday)
                            .addFormDataPart("town_id",town_id2).addFormDataPart("diploma", diploma)
                            .addFormDataPart("religion",religion2).addFormDataPart("marriage",marriage)
                            .addFormDataPart("living[]",living).addFormDataPart("income", income)
                            .addFormDataPart("incomeways[]",incomeways).addFormDataPart("payways[]",payways)
                            .addFormDataPart("oafish",oafish).addFormDataPart("insanity[]",insanity)
                            .addFormDataPart("illness[]",illness).addFormDataPart("accident[]",accident)
                            .build();
                    Request request = new Request.Builder().url(ConstantValue.URL_TWO).post(requestBody).build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.show(getApplicationContext(),"请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String str = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.show(getApplicationContext(),"上传成功");
                                }
                            });
                        }
                    });
                }




                break;
            // 添加名字
            case R.id.rl_username:
                showUserDialog();
                break;
            // 添加照片
            case R.id.iv_more:
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,IMAGE_OPEN);
                break;
            // 性别
            case R.id.rl_gender:
                List<String> genderList = new ArrayList<>();
                ArrayList<Integer> genderInt = new ArrayList<>();
                genderList.add("男");
                genderList.add("女");
                genderInt.add(1);
                genderInt.add(0);
                ShowPickerInView(genderList,genderInt,rl_gender);
                break;
            // 出生日期
            case R.id.rl_birthday:
                showTimePicker();
                break;
            // 所属镇区
            case R.id.rl_town_id:
               ShowPickerInView(townNameList,idList,rl_town_id);
                break;
            // 文化程度
            case R.id.rl_diploma:
                List<String> diplomaList = new ArrayList<>();
                Map<String, String> map = new HashMap<>();
                map.put("文盲","A1.1");
                map.put("小学","A1.2");
                map.put("初中","A1.3");
                map.put("高中/技校/中专","A1.4");
                map.put("大专以上","A1.5");
                map.put("不详","A1.6");

                diplomaList.add("文盲");
                diplomaList.add("小学");
                diplomaList.add("初中");
                diplomaList.add("高中/技校/中专");
                diplomaList.add("大专以上");
                diplomaList.add("不详");
                ShowPickerStringView(diplomaList,map,rl_diploma,"文化程度：");
                break;
            // 宗教信仰
            case R.id.rl_religion:
                List<String> religionList = new ArrayList<>();
                ArrayList<Integer> relitionInt = new ArrayList<>();
                religionList.add("有");
                religionList.add("无");
                relitionInt.add(1);
                relitionInt.add(0);
                ShowPickerInView(religionList,relitionInt,rl_religion);
                break;
            // 婚姻状态
            case R.id.rl_marriage:
                List<String> marriageList = new ArrayList<>();
                Map<String, String> marriageMap = new HashMap<>();
                marriageMap.put("已婚","A2.1");
                marriageMap.put("丧偶","A2.2");
                marriageMap.put("离婚","A2.3");
                marriageMap.put("未婚","A2.4");
                marriageMap.put("未说明婚姻状况","A2.5");

                marriageList.add("已婚");
                marriageList.add("丧偶");
                marriageList.add("离婚");
                marriageList.add("未婚");
                marriageList.add("未说明婚姻状况");
                ShowPickerStringView(marriageList,marriageMap,rl_marriage,"婚姻状态：");
                break;
            // 居住方式
            case R.id.rl_living:
                showLivingDialog();
                break;
            // 个人收入
            case R.id.rl_income:
                showIncomeDialog();
                break;
            // 收入方式
            case R.id.rl_incomeways:
                showIncomeWaysDialog();
                break;
            // 支付方式
            case R.id.rl_payways:
                showPaywaysDialog();
                break;
            // 痴呆程度
            case R.id.rl_oafish:
                List<String> oafishList = new ArrayList<>();
                Map<String, String> oafishMap = new HashMap<>();
                oafishMap.put("无痴呆表现","A6.1");
                oafishMap.put("轻度痴呆","A6.2");
                oafishMap.put("中度痴呆","A6.3");
                oafishMap.put("重度痴呆","A6.4");

                oafishList.add("无痴呆表现");
                oafishList.add("轻度痴呆");
                oafishList.add("中度痴呆");
                oafishList.add("重度痴呆");
                ShowPickerStringView(oafishList,oafishMap,rl_oafish,"痴呆程度：");
                break;
            // 精神病表现
            case R.id.rl_insanity:
                showInsanityDialog();
                break;
            // 患病
            case R.id.rl_illness:
                showIllnessDialog();
                break;
            // 意外事件
            case R.id.rl_accident:
                showAccidentDialog();
                break;
            default:
                break;
        }
    }

    /**
     * 选择照片的回函数
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if(resultCode == RESULT_OK && requestCode==IMAGE_OPEN){
            Uri uri = data.getData();
            if(!TextUtils.isEmpty(uri.getAuthority())){
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[]{MediaStore.Images.Media.DATA},
                        null,
                        null,
                        null);
                if(null == cursor){
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                //图片地址
                pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                pictureName = pathImage.substring(pathImage.lastIndexOf("/")+1);
                Bitmap bitmap = BitmapFactory.decodeFile(pathImage);
                iv_more.setImageBitmap(bitmap);
                System.out.println(pathImage);
            }
        }
    }

    /**
     * 患病
     */
    private void showIllnessDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_illness, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        final ArrayList<String> illnessList = new ArrayList<>();
        final ArrayList<String> letterList = new ArrayList<>();
        CheckBox cb_illness_nothing = (CheckBox) view
                .findViewById(R.id.cb_illness_nothing);
        CheckBox cb_illness_hypertension = (CheckBox) view
                .findViewById(R.id.cb_illness_hypertension);
        CheckBox cb_illness_CHD = (CheckBox) view
                .findViewById(R.id.cb_illness_CHD);
        CheckBox cb_illness_heart = (CheckBox) view
                .findViewById(R.id.cb_illness_heart);
        CheckBox cb_illness_diabetes = (CheckBox) view
                .findViewById(R.id.cb_illness_diabetes);
        CheckBox cb_illness_digestion = (CheckBox) view
                .findViewById(R.id.cb_illness_digestion);
        CheckBox cb_illenss_bone = (CheckBox) view
                .findViewById(R.id.cb_illenss_bone);
        CheckBox cb_illenss_hyperlipaemia = (CheckBox) view
                .findViewById(R.id.cb_illenss_hyperlipaemia);
        CheckBox cb_illenss_brain = (CheckBox) view
                .findViewById(R.id.cb_illenss_brain);
        CheckBox cb_illenss_cancer = (CheckBox) view
                .findViewById(R.id.cb_illenss_cancer);
        CheckBox cb_illenss_kidney = (CheckBox) view
                .findViewById(R.id.cb_illenss_kidney);
        CheckBox cb_illenss_anemia = (CheckBox) view
                .findViewById(R.id.cb_illenss_anemia);
        final EditText et_illenss_others = (EditText) view
                .findViewById(R.id.et_illenss_others);
        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);
        cb_illness_nothing
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.1");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.1");
                        }
                    }
                });
        cb_illness_hypertension
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.2");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.2");
                        }
                    }
                });
        cb_illness_CHD
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.3");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.3");
                        }
                    }
                });
        cb_illness_heart
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.4");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.4");
                        }
                    }
                });
        cb_illness_diabetes
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.5");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.5");
                        }
                    }
                });
        cb_illness_digestion
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.6");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.6");
                        }
                    }
                });
        cb_illenss_bone
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.7");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.7");
                        }
                    }
                });
        cb_illenss_hyperlipaemia
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.8");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.8");
                        }
                    }
                });
        cb_illenss_brain
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.9");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.9");
                        }
                    }
                });
        cb_illenss_cancer
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.10");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.10");
                        }
                    }
                });
        cb_illenss_kidney
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.11");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.11");
                        }
                    }
                });
        cb_illenss_anemia
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            illnessList.add(mode);
                            letterList.add("A8.12");
                        } else {
                            illnessList.remove(mode);
                            letterList.remove("A8.12");
                        }
                    }
                });
        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String illenss_others = et_illenss_others.getText().toString();
                if (!TextUtils.isEmpty(illenss_others)) {
                    illnessList.add(illenss_others);
                    letterList.add("A8.13");
                    String string = letterList.toString();
                    illness = string.substring(1, string.length() - 1);
                    illness = illness + "&illenss_others=" + illenss_others;
                } else {
                    String string = letterList.toString();
                    illness = string.substring(1, string.length() - 1);
                }
                rl_illness.setText("患病：" + illnessList.toString());
                System.out.println("========患病========" + illness);
                dialog.dismiss();
            }
        });
    }

    // 未完成
    private void showAccidentDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_accident, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        final ArrayList<String> accidentList = new ArrayList<>();
        final ArrayList<String> letterList = new ArrayList<>();

        CheckBox cb_accident_dumble = (CheckBox) view
                .findViewById(R.id.cb_accident_dumble);
        CheckBox cb_dumble_one = (CheckBox) view
                .findViewById(R.id.cb_dumble_one);
        CheckBox cb_dumble_two = (CheckBox) view
                .findViewById(R.id.cb_dumble_two);
        CheckBox cb_dumble_three = (CheckBox) view
                .findViewById(R.id.cb_dumble_three);
        CheckBox cb_accident_losting = (CheckBox) view
                .findViewById(R.id.cb_accident_losting);
        CheckBox cb_losting_noe = (CheckBox) view
                .findViewById(R.id.cb_losting_noe);
        CheckBox cb_losting_two = (CheckBox) view
                .findViewById(R.id.cb_losting_two);
        CheckBox cb_losting_three = (CheckBox) view
                .findViewById(R.id.cb_losting_three);
        CheckBox cb_illenss_dysphagia = (CheckBox) view
                .findViewById(R.id.cb_illenss_dysphagia);
        CheckBox cb_dysphagia_one = (CheckBox) view
                .findViewById(R.id.cb_dysphagia_one);
        CheckBox cb_dysphagia_two = (CheckBox) view
                .findViewById(R.id.cb_dysphagia_two);
        CheckBox cb_dysphagia_three = (CheckBox) view
                .findViewById(R.id.cb_dysphagia_three);
        CheckBox cb_accident_kill = (CheckBox) view
                .findViewById(R.id.cb_accident_kill);
        CheckBox cb_kill_one = (CheckBox) view.findViewById(R.id.cb_kill_one);
        CheckBox cb_kill_two = (CheckBox) view.findViewById(R.id.cb_kill_two);
        CheckBox cb_kill_three = (CheckBox) view
                .findViewById(R.id.cb_kill_three);
        final EditText et_accident_others = (EditText) view
                .findViewById(R.id.et_accident_others);

        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);
        cb_accident_dumble
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.1.1");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.1.1");
                        }
                    }
                });
        cb_dumble_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    accidentList.add(mode);
                    letterList.add("A9.1.2");
                } else {
                    accidentList.remove(mode);
                    letterList.remove("A9.1.2");
                }
            }
        });
        cb_dumble_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    accidentList.add(mode);
                    letterList.add("A9.1.3");
                } else {
                    accidentList.remove(mode);
                    letterList.remove("A9.1.3");
                }
            }
        });
        cb_dumble_three
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.1.4");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.1.4");
                        }
                    }
                });
        cb_accident_losting
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.2.1");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.2.1");
                        }
                    }
                });
        cb_losting_noe
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.2.2");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.2.2");
                        }
                    }
                });
        cb_losting_two
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.2.3");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.2.3");
                        }
                    }
                });
        cb_losting_three
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.2.4");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.2.4");
                        }
                    }
                });
        cb_illenss_dysphagia
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.3.1");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.3.1");
                        }
                    }
                });
        cb_dysphagia_one
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.3.2");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.3.2");
                        }
                    }
                });
        cb_dysphagia_two
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.3.3");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.3.3");
                        }
                    }
                });
        cb_dysphagia_three
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.3.4");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.3.4");
                        }
                    }
                });
        cb_accident_kill
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            accidentList.add(mode);
                            letterList.add("A9.4.1");
                        } else {
                            accidentList.remove(mode);
                            letterList.remove("A9.4.1");
                        }
                    }
                });
        cb_kill_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    accidentList.add(mode);
                    letterList.add("A9.4.2");
                } else {
                    accidentList.remove(mode);
                    letterList.remove("A9.4.2");
                }
            }
        });
        cb_kill_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    accidentList.add(mode);
                    letterList.add("A9.4.3");
                } else {
                    accidentList.remove(mode);
                    letterList.remove("A9.4.3");
                }
            }
        });
        cb_kill_three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    accidentList.add(mode);
                    letterList.add("A9.4.4");
                } else {
                    accidentList.remove(mode);
                    letterList.remove("A9.4.4");
                }
            }
        });
        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String accident_others = et_accident_others.getText()
                        .toString().trim();
                if (!TextUtils.isEmpty(accident_others)) {
                    accidentList.add(accident_others);
                    letterList.add("A9.5");
                    String string = letterList.toString();
                    accident = string.substring(1, string.length() - 1);
                    accident = accident + "&accident_others=" + accident_others;
                } else {
                    String string = letterList.toString();
                    accident = string.substring(1, string.length() - 1);
                }
                rl_accident.setText("意外事件：" + accidentList.toString());
                // 將ArrayList里的数据转为字符串，然后去掉[]，剩下生成一条字符串作为全局变量
                System.out.println("========意外事件========" + accident);
                dialog.dismiss();
            }
        });
    }

    /**
     * 精神病状态
     */
    private void showInsanityDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_insanity, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        final ArrayList<String> insanityList = new ArrayList<>();
        final ArrayList<String> letterList = new ArrayList<>();
        final CheckBox cb_insanity_nothing = (CheckBox) view
                .findViewById(R.id.cb_insanity_nothing);
        CheckBox cb_insanity_schizophrenia = (CheckBox) view
                .findViewById(R.id.cb_insanity_schizophrenia);
        CheckBox cb_insanity_bipolar = (CheckBox) view
                .findViewById(R.id.cb_insanity_bipolar);
        CheckBox cb_insanity_stubborn = (CheckBox) view
                .findViewById(R.id.cb_insanity_stubborn);
        CheckBox cb_insanity_disorder = (CheckBox) view
                .findViewById(R.id.cb_insanity_disorder);
        CheckBox cb_insanity_epilepsy = (CheckBox) view
                .findViewById(R.id.cb_insanity_epilepsy);
        CheckBox cb_insanity_lag = (CheckBox) view
                .findViewById(R.id.cb_insanity_lag);
        final EditText et_insanity_others = (EditText) view
                .findViewById(R.id.et_insanity_others);
        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);

        cb_insanity_nothing
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.1");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.1");
                        }
                    }
                });
        cb_insanity_schizophrenia
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.2");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.2");
                        }
                    }
                });
        cb_insanity_bipolar
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.3");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.3");
                        }
                    }
                });
        cb_insanity_stubborn
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.4");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.4");
                        }
                    }
                });
        cb_insanity_disorder
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.5");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.5");
                        }
                    }
                });
        cb_insanity_epilepsy
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.6");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.6");
                        }
                    }
                });
        cb_insanity_lag
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            insanityList.add(mode);
                            letterList.add("A7.7");
                        } else {
                            insanityList.remove(mode);
                            letterList.remove("A7.7");
                        }
                    }
                });

        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String insanity_others = et_insanity_others.getText()
                        .toString().trim();
                if (!TextUtils.isEmpty(insanity_others)) {
                    insanityList.add(insanity_others);
                    letterList.add("A7.8");
                    String string = letterList.toString();
                    insanity = string.substring(1, string.length() - 1);
                    insanity = insanity + "&insanity_others=" + insanity_others;
                } else {
                    String string = letterList.toString();
                    insanity = string.substring(1, string.length() - 1);
                }
                rl_insanity.setText("精神病表现：" + insanityList.toString());
                System.out.println("=======精神病表现=======" + insanity);
                dialog.dismiss();
            }
        });
    }
    /**
     * 支付方式
     */
    private void showPaywaysDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_paysways, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();
        final ArrayList<String> paywayList = new ArrayList<>();
        final ArrayList<String> letterList = new ArrayList<>();
        CheckBox cb_paysway_staff = (CheckBox) view
                .findViewById(R.id.cb_paysway_staff);
        CheckBox cb_paysway_inmate = (CheckBox) view
                .findViewById(R.id.cb_paysway_inmate);
        CheckBox cb_paysway_village = (CheckBox) view
                .findViewById(R.id.cb_paysway_village);
        CheckBox cb_paysway_poverty = (CheckBox) view
                .findViewById(R.id.cb_paysway_poverty);
        CheckBox cb_paysway_commerce = (CheckBox) view
                .findViewById(R.id.cb_paysway_commerce);
        CheckBox cb_paysway_public = (CheckBox) view
                .findViewById(R.id.cb_paysway_public);
        CheckBox cb_paysway_private = (CheckBox) view
                .findViewById(R.id.cb_paysway_private);
        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);
        final EditText et_paysways_others = (EditText) view
                .findViewById(R.id.et_paysways_others);
        cb_paysway_staff
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.1");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.1");
                        }
                    }
                });
        cb_paysway_inmate
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.2");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.2");
                        }
                    }
                });
        cb_paysway_village
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.3");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.3");
                        }
                    }
                });
        cb_paysway_poverty
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.4");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.4");
                        }
                    }
                });
        cb_paysway_commerce
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.5");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.5");
                        }
                    }
                });
        cb_paysway_public
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.6");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.6");
                        }
                    }
                });
        cb_paysway_private
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            paywayList.add(mode);
                            letterList.add("A4.7");
                        } else {
                            paywayList.remove(mode);
                            letterList.remove("A4.7");
                        }
                    }
                });
        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String pay_others = et_paysways_others.getText().toString()
                        .trim();
                if (!TextUtils.isEmpty(pay_others)) {
                    paywayList.add(pay_others);
                    letterList.add("A4.8");
                    String string = letterList.toString();
                    payways = string.substring(1, string.length() - 1);
                    payways = payways + "&pay_others=" + pay_others;
                } else {
                    String string = letterList.toString();
                    payways = string.substring(1, string.length() - 1);
                }
                rl_payways.setText("支付方式：" + paywayList.toString());
                System.out.println("========支付方式========" + payways);
                dialog.dismiss();
            }
        });
    }
    /**
     * 收入方式
     */
    private void showIncomeWaysDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_incomeways,
                null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        final ArrayList<String> incomewayList = new ArrayList<>();
        final ArrayList<String> showList = new ArrayList<>();
        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);
        CheckBox cb_annuity = (CheckBox) view.findViewById(R.id.cb_annuity);
        CheckBox cb_children = (CheckBox) view.findViewById(R.id.cb_children);
        CheckBox cb_income_friend = (CheckBox) view
                .findViewById(R.id.cb_income_friend);
        final EditText et_income_others = (EditText) view
                .findViewById(R.id.et_income_others);

        cb_annuity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String incomeway = buttonView.getText().toString();
                if (isChecked) {
                    showList.add(incomeway);
                    incomewayList.add("A5.1");
                } else {
                    showList.remove(incomeway);
                    incomewayList.remove("A5.1");
                }
            }
        });
        cb_children.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String incomeway = buttonView.getText().toString();
                if (isChecked) {
                    showList.add(incomeway);
                    incomewayList.add("A5.2");
                } else {
                    showList.remove(incomeway);
                    incomewayList.remove("A5.2");
                }
            }
        });
        cb_income_friend
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String incomeway = buttonView.getText().toString();
                        if (isChecked) {
                            showList.add(incomeway);
                            incomewayList.add("A5.3");
                        } else {
                            showList.remove(incomeway);
                            incomewayList.remove("A5.3");
                        }
                    }
                });
        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String income_others = et_income_others.getText().toString()
                        .trim();
                if (!TextUtils.isEmpty(income_others)) {
                    showList.add(income_others);
                    incomewayList.add("A5.4");
                    String string = incomewayList.toString();
                    incomeways = string.substring(1, string.length() - 1);
                    incomeways = incomeways + "&income_others=" + income_others;
                    // 如此？？？incomewayList.add("A5.4&income_others="+income_others);
                } else {
                    String string = incomewayList.toString();
                    incomeways = string.substring(1, string.length() - 1);
                }
                rl_incomeways.setText("收入方式：" + showList.toString());
                System.out.println("=======收入方式========" + incomeways);
                dialog.dismiss();
            }
        });
    }
    /**
     * 居住方式
     */
    private void showLivingDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog dialog = builder.create();
        View view = View.inflate(this, R.layout.dialog_living, null);
        dialog.setView(view, 0, 0, 0, 0);
        dialog.show();

        final ArrayList<String> liveList = new ArrayList<>();
        final ArrayList<String> letterList = new ArrayList<>();
        CheckBox cb_alone = (CheckBox) view.findViewById(R.id.cb_alone);
        CheckBox cb_mate = (CheckBox) view.findViewById(R.id.cb_mate);
        CheckBox cb_children = (CheckBox) view.findViewById(R.id.cb_children);
        CheckBox cb_parent = (CheckBox) view.findViewById(R.id.cb_parent);
        CheckBox cb_brother = (CheckBox) view.findViewById(R.id.cb_brother);
        CheckBox cb_relative = (CheckBox) view.findViewById(R.id.cb_relative);
        CheckBox cb_not_relative = (CheckBox) view
                .findViewById(R.id.cb_not_relative);
        CheckBox cb_retirement = (CheckBox) view
                .findViewById(R.id.cb_retirement);
        Button bt_check_ensure = (Button) view
                .findViewById(R.id.bt_check_ensure);
        cb_alone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    System.out.println("onCheckedChanged" + mode);
                    liveList.add(mode);
                    letterList.add("A3.1");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.1");
                }
            }
        });
        cb_mate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.2");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.2");
                }
            }
        });
        cb_children.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.3");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.3");
                }
            }
        });
        cb_parent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.4");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.4");
                }
            }
        });
        cb_brother.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.5");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.5");
                }
            }
        });
        cb_relative.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.6");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.6");
                }
            }
        });
        cb_not_relative
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        String mode = buttonView.getText().toString();
                        if (isChecked) {
                            liveList.add(mode);
                            letterList.add("A3.7");
                        } else {
                            liveList.remove(mode);
                            letterList.remove("A3.7");
                        }
                    }
                });
        cb_retirement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String mode = buttonView.getText().toString();
                if (isChecked) {
                    liveList.add(mode);
                    letterList.add("A3.8");
                } else {
                    liveList.remove(mode);
                    letterList.remove("A3.8");
                }
            }
        });
        bt_check_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("liveList的值为：" + liveList);
                rl_living.setText("居住方式：" + liveList.toString());
                String string = letterList.toString();
                living = string.substring(1, string.length() - 1);
                System.out.println("========居住方式========" + living);
                dialog.dismiss();
            }
        });
    }

    /**
     * 个人收入
     */
    private void showIncomeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_income, null);
        dialog.setView(view);
        final EditText et_income = view.findViewById(R.id.et_income);
        Button bt_in_sure = view.findViewById(R.id.bt_in_sure);
        Button bt_in_cancel = view.findViewById(R.id.bt_in_cancel);
        bt_in_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_income.getText().toString().trim();
                if(name.isEmpty()){
                    ToastUtil.show(getApplicationContext(),"个人收入不能为空");
                }else{
                    income = name;
                    rl_income.setText("个人收入："+income+"/月");
                    System.out.println("老人的收入："+income);
                    dialog.dismiss();
                }
            }
        });
        bt_in_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * String 类型
     * @param list
     * @param map
     * @param rl_text
     */
    private void ShowPickerStringView(final List<String> list, final Map<String, String> map, final TextView rl_text, final String text) {
        OptionsPickerView pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = list.get(options1);
                String value = map.get(str);
                if(value.contains("A1")){
                    System.out.println(value);
                    diploma = value;
                }else if(value.contains("A2")){
                    System.out.println(value);
                    marriage = value;
                }else if(value.contains("A6")){
                    System.out.println(value);
                    oafish = value;
                }
                rl_text.setText(text+str);
            }
        }).build();
        pickerView.setPicker(list);
        pickerView.show();
    }

    /**
     * Integer 类型
     */
    private void showTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1910, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2022, 11, 31);
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(new Date());
                System.out.println("出生日期："+time);
                birthday = time;
                rl_birthday.setText("出生日期："+birthday);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
        pvTime.show();
    }


    /**
     * 自定义输入名字对话框
     */
    private void showUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_username, null);
        dialog.setView(view);
        final EditText et_username = view.findViewById(R.id.et_username);
        Button bt_sure = view.findViewById(R.id.bt_sure);
        Button bt_cancel = view.findViewById(R.id.bt_cancel);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_username.getText().toString().trim();
                if(name.isEmpty()){
                    ToastUtil.show(getApplicationContext(),"用户名不能为空");
                }else{
                    username = name;
                    rl_username.setText(username);
                    System.out.println("老人的名字："+username);
                    dialog.dismiss();
                }
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * rl_town_id
     * @param list
     *
     * @param intList
     * @param townString
     */
    private void ShowPickerInView(final List<String> list, final List<Integer> intList, final TextView townString) {
        final OptionsPickerView pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = list.get(options1);
                if("男".equals(str) || "女".equals(str)){
                    townString.setText("性别:"+str);
                    gender = intList.get(options1);
                }
                else if("有".equals(str) || "无".equals(str)){
                    townString.setText("宗教信仰："+str);
                    religion = intList.get(options1);
                }
                else{
                    townString.setText("所属镇区："+str);
                    town_id = intList.get(options1);
                }
                System.out.println("religion:"+religion);
                System.out.println("gender:"+gender);
                System.out.println("town_id:"+town_id);
            }
        }).build();
        pickerView.setPicker(list);
        pickerView.show();
    }
}
