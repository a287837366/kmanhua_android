package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;


import java.util.ArrayList;

import kankan.km.com.manhupro.R;

/**
 * Created by apple on 16/2/14.
 */
public class MainBaseAdapter extends BaseAdapter{
    private String TAG = MainBaseAdapter.class.getSimpleName();

    private ArrayList<String> stringArrayList;
    private LayoutInflater inflater;
    private Activity mActivity;

    private final int TYPE_H = 0;
    private final int TYPE_B = 1;

    public MainBaseAdapter(Activity mActivity, ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {
        return this.stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.stringArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        if (position == 0) {
            return TYPE_H;

        } else {

            return TYPE_B;
        }
    }

    @Override
    public int getViewTypeCount() {

        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ViewHeaderHolder headerHolder = null;


        int type = getItemViewType(position);

        if (convertView == null) {


            switch (type){

                case TYPE_H:

                    convertView = inflater.inflate(R.layout.listitem_mainheader, parent, false);
                    headerHolder = new ViewHeaderHolder();

                    convertView.setTag(headerHolder);

                    break;

                case TYPE_B:

                    convertView = inflater.inflate(R.layout.listitem_mainbase, parent, false);
                    viewHolder = new ViewHolder();

                    convertView.setTag(viewHolder);

                    break;

                default:

                    break;

            }


        } else {

            switch (type) {
                case TYPE_H:
                    headerHolder = (ViewHeaderHolder) convertView.getTag();
                    break;
                case TYPE_B:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
                default:
                    break;
            }

        }


        return convertView;
    }


    //HeaderItem
    private class ViewHeaderHolder{


    }


    //普通的Item
    private class ViewHolder{

        TextView text_time;
        TextView text_title;

    }

}
