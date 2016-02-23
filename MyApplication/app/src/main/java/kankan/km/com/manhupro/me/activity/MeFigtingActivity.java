package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import kankan.km.com.manhupro.R;

/**
 * Created by aspn300 on 16/2/23.
 */
public class MeFigtingActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figtingme);

        initObjects();
        initViews();
    }

    private void initObjects(){


    }

    private void initViews(){
        findViewById(R.id.btn_zan).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_zan:

                Toast.makeText(this, "감사합니당.", Toast.LENGTH_SHORT).show();

                break;

            case  R.id.btn_back:

                finish();

                break;

            default:

                break;


        }



    }
}
