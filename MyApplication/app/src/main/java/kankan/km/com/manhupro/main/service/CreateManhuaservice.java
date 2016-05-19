package kankan.km.com.manhupro.main.service;

import java.util.ArrayList;

import kankan.km.com.manhupro.tools.httptools.HttpClinet;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.BitmapBean;

/**
 * Created by apple on 16/5/17.
 */
public class CreateManhuaservice {



    public void getManhuaById(ArrayList<BitmapBean> bips){
       HttpClinet.getInstance().updateImage("http://192.168.1.104:8080/kankanAdmin/UploadImage", bips, null, 100);
    }


}
