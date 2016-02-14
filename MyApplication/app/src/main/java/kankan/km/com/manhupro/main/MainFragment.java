package kankan.km.com.manhupro.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.adapter.MainBaseAdapter;

/**
 * Created by apple on 16/2/14.
 */
public class MainFragment extends Fragment{

    private Activity activity;
    private ListView listView_Main;
    private LayoutInflater inflater;
    private MainBaseAdapter adapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = activity;
        inflater = LayoutInflater.from(activity);
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

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
