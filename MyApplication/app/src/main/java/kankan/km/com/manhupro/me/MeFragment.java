package kankan.km.com.manhupro.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.UserLoginActivity;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.me.activity.MeFeedBackActivity;
import kankan.km.com.manhupro.me.activity.MeHistoryActitiy;
import kankan.km.com.manhupro.me.activity.MeNoResoinsebilityActivity;
import kankan.km.com.manhupro.me.activity.MeUpdateNikeName;
import kankan.km.com.manhupro.property.SharedPreUtils;
import kankan.km.com.manhupro.tools.dbtools.DBManager;

/**
 * Created by apple on 16/2/14.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    private String TAG = MeFragment.class.getSimpleName();

    private RelativeLayout btn_feedBack;
    private RelativeLayout btn_askMe;
    private RelativeLayout btn_fettingMe;
    private RelativeLayout btn_shareMe;

    private LinearLayout me_mainView;
    private RelativeLayout emptyView;

    private DBManager dbManager;

    private TextView text_nikename;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        this.initViews(view);

        return view;
    }

    private void initViews(View view){

        dbManager = new DBManager(this.getActivity());

        btn_feedBack = (RelativeLayout) view.findViewById(R.id.btn_feedBack);
        btn_askMe = (RelativeLayout) view.findViewById(R.id.btn_askMe);
        btn_fettingMe = (RelativeLayout) view.findViewById(R.id.btn_fettingMe);
        btn_shareMe = (RelativeLayout) view.findViewById(R.id.btn_shareMe);
        me_mainView = (LinearLayout) view.findViewById(R.id.me_mainView);
        emptyView = (RelativeLayout) view.findViewById(R.id.emptyView);
        text_nikename = (TextView) view.findViewById(R.id.text_nikename);

        btn_feedBack.setOnClickListener(this);
        btn_askMe.setOnClickListener(this);
        btn_fettingMe.setOnClickListener(this);
        btn_shareMe.setOnClickListener(this);
        view.findViewById(R.id.btn_login).setOnClickListener(this);
        view.findViewById(R.id.btn_Logout).setOnClickListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        refreshView();
    }

    private void refreshView(){


        UserModel model =(UserModel) SharedPreUtils.getObject(this.getActivity(), "AM_KEY_USER");

        if (model == null){

            emptyView.setVisibility(View.VISIBLE);
            me_mainView.setVisibility(View.GONE);

        } else {

            emptyView.setVisibility(View.GONE);
            me_mainView.setVisibility(View.VISIBLE);

            text_nikename.setText(model.getNikename());

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_feedBack:

                Log.d(TAG, "의견제출 하기");
                gotoUpdateNikeName();

                break;

            case R.id.btn_askMe:

                Log.d(TAG, "제휴문의");
                gotoHistory();

                break;

            case R.id.btn_fettingMe:

                Log.d(TAG, "땐짠하기");
                gotoFeedBack();

                break;

            case R.id.btn_shareMe:

                Log.d(TAG, "웨이씬 공유하기");
                gotoNoRes();

                break;

            case R.id.btn_Logout:
                SharedPreUtils.clearObject(this.getActivity(), "AM_KEY_USER");
                dbManager.deleteAllManahua();
                Toast.makeText(this.getActivity(), "注销成功", Toast.LENGTH_SHORT).show();

                refreshView();

                break;


            case R.id.btn_login:

                gotoUserLogin();

                break;

            default:

                break;


        }

    }


    private void gotoFeedBackActivity(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeFeedBackActivity.class);
        this.getActivity().startActivity(intent);
    }

    private void gotoHistory(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeHistoryActitiy.class);
        this.startActivity(intent);


    }

    private void gotoUpdateNikeName(){

        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeUpdateNikeName.class);
        this.startActivity(intent);

    }


    private void gotoFeedBack(){

        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeFeedBackActivity.class);
        this.startActivity(intent);

    }

    private void gotoNoRes(){

        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeNoResoinsebilityActivity.class);
        this.startActivity(intent);

    }


    private void gotoUserLogin(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), UserLoginActivity.class);
        this.getActivity().startActivity(intent);

    }
}
