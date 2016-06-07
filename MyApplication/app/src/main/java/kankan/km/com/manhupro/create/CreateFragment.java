package kankan.km.com.manhupro.create;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.UserLoginActivity;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.activity.CreateManhuaAcvitiy;
import kankan.km.com.manhupro.main.adapter.ChooseTypesAdapter;
import kankan.km.com.manhupro.main.service.ManhuaService;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.property.SharedPreUtils;

/**
 * Created by apple on 16/6/6.
 */
public class CreateFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private MainActivity activity;

    private GridView gridTypes;
    private Button btn_commit;
    private ChooseTypesAdapter adpter;

    private RelativeLayout emptyView;

    private ManhuaService manhuaService;

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
        View view = inflater.inflate(R.layout.acvitiy_choosetype, null);

        manhuaService = new ManhuaService(this.activity, new MyHandler());

        this.initViews(view);

        view.findViewById(R.id.btn_commit).setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        refrshView();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        refrshView();
    }

    public void refrshView(){

        UserModel model =(UserModel) SharedPreUtils.getObject(this.getActivity(), "AM_KEY_USER");

        if (model == null){

            emptyView.setVisibility(View.VISIBLE);
            gridTypes.setVisibility(View.GONE);
            btn_commit.setVisibility(View.GONE);

        } else {

            emptyView.setVisibility(View.GONE);
            gridTypes.setVisibility(View.VISIBLE);
            btn_commit.setVisibility(View.VISIBLE);
        }



    }

    private void initViews(View view){

        adpter = new ChooseTypesAdapter(this.getActivity());

        view.findViewById(R.id.btn_login).setOnClickListener(this);
        emptyView = (RelativeLayout) view.findViewById(R.id.emptyView);
        btn_commit = (Button) view.findViewById(R.id.btn_commit);

        gridTypes = (GridView) view.findViewById(R.id.gridtypes);
        gridTypes.setAdapter(adpter);
        gridTypes.setOnItemClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initObjects(){


    }

    class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);



            if (msg.what != 0){
                Toast.makeText(activity, "连接服务器失败", Toast.LENGTH_SHORT).show();
                activity.dismissLoad();

                return;
            }

            switch (msg.getData().getInt(Constant.TAG_NEWORK)){

                case Constant.NETWORK_TAG.GET_MANHUA_TAG:

                    activity.dismissLoad();

                    break;

                case Constant.NETWORK_TAG.CHECK_PERMISSON:
                    activity.dismissLoad();


                    if (msg.getData().getBoolean(Constant.INTENT_TAG.CAN_UPDATE)){

                        gotoCreatePage();

                    } else {

                        Toast.makeText(activity, "该手机今天已上传过消息", Toast.LENGTH_SHORT).show();

                    }



                    break;


                default:

                    break;


            }





        }

    }

    private void gotoUserLogin(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), UserLoginActivity.class);
        this.getActivity().startActivity(intent);

    }
    private void gotoCreatePage(){

        Intent intent = new Intent();
        intent.setClass(this.getActivity(), CreateManhuaAcvitiy.class);
        intent.putExtra(Constant.INTENT_TAG.CREATE_TYPE, adpter.getSelectedPosion() + 1);
        startActivityForResult(intent, 100);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_login:
                gotoUserLogin();
                break;

            case R.id.btn_commit:

                activity.showLoad();

                UserModel model =(UserModel) SharedPreUtils.getObject(this.getActivity(), "AM_KEY_USER");
                manhuaService.checkPermession(model.getUsername());

                break;

            default:

                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adpter.setSelectedPosion(i);
        adpter.notifyDataSetChanged();
    }
}
