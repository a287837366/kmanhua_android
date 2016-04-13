package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.activity.ManhuaDetailActivity;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.property.Constant;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;

/**
 * Created by apple on 16/2/14.
 */
public class MainBaseAdapter extends BaseAdapter{
    private String TAG = MainBaseAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private Activity mActivity;

    private final int TYPE_B = 1;
    private final int TYPE_D = 2;

    private ArrayList<ManhuaModel> news;

    public MainBaseAdapter(Activity mActivity, ArrayList<ManhuaModel> newLists) {
        this.news = newLists;
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {

        if (news.size() == 0) {
            return 0;
        }

        return this.news.size() + 1;
    }

    @Override
    public Object getItem(int position) {

        return null;

    }


    @Override
    public long getItemId(int position) {

        return position;

    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if(position == news.size()) {
            return TYPE_D;
        }

        else {
            return TYPE_B;
        }
    }

    @Override
    public int getViewTypeCount() {

        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ViewBottomHolder bottomHolder = null;


        int type = getItemViewType(position);

        if (convertView == null) {


            switch (type){

                case TYPE_B:

                    convertView = inflater.inflate(R.layout.listitem_mainbase, parent, false);
                    viewHolder = new ViewHolder();

//                    viewHolder.imageView_icon = (NetworkImageView) convertView.findViewById(R.id.imageView_icon);
//                    viewHolder.text_time = (TextView) convertView.findViewById(R.id.text_time);
//                    viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);

                    convertView.setTag(viewHolder);

                    break;

                case TYPE_D:

                    convertView = inflater.inflate(R.layout.listitem_nomaldata, parent, false);
                    bottomHolder = new ViewBottomHolder();


                    convertView.setTag(bottomHolder);
                    break;

                default:

                    break;

            }


        } else {

            switch (type) {

                case TYPE_B:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_D:
                    bottomHolder = (ViewBottomHolder) convertView.getTag();
                    break;

                default:
                    break;
            }

        }

        return convertView;
    }

    //普通的Item
    private class ViewHolder{

        TextView text_time;
        TextView text_title;

        NetworkImageView imageView_icon;



    }

    private class ViewBottomHolder{



    }



}
