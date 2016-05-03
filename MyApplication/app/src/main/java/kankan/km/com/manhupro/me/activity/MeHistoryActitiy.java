package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.activity.ManhuaDetailActivity;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.dbtools.DBManager;

/**
 * Created by apple on 16/5/3.
 */
public class MeHistoryActitiy extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView list_history;
    private ArrayList<ManhuaModel> datas;
    private DBManager dbManager;
    private MainBaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_history);

        initObjects();
        initView();
    }

    private void initObjects(){
        dbManager = new DBManager(this);
        datas = dbManager.getHistoryManhua();

        adapter = new MainBaseAdapter(this, datas);
    }

    private void initView(){

        findViewById(R.id.btn_back).setOnClickListener(this);

        list_history = (ListView) findViewById(R.id.list_history);
        list_history.setOnItemClickListener(this);
        list_history.setAdapter(adapter);
        adapter.refreshByType(0);

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
        startActivity(intent);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        gotoManhuDetilaPage(datas.get(i));
    }
}
