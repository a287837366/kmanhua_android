package kankan.km.com.manhupro.login.activity.service;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by aspn300 on 16/4/17.
 */
public class UserLoginService implements ResponseCallback {

    private static final String TAG = UserLoginService.class.getSimpleName();

    private RequestQueue mQueue;
    private Handler mHandler;
    private Activity activity;

    public UserLoginService(Activity activity, Handler handler){

        this.activity = activity;
        this.mHandler = handler;

        mQueue = Volley.newRequestQueue(activity);
    }

    public void getLoginUser(String userName, String userPW){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        params.put("userpw", StringUtils.stringToMD5(userPW));

        mQueue.add(HttpClinet.getInstance().postRequset("/user/getByUserId.php", params, this, 100));


    }

    @Override
    public void send(int method, int tag, String json) {

        Log.d(TAG , json);

    }

    @Override
    public void error(int tag, int error) {

    }


}
