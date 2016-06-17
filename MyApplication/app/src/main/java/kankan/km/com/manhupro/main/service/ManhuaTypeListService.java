package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.module.ManhuaResponseModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.splash.module.CheckVersionResponse;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by apple on 16/6/17.
 */
public class ManhuaTypeListService implements ResponseCallback {


    private RequestQueue mQueue;

    private String manhuaType;
    private Handler mHandler;

    public boolean isNoData;

    public ArrayList<ManhuaModel> news;

    public int pageCount;


    public ManhuaTypeListService(Activity activity, Handler handler, String manhuaType){

        this.manhuaType = manhuaType;

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;

        isNoData = false;

        news = new ArrayList<ManhuaModel>();

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

                            } else {

                                ArrayList<String> extendImage = new ArrayList<String>();

                                for (String s_imge : imgsString){
                                    extendImage.add(s_imge);

                                }

                                model.setImages(extendImage);

                            }



                        }

                        news.add(model);

                    }



                }


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
