package zhou.com.theold.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhou.com.theold.R;
import zhou.com.theold.utils.ConstantValue;
import zhou.com.theold.utils.SpUtil;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPage;
    private List<View> viewList;
    private TextView tv_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initUI();
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.guide1,null);
        View view2 = inflater.inflate(R.layout.guide2,null);
        View view3 = inflater.inflate(R.layout.guide3,null);
        tv_next = view3.findViewById(R.id.tv_next);
        initClick();
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewPage.setAdapter(new MyAdapter());
    }

    private void initClick() {
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                SpUtil.putString(getApplicationContext(), ConstantValue.GUILD_OVER,"guild_over");
                startActivity(intent);
                finish();
            }
        });
    }


    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    }



    private void initUI() {
        viewPage = (ViewPager) findViewById(R.id.viewPage);
    }
}
