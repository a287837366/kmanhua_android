package kankan.km.com.manhupro.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.ChooseTypesAdapter;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/5/23.
 */
public class ChooseTypeActivity extends BaseAcvitiy implements View.OnClickListener, AdapterView.OnItemClickListener{

    private GridView gridTypes;
    private ChooseTypesAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitiy_choosetype);

        initData();
        initView();
    }


    private void initView(){
        findViewById(R.id.btn_cancle).setOnClickListener(this);
        findViewById(R.id.btn_commit).setOnClickListener(this);

        gridTypes = (GridView) findViewById(R.id.gridtypes);
        gridTypes.setAdapter(adpter);
        gridTypes.setOnItemClickListener(this);
    }


    private void initData(){

        adpter = new ChooseTypesAdapter(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 30){
            setResult(30);
            finish();
        }
    }

    private void gotoCreatePage(){
        Intent intent = new Intent();
        intent.setClass(this, CreateManhuaAcvitiy.class);
        intent.putExtra(Constant.INTENT_TAG.CREATE_TYPE, adpter.getSelectedPosion() + 1);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        adpter.setSelectedPosion(i);
        adpter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_cancle:
                finish();
                break;

            case R.id.btn_commit:
                gotoCreatePage();
                break;


            default:

                break;

        }

    }
}
