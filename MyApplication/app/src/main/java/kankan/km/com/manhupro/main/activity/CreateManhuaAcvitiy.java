package kankan.km.com.manhupro.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.tools.AlbumTools.AlbumHelper;
import kankan.km.com.manhupro.tools.tools.AlbumTools.acvitity.ChoosePictureActivity;

/**
 * Created by apple on 16/5/11.
 */
public class CreateManhuaAcvitiy extends BaseAcvitiy implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmanhua);


        initData();
        initView();

    }

    private void initData(){


    }

    private void initView(){

        findViewById(R.id.button).setOnClickListener(this);

    }

    private void gotoChooseImageView(){
        Intent intent = new Intent();
        intent.setClass(this, ChoosePictureActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.button:

                gotoChooseImageView();

                break;


            default:

                break;

        }

    }
}
