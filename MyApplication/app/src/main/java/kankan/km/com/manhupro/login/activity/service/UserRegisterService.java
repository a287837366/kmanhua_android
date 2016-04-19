package kankan.km.com.manhupro.login.activity.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import kankan.km.com.manhupro.login.activity.module.PostUserInfoResponse;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;


/**
 * Created by aspn300 on 16/4/19.
 */
public class UserRegisterService implements ResponseCallback {

    private static final String TAG = UserRegisterService.class.getSimpleName();

    private RequestQueue mQueue;
    private Handler mHandler;
    private Activity activity;

    public UserRegisterService(Activity activity, Handler handler){

        this.activity = activity;
        this.mHandler = handler;

        mQueue = Volley.newRequestQueue(activity);
    }

    public void resUser(String userName, String userPW, String nikeName){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        params.put("userpw", StringUtils.stringToMD5(userPW));
        params.put("usernikename", nikeName);

        mQueue.add(HttpClinet.getInstance().postRequset("/user/postUserInfo.php", params, this, Constant.NETWORK_TAG.REGISTER_USER));
    }

    @Override
    public void send(int method, int tag, String json) {
        Log.d(TAG, "  " + json);

        PostUserInfoResponse response = (PostUserInfoResponse) StringUtils.jsonToBean(PostUserInfoResponse.class, json);

        if (response.getError().equals("0")){
            Message msg = new Message();
            msg.what = 1;
            mHandler.sendMessage(msg);
        } else {

            Message msg = new Message();
            msg.what = -1;
            mHandler.sendMessage(msg);
        }

    }

    @Override
    public void error(int tag, int error) {
        Message msg = new Message();
        msg.what = -1;
    }
}
