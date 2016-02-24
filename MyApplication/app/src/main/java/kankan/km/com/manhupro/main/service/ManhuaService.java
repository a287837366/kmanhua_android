package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

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

    public ArrayList<ManhuaModel> newManhuas;
    public ArrayList<ManhuaModel> oldManhuas;

    private Handler mHandler;

    public boolean isNoData;

    public boolean isRoading;


    public ManhuaService(Activity activity, Handler handler){
        mQueue = Volley.newRequestQueue(activity);
        mHandler = handler;

        isNoData = false;
        isRoading = false;

        newManhuas = new ArrayList<ManhuaModel>();
        oldManhuas = new ArrayList<ManhuaModel>();
    }

    public void getManhuaList(){
        isRoading = true;
        mQueue.add(HttpClinet.getInstance().getRequset("/manhua/getManhuaList.php?page=" + pageCount, this, Constant.REQUSET_TAG.GET_MANHUA_TAG));

    }


    @Override
    public void send(int method, int tag, String json) {

        isRoading = false;

        switch (tag){

            case Constant.REQUSET_TAG.GET_MANHUA_TAG:

                ManhuaResponseModel response = (ManhuaResponseModel) StringUtils.jsonToBean(ManhuaResponseModel.class, json);

                if (this.newManhuas.size() == 0){

                    if (response.getNewdata() == null) {
                        this.newManhuas.addAll(new ArrayList<ManhuaModel>());
                    } else {
                        this.newManhuas.addAll(response.getNewdata());
                    }


                }

                this.oldManhuas.addAll(response.getFreedata());

                pageCount ++;

                if (pageCount >= Integer.parseInt(response.getCount())){
                    isNoData = true;
                } else {
                    isNoData = false;
                }

                mHandler.sendEmptyMessage(HttpClinet.NETWORK_SUCCESS);

                break;

            default:

                break;
        }

    }

    @Override
    public void error(int tag, int error) {
        mHandler.sendEmptyMessage(HttpClinet.NETWORK_ERROR);
        isRoading = false;

    }
}
