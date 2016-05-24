package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.BitmapBean;

/**
 * Created by apple on 16/5/17.
 */
public class CreateManhuaservice implements ResponseCallback {
    public static final String IMAGE_HANDLER_TAG = "IMAGE_LIST";

    public static final int UPDATE_IMAGE_TAG = 1003;

    private RequestQueue mQueue;

    private Handler mHandler;

    private Activity activity;


    String deviceId;


    public CreateManhuaservice(Activity activity, Handler handler){
        this.activity = activity;

        TelephonyManager TelephonyMgr = (TelephonyManager)activity.getSystemService(activity.TELEPHONY_SERVICE);
        deviceId = TelephonyMgr.getDeviceId();

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;
    }


    public void updateImage(ArrayList<BitmapBean> bips){
        UserModel model =(UserModel) SharedPreUtils.getObject(this.activity, "AM_KEY_USER");

       HttpClinet.getInstance().updateImage("/kankanAdmin/UploadImage", bips, this, UPDATE_IMAGE_TAG, model.getUsername(), deviceId);
    }


//    NSDictionary *paramDic = @{@"manhuaName" : dic[@"manhuaName"] ,
//        @"m_fromdata" : [[UserSharePrefre sharedInstance] nikeName] ,
//        @"m_type" : dic[@"m_type"] ,
//        @"u_phoneno" : dic[@"u_phoneno"] ,
//        @"mcontent" : dic[@"mcontent"] ,
//        @"imageList" : dic[@"imageList"] ,
//        @"username" : [[UserSharePrefre sharedInstance] userId]};

    /**
     * @param -m_fromdata   - nikeName
     * @param -m_type -类型
     * @param -u_phoneno -u_phoneno
     * @param -mcontent
     * @param -imageList -图片数组
     * @param -username
     * @param -manhuaName
     * */

    public void postManhuaDetail(HashMap<String, String> params){
        params.put("device_id", deviceId);
        mQueue.add(HttpClinet.getInstance().postRequset("/kankanAdmin/UpdateManhuaContent", params, this, Constant.NETWORK_TAG.UPDATE_MANHUA));

    }


    @Override
    public void send(int method, int tag, String json) {


        Message msg = new Message();
        Bundle b = new Bundle();
        b.putInt(Constant.TAG_NEWORK, tag);

        switch (tag){


            case Constant.NETWORK_TAG.UPDATE_MANHUA:
                Log.d("--------", json);
                break;

            case UPDATE_IMAGE_TAG:

                try {

                    if (json == null || json.length() == 0){

                        msg.what = 999;
                        mHandler.sendMessage(msg);
                        return;
                    }

                    JSONObject json1 = new JSONObject(json);



                    if (json1.getString("data").length() == 0) {

                        msg.what = 999;

                        mHandler.sendMessage(msg);

                    } else {

                        msg.what = 0;

                        b.putString(IMAGE_HANDLER_TAG, json1.getString("data"));

                        msg.setData(b);

                        mHandler.sendMessage(msg);
                    }




                } catch (Exception e){

                    msg.what = 999;

                    mHandler.sendMessage(msg);
                }

                break;


            default:

                break;


        }


    }


    @Override
    public void error(int tag, int error) {


        Bundle b = new Bundle();
        b.putInt(Constant.TAG_NEWORK, tag);

        Message msg = new Message();
        msg.what = 999;

        msg.setData(b);


        mHandler.sendMessage(msg);


    }

}
