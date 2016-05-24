package kankan.km.com.manhupro.main.module;

/**
 * Created by apple on 16/5/24.
 */
public class CheckPermissonResponse {

    private String error;
    private String count;
    private String msg;
    private String data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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
