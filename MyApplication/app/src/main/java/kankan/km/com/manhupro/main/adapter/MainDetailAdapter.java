package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.module.ManhuaDetailModel;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;

/**
 * Created by aspn300 on 16/2/18.
 */
public class MainDetailAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater inflater;
    private ArrayList<ManhuaDetailModel> viewLists;

    public MainDetailAdapter(Activity activity, ArrayList<ManhuaDetailModel> viewLists){

        this.mActivity = activity;
        this.viewLists = viewLists;
        inflater = LayoutInflater.from(mActivity);

    }

    @Override
    public int getCount() {

        return viewLists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }


    //普通的Item
    private class ViewHolder{
        NetworkImageView imageView_detail;
        TextView textLable;

        LinearLayout.LayoutParams linearParams;
    }




}
