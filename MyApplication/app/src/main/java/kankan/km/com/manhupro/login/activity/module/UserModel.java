package kankan.km.com.manhupro.login.activity.module;

import java.io.Serializable;

/**
 * Created by aspn300 on 16/4/18.
 */
public class UserModel implements Serializable{
    private String phoneNo;
    private String id;
    private String nikename;
    private String username;
    private String userpw;

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
