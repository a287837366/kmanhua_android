package kankan.km.com.manhupro.login.activity.module;

/**
 * Created by aspn300 on 16/4/18.
 */
public class GetUserResponse {

    private String count;
    private String error;
    private String msg;

    private UserModel data;


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

    public UserModel getData() {
        return data;
    }

    public void setData(UserModel data) {
        this.data = data;
    }
}
