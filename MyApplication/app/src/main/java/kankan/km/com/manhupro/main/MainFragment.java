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
import android.widget.ListView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.activity.ManhuaDetailActivity;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;
import kankan.km.com.manhupro.main.service.ManhuaService;
import kankan.km.com.manhupro.property.Constant;

/**
 * Created by apple on 16/2/14.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener{

    private String TAG = MainFragment.class.getSimpleName();

    private Activity activity;
    private ListView listView_Main;

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

        this.adapter = new MainBaseAdapter(this.activity, manhuaService.newManhuas, manhuaService.oldManhuas);

        listView_Main.setAdapter(this.adapter);

        manhuaService.getManhuaList();
    }


    class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            if (manhuaService.isNoData){
                adapter.refreshByFooter(MainBaseAdapter.REFRESH_TYPE_NO);
            } else {
                adapter.refreshByFooter(MainBaseAdapter.REFRESH_TYPE_GET);
            }



        }

    }




    //-----Actions
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


        if (manhuaService.oldManhuas.size() > 0 && position == manhuaService.oldManhuas.size() + 1){

            if (manhuaService.isNoData) return;

            if (manhuaService.isRoading) return;

            adapter.refreshByFooter(MainBaseAdapter.REFRESH_TYPE_ING);

            manhuaService.getManhuaList();

        } else {
            this.gotoManhuDetilaPage(manhuaService.oldManhuas.get(position  - 1).getM_uid(), manhuaService.oldManhuas.get(position - 1).getM_name());

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
}
