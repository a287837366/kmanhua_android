package kankan.km.com.manhupro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    private TextView texttab_main;
    private TextView texttab_create;
    private TextView texttab_me;

    private ImageView image_tab_home;
    private ImageView image_tab_create;
    private ImageView image_tab_me;

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

        if (resultCode == 30){


            if (fm.findFragmentByTag(TAB_MAIN) != null){
                MainFragment mainFragment = (MainFragment) fm.findFragmentByTag(TAB_MAIN);
                mainFragment.refreshAllData();
            }


        }



    }

    private void initViews(){
        texttab_main = (TextView) findViewById(R.id.texttab_main);
        texttab_create = (TextView) findViewById(R.id.texttab_create);
        texttab_me = (TextView) findViewById(R.id.texttab_me);

        image_tab_home = (ImageView) findViewById(R.id.image_tab_home);
        image_tab_create = (ImageView) findViewById(R.id.image_tab_create);
        image_tab_me = (ImageView) findViewById(R.id.image_tab_me);

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

        changeTab(tag);

    }

    private void changeTab(String tag){

        if (tag.equals(TAB_MAIN)){
            texttab_main.setTextColor(Color.parseColor("#C50B26"));
            texttab_create.setTextColor(Color.parseColor("#888888"));
            texttab_me.setTextColor(Color.parseColor("#888888"));


            image_tab_home.setImageResource(R.mipmap.tabbar_essence_click_icon);
            image_tab_create.setImageResource(R.mipmap.tabbar_publish_icon);
            image_tab_me.setImageResource(R.mipmap.tabbar_me_icon);

        } else if (tag.equals(TAB_CREATE)){

            texttab_main.setTextColor(Color.parseColor("#888888"));
            texttab_create.setTextColor(Color.parseColor("#C50B26"));
            texttab_me.setTextColor(Color.parseColor("#888888"));

            image_tab_home.setImageResource(R.mipmap.tabbar_essence_icon);
            image_tab_create.setImageResource(R.mipmap.tabbar_publish_click_icon);
            image_tab_me.setImageResource(R.mipmap.tabbar_me_icon);

        } else if (tag.equals(TAB_ME)){
            texttab_main.setTextColor(Color.parseColor("#888888"));
            texttab_create.setTextColor(Color.parseColor("#888888"));
            texttab_me.setTextColor(Color.parseColor("#C50B26"));

            image_tab_home.setImageResource(R.mipmap.tabbar_essence_icon);
            image_tab_create.setImageResource(R.mipmap.tabbar_publish_icon);
            image_tab_me.setImageResource(R.mipmap.tabbar_me_click_icon);

        }
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
