package kankan.km.com.manhupro.main.service;

import android.app.Activity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.httptools.ResponseCallback;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaService implements ResponseCallback{

    private final int GET_MANHUA_TAG = 1001;

    private RequestQueue mQueue;
    private ResponseCallback callBack;

    public int pageSize;
    public int pageCount;

    public ManhuaService(Activity activity){
        mQueue = Volley.newRequestQueue(activity);
    }

    public void getManhuaList(){

        mQueue.add(HttpClinet.getInstance().getRequset("/manhua/getManhuaList.php?page=" + pageCount, this, GET_MANHUA_TAG));

    }


    @Override
    public void send(int method, int tag, String json) {

        switch (tag){

            case GET_MANHUA_TAG:
                Log.d("----------", json);
                pageCount ++;
                break;

            default:

                break;
        }

    }

    @Override
    public void error(int tag, int error) {

    }
}
