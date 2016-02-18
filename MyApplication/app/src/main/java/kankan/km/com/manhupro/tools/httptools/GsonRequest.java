package kankan.km.com.manhupro.tools.httptools;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest extends Request<JSONObject> {
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final Response.Listener<JSONObject> listener;

    /**
     * 网络请求方法
     *
     * @param method        请求类型
     * @param url           请求地址
     * @param headers       头文件参数
     * @param params        post参数
     * @param listener      监听
     * @param errorListener 错误监听
     */
    public GsonRequest(int method, String url, Map<String, String> headers, Map<String, String> params, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.headers = headers;
        this.listener = listener;
        this.params = params;

    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getHeaders();
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        listener.onResponse(response);
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));

        } catch (UnsupportedEncodingException e) {

            return Response.error(new ParseError(e));

        } catch (JSONException je) {

            return Response.error(new ParseError(je));

        }
    }


}
