package kankan.km.com.manhupro.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;

/**
 * Created by apple on 16/2/14.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener{

    private String TAG = MainFragment.class.getSimpleName();

    private Activity activity;
    private ListView listView_Main;
    private MainBaseAdapter adapter;

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

        ArrayList<String> strings = new ArrayList<String>();
        strings.add("1111");
        strings.add("2222");
        strings.add("3333");
        strings.add("4444");
        this.adapter = new MainBaseAdapter(this.activity, strings);

        listView_Main.setAdapter(this.adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.println(TAG + "onItemClick --> " + position);
        Log.d(TAG , "onItemClick -->  " + position);


    }
}
