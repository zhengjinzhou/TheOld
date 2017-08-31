package zhou.com.theold.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.lidroid.xutils.bitmap.core.BitmapCache;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import zhou.com.theold.R;
import zhou.com.theold.json.JsonSelect;
import zhou.com.theold.utils.FileDataUtil;
import zhou.com.theold.utils.FileUtils;
import zhou.com.theold.utils.ToastUtil;

import static android.R.attr.path;
import static zhou.com.theold.R.id.tv_addpeople_name;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int IMAGE_OPEN = 1;//标记打开图库
    private static final int PHOTO_CAMERA = 2;//打开视频标记
    private TextView tv_update;
    private LinearLayout ll_image_add;
    private LinearLayout ll_video_add;
    private ListView lv_activity_list;
    private ImageView iv_add_image;
    private List<String> nameList;
    private List<String> typeNameList;
    private List<String> nametypeList;
    private List<String> typeNameAssesItems;
    private List<Integer> items;//要上传的items数组
    private TextView tv_people_back;
    private JsonSelect jsonSelect;
    private Map<String, Integer> mapID;
    private String pictureName;
    private VideoView iv_add_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        initUI();
        // 初始化数据
        initData();
        // lv_activity_list的适配器
        lv_activity_list.setAdapter(new MAdapter());
        //lv_activity_list点击事件
        initListClick();
    }

    /**
     * lv_activity_list点击事件
     * 要点击的才点击
     */
    private void initListClick() {
        lv_activity_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(typeNameList.contains(nametypeList.get(i))){
                    List<String> subList=null;

                    int indexOf = typeNameAssesItems.indexOf(nametypeList.get(i));
                    int indexOf2 = typeNameList.indexOf(nametypeList.get(i));
                    int index = indexOf2 +1;
                    if(index==typeNameList.size()){
                        String string = typeNameList.get(typeNameList.size()-1);
                        int indexOf4 = typeNameAssesItems.indexOf(string);
                        subList = typeNameAssesItems.subList(indexOf4+1, typeNameAssesItems.size());
                    }else {
                        String string = typeNameList.get(indexOf2+1);
                        int indexOf3 = typeNameAssesItems.indexOf(string);
                        subList = typeNameAssesItems.subList(indexOf+1, indexOf3);
                    }
                    ShowPickerView(subList);
                }

            }
        });
    }

    /**
     *
     * @param subList
     */
    private void
    ShowPickerView(final List<String> subList) {
        items = new ArrayList<>();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = subList.get(options1);
                Integer integer = mapID.get(tx);
                items.add(integer);
                System.out.println("---items----"+integer);

            }
        }).build();
        pvOptions.setPicker(subList);
        pvOptions.show();
    }

    /**
     * lv_activity_list的适配器
     */
    class MAdapter extends BaseAdapter{

        private static final int TYPE_NAME = 0;
        private static final int TYPE_DES = 1;
        private static final int TYPE_ITEM_COUNT = 2;

        @Override
        public int getViewTypeCount() {
            return TYPE_ITEM_COUNT;
        }

        @Override
        public int getItemViewType(int position) {
            if(nameList.contains(nametypeList.get(position))){
                return TYPE_NAME;
            }else{
                return TYPE_DES;
            }
        }

        @Override
        public int getCount() {
            return nametypeList.size();//所有的总数
        }

        @Override
        public Object getItem(int i) {
            return nametypeList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            ViewTitleHolder holder2 = null;
            switch (getItemViewType(i)){
                case TYPE_NAME:
                    if(view==null){
                        holder = new ViewHolder();
                        view = View.inflate(getApplicationContext(),R.layout.list_add_people,null);
                        holder.tv_addpeople_name = view.findViewById(tv_addpeople_name);
                        view.setTag(holder);
                    }else {
                        holder=(ViewHolder) view.getTag();
                    }
                    holder.tv_addpeople_name.setText(nametypeList.get(i));
                    break;

                case TYPE_DES:
                    if(view==null){
                        holder2 = new ViewTitleHolder();
                        view = View.inflate(getApplicationContext(),R.layout.list_add_people2,null);
                        holder2.tv_addtitle = view.findViewById(R.id.tv_addtitle);
                        view.setTag(holder2);
                    }else{
                        holder2 = (ViewTitleHolder) view.getTag();
                    }
                    holder2.tv_addtitle.setText(nametypeList.get(i));
                    break;
            }
            return view;
        }
    }

    static class ViewHolder {
        TextView tv_addpeople_name;
    }
    static class ViewTitleHolder {
        TextView tv_addtitle;
    }
    /**
     * 获取添加记录选项信息
     * select_old_man
     * 从文件中获取
     */
    private void initData() {
        String select_old_man = FileDataUtil.loadDataFile(getApplicationContext(), "select_old_man");
        Gson gson = new Gson();
        JsonSelect jsonSelect = gson.fromJson(select_old_man, JsonSelect.class);
        List<JsonSelect.SelectListBean> selectList = jsonSelect.getSelectList();
        nameList = new ArrayList<>();//name eg:日常活动
        typeNameList = new ArrayList<>();//typeName eg:如厕
        nametypeList = new ArrayList<>();//name&typeName
        typeNameAssesItems = new ArrayList<>();//AssesItems eg:需极大帮助或完全依赖他人
        mapID = new HashMap<>();

        for(int i=0;i<selectList.size();i++){
            JsonSelect.SelectListBean selectListBean = selectList.get(i);
            List<JsonSelect.SelectListBean.TypesBean> types = selectListBean.getTypes();
            String name = selectListBean.getName();
            nameList.add(name);
            nametypeList.add(name);

            for(int j=0;j<types.size();j++){
                JsonSelect.SelectListBean.TypesBean typesBean = types.get(j);
                String typeName = typesBean.getTypeName();
                typeNameAssesItems.add(typeName);
                List<JsonSelect.SelectListBean.TypesBean.AssesItemsBean> assesItems = typesBean.getAssesItems();

                for(int k=0;k<assesItems.size();k++){
                    JsonSelect.SelectListBean.TypesBean.AssesItemsBean assesItemsBean = assesItems.get(k);
                    String description = assesItemsBean.getDescription();
                    int id = assesItemsBean.getId();
                    typeNameAssesItems.add(description);
                    mapID.put(description,id);//map标记
                }
                typeNameList.add(typeName);
                nametypeList.add(typeName);
            }
        }

    }

    private void initUI() {
        tv_people_back = (TextView) findViewById(R.id.tv_people_back);
        tv_update = (TextView) findViewById(R.id.tv_update);
        iv_add_image = (ImageView) findViewById(R.id.iv_add_image);
        iv_add_video = (VideoView) findViewById(R.id.iv_add_video);

        ll_image_add = (LinearLayout) findViewById(R.id.ll_image_add);
        ll_video_add = (LinearLayout) findViewById(R.id.ll_video_add);
        lv_activity_list = (ListView) findViewById(R.id.lv_activity_list);
        tv_people_back.setOnClickListener(this);
        tv_update.setOnClickListener(this);
        ll_image_add.setOnClickListener(this);
        ll_video_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.tv_people_back:
                finish();
            case R.id.tv_update:
                ToastUtil.show(getApplicationContext(),"上传，待续");
                break;
            case R.id.ll_image_add:
                //选择图片
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_OPEN);
                break;
            case R.id.ll_video_add:
                intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,PHOTO_CAMERA);
                break;
        }
    }

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
                String pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

                //保存图片的文件名，注意索引加1
                pictureName = pathImage.substring(pathImage.lastIndexOf("/")+1);

                Bitmap bitmap = BitmapFactory.decodeFile(pathImage);
                iv_add_image.setImageBitmap(bitmap);
                System.out.println(pathImage);
            }
        }
        /**
         * 视频回调
         */
        if(requestCode==RESULT_OK){
            Uri uri = data.getData();
            if(!TextUtils.isEmpty(uri.getAuthority())){
                String pathVideo = FileUtils.getPath(this, uri);
                System.out.println("asdfasdfasdfasdf"+pathVideo);

            }
        }
    }
}
