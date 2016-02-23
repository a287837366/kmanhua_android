package kankan.km.com.manhupro.splash.service;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.splash.model.VersionModel;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by apple on 16/2/23.
 */
public class SplashService implements ResponseCallback {


    private RequestQueue mQueue;
    private Handler mHandler;

    public VersionModel version;

    public  SplashService(Activity activity, Handler handler){
        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;

    }

    public void checkVersion(){
        mQueue.add(HttpClinet.getInstance().getRequset("/version/checkVersion.php?device=Android&versionCode=" + Constant.APP_INFO.APP_VERSION, this , Constant.REQUSET_TAG.GET_VERSION_TAG));
    }

    @Override
    public void send(int method, int tag, String json) {

        Log.d(">>>>>", json);

        version = (VersionModel) StringUtils.jsonToBean(VersionModel.class, json);

        mHandler.sendEmptyMessage(0);
    }

    @Override
    public void error(int tag, int error) {

    }
}
