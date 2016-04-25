package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import kankan.km.com.manhupro.R;

/**
 * Created by aspn300 on 16/2/17.
 */
public class MeFeedBackActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mefeedback);
        System.out.print("MeFeedBackActivity :: onCreate");


        this.initObjects();
        this.initViews();

    }

    private void initObjects(){


    }

    private void initViews(){

        findViewById(R.id.btn_back).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_back:
                finish();

                break;

            default:

                break;

        }


    }
}
