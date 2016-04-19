package kankan.km.com.manhupro.login.activity.module;

/**
 * Created by aspn300 on 16/4/19.
 */
public class PostUserInfoResponse {


    private String count;
    private String error;
    private String msg;

    private String data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
