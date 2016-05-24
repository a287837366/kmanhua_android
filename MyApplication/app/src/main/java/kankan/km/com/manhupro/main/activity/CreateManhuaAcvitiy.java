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
import java.util.HashMap;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.adapter.CreateImageAdapter;
import kankan.km.com.manhupro.main.service.CreateManhuaservice;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;
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
    private CreateManhuaservice service;

    private Context context;

    private int createType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmanhua);

        initData();
        initView();

    }

    private void initData(){

        context = this;

        //----测试
        service = new CreateManhuaservice(this, new MyHandler());
        imageList = new ArrayList<BitmapBean>();
        adapter = new CreateImageAdapter(this, imageList);

        createType = getIntent().getIntExtra(Constant.INTENT_TAG.CREATE_TYPE, 1);

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
        intent.putExtra(Constant.INTENT_TAG.CURRENT_SELECTED_IMAGE, imageList.size());
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

                this.showLoad();

                if (imageList.size() == 0){


                    UserModel model =(UserModel) SharedPreUtils.getObject(context, "AM_KEY_USER");


                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("m_fromdata", model.getNikename());
                    params.put("m_type", "" + createType);
                    params.put("u_phoneno", edit_phone.getText().toString());
                    params.put("mcontent", edit_conmand.getText().toString());
                    params.put("imageList", "");
                    params.put("username", model.getUsername());
                    params.put("manhuaName", edit_title.getText().toString());

                    service.postManhuaDetail(params);

                } else {

                    service.updateImage(imageList);
                }



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

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);


            switch (msg.getData().getInt(Constant.TAG_NEWORK)){

                case Constant.NETWORK_TAG.UPDATE_MANHUA:
                    dismissLoad();

                    Toast.makeText(context, "创建成功", Toast.LENGTH_SHORT).show();
                    setResult(30);
                    finish();

                    break;

                case CreateManhuaservice.UPDATE_IMAGE_TAG:

                    if (msg.what == 0) {


                        /**
                         * @param -m_fromdata   - nikeName
                         * @param -m_type -类型
                         * @param -u_phoneno -u_phoneno
                         * @param -mcontent
                         * @param -imageList -图片数组
                         * @param -username
                         * */
                        UserModel model =(UserModel) SharedPreUtils.getObject(context, "AM_KEY_USER");


                        Log.d("TAG ", "m_fromdata  => " + model.getNikename());
                        Log.d("TAG ", "m_type  => " + createType);
                        Log.d("TAG ", "u_phoneno  => " + edit_phone.getText().toString());
                        Log.d("TAG ", "mcontent  => " + edit_conmand.getText().toString());
                        Log.d("TAG ", "imageList  => " + msg.getData().getString(CreateManhuaservice.IMAGE_HANDLER_TAG));
                        Log.d("TAG ", "username  => " + model.getUsername());
                        Log.d("TAG ", "manhuaName  => " + edit_title.getText().toString());

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("m_fromdata", model.getNikename());
                        params.put("m_type", "" + createType);
                        params.put("u_phoneno", edit_phone.getText().toString());
                        params.put("mcontent", edit_conmand.getText().toString());
                        params.put("imageList", msg.getData().getString(CreateManhuaservice.IMAGE_HANDLER_TAG));
                        params.put("username", model.getUsername());
                        params.put("manhuaName", edit_title.getText().toString());

                        service.postManhuaDetail(params);


                    } else {

                        dismissLoad();

                    }



                    break;


                default:

                    break;


            }





        }

    }


    public final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            Bimp.drr.clear();
            ArrayList<BitmapBean> imgpathList = (ArrayList<BitmapBean>) msg.obj;

            imageList.addAll(imgpathList);
            adapter.notifyDataSetChanged();

            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) image_grid.getLayoutParams();

            WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            int screenWidth = wm.getDefaultDisplay().getWidth();

            int gridViewHeight = ((screenWidth + 30) / 4) * ((imageList.size() / 4) + 1);
            linearParams.height = gridViewHeight;
            image_grid.setLayoutParams(linearParams);

        }
    };

}
