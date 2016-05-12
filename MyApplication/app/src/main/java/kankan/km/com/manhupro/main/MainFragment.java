package kankan.km.com.manhupro.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import kankan.km.com.manhupro.BaseAcvitiy;
import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.UserLoginActivity;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.activity.CreateManhuaAcvitiy;
import kankan.km.com.manhupro.main.activity.ManhuaDetailActivity;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.main.service.ManhuaService;
import kankan.km.com.manhupro.me.MeActivity;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;

/**
 * Created by apple on 16/2/14.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private String TAG = MainFragment.class.getSimpleName();

    private MainActivity activity;
    private ListView listView_Main;
    private Button btn_me;

    private MainBaseAdapter adapter;
    private ManhuaService manhuaService;
    private PopupWindow popupWindow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = (MainActivity) activity;



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);

        this.initViews(view);

        return view;
    }

    private void initViews(View view){
        listView_Main = (ListView) view.findViewById(R.id.listView_Main);
        btn_me = (Button)view.findViewById(R.id.btn_me);

        btn_me.setOnClickListener(this);
        listView_Main.setOnItemClickListener(this);
        view.findViewById(R.id.btn_menu).setOnClickListener(this);
        view.findViewById(R.id.btn_create).setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();
//        activity.showLoad();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.initObjects();
    }

    private void initObjects(){
        Log.e(TAG, "initObjects");

        manhuaService = new ManhuaService(this.activity, new MyHandler());

        this.adapter = new MainBaseAdapter(this.activity, manhuaService.news);

        listView_Main.setAdapter(this.adapter);

        manhuaService.getManhuaListByType(0);
    }


    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            adapter.refreshByType(manhuaService.isNoData ? 0 : 1);

        }

    }




    //-----Actions
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (position == manhuaService.news.size()){

            if (manhuaService.isNoData){

                return;
            }

            manhuaService.getManhuaListByCurrent();
            adapter.refreshByType(2);
            return;
        }

        this.gotoManhuDetilaPage(manhuaService.news.get(position));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_menu:
                this.showPopView(v);
                break;

            case R.id.btn_me:

                UserModel model =(UserModel) SharedPreUtils.getObject(this.getActivity(), "AM_KEY_USER");

                if (model == null){

                    this.gotoUserLogin();
                } else {

                    this.gotoMeActivity();
                }


                break;

            case R.id.btn_create:
                this.gotoCreatePage();
                break;

        }
    }

    //-----Goto
    private void gotoManhuDetilaPage(ManhuaModel model){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), ManhuaDetailActivity.class);
        intent.putExtra(Constant.INTENT_TAG.MANHUA_ID, model.getM_uid());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_TITLE, model.getM_title());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_ICON, model.getM_icon());
        intent.putExtra(Constant.INTENT_TAG.USER_NAME, model.getM_fromdata());
        intent.putExtra(Constant.INTENT_TAG.CREATE_TIME, model.getM_createTime());
        intent.putExtra(Constant.INTENT_TAG.USER_PHONE, model.getU_phoneno());
        intent.putExtra(Constant.INTENT_TAG.MANHUA_TYPE, model.getM_type());
        this.getActivity().startActivity(intent);
    }

    private void gotoUserLogin(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), UserLoginActivity.class);
        this.getActivity().startActivity(intent);

    }

    private void gotoCreatePage(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), CreateManhuaAcvitiy.class);
        this.getActivity().startActivity(intent);
    }

    private void gotoMeActivity(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeActivity.class);
        this.getActivity().startActivity(intent);
    }


    //--------popView
    private void showPopView(View view){

        if (popupWindow == null)
        {
            View contentView = LayoutInflater.from(activity).inflate(
                    R.layout.pop_rightmenu, null);

            PopViewClick popClick = new PopViewClick();

            contentView.findViewById(R.id.btn_all).setOnClickListener(popClick);
            contentView.findViewById(R.id.btn_recruit).setOnClickListener(popClick);
            contentView.findViewById(R.id.btn_wanted).setOnClickListener(popClick);
            contentView.findViewById(R.id.btn_estate).setOnClickListener(popClick);
            contentView.findViewById(R.id.btn_pet).setOnClickListener(popClick);

            popupWindow  = new PopupWindow(contentView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);

            popupWindow.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.click_main_tab));
            popupWindow.setTouchable(true);


        }


        popupWindow.showAsDropDown(view);

    }

    private void hidePopView(int manhuaType)
    {

        if (popupWindow == null)
        {
            return;
        }

        manhuaService.news.clear();
        manhuaService.pageCount = 0;
        adapter.notifyDataSetChanged();
        popupWindow.dismiss();

        manhuaService.getManhuaListByType(manhuaType);
    }

    private class PopViewClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.btn_all:
                    Log.d(TAG, "点击全部");
                    hidePopView(0);
                    break;

                case R.id.btn_recruit:
                    hidePopView(1);
                    Log.d(TAG, "招聘信息");
                    break;

                case R.id.btn_wanted:
                    hidePopView(2);
                    Log.d(TAG, "求职信息");
                    break;

                case R.id.btn_estate:
                    hidePopView(3);
                    Log.d(TAG, "房产信息");
                    break;

                case R.id.btn_pet:
                    hidePopView(4);
                    Log.d(TAG, "宠物信息");
                    break;




                default:

                    break;

            }

        }
    }
}
