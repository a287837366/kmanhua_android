package kankan.km.com.manhupro.tools.httptools;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by apple on 16/2/18.
 */
public class HttpClinet {
    private final String TAG = HttpClinet.class.getSimpleName();

    private static HttpClinet instance;
//    private static final String PROTOCAL = "http://1.85kankan.sinaapp.com/";
    private static final String PROTOCAL = "http://192.168.1.104:8080/";

    public static final int GET = Request.Method.GET;
    public static final int POST = Request.Method.POST;

    public static final int NETWORK_ERROR = -1;

    public static HttpClinet getInstance(){

        if (instance == null){
            instance = new HttpClinet();
        }

        return instance;
    }

    public GsonRequest getRequset(String url, final ResponseCallback callback, final int tag){
        Log.d(TAG, " Url ---> " + PROTOCAL + url);

        GsonRequest requset = new GsonRequest(GET, PROTOCAL + url, null, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response => " + response.toString());

                callback.send(GET, tag, response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

                callback.error(tag, NETWORK_ERROR);

            }
        });

        return requset;
    }

    public GsonRequest postRequset(String url, Map<String, String> params, final ResponseCallback callback, final int tag){

        GsonRequest requset = new GsonRequest(POST, PROTOCAL + url, null, params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Response => " + response.toString());

                callback.send(GET, tag, response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {

                callback.error(tag, NETWORK_ERROR);

            }
        });

        return requset;

    }

}
