package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by apple on 16/2/14.
 */
public class MainBaseAdapter extends BaseAdapter{

    private ArrayList<String> stringArrayList;

    public MainBaseAdapter(Activity mActivity, ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;

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
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }

}
