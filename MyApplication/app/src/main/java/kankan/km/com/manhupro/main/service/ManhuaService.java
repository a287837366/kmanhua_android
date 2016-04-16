package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.module.ManhuaResponseModel;
import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;
import kankan.km.com.manhupro.tools.stringtools.StringUtils;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaService implements ResponseCallback{
    private String TAG = ManhuaService.class.getSimpleName();


    private final int GET_MANHUA_TAG = 1001;

    private RequestQueue mQueue;

    public int pageCount;

    public ArrayList<ManhuaModel> news;

    private Handler mHandler;

    public boolean isNoData;

    public int manhuaType;

    public ManhuaService(Activity activity, Handler handler){
        manhuaType = 0;

        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;

        isNoData = false;

        news = new ArrayList<ManhuaModel>();
    }

    public void getManhuaListByType(int type){

        manhuaType = type;

        mQueue.add(HttpClinet.getInstance().getRequset("/manhua/getManhuaList.php?page=" + pageCount +"&type=" + type, this, GET_MANHUA_TAG));

    }

    public void getManhuaListByCurrent(){
        mQueue.add(HttpClinet.getInstance().getRequset("/manhua/getManhuaList.php?page=" + pageCount +"&type=" + manhuaType, this, GET_MANHUA_TAG));
    }


    @Override
    public void send(int method, int tag, String json) {

        switch (tag){

            case GET_MANHUA_TAG:

                ManhuaResponseModel response = (ManhuaResponseModel) StringUtils.jsonToBean(ManhuaResponseModel.class, json);

                news.addAll(response.getData());

                pageCount ++;

                if (pageCount >= Integer.parseInt(response.getCount())){
                    isNoData = true;
                } else {
                    isNoData = false;
                }

                mHandler.sendEmptyMessage(1);

                break;

            default:

                break;
        }

    }

    @Override
    public void error(int tag, int error) {

    }
}
