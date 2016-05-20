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

    private String setPostParam(String key, String value, String boundary) {

        StringBuffer sb = new StringBuffer();
        //在boundary关需添加两个横线
        sb = sb.append("--").append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"" + key + "\"");
        //提交的数据前要有两个回车换行
        sb.append("\r\n\r\n");
        sb.append(value);
        sb.append("\r\n");


        return sb.toString();
    }

    public void updateImage(final String requestURL, final List<BitmapBean> bitmaps, final ResponseCallback callback, final int tag) {

        Log.d("上传图片", "图片数量为" + bitmaps.size());

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
                    conn.setRequestProperty("connection", "keep-alive");
                    conn.setRequestProperty("Content-Type", content_type + ";boundary=" + boundary);

                    //在boundary关需添加两个横线
//                    sb = sb.append("--").append(boundary);
//                    sb.append("\r\n");
//                    sb.append("Content-Disposition: form-data; name=\"username\"");
//                    //提交的数据前要有两个回车换行
//                    sb.append("\r\n\r\n");
//                    sb.append("11111");
//                    sb.append("\r\n");
//                    //第二个提交的参数
//                    sb.append("--").append(boundary);
//                    sb.append("\r\n");
//                    sb.append("Content-Disposition: form-data; name=\"submit\"");
//                    sb.append("\r\n\r\n");
//                    sb.append("Convert");
//                    sb.append("\r\n");
                    //body结束时 boundary前后各需添加两上横线，最添加添回车换行


//                    conn.setRequestProperty("username", "test003");
//                    conn.setRequestProperty("imagecout", bitmaps.size() + "");
//                    conn.setRequestProperty("imagetag", imageTag + "");
//                    conn.setRequestProperty("deveice_id", "11111111");

                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

                    dos.writeBytes(setPostParam("username", "test003", boundary));
                    dos.writeBytes(setPostParam("imagecout", "" + bitmaps.size() , boundary));
                    dos.writeBytes(setPostParam("imagetag", "" + imageTag, boundary));
                    dos.writeBytes(setPostParam("deveice_id", "11111111", boundary));

                    for (int i = 0; i < bitmaps.size(); i++) {

                        dos.writeBytes(prefix + boundary);
                        dos.writeBytes(end);

                        dos.writeBytes("Content-Disposition: form-data; name=\"" + imageTag + "_" + i + "\";filename=\"" +imageTag + "_" + i + ".jpg\"" );
                        dos.writeBytes(end);

                        dos.writeBytes("Content-Type: image/jpg");
                        dos.writeBytes(end);
                        dos.writeBytes(end);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmaps.get(i).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, baos);
                        dos.write(baos.toByteArray(), 0, baos.toByteArray().length);
                        dos.writeBytes(baos.toString("utf-8"));


                        dos.writeBytes(end);



                    }


                    dos.writeBytes(prefix + boundary + prefix + end);


                    dos.close();
                    dos.flush();




                    int response = conn.getResponseCode();


                } catch (Exception e){
                    e.printStackTrace();
                }


            }
        };

        new Thread(downloadRun).start();


    }
}
