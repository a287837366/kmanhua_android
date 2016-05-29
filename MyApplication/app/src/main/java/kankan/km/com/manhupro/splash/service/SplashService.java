package kankan.km.com.manhupro.splash.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;


/**
 * Created by apple on 16/5/27.
 */
public class SplashService implements ResponseCallback {

    private RequestQueue mQueue;
    private Handler mHandler;



    public SplashService(Activity activity, Handler handler){

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;
    }

    public void checkAppVersion(String appVersion){

        mQueue.add(HttpClinet.getInstance().getRequset("/kankanAdmin/CheckVersionUpdate", this, 0));

    }



    @Override
    public void send(int method, int tag, String json) {



    }

    @Override
    public void error(int tag, int error) {
        Log.d(">>>>>>>","error");

        if (mHandler == null)
            return;

        Message msg = new Message();
        msg.what = 999;
        mHandler.sendMessage(msg);

    }
}
