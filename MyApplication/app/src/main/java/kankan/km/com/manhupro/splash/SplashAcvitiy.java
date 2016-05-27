package kankan.km.com.manhupro.splash;

import android.content.Intent;
import android.os.Bundle;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
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
        service = new SplashService(this, null);

    }

    private void initView(){


    }


    private void gotoMainActivity(){
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);

        finish();

    }


}
