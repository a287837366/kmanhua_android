package kankan.km.com.manhupro.main.module;

import java.util.ArrayList;

/**
 * Created by apple on 16/2/18.
 */
public class ManhuaResponseModel {

    private String m_name;
    private String count;
    private String msg;
    private ArrayList<ManhuaModel> newdata;
    private ArrayList<ManhuaModel> freedata;

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
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

    public ArrayList<ManhuaModel> getNewdata() {
        return newdata;
    }

    public void setNewdata(ArrayList<ManhuaModel> newdata) {
        this.newdata = newdata;
    }

    public ArrayList<ManhuaModel> getFreedata() {
        return freedata;
    }

    public void setFreedata(ArrayList<ManhuaModel> freedata) {
        this.freedata = freedata;
    }
}
