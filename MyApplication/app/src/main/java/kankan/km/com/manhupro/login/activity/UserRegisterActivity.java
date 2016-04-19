package kankan.km.com.manhupro.login.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.service.UserRegisterService;

/**
 * Created by apple on 16/4/16.
 */
public class UserRegisterActivity extends Activity implements View.OnClickListener{

    private EditText edit_userName;
    private EditText edit_NikeName;
    private EditText edit_pw;
    private EditText edit_confirm;

    private UserRegisterService serivce;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);

        initData();
        initView();
    }

    private void initData(){
        context = this;

        serivce = new UserRegisterService(this, new MyHandler());

    }

    private void initView(){

        edit_userName = (EditText) findViewById(R.id.edit_userName);
        edit_NikeName = (EditText) findViewById(R.id.edit_NikeName);
        edit_pw = (EditText) findViewById(R.id.edit_pw);
        edit_confirm = (EditText) findViewById(R.id.edit_confirm);

        findViewById(R.id.btn_Register).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_Register:

                if (canRegister()){
                    serivce.resUser(edit_userName.getText().toString(), edit_pw.getText().toString(), edit_NikeName.getText().toString());
                }

                break;

            default:

                break;
        }

    }

    private boolean canRegister(){

        if (edit_userName.getText().length() <= 0){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_userName.getText().length() <= 6){
            Toast.makeText(this, "账号要6位或6位以上", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_NikeName.getText().length() <= 0){
            Toast.makeText(this, "请输入昵称", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_pw.getText().length() <= 0){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_pw.getText().length() <= 5){
            Toast.makeText(this, "密码需要6位或6位以上", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_pw.getText().length() >= 13){
            Toast.makeText(this, "密码需要14位以下", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edit_confirm.getText().length() <= 0){
            Toast.makeText(this, "请重新输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }



        if (!edit_confirm.getText().toString().equals(edit_pw.getText().toString())){
            Toast.makeText(this, "俩次输入密码不一样", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if (msg.what == 1){
                Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(context, "账号已存在", Toast.LENGTH_SHORT).show();

            }


        }

    }

}
