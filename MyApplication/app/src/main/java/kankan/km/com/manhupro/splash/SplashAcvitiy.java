package kankan.km.com.manhupro.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;
import kankan.km.com.manhupro.splash.service.SplashService;

/**
 * Created by apple on 16/5/27.
 */
public class SplashAcvitiy extends BaseAcvitiy{


    private SplashService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitiy_splash);


        initData();
        initView();

        service.checkAppVersion("");
    }

    private void initData(){
        service = new SplashService(this, new MyHandler());

    }

    private void initView(){


    }


    private void gotoMainActivity(String url, String jumpUrl){

        Log.d("()()()()()()()()", url + "    " + jumpUrl);
        Intent intent = new Intent();
        intent.putExtra(Constant.INTENT_TAG.JUMP_URL, jumpUrl);
        intent.putExtra(Constant.INTENT_TAG.MAIN_ADS_IMAGE, url);
        intent.setClass(this, MainActivity.class);
        startActivity(intent);

        finish();

    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            try {



                Thread.sleep(2000);
                gotoMainActivity(service.mainVersion.getImage(), service.mainVersion.getJumpUrl());

            } catch (Exception e){


            }




        }

    }


}
