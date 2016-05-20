package kankan.km.com.manhupro.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.login.activity.service.UserLoginService;
import kankan.km.com.manhupro.property.SharedPreUtils;

/**
 * Created by apple on 16/4/15.
 */
public class UserLoginActivity extends BaseAcvitiy implements View.OnClickListener{

    private static final String SHARE_KEY = "AM_KEY_USER";

    private static final String TAG = UserLoginActivity.class.getSimpleName();

    private EditText edit_UserName;
    private EditText edit_PassWord;

    private UserLoginService service;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        initDate();
        initView();

    }

    private void initDate(){
        context = this;

        service = new UserLoginService(this, new MyHandler());
    }

    private void initView(){

        edit_UserName = (EditText) findViewById(R.id.editUserName);
        edit_PassWord = (EditText) findViewById(R.id.edit_PassWord);

        findViewById(R.id.btn_cancle).setOnClickListener(this);
        findViewById(R.id.btn_resgiter).setOnClickListener(this);
        findViewById(R.id.btn_Register).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_cancle:
                finish();
                break;

            case R.id.btn_resgiter:

                Log.d(TAG, "点击注册");
                gotoRegister();
                break;

            case R.id.btn_Register:
                Log.d(TAG, "点击登入");

                if (canConfrim()){
                    dismissLoad();
                    service.getLoginUser(edit_UserName.getText().toString(), edit_PassWord.getText().toString());
                }


                break;


            default:

                break;

        }
    }


    //------判断输入框
    private boolean canConfrim() {

        if (edit_UserName.getText().length() <= 0){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_PassWord.getText().length() <= 0){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


    //-------Goto
    private void gotoRegister(){

        Intent intent = new Intent();
        intent.setClass(this, UserRegisterActivity.class);
        this.startActivity(intent);

    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);



            if (msg.what == -1){
                Toast.makeText(context, "账号或者密码错误", Toast.LENGTH_SHORT).show();

            } else {

                if (service.model != null){
                    SharedPreUtils.saveObject(context, SHARE_KEY, service.model);
                }

                UserModel model = (UserModel) SharedPreUtils.getObject(context, SHARE_KEY);
                Log.d(TAG, ">>>>>>>>>" + model);
//                Toast.makeText(context, "登入成功" + model.getUserpw(), Toast.LENGTH_SHORT).show();
                finish();
            }



        }

    }


}
