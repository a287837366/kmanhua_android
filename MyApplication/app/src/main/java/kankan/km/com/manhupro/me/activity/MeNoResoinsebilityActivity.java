package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import kankan.km.com.manhupro.R;

/**
 * Created by aspn300 on 16/4/23.
 */
public class MeNoResoinsebilityActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menores);

        init();
    }

    private void init(){

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
