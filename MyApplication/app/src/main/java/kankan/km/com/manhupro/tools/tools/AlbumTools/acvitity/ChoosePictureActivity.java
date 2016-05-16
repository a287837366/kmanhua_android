package kankan.km.com.manhupro.tools.tools.AlbumTools.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.httptools.BitmapCache;
import kankan.km.com.manhupro.tools.tools.AlbumTools.AlbumHelper;
import kankan.km.com.manhupro.tools.tools.AlbumTools.Bimp;
import kankan.km.com.manhupro.tools.tools.AlbumTools.adapter.ImageBucketAdapter;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.ImageBucket;

/**
 * Created by apple on 16/5/13.
 */
public class ChoosePictureActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener{

    AlbumHelper helper;
    List<ImageBucket> dataList;
    ImageBucketAdapter adapter;
    GridView gridView;
    public static Bitmap bimap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosepicture);

        initData();
        initView();

    }

    private void initData(){

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        dataList = helper.getImagesBucketList(false);

        Log.i(">>>>>", "" + dataList.size());

    }

    private void initView(){

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new ImageBucketAdapter(this, dataList);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 20) {
            setResult(20);
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra("imagelist", (Serializable) dataList.get(position).imageList);
        startActivityForResult(intent, 20);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bimp.drr.clear();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_back:

                Bimp.drr.clear();
                this.finish();

                break;

        }

    }
}
