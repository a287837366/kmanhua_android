package kankan.km.com.manhupro.tools.httptools;

/**
 * Created by apple on 16/2/18.
 */
public interface ResponseCallback {

    /**
     * 网络请求成功 处理方法
     * @param method            请求类型
     * @param tag      网络请求标示
     * @param json              请求得到的json数据
     */
    void send(int method, int tag, String json);


    /**
     * 网络请求失败 处理方法
     *
     * @param tag     网络请求标示
     * @param error            错误代码
     */
    void error(int tag, int error);


}
