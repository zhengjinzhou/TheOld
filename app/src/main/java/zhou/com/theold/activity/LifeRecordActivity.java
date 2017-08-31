package zhou.com.theold.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonOldManData;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.CurrentTimeUtil;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.Md5Util;
import zhou.com.theold.utils.SpUtil;
import zhou.com.theold.utils.ToastUtil;

public class LifeRecordActivity extends AppCompatActivity {

    private ListView lv_name_list;
    private ImageView iv_add;
    private MyAdapter myAdapter;
    private List<String> townNameList;
    private List<String> allList;
    private List<String> usernameList;
    private List<String> minuUrlList;
    private List<String> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_record);
        initUI();
        initData(); // 获取数据  并通过Gson解析
        initClick();//为每个username设置点击事件
    }

    /**
     * 对每个item对点击事件
     * 同时，每个点击都会有数据请求
     * 将数据保存到应用文件中，以备使用
     */
    private void initClick() {
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LifeRecordActivity.this,
                        AddDataActivity.class);
                startActivity(intent);
            }
        });
        myAdapter = new MyAdapter();
        lv_name_list.setAdapter(myAdapter);
        lv_name_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                if(usernameList.contains(allList.get(position))){
                    //保存用户名  保存当前用户的照片地址
                    SpUtil.putString(getApplicationContext(),ConstantValue.USERNAME,allList.get(position));
                    String _t = CurrentTimeUtil.nowTime();
                    String opt = "21";
                    final String cid = idList.get(position);
                    //记住该人照片的cid  用于下一个界面
                    SpUtil.putString(getApplicationContext(),ConstantValue.CID,cid);
                    String newJoint = "_t=" + _t+ "&cid=" + cid +"&opt=" + opt + ConstantValue.APP_ENCRYPTION_KEY;
                    String _s = Md5Util.encoder(newJoint);
                    OkHttpClient okHttpClient = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder()
                            .add("opt", opt)
                            .add("cid",cid)
                            .add("_t",_t)
                            .add("_s",_s)
                            .build();
                    Request request = new Request.Builder().url(ConstantValue.URL_ONE).post(formBody).build();
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
                            //System.out.println(str);
                            FileDataUtil.saveDataToFile(getApplicationContext(),str,"each_old_man");
                            System.out.println("老人的id"+cid);
                            Intent intent = new Intent(getApplicationContext(),DetailDataActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    /**
     * 获取数据  并通过Gson解析
     */
    private void initData() {
        allList = new ArrayList<>();//镇区和用户名
        townNameList = new ArrayList<>();//镇区
        usernameList = new ArrayList<>();//用户名
        minuUrlList = new ArrayList<>();//照片地址
        idList = new ArrayList<>();

        String old_man_data = FileDataUtil.loadDataFile(getApplicationContext(), "old_man_data");
        Gson gson = new Gson();
        JsonOldManData data = gson.fromJson(old_man_data, JsonOldManData.class);
        List<JsonOldManData.TownListBean> townList = data.getTownList();
        for(int i = 0;i<townList.size();i++){
            JsonOldManData.TownListBean townListBean = townList.get(i);
            String townName = townListBean.getTownName();
            idList.add("0");
            townNameList.add(townName);
            minuUrlList.add(townName);
            allList.add(townName);
            List<JsonOldManData.TownListBean.OldsBean> olds = townListBean.getOlds();
            for(int j=0;j<olds.size();j++){
                JsonOldManData.TownListBean.OldsBean oldsBean = olds.get(j);
                String username = oldsBean.getUsername();
                String miniUrl = oldsBean.getMiniUrl();
                int id = oldsBean.getId();
                idList.add(id+"");

                minuUrlList.add(miniUrl);
                usernameList.add(username);
                allList.add(username);
            }
        }
    }

    private void initUI() {
        iv_add = (ImageView) findViewById(R.id.iv_add);
        lv_name_list = (ListView) findViewById(R.id.lv_name_list);
    }

    // lv_name_list适配器
    public class MyAdapter extends BaseAdapter{

        /**
         * Item类型,int值.必须从0开始依次递增.
         * */
        private static final int TYPE_NAME = 0;
        private static final int TYPE_DES = 1;

        /**
         * Item Type 的数量
         * */
        private static final int TYPE_ITEM_COUNT = 2;

        @Override
        public int getItemViewType(int position) {
            if(usernameList.contains(allList.get(position))){
                return TYPE_NAME;
            } else {
                return TYPE_DES;
            }
        }

        @Override
        public int getViewTypeCount() {
            return TYPE_ITEM_COUNT;
        }

        @Override
        public int getCount() {
            return allList.size();
        }

        @Override
        public Object getItem(int i) {
            return allList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            ViewHolder holder = null;
            View2Holder holder2 = null;
            /**
             * 对类型进行判断,分别inflate不同的布局.
             * */
            switch (getItemViewType(position)){
                case TYPE_NAME:
                    holder = new ViewHolder();
                    if (convertView == null) {
                        convertView = View.inflate(getApplicationContext(),
                                R.layout.list_record_life, null);
                        holder.tv_name = (TextView) convertView
                                .findViewById(R.id.tv_name);
                        holder.im_person = (ImageView) convertView
                                .findViewById(R.id.im_person);
                        convertView.setTag(holder);
                    } else {
                        holder = (ViewHolder) convertView.getTag();
                    }
                    //这里放照片，稍后再续
                    String minPath = ConstantValue.urlPathImg+minuUrlList.get(position);
                    SpUtil.putString(getApplicationContext(),ConstantValue.MINIURL,minPath);//存储该老人的详细照片地址
                    System.out.println("图片地址:"+minPath);
                    holder.tv_name.setText(allList.get(position));// 怎样设置？？？？
                    Picasso.with(getApplicationContext())
                            .load(minPath).resize(30,30).centerCrop().placeholder(R.mipmap.model).into(holder.im_person);
                    break;
                case TYPE_DES:
                    holder2 = new View2Holder();
                    if (convertView == null) {
                        convertView = View.inflate(getApplicationContext(),
                                R.layout.list_record_life2, null);
                        holder2.tv_des = (TextView) convertView
                                .findViewById(R.id.tv_des);
                        convertView.setTag(holder2);
                    } else {
                        holder2 = (View2Holder) convertView.getTag();
                    }
                    holder2.tv_des.setText(allList.get(position));
                    break;
            }
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView im_person;
        TextView tv_name;
    }

    static class View2Holder {
        TextView tv_des;
    }
}
