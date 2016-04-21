package kankan.km.com.manhupro.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.property.SharedPreUtils;

/**
 * Created by apple on 16/4/13.
 */
public class MeActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    private void initData(){


    }


    private void initView()
    {
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_Logout).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_Logout:

                SharedPreUtils.clearObject(this, "AM_KEY_USER");

                finish();
                break;





        }

    }
}
