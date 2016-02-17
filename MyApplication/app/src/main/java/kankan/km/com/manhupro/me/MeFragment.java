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
import android.widget.RelativeLayout;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.me.activity.MeFeedBackActivity;

/**
 * Created by apple on 16/2/14.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    private String TAG = MeFragment.class.getSimpleName();

    private RelativeLayout btn_feedBack;
    private RelativeLayout btn_askMe;
    private RelativeLayout btn_fettingMe;
    private RelativeLayout btn_shareMe;

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

        btn_feedBack = (RelativeLayout) view.findViewById(R.id.btn_feedBack);
        btn_askMe = (RelativeLayout) view.findViewById(R.id.btn_askMe);
        btn_fettingMe = (RelativeLayout) view.findViewById(R.id.btn_fettingMe);
        btn_shareMe = (RelativeLayout) view.findViewById(R.id.btn_shareMe);

        btn_feedBack.setOnClickListener(this);
        btn_askMe.setOnClickListener(this);
        btn_fettingMe.setOnClickListener(this);
        btn_shareMe.setOnClickListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_feedBack:

                Log.d(TAG, "의견제출 하기");
                gotoFeedBackActivity();

                break;

            case R.id.btn_askMe:

                Log.d(TAG, "제휴문의");

                break;

            case R.id.btn_fettingMe:

                Log.d(TAG, "땐짠하기");

                break;

            case R.id.btn_shareMe:

                Log.d(TAG, "웨이씬 공유하기");

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
}
