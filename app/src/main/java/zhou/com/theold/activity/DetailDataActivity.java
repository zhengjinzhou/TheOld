package zhou.com.theold.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonEachOldMan;
import zhou.com.theold.json.JsonOldManNews;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.CurrentTimeUtil;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.Md5Util;
import zhou.com.theold.utils.SpUtil;
import zhou.com.theold.utils.ToastUtil;

public class DetailDataActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_detail_back;
    private ImageView iv_detail_add;
    private LinearLayout ll_detail_data;
    private ImageView im_old_photo;
    private TextView tv_detail_name;
    private TextView tv_detail_address;
    private ListView lv_detail_data;
    private List<JsonEachOldMan.RecordListBean> recordList;
    private TextView tv_record;
    private String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);
        //用于请求下一个界面的数据  放在开头是保证下一界面有数据  查看老人的信息
        initNetData();
        initUI();
        //获取数据
        initListView();
    }

    private void initListView() {
        cityName = SpUtil.getString(getApplicationContext(), ConstantValue.CITY_NAME, "");
        String username = SpUtil.getString(getApplicationContext(), ConstantValue.USERNAME, "");
        String imagePath = SpUtil.getString(getApplicationContext(), ConstantValue.MINIURL, "");
        tv_detail_name.setText(username);

        Picasso.with(getApplicationContext()).load(imagePath).resize(100,100).centerCrop().placeholder(R.mipmap.model).into(im_old_photo);

        String each_old_man = FileDataUtil.loadDataFile(getApplicationContext(), "each_old_man");
        Gson gson = new Gson();
        JsonEachOldMan jsonEachOldMan = gson.fromJson(each_old_man, JsonEachOldMan.class);
        recordList = jsonEachOldMan.getRecordList();
        if(recordList.size()==0){
            tv_record.setText("以下没有记录");
            return;
        }else{
            MyAdapter adapter = new MyAdapter();
            lv_detail_data.setAdapter(adapter);
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return recordList.size();
        }

        @Override
        public Object getItem(int i) {
            return recordList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view==null){
                holder = new ViewHolder();
                view = View.inflate(getApplicationContext(),R.layout.list_detail_item,null);
                holder.im_photo = view.findViewById(R.id.im_photo);
                holder.tv_photo_communication =  view.findViewById(R.id.tv_photo_communication);
                holder.tv_photo_custom = view.findViewById(R.id.tv_photo_custom);
                holder.tv_photo_activity = view.findViewById(R.id.tv_photo_activity);
                holder.tv_photo_state =  view.findViewById(R.id.tv_photo_state);
                holder.tv_iv_date = view.findViewById(R.id.tv_iv_date);
                holder.tv_photo_participation = view.findViewById(R.id.tv_photo_participation);

                holder.tv_video_communication =  view.findViewById(R.id.tv_video_communication);
                holder.tv_video_custom =  view.findViewById(R.id.tv_video_custom);
                holder.tv_video_activity =  view.findViewById(R.id.tv_video_activity);
                holder.tv_video_state =  view.findViewById(R.id.tv_video_state);
                holder.tv_video_participation =  view.findViewById(R.id.tv_video_participation);
                holder.tv_vv_date = view.findViewById(R.id.tv_vv_date);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            for(int j=0;j<recordList.size();j++){
                JsonEachOldMan.RecordListBean recordListBean = recordList.get(j);
                String record_time = recordListBean.getRecord_time();//获取记录时间

                JsonEachOldMan.RecordListBean.RecordMapBean recordMap = recordListBean.getRecordMap();
                List<JsonEachOldMan.RecordListBean.PhotosBean> photos = recordListBean.getPhotos();
                //这里有问题，但是暂时只知道可能是照片啥的问题
                JsonEachOldMan.RecordListBean.PhotosBean photosBean = photos.get(0);

                String imgPath = photosBean.getImgPath();//大照片地址
                String miniUrl = photosBean.getMiniUrl();//小照片地址
                String mini = ConstantValue.urlPathImg + miniUrl;
                String img = ConstantValue.urlPathImg + imgPath;
                Picasso.with(getApplicationContext()).load(mini).resize(220,150).centerCrop().placeholder(R.mipmap.model).into(holder.im_photo);

                int perception = recordMap.get感知沟通();
                int daily = recordMap.get日常活动();
                int social = recordMap.get社会参与();
                int spirit = recordMap.get精神状态();
                int behavior = recordMap.get行为习惯();

                holder.tv_photo_communication.setText("沟通感知："+perception);
                holder.tv_photo_custom.setText("行为习惯："+behavior);
                holder.tv_photo_activity.setText("日常活动："+daily);
                holder.tv_photo_state.setText("精神状态："+spirit);
                holder.tv_photo_participation.setText("社会参与"+social);
                holder.tv_iv_date.setText(record_time);

                holder.tv_video_communication.setText("沟通感知："+perception);
                holder.tv_video_custom.setText("行为习惯："+behavior);
                holder.tv_video_activity.setText("日常活动："+daily);
                holder.tv_video_state.setText("精神状态："+spirit);
                holder.tv_video_participation.setText("社会参与"+social);
                holder.tv_vv_date.setText(record_time);
            }
            return view;
        }
    }
    static class ViewHolder{
        ImageView im_photo;
        TextView tv_photo_communication;
        TextView tv_photo_custom;
        TextView tv_photo_activity;
        TextView tv_photo_state;
        TextView tv_photo_participation;
        TextView tv_iv_date;
        TextView tv_vv_date;
        TextView tv_video_communication;
        TextView tv_video_custom;
        TextView tv_video_activity;
        TextView tv_video_state;
        TextView tv_video_participation;
    }

    private void initUI() {
        tv_detail_back = (TextView) findViewById(R.id.tv_detail_back);
        iv_detail_add = (ImageView) findViewById(R.id.iv_detail_add);
        ll_detail_data = (LinearLayout) findViewById(R.id.ll_detail_data);
        im_old_photo = (ImageView) findViewById(R.id.im_old_photo);
        tv_detail_name = (TextView) findViewById(R.id.tv_detail_name);
        tv_detail_address = (TextView) findViewById(R.id.tv_detail_address);
        lv_detail_data = (ListView) findViewById(R.id.lv_detail_data);
        tv_record = (TextView) findViewById(R.id.tv_record);

        tv_detail_back.setOnClickListener(this);
        ll_detail_data.setOnClickListener(this);
        iv_detail_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            //结束当前页面，返回LifeRecordActivity
            case R.id.tv_detail_back:
                finish();
                break;

            //进入ShowDataActivity
            case R.id.ll_detail_data:
                intent = new Intent(getApplicationContext(),ShowDataActivity.class);
                startActivity(intent);
                break;

            //进入AddPeopleActivity
            case R.id.iv_detail_add:
                intent = new Intent(getApplicationContext(),AddPeopleActivity.class);
                startActivity(intent);
                ToastUtil.show(getApplicationContext(),"不要忘了网络");
                break;
        }
    }

    /**
     * 查看老人的信息
     *
     * 用于请求下一个界面的数据
     *
     * 放在开头是保证下一界面有数据
     */
    private void initNetData() {
        String opt = "4";//查看老人的信息
        String cid = SpUtil.getString(getApplicationContext(), ConstantValue.CID,"");
        System.out.println("cid"+cid);
        String _t = CurrentTimeUtil.nowTime();
        String joint = "_t=" +_t + "&cid=" + cid+ "&opt="+opt + ConstantValue.APP_ENCRYPTION_KEY;
        System.out.println("joint"+joint);
        String _s = Md5Util.encoder(joint);
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("opt", opt)
                .add("cid", cid)
                .add("_s", _s)
                .add("_t", _t)
                .build();

        System.out.println("cid="+cid+"opt="+opt+"_s="+_s+"_t="+_t+"URL="+ConstantValue.URL_ONE);

        Request request = new Request.Builder().url(ConstantValue.URL_ONE).post(formBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String str = response.body().string();
            System.out.println("显示老人信息"+str);
            FileDataUtil.saveDataToFile(getApplicationContext(),str,"show_old_news");

            Gson gson = new Gson();
            JsonOldManNews jsonOldManNews = gson.fromJson(str, JsonOldManNews.class);
            String error = jsonOldManNews.getError();
            System.out.println("error"+ error);
            JsonOldManNews.OldBean old = jsonOldManNews.getOld();
            JsonOldManNews.OldBean.TownBean town = old.getTown();
            final String townName = town.getTownName();
            System.out.println("显示老人townName"+ townName);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_detail_address.setText(cityName +" "+townName);
                }
            });
        }
    };
}
