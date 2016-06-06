package kankan.km.com.manhupro.create;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;


import kankan.km.com.manhupro.MainActivity;
import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.login.activity.module.UserModel;
import kankan.km.com.manhupro.main.adapter.ChooseTypesAdapter;
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

        this.initViews(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

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


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_login:

                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
