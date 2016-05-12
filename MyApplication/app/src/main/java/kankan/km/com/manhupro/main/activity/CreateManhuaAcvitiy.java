package kankan.km.com.manhupro.main.activity;

import android.os.Bundle;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.tools.AlbumTools.AlbumHelper;

/**
 * Created by apple on 16/5/11.
 */
public class CreateManhuaAcvitiy extends BaseAcvitiy{

    AlbumHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmanhua);


        initData();
    }

    private void initData(){
        helper = AlbumHelper.getHelper();
        helper.init(this);

        helper.getImagesBucketList(false);

    }
}
