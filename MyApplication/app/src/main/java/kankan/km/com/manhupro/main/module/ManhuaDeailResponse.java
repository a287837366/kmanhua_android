package kankan.km.com.manhupro.main.module;

import java.util.ArrayList;

/**
 * Created by aspn300 on 16/2/18.
 */
public class ManhuaDeailResponse {

    private String error;
    private String count;
    private String msg;
    private ArrayList<ManhuaDetailModel> data;

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

    public ArrayList<ManhuaDetailModel> getData() {
        return data;
    }

    public void setData(ArrayList<ManhuaDetailModel> data) {
        this.data = data;
    }

}
