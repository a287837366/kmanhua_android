package kankan.km.com.manhupro.tools.httptools;


import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.BitmapBean;

/**
 * Created by apple on 16/2/18.
 */
public class HttpClinet {
    private final String TAG = HttpClinet.class.getSimpleName();

    private static HttpClinet instance;
//    private static final String PROTOCAL = "http://1.85kankan.sinaapp.com/";
    private static final String PROTOCAL = "http://10.0.1.112:8080/";

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

    public void updateImage(final String requestURL, final List<BitmapBean> bitmaps, final ResponseCallback callback, final int tag) {

        Runnable downloadRun = new Runnable(){

            @Override
            public void run() {
                Log.d("TAG", "" + System.currentTimeMillis());
                String BOUNDARY = "aifudao7816510d1hq";

                String prefix = "--", end = "\r\n";
                String content_type = "multipart/form-data;"; // 内容类型
                String CHARSET = "utf-8"; // 设置编码

                try {
                    long imageTag = System.currentTimeMillis();

                    URL url = new URL(requestURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Charset", CHARSET); // 设置编码
                    conn.setRequestProperty("Content-Type", content_type + "boundary" + BOUNDARY);
                    conn.setRequestMethod("POST"); // 请求方式

                    conn.setUseCaches(false);
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    conn.setRequestProperty("username", "test003");
                    conn.setRequestProperty("username", "test003");


//                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//
//                    StringBuffer params = new StringBuffer();
//                    params.append("username=test003");
//
//                    dos.writeBytes(URLEncoder.encode("username=test003" , "utf-8"));

//
//
//                    for (int i = 0; i < bitmaps.size(); i++) {
//
//                        dos.writeBytes("Content-Disposition: form-data; name=\"" + imageTag + "_" + i + "\";filename=\"" + bitmaps.get(i).getFileName() + "\"" + end);
//                        dos.writeBytes("Content-Type: image/jpg" + end);
//
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmaps.get(i).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
//                        dos.write(baos.toByteArray(), 0, baos.toByteArray().length);
//
//                        dos.writeBytes(end);
//                        dos.writeBytes(prefix + boundary + prefix + end);
//
//                    }
//
//                    dos.close();
//                    dos.flush();



                    int response = conn.getResponseCode();


                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        };

        new Thread(downloadRun).start();


    }
}
