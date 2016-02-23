package kankan.km.com.manhupro.splash;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.splash.service.SplashService;

/**
 * Created by apple on 16/2/23.
 */
public class SplashActivity extends Activity{


    private SplashService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initObjects();
        initViews();

        service.checkVersion();
    }

    private void initObjects(){
        service = new SplashService(this, new MyHandler());

    }

    private void initViews(){

    }

    private void gotoMainActivity(){

        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);

        finish();

    }

    private void checkVersion(){

        if (service.version.getUpdataType().equals("2")){
            //必须升级
            showMustUpdate();
        } else if (service.version.getUpdataType().equals("1")) {
            //可以跳过
            showCanUpdate();
        } else {

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();

            } finally {

                gotoMainActivity();
            }

        }



    }

    private void showMustUpdate(){

        new AlertDialog.Builder(SplashActivity.this)
                .setTitle("发现新版本")
                .setMessage(service.version.getMsg())
                .setPositiveButton("去升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();

    }

    private void showCanUpdate(){
        new AlertDialog.Builder(SplashActivity.this)
            .setTitle("发现新版本")
            .setMessage(service.version.getMsg())
                .setPositiveButton("去升级", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("暂不升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gotoMainActivity();
                    }
                })
                .show();
    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            checkVersion();

        }
    }
}
