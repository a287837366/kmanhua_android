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
import java.util.UUID;


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

    private String setPostParam(String key, String value) {

        return "Content-Disposition: form-data; name=\"" + key +"\"" + "\r\n\n" + value + "";
    }

    public void updateImage(final String requestURL, final List<BitmapBean> bitmaps, final ResponseCallback callback, final int tag) {

        Runnable downloadRun = new Runnable(){

            @Override
            public void run() {
                String boundary = "Boundary+5404CB30F509607D"; // 边界标识 随机生成
                String prefix = "--", end = "\r\n";
                String content_type = "multipart/form-data"; // 内容类型
                String CHARSET = "utf-8"; // 设置编码

                try {
                    long imageTag = System.currentTimeMillis();

                    URL url = new URL(requestURL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setReadTimeout(60000);
//                    conn.setConnectTimeout(60000);
                    conn.setDoInput(true); // 允许输入流
                    conn.setDoOutput(true); // 允许输出流
                    conn.setUseCaches(false); // 不允许使用缓存
                    conn.setRequestMethod("POST"); // 请求方式
                    conn.setRequestProperty("Charset", CHARSET); // 设置编码
//                    conn.setRequestProperty("connection", "keep-alive");
                    conn.setRequestProperty("Content-Type", content_type + ";boundary=" + boundary);
//
//                    conn.setRequestProperty("username", "test003");
//                    conn.setRequestProperty("imagecout", bitmaps.size() + "");
//                    conn.setRequestProperty("imagetag", imageTag + "");
//                    conn.setRequestProperty("deveice_id", "11111111");

                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

                    StringBuffer stringBuffer = new StringBuffer();
//                    stringBuffer.append(prefix);
//                    stringBuffer.append(boundary);
//                    stringBuffer.append(end);
//                    dos.write(stringBuffer.toString().getBytes());
////
                    dos.writeBytes(prefix + boundary + end);

                    dos.writeBytes("Content-Disposition: form-data; name=\"deveice_id\"" + end);
                    dos.writeBytes("4E6BB995-BE23-49AD-8DF1-6337EAA06ACC" + end);
                    dos.writeBytes(end);

                    dos.writeBytes(prefix + boundary + prefix + end);

                    dos.close();
//                    dos.flush();


//                    dos.writeBytes(setPostParam("deveice_id", "4E6BB995-BE23-49AD-8DF1-6337EAA06ACC"));
//                    dos.writeBytes("\n" + prefix + boundary + "\n");
//                    dos.writeBytes(setPostParam("username", "test003"));
//                    dos.writeBytes("\n" + prefix + boundary + "\n");
//                    dos.writeBytes(setPostParam("imagetag", imageTag + ""));
//                    dos.writeBytes("\n" + prefix + boundary + "\n");
//                    dos.writeBytes(setPostParam("imagecout", bitmaps.size() + ""));



//                    for (int i = 0; i < bitmaps.size(); i++) {
//
//                        dos.writeBytes(end + prefix + boundary + end);
//
//                        dos.writeBytes("Content-Disposition: form-data; name=\"" + imageTag + "_" + i + "\";filename=\"" + bitmaps.get(i).getFileName() + "\"" + end);
//                        dos.writeBytes("Content-Type: image/jpg" + end);
//                        dos.writeBytes(end);
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmaps.get(i).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
////                        dos.write(baos.toByteArray(), 0, baos.toByteArray().length);
//                        dos.writeBytes(baos.toString("utf-8"));
//
//                        System.out.println(">>>>>>> " + baos.toByteArray().length + "     "  + "   " + bitmaps.get(i).getFileName());
//
//                        dos.writeBytes(end + prefix + boundary + prefix + end);
//
//                    }






                    int response = conn.getResponseCode();


                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        };

        new Thread(downloadRun).start();


    }
}
