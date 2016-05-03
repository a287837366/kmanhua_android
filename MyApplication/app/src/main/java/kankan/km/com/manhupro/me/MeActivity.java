package kankan.km.com.manhupro.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.me.activity.MeFeedBackActivity;
import kankan.km.com.manhupro.me.activity.MeHistoryActitiy;
import kankan.km.com.manhupro.me.activity.MeNoResoinsebilityActivity;
import kankan.km.com.manhupro.me.activity.MeUpdateNikeName;
import kankan.km.com.manhupro.property.SharedPreUtils;
import kankan.km.com.manhupro.tools.dbtools.DBManager;

/**
 * Created by apple on 16/4/13.
 */
public class MeActivity extends Activity implements View.OnClickListener{
    private static final String TAG = MeActivity.class.getSimpleName();

    private TextView text_nikename;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initData();
        initView();

    }


    @Override
    protected void onResume() {
        super.onResume();

        UserModel model = (UserModel) SharedPreUtils.getObject(this, "AM_KEY_USER");
        text_nikename.setText(model.getNikename());
    }

    private void initData(){

        dbManager = new DBManager(this);


    }


    private void initView()
    {
        text_nikename = (TextView) findViewById(R.id.text_nikename);
        
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_Logout).setOnClickListener(this);
        findViewById(R.id.btn_nikename).setOnClickListener(this);
        findViewById(R.id.btn_history).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_shengming).setOnClickListener(this);

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
                dbManager.deleteAllManahua();
                Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();

                finish();

                break;

            case R.id.btn_nikename:
                Log.d(TAG, "修改昵称");

                this.gotoUpdateNikeName();

                break;

            case R.id.btn_history:
                Log.d(TAG, "我收藏的");
                this.gotoHistory();
                break;

            case R.id.btn_update:
                Log.d(TAG, "关于上传");
                this.gotoFeedBack();

                break;

            case R.id.btn_shengming:
                Log.d(TAG, "免责声明");
                this.gotoNoRes();
                break;

        }

    }


    private void gotoHistory(){
        Intent intent = new Intent();
        intent.setClass(this, MeHistoryActitiy.class);
        this.startActivity(intent);


    }

    private void gotoUpdateNikeName(){

        Intent intent = new Intent();
        intent.setClass(this, MeUpdateNikeName.class);
        this.startActivity(intent);

    }


    private void gotoFeedBack(){

        Intent intent = new Intent();
        intent.setClass(this, MeFeedBackActivity.class);
        this.startActivity(intent);

    }

    private void gotoNoRes(){

        Intent intent = new Intent();
        intent.setClass(this, MeNoResoinsebilityActivity.class);
        this.startActivity(intent);

    }
}
