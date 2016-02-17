package kankan.km.com.manhupro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import kankan.km.com.manhupro.main.MainFragment;
import kankan.km.com.manhupro.me.MeFragment;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener{

    private static final String TAB_MAIN = "Main_TAB";
    private static final String TAB_ME = "Me_TAB";

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment mFragment;

    //[[---Views
    private RadioGroup mRadioGroup;

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

    private void initViews(){
        mRadioGroup = (RadioGroup) findViewById(R.id.rgourp_main);


        mRadioGroup.setOnCheckedChangeListener(this);

        this.changeFragment("Main_TAB");
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

        } else if(tag.equals(TAB_ME)){

            return new MeFragment();

        }

        return null;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {

        switch (checkId) {

            case R.id.click_main_tab:

                this.changeFragment(TAB_MAIN);

                break;

            case R.id.click_main_me:

                this.changeFragment(TAB_ME);

                break;

        }

    }
}
