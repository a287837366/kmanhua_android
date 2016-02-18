package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kankan.km.com.manhupro.main.module.ManhuaDeailResponse;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.main.module.ManhuaResponseModel;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by aspn300 on 16/2/18.
 */
public class ManhuaDetailService implements ResponseCallback{

    private final int GET_MANHUADETAIL_TAG = 1002;

    private RequestQueue mQueue;

    private Handler mHandler;

    public ManhuaDetailService(Activity activity, Handler handler){
        mQueue = Volley.newRequestQueue(activity);

        mHandler = handler;
    }

    public void getManhuaById(String manhuaId){
        mQueue.add(HttpClinet.getInstance().getRequset("/manhua/getManhuaById.php?manhuaid=" + manhuaId, this, GET_MANHUADETAIL_TAG));
    }

    @Override
    public void send(int method, int tag, String json) {

        ManhuaDeailResponse response = (ManhuaDeailResponse) StringUtils.jsonToBean(ManhuaDeailResponse.class, json);

        Gson gson = new Gson();

        ArrayList<ManhuaDetailModel> viewLists = gson.fromJson(response.getData().get(0).getViewdetail(), new TypeToken<ArrayList<ManhuaDetailModel>>(){}.getType());

        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void error(int tag, int error) {

    }
}
