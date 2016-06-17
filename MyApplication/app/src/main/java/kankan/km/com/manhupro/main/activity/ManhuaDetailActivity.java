package kankan.km.com.manhupro.main.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.UserLoginActivity;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.adapter.DetailImageAdapter;
import kankan.km.com.manhupro.main.adapter.ImageStatusAdapter;
import kankan.km.com.manhupro.main.adapter.MainDetailAdapter;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.service.ManhuaDetailService;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;
import kankan.km.com.manhupro.tools.dbtools.DBManager;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaDetailActivity extends BaseAcvitiy implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private static final String TAG = ManhuaDetailActivity.class.getSimpleName();


    private ManhuaDetailService service;

    private String manhuaId;
    private String manhuaTitle;
    private String m_icon;
    private String m_fromdata;
    private String m_createTime;
    private String u_phoneno;
    private String m_type;

    private ScrollView main_scroll;
    private TextView textDetail;
    private TextView text_title;
    private TextView btn_fav;
    private TextView text_count;

    private ViewPager viewPager_image;
    private ImageStatusAdapter mAdapter;
    private RelativeLayout imageList_parent;

    private DBManager dbManager;
    private ArrayList<String> imageLists;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhuadetail);

        context = this;

        manhuaId = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_ID);
        manhuaTitle = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_TITLE);
        m_icon = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_ICON);
        m_fromdata = getIntent().getStringExtra(Constant.INTENT_TAG.USER_NAME);
        m_createTime = getIntent().getStringExtra(Constant.INTENT_TAG.CREATE_TIME);
        u_phoneno = getIntent().getStringExtra(Constant.INTENT_TAG.USER_PHONE);
        m_type = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_TYPE);

        this.initViews();
        this.initObjects();

    }

    private void initViews(){

        findViewById(R.id.btn_back).setOnClickListener(this);
        btn_fav = (Button)findViewById(R.id.btn_fav);
        btn_fav.setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);

//        image_grid = (GridView) findViewById(R.id.image_grid);
        main_scroll = (ScrollView)findViewById(R.id.main_scroll);
        textDetail = (TextView) findViewById(R.id.textDetail);
        viewPager_image = (ViewPager) findViewById(R.id.viewPager_image);
        imageList_parent = (RelativeLayout) findViewById(R.id.imageList_parent);
        text_title = (TextView) findViewById(R.id.text_title);
        text_count = (TextView) findViewById(R.id.text_count);


        viewPager_image.setOnPageChangeListener(this);

    }

    private void initObjects(){

        this.showLoad();

        dbManager = new DBManager(this);
        service = new ManhuaDetailService(this, new MyHandler());
        service.getManhuaById(manhuaId);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.listview_feed_item_product_page:
                gotoDetailPage(viewPager_image.getCurrentItem());

                break;

            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_fav:
                Log.d(TAG, "点击收藏");

                if (isLogined()){

                    if (!dbManager.isFav(manhuaId)){

                        if (dbManager.getManhuaSize() == 20){
                            Toast.makeText(context, "收藏不能超过20条", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        ManhuaModel model = new ManhuaModel();
                        model.setM_uid(manhuaId);
                        model.setM_icon(m_icon);
                        model.setM_title(manhuaTitle);
                        model.setM_type(m_type);
                        model.setM_createTime(m_createTime);
                        model.setM_fromdata(m_fromdata);
                        model.setU_phoneno(u_phoneno);

                        dbManager.addFav(model);

                        btn_fav.setText("取消收藏");
                        Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();

                    } else {

                        dbManager.deleteById(manhuaId);
                        btn_fav.setText("收藏");
                        Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    showLoginAlert();

                }



                break;

            case R.id.btn_call:
                Log.d(TAG, "点击拨打电话");
                showChooseView();
                break;

            default:

                break;

        }

    }

    private boolean isLogined(){

        UserModel model =(UserModel) SharedPreUtils.getObject(this, "AM_KEY_USER");

        if (model == null){

            return false;

        } else {

            return true;

        }

    }

    private void showLoginAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("收藏需要登入");

        builder.setPositiveButton("登入", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setClass(context, UserLoginActivity.class);
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();


    }

    private void showChooseView(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String phose[] = u_phoneno.split(";");
        builder.setTitle("选择电话号码");

        builder.setItems(phose, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phose[which]));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        builder.show();

    }

    private void setView(){

        imageLists = new ArrayList<String>();

        if (service.model.getImagelist().length() > 0){

            String[] imgsString = service.model.getImagelist().split(",");

            for (String s_imge : imgsString){
                imageLists.add(s_imge);

            }
        }


        if (imageLists.size() > 0){
            imageList_parent.setVisibility(View.VISIBLE);
        }


        text_count.setText("1" + " / " + imageLists.size());

        mAdapter = new ImageStatusAdapter(this);
        mAdapter.setList(imageLists);
        mAdapter.setImageClickListner(this);

        viewPager_image.setAdapter(mAdapter);

        textDetail.setText(service.model.getMcontent());
        text_title.setText(manhuaTitle);

        if (dbManager.isFav(manhuaId)){
            btn_fav.setText("取消收藏");
        } else {
            btn_fav.setText("收藏");
        }


        this.dismissLoad();
    }

    private void gotoDetailPage(int item){
        Intent intent = new Intent();
        intent.setClass(this, ImageStatusActivity.class);
        intent.putExtra("position", item);
        intent.putStringArrayListExtra(Constant.INTENT_TAG.IMG_LIST, imageLists);
        startActivity(intent);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (imageLists.size() > 0){
            text_count.setText(position + 1 + " / " + imageLists.size());
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if (msg.what == 0){

                setView();
            } else {

                Toast.makeText(context, "该消息已被删除", Toast.LENGTH_SHORT).show();
                dismissLoad();
                finish();
            }


        }

    }
}
