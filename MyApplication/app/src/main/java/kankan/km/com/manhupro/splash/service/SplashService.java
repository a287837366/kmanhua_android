package kankan.km.com.manhupro.splash.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import kankan.km.com.manhupro.main.module.CheckPermissonResponse;
import kankan.km.com.manhupro.splash.module.CheckVersion;
import kankan.km.com.manhupro.splash.module.CheckVersionResponse;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;


/**
 * Created by apple on 16/5/27.
 */
public class SplashService implements ResponseCallback {

    private RequestQueue mQueue;
    private Handler mHandler;

    public CheckVersion mainVersion;

    public SplashService(Activity activity, Handler handler){

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;
    }

    public void checkAppVersion(String appVersion){

        mQueue.add(HttpClinet.getInstance().getRequset("/kankanAdmin/CheckVersionUpdate", this, 0));

    }



    @Override
    public void send(int method, int tag, String json) {


        CheckVersionResponse response = (CheckVersionResponse) StringUtils.jsonToBean(CheckVersionResponse.class, json);

        mainVersion = response.getMainImage();

        Message msg = new Message();
        msg.what = 1;
        mHandler.sendMessage(msg);

    }

    @Override
    public void error(int tag, int error) {
        Log.d(">>>>>>>","error");

        if (mHandler == null)
            return;

        mainVersion = new CheckVersion();
        mainVersion.setImage("");
        mainVersion.setJumpUrl("");

        Message msg = new Message();
        msg.what = 999;
        mHandler.sendMessage(msg);

    }
}
