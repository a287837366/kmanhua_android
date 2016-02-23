package kankan.km.com.manhupro.me.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import kankan.km.com.manhupro.R;

/**
 * Created by aspn300 on 16/2/17.
 */
public class MeFeedBackActivity extends Activity implements View.OnClickListener{

    private EditText edit_feedBack;

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
        edit_feedBack = (EditText) findViewById(R.id.edit_feedBack);

        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_commit).setOnClickListener(this);

    }

    private void showAlert(){
        new AlertDialog.Builder(MeFeedBackActivity.this)
                .setTitle("좋은 의견 감사합니다,")
                .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_back:

                finish();

                break;

            case R.id.btn_commit:

               if (edit_feedBack.getText().length() == 0){
                   Toast.makeText(this, "의견을 입력해주세요.", Toast.LENGTH_SHORT).show();
                   return;
               }

                showAlert();

                break;

            default:

                break;


        }



    }


}
