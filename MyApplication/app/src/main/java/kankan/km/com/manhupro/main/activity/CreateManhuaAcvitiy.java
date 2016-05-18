package kankan.km.com.manhupro.main.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.CreateImageAdapter;
import kankan.km.com.manhupro.main.service.CreateManhuaservice;
import kankan.km.com.manhupro.tools.tools.AlbumTools.Bimp;
import kankan.km.com.manhupro.tools.tools.AlbumTools.acvitity.ChoosePictureActivity;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.BitmapBean;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.RunImageListBean;

/**
 * Created by apple on 16/5/11.
 */
public class CreateManhuaAcvitiy extends BaseAcvitiy implements View.OnClickListener, AdapterView.OnItemClickListener{


    private EditText edit_title;
    private EditText edit_conmand;
    private EditText edit_phone;

    private GridView image_grid;

    private CreateImageAdapter adapter;
    private ArrayList<BitmapBean> imageList;
    CreateManhuaservice service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmanhua);





        initData();
        initView();

    }

    private void initData(){

        //----测试




        service = new CreateManhuaservice();
        imageList = new ArrayList<BitmapBean>();
        adapter = new CreateImageAdapter(this, imageList);

    }

    private void initView(){

//        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);
        findViewById(R.id.btn_create).setOnClickListener(this);


        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_conmand = (EditText) findViewById(R.id.edit_conmand);
        edit_phone = (EditText) findViewById(R.id.edit_phone);

        image_grid = (GridView) findViewById(R.id.image_grid);
        image_grid.setAdapter(adapter);
        image_grid.setOnItemClickListener(this);


        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) image_grid.getLayoutParams();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();

        int gridViewHeight = ((screenWidth + 30) / 4) * ((imageList.size() / 4) + 1);
        linearParams.height = gridViewHeight;
        image_grid.setLayoutParams(linearParams);

    }

    private void gotoChooseImageView(){
        Intent intent = new Intent();
        intent.setClass(this, ChoosePictureActivity.class);
        startActivityForResult(intent, 20);
    }


    private void checkInput(){


        if (edit_title.getText().toString().length() == 0){
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edit_title.getText().toString().length() > 20){
            Toast.makeText(this, "不能超过20个字", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edit_title.getText().toString().length() == 0){
            Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (20 == requestCode) {

            RunImageListBean readlists= new RunImageListBean();
            readlists.readList(handler);
        }

    }

    @Override
    public void onClick(View view) {


        switch (view.getId()){
//
//            case R.id.button:
//
//                gotoChooseImageView();
//
//                break;

            case R.id.btn_back:

                finish();

                break;

            case R.id.btn_create:

                this.checkInput();
                service.getManhuaById(imageList);

                break;





            default:

                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (imageList.size() == 9){

            return;
        }

        if (imageList.size() == position){
            this.gotoChooseImageView();
            return;
        }

    }


    public final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            Bimp.drr.clear();
            ArrayList<BitmapBean> imgpathList = (ArrayList<BitmapBean>) msg.obj;

            imageList.addAll(imgpathList);
            adapter.notifyDataSetChanged();

        }
    };

}
