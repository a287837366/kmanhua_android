package kankan.km.com.manhupro.main.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.DetailImageAdapter;
import kankan.km.com.manhupro.main.adapter.MainDetailAdapter;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.service.ManhuaDetailService;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.dbtools.DBManager;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaDetailActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private static final String TAG = ManhuaDetailActivity.class.getSimpleName();


    private ManhuaDetailService service;

    private String manhuaId;
    private String manhuaTitle;
    private String m_icon;
    private String m_fromdata;
    private String m_createTime;
    private String u_phoneno;
    private String m_type;

    private GridView image_grid;
    private ScrollView main_scroll;
    private TextView text_username;
    private TextView text_createTime;
    private TextView textDetail;

    private DetailImageAdapter imageAdapter;
    private DBManager dbManager;
    private ArrayList<String> imageLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhuadetail);

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
        findViewById(R.id.btn_fav).setOnClickListener(this);
        findViewById(R.id.btn_call).setOnClickListener(this);

        image_grid = (GridView) findViewById(R.id.image_grid);
        main_scroll = (ScrollView)findViewById(R.id.main_scroll);

        text_createTime = (TextView) findViewById(R.id.text_createTime);
        text_username = (TextView) findViewById(R.id.text_username);
        textDetail = (TextView) findViewById(R.id.textDetail);
    }

    private void initObjects(){


        dbManager = new DBManager(this);
        service = new ManhuaDetailService(this, new MyHandler());
        service.getManhuaById(manhuaId);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_fav:
                Log.d(TAG, "点击收藏");


                if (!dbManager.isFav(manhuaId)){

                    ManhuaModel model = new ManhuaModel();
                    model.setM_uid(manhuaId);
                    model.setM_icon(m_icon);
                    model.setM_title(manhuaTitle);
                    model.setM_type(m_type);
                    model.setM_createTime(m_createTime);
                    model.setM_fromdata(m_fromdata);
                    model.setU_phoneno(u_phoneno);

                    dbManager.addFav(model);

                } else {

                    dbManager.deleteById(manhuaId);
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


    private void showChooseView(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String phose[] = u_phoneno.split(";");
        builder.setTitle("选择电话号码");

        builder.setItems(phose, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                Log.d(TAG, phose[which]);

            }
        });


        builder.show();

    }

    private void setView(){

        imageLists = new ArrayList<String>();

        if (service.model.getImagelist().length() > 0){

            String[] imgsString = service.model.getImagelist().split(",");

            System.out.println(">>>>>>>>>>>>>>>>" + imgsString.length);

            for (String s_imge : imgsString){
                imageLists.add(s_imge);

            }
        }



        imageAdapter = new DetailImageAdapter(this, imageLists);
        image_grid.setAdapter(imageAdapter);
        image_grid.setOnItemClickListener(this);

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) image_grid.getLayoutParams();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();

        int gridViewHeight = ((screenWidth + 30) / 4) * ((imageLists.size() / 4) + 1);
        linearParams.height = gridViewHeight;
        image_grid.setLayoutParams(linearParams);

        main_scroll.scrollTo(0, 0);

        text_username.setText(m_fromdata);
        text_createTime.setText(m_createTime);
        textDetail.setText(service.model.getMcontent());

        if (dbManager.isFav(manhuaId)){
            Log.d(TAG, "已收藏");
        } else {
            Log.d(TAG, "未收藏");
        }

    }

    private void gotoDetailPage(int item){
        Intent intent = new Intent();
        intent.setClass(this, ImageStatusActivity.class);
        intent.putExtra("position", item);
        intent.putStringArrayListExtra(Constant.INTENT_TAG.IMG_LIST, imageLists);
        startActivity(intent);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        gotoDetailPage(i);
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            setView();
        }

    }
}
