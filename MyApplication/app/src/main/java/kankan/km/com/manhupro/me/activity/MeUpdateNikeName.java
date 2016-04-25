package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.me.service.MeUpdateNikeNameService;
import kankan.km.com.manhupro.property.SharedPreUtils;


/**
 * Created by aspn300 on 16/4/22.
 */
public class MeUpdateNikeName extends Activity implements View.OnClickListener{

    private EditText edit_nikename;
    private Context context;

    private MeUpdateNikeNameService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenikename);

        this.init();
    }

    private void init(){
        context = this;

        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_comfirm).setOnClickListener(this);

        edit_nikename = (EditText) findViewById(R.id.edit_nikename);

        service = new MeUpdateNikeNameService(this, new MyHandler());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_comfirm:

                if (edit_nikename.getText().toString().length() <= 0){
                    Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }

                UserModel user = (UserModel) SharedPreUtils.getObject(context, "AM_KEY_USER");

                service.updateNikeName(user.getUsername(), user.getUserpw(), edit_nikename.getText().toString());

                break;


            default:

                break;

        }

    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if (msg.what == -1){
                Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();

            } else {

                UserModel user = (UserModel) SharedPreUtils.getObject(context, "AM_KEY_USER");
                user.setNikename(edit_nikename.getText().toString());

                SharedPreUtils.saveObject(context, "AM_KEY_USER", user);

                Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }



        }

    }
}
