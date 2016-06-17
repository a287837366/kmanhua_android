package kankan.km.com.manhupro.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.TypeManhuaListAdatper;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.service.ManhuaTypeListService;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/6/17.
 */
public class ManhuaTypeListActivity extends BaseAcvitiy implements View.OnClickListener, AdapterView.OnItemClickListener{

    private TypeManhuaListAdatper adatper;
    private ManhuaTypeListService manhuaService;

    private Context context;

    private String manhuaType;

    private TextView text_title;

    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acvitity_typelistmanhua);

        initData();
        initView();


        manhuaService.getManhuaListByCurrent();
    }

    private void initData() {
        context = this;

        manhuaType = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_TYPE);

        manhuaService = new ManhuaTypeListService(this, new MyHandler(), manhuaType);

        adatper = new TypeManhuaListAdatper(this, manhuaService.news);

    }

    private void initView() {

        mainList = (ListView) findViewById(R.id.mainList);

        findViewById(R.id.btn_back).setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);

        mainList.setOnItemClickListener(this);

        if (manhuaType.equals("1")) {
            text_title.setText("招聘");
        } else if (manhuaType.equals("2")) {
            text_title.setText("求职");
        } else if (manhuaType.equals("3")) {
            text_title.setText("房产");
        } else if (manhuaType.equals("4")) {
            text_title.setText("宠物");
        }


        mainList.setAdapter(adatper);
    }

    //-----Actions
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (position == manhuaService.news.size() + 1){

            if (manhuaService.isNoData){

                return;
            }

            manhuaService.getManhuaListByCurrent();
            adatper.refreshByType(2);
            return;
        }

        if (position == 0){

            return;
        }

        this.gotoManhuDetilaPage(manhuaService.news.get(position - 1));

    }


    //-----Goto
    private void gotoManhuDetilaPage(ManhuaModel model){
        Intent intent = new Intent();
        intent.setClass(this, ManhuaDetailActivity.class);
        intent.putExtra(Constant.INTENT_TAG.MANHUA_ID, model.getM_uid());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_TITLE, model.getM_title());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_ICON, model.getM_icon());
        intent.putExtra(Constant.INTENT_TAG.USER_NAME, model.getM_fromdata());
        intent.putExtra(Constant.INTENT_TAG.CREATE_TIME, model.getM_createTime());
        intent.putExtra(Constant.INTENT_TAG.USER_PHONE, model.getU_phoneno());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_TYPE, model.getM_type());
        this.startActivity(intent);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_back:
                finish();
                break;

            default:

                break;

        }

    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.getData().getInt(Constant.TAG_NEWORK)){

                case Constant.NETWORK_TAG.GET_MANHUA_TAG:


                    if (msg.what != 0){
                        Toast.makeText(context, "连接服务器失败", Toast.LENGTH_SHORT).show();
                        dismissLoad();
                        adatper.refreshByType(1);

                        return;
                    }

                    dismissLoad();
                    adatper.refreshByType(manhuaService.isNoData ? 0 : 1);

                    break;


                default:

                    break;


            }





        }

    }
}
