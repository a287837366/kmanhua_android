package kankan.km.com.manhupro.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.ImageStatusAdapter;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/5/3.
 */
public class ImageStatusActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    private Activity mActivity;
    private ArrayList<String> imgList;

    private ImageStatusAdapter mAdapter;
    private ViewPager viewPager_status;
    private TextView text_page;
    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_mage_status);

        initObject();
        initView();
    }

    private void initObject(){
        imgList = getIntent().getStringArrayListExtra(Constant.INTENT_TAG.IMG_LIST);
        currentItem = getIntent().getIntExtra("position", 0);

        mAdapter = new ImageStatusAdapter(mActivity);
        mAdapter.setList(imgList);
        mAdapter.setImageClickListner(this);

    }

    private void initView(){
        viewPager_status = (ViewPager) findViewById(R.id.viewPager_status);
        viewPager_status.setOnPageChangeListener(this);
        text_page = (TextView) findViewById(R.id.text_page);

        viewPager_status.setAdapter(mAdapter);
        viewPager_status.setCurrentItem(currentItem);

        if (imgList.size() > 1){
            text_page.setText(currentItem + 1 + " / " + imgList.size());
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (imgList.size() > 0){
            text_page.setText(position + 1 + " / " + imgList.size());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
