package kankan.km.com.manhupro.tools.tools.AlbumTools.acvitity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.tools.AlbumTools.AlbumHelper;
import kankan.km.com.manhupro.tools.tools.AlbumTools.Bimp;
import kankan.km.com.manhupro.tools.tools.AlbumTools.adapter.ImageGridAdapter;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.ImageItem;

/**
 * Created by apple on 16/5/16.
 */
public class ImageGridActivity extends Activity implements View.OnClickListener{


    List<ImageItem> dataList;
    GridView gridView;
    ImageGridAdapter adapter;// 自定义的适配
    AlbumHelper helper;
    Button bt;

    int currentSize;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
//                    Utils.toast(ImageGridActivity.this,getResources().getString(R.string.maxchioce)+Bimp.drrlength+getResources().getString(R.string.sheetpaper));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_imagechoice_grid);
        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());
        dataList = (List<ImageItem>) getIntent().getSerializableExtra("imagelist");

        currentSize = getIntent().getIntExtra(Constant.INTENT_TAG.CURRENT_SELECTED_IMAGE, 0);

        initView();
    }




    private void saves(){

        ArrayList<String> list = new ArrayList<String>();
        Collection<String> c = adapter.map.values();
        Iterator<String> it = c.iterator();

        for (; it.hasNext();) {
            list.add(it.next());
        }

        if (Bimp.act_bool) {
            Bimp.act_bool = false;
        }

        for (int i = 0; i < list.size(); i++) {
            Bimp.drr.add(list.get(i));
        }

    }

    /**
     * 初始化view视图
     */
    private void initView() {

        findViewById(R.id.btn_back).setOnClickListener(this);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saves();

                setResult(20);
                ImageGridActivity.this.finish();
            }
        });

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(ImageGridActivity.this, dataList, mHandler);
        adapter.setSelectTotal(currentSize);
        gridView.setAdapter(adapter);

        adapter.setTextCallback(new ImageGridAdapter.TextCallback() {
            public void onListen(int count) {
//                bt.setText(getResources().getString(R.string.confirm) + "(" + count+getResources().getString(R.string.sheetpaper) + ")" );
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                adapter.notifyDataSetChanged();
            }

        });

    }

    @Override
    protected void onDestroy() {
        setResult(1000);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_back:
                saves();
                this.finish();
                break;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saves();
        this.finish();
    }
}
