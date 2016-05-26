package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kankan.km.com.manhupro.main.module.CheckPermissonResponse;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.module.ManhuaResponseModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaService implements ResponseCallback{
    private String TAG = ManhuaService.class.getSimpleName();

    private RequestQueue mQueue;

    public int pageCount;

    public ArrayList<ManhuaModel> news;

    private Handler mHandler;

    public boolean isNoData;

    public int manhuaType;

    public String deviceId;

    public ManhuaService(Activity activity, Handler handler){
        TelephonyManager TelephonyMgr = (TelephonyManager)activity.getSystemService(activity.TELEPHONY_SERVICE);
        deviceId = TelephonyMgr.getDeviceId();

        manhuaType = 0;

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;

        isNoData = false;

        news = new ArrayList<ManhuaModel>();
    }

    public void checkPermession(String username){

        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("device_id", deviceId);

        mQueue.add(HttpClinet.getInstance().postRequset("/kankanAdmin/CheckUpdatePermission", params, this, Constant.NETWORK_TAG.CHECK_PERMISSON));

    }

    public void getManhuaListByType(int type){

        manhuaType = type;

        mQueue.add(HttpClinet.getInstance().getRequset("/kankanAdmin/GetManhuaListByType?page=" + pageCount +"&type=" + type, this, Constant.NETWORK_TAG.GET_MANHUA_TAG));

    }

    public void getManhuaListByCurrent(){
        mQueue.add(HttpClinet.getInstance().getRequset("/kankanAdmin/GetManhuaListByType?page=" + pageCount +"&type=" + manhuaType, this, Constant.NETWORK_TAG.GET_MANHUA_TAG));
    }


    @Override
    public void send(int method, int tag, String json) {

        Message msg = new Message();
        Bundle b = new Bundle();
        b.putInt(Constant.TAG_NEWORK, tag);

        switch (tag){

            case Constant.NETWORK_TAG.GET_MANHUA_TAG:

                ManhuaResponseModel response = (ManhuaResponseModel) StringUtils.jsonToBean(ManhuaResponseModel.class, json);


                if (response.getData() != null){

                    for (int i = 0; i < response.getData().size(); i ++){

                        ManhuaModel model = response.getData().get(i);

                        if (model.getT_images().length() > 0){

                            String[] imgsString = model.getT_images().split(",");

                            if (imgsString.length < 3){

                                continue;
                            }

                            ArrayList<String> extendImage = new ArrayList<String>();

                            for (String s_imge : imgsString){
                                extendImage.add(s_imge);

                            }

                            model.setImages(extendImage);

                        }

                        news.add(model);
                    }

                }



//                news.addAll(response.getData());

                pageCount ++;

                if (pageCount >= Integer.parseInt(response.getCount())){
                    isNoData = true;
                } else {
                    isNoData = false;
                }

                msg.what = 0;
                msg.setData(b);

                mHandler.sendMessage(msg);

                break;

            case Constant.NETWORK_TAG.CHECK_PERMISSON:

                msg.what = 0;

                CheckPermissonResponse response1 = (CheckPermissonResponse) StringUtils.jsonToBean(CheckPermissonResponse.class, json);



                if (response1.getData().equals("0")){

                    b.putBoolean(Constant.INTENT_TAG.CAN_UPDATE, true);

                } else {

                    b.putBoolean(Constant.INTENT_TAG.CAN_UPDATE, false);
                }



                msg.setData(b);
                mHandler.sendMessage(msg);

                break;

            default:

                break;
        }

    }

    @Override
    public void error(int tag, int error) {

        Message msg = new Message();
        Bundle b = new Bundle();
        b.putInt(Constant.TAG_NEWORK, tag);

        msg.what = 999;

        mHandler.sendMessage(msg);

    }
}
