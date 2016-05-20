package kankan.km.com.manhupro.me.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import kankan.km.com.manhupro.me.model.UpdateNikeNameResponse;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by aspn300 on 16/4/23.
 */
public class MeUpdateNikeNameService implements ResponseCallback {

    private RequestQueue mQueue;
    private Handler mHandler;

    public MeUpdateNikeNameService(Activity activity, Handler handler){

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;
    }

    public void updateNikeName(String userName, String userpw, String nikeName){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        params.put("userpw", userpw);
        params.put("usernikename", nikeName);

        mQueue.add(HttpClinet.getInstance().postRequset("/kankanAdmin/UpdateNikeNameByUser", params, this, Constant.NETWORK_TAG.UPDATE_NIKENAME));

    }

    @Override
    public void send(int method, int tag, String json) {

        Log.d(">>>>>>", json);
        UpdateNikeNameResponse response = (UpdateNikeNameResponse) StringUtils.jsonToBean(UpdateNikeNameResponse.class, json);

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
        mHandler.sendMessage(msg);

    }
}
