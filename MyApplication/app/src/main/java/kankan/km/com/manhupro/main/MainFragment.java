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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.UserLoginActivity;
import kankan.km.com.manhupro.main.activity.ManhuaDetailActivity;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;
import kankan.km.com.manhupro.main.service.ManhuaService;
import kankan.km.com.manhupro.me.MeActivity;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/2/14.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener{

    private String TAG = MainFragment.class.getSimpleName();

    private Activity activity;
    private ListView listView_Main;
    private Button btn_me;

    private MainBaseAdapter adapter;

    private ManhuaService manhuaService;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;

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

            adapter.notifyDataSetChanged();

        }

    }




    //-----Actions
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_menu:

                break;

            case R.id.btn_me:
//                this.gotoMeActivity();
                this.gotoUserLogin();
                break;

        }
    }

    //-----Goto
    private void gotoManhuDetilaPage(String manhuaId, String manhuaName){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), ManhuaDetailActivity.class);
        intent.putExtra(Constant.INTENT_TAG.MANHUA_ID, manhuaId);
        intent.putExtra(Constant.INTENT_TAG.MANHUA_TITLE, manhuaName);
        this.getActivity().startActivity(intent);
    }

    private void gotoUserLogin(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), UserLoginActivity.class);
        this.getActivity().startActivity(intent);

    }

    private void gotoMeActivity(){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), MeActivity.class);
        this.getActivity().startActivity(intent);
    }
}
