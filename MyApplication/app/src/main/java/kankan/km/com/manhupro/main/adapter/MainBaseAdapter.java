package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.module.ManhuaModel;

/**
 * Created by apple on 16/2/14.
 */
public class MainBaseAdapter extends BaseAdapter{
    private String TAG = MainBaseAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private Activity mActivity;

    private final int TYPE_H = 0;
    private final int TYPE_B = 1;

    private ArrayList<ManhuaModel> newLists;
    private ArrayList<ManhuaModel> freeLists;

    public MainBaseAdapter(Activity mActivity, ArrayList<ManhuaModel> newLists, ArrayList<ManhuaModel> freeLists) {
        this.newLists = newLists;
        this.freeLists = freeLists;
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {

        return this.freeLists.size() + 1;
    }

    @Override
    public Object getItem(int position) {

        if (position == 0){

            return newLists;
        }

        return freeLists.get(position - 1);

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

                    headerHolder.img_title = (RelativeLayout) convertView.findViewById(R.id.img_title);
                    headerHolder.img_content1 = (RelativeLayout) convertView.findViewById(R.id.img_content1);
                    headerHolder.img_content2 = (RelativeLayout) convertView.findViewById(R.id.img_content2);
                    headerHolder.img_content3 = (RelativeLayout) convertView.findViewById(R.id.img_content3);

                    headerHolder.textView_top = (TextView) convertView.findViewById(R.id.textView_top);

                    headerHolder.textHeader_time1 = (TextView) convertView.findViewById(R.id.textHeader_time1);
                    headerHolder.textHeader_time2 = (TextView) convertView.findViewById(R.id.textHeader_time2);
                    headerHolder.textHeader_time3 = (TextView) convertView.findViewById(R.id.textHeader_time3);
                    headerHolder.textHeader_title1 = (TextView) convertView.findViewById(R.id.textHeader_title1);
                    headerHolder.textHeader_title2 = (TextView) convertView.findViewById(R.id.textHeader_title2);
                    headerHolder.textHeader_title3 = (TextView) convertView.findViewById(R.id.textHeader_title3);

                    headerHolder.img_title.setOnClickListener(headerHolder);
                    headerHolder.img_content1.setOnClickListener(headerHolder);
                    headerHolder.img_content2.setOnClickListener(headerHolder);
                    headerHolder.img_content3.setOnClickListener(headerHolder);

                    convertView.setTag(headerHolder);

                    break;

                case TYPE_B:

                    convertView = inflater.inflate(R.layout.listitem_mainbase, parent, false);
                    viewHolder = new ViewHolder();

                    viewHolder.text_time = (TextView) convertView.findViewById(R.id.text_time);
                    viewHolder.text_title = (TextView) convertView.findViewById(R.id.text_title);

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

        if (type == TYPE_H){

            setHeader(headerHolder);

        } else {

            viewHolder.text_time.setText(freeLists.get(position - 1).getM_createTime());
            viewHolder.text_title.setText(freeLists.get(position - 1).getM_name());

        }


        return convertView;
    }


    private void setHeader(ViewHeaderHolder headerHolder){

        if (this.newLists.size() == 0)
            return;

        headerHolder.textView_top.setText(this.newLists.get(0).getM_name());

        headerHolder.textHeader_time1.setText(this.newLists.get(1).getM_createTime());
        headerHolder.textHeader_title1.setText(this.newLists.get(1).getM_name());

        headerHolder.textHeader_time2.setText(this.newLists.get(2).getM_createTime());
        headerHolder.textHeader_title2.setText(this.newLists.get(2).getM_name());

        headerHolder.textHeader_time3.setText(this.newLists.get(3).getM_createTime());
        headerHolder.textHeader_title3.setText(this.newLists.get(3).getM_name());

    }



    //HeaderItem
    private class ViewHeaderHolder implements View.OnClickListener{

        RelativeLayout img_title;
        RelativeLayout img_content1;
        RelativeLayout img_content2;
        RelativeLayout img_content3;

        TextView textView_top;
        TextView textHeader_title1;
        TextView textHeader_title2;
        TextView textHeader_title3;
        TextView textHeader_time1;
        TextView textHeader_time2;
        TextView textHeader_time3;

        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case  R.id.img_content1:

                    break;

                case R.id.img_content2:

                    break;

                case R.id.img_content3:

                    break;

                case R.id.img_title:

                    break;

                default:

                    break;

            }


        }
    }



    //普通的Item
    private class ViewHolder{

        TextView text_time;
        TextView text_title;

    }

}
