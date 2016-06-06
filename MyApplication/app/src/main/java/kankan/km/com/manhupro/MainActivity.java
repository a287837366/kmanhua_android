package kankan.km.com.manhupro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import kankan.km.com.manhupro.create.CreateFragment;
import kankan.km.com.manhupro.main.MainFragment;
import kankan.km.com.manhupro.me.MeFragment;


public class MainActivity extends BaseAcvitiy implements View.OnClickListener{

    private static final String TAB_MAIN = "Main_TAB";
    private static final String TAB_CREATE = "TAB_CREATE";
    private static final String TAB_ME = "TAB_ME";

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment mFragment;

    //[[---Views

    //---Views]]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.print("MainActivity :: onCreate");


        this.initObjects();
        this.initViews();

    }

    private void initObjects(){
        fm = getSupportFragmentManager();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(">>>>>", "requestCode = " + requestCode + "  resultCode = " + resultCode);

        if (requestCode == 100){

            if (resultCode == 30){

                MainFragment mainFragment = (MainFragment) mFragment;
                mainFragment.refreshAllData();

            }
        }



    }

    private void initViews(){
        this.changeFragment(TAB_MAIN);

        findViewById(R.id.tab_main).setOnClickListener(this);
        findViewById(R.id.tab_create).setOnClickListener(this);
        findViewById(R.id.tab_me).setOnClickListener(this);

    }


    private void changeFragment(String tag){
        ft = fm.beginTransaction();


        //先隐藏以前的
        if (mFragment != null){
            ft.hide(mFragment);
        }

        //判断缓存里是否存在
        if (fm.findFragmentByTag(tag) != null){

            mFragment = fm.findFragmentByTag(tag);

        } else {

            mFragment = this.getFragmentByTag(tag);

        }

        //后显示新增的
        if (!mFragment.isAdded()){
            ft.add(R.id.layout_main_fragment, mFragment, tag);
        }

        ft.show(mFragment);
        ft.commit();

    }

    private Fragment getFragmentByTag(String tag){

        if (tag.equals(TAB_MAIN)){

            return new MainFragment();

        } else if (tag.equals(TAB_CREATE)){

            return new CreateFragment();

        } else if (tag.equals(TAB_ME)){

            return new MeFragment();
        }

        return null;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){

            case R.id.tab_main:
                this.changeFragment(TAB_MAIN);
                break;

            case R.id.tab_create:
                this.changeFragment(TAB_CREATE);
                break;

            case R.id.tab_me:
                this.changeFragment(TAB_ME);
                break;

            default:

                break;


        }

    }
}
