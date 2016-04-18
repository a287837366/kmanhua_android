package kankan.km.com.manhupro.login.activity.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import kankan.km.com.manhupro.login.activity.module.GetUserResponse;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.property.Constant;
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

    public UserModel model;

    public UserLoginService(Activity activity, Handler handler){
        model = null;

        this.activity = activity;
        this.mHandler = handler;

        mQueue = Volley.newRequestQueue(activity);
    }

    public void getLoginUser(String userName, String userPW){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        params.put("userpw", StringUtils.stringToMD5(userPW));

        Log.d(TAG, "MD5====> " + StringUtils.stringToMD5(userPW));

        mQueue.add(HttpClinet.getInstance().postRequset("/user/getByUserId.php", params, this, Constant.NETWORK_TAG.GET_USER));


    }

    @Override
    public void send(int method, int tag, String json) {

        GetUserResponse response = (GetUserResponse) StringUtils.jsonToBean(GetUserResponse.class, json);

        Log.d(TAG, "  " + response);

        if (response == null){
            Message msg = new Message();
            msg.what = -1;
            mHandler.sendMessage(msg);

        } else {
            Message msg = new Message();
            msg.what = 1;
            model = response.getData();
            mHandler.sendMessage(msg);
        }

    }

    @Override
    public void error(int tag, int error) {
        mHandler.sendEmptyMessage(-1);
    }


}
