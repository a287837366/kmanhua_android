package kankan.km.com.manhupro.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.MainDetailAdapter;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.main.service.ManhuaDetailService;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaDetailActivity extends Activity implements View.OnClickListener{

    private ManhuaDetailService service;

    private String manhuaId;
    private String manhuaTitle;

    private ListView list_ViewLists;
    private TextView text_TopTitle;

    private MainDetailAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhuadetail);

        manhuaId = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_ID);
        manhuaTitle = getIntent().getStringExtra(Constant.INTENT_TAG.MANHUA_TITLE);

        this.initViews();
        this.initObjects();

    }

    private void initViews(){
        list_ViewLists = (ListView) findViewById(R.id.list_ViewLists);
        text_TopTitle = (TextView) findViewById(R.id.text_TopTitle);

        findViewById(R.id.btn_back).setOnClickListener(this);
    }

    private void initObjects(){

        service = new ManhuaDetailService(this, new MyHandler());
        adapter = new MainDetailAdapter(this, service.viewLists);

        service.getManhuaById(manhuaId);
        list_ViewLists.setAdapter(adapter);

        text_TopTitle.setText(manhuaTitle);
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

            adapter.notifyDataSetChanged();

            super.handleMessage(msg);

        }

    }
}
