package kankan.km.com.manhupro.main.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.service.ManhuaDetailService;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaDetailActivity extends Activity{

    ManhuaDetailService service;

    private String manhuaId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhuadetail);

        this.initViews();
        this.initObjects();

    }

    private void initViews(){

    }

    private void initObjects(){
        manhuaId = getIntent().getStringExtra("ManhuaId");

        service = new ManhuaDetailService(this, new MyHandler());

        service.getManhuaById(manhuaId);
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

        }

    }
}
