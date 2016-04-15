package kankan.km.com.manhupro.login.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kankan.km.com.manhupro.R;

/**
 * Created by apple on 16/4/15.
 */
public class UserLoginActivity extends Activity implements View.OnClickListener{

    private static final String TAG = UserLoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        initView();

    }

    private void initView(){

        findViewById(R.id.btn_cancle).setOnClickListener(this);
        findViewById(R.id.btn_resgiter).setOnClickListener(this);
        findViewById(R.id.btn_Login).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_cancle:
                finish();
                break;

            case R.id.btn_resgiter:

                Log.d(TAG, "点击注册");

                break;

            case R.id.btn_Login:

                Log.d(TAG, "点击登入");

                break;


            default:

                break;

        }



    }
}
