package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by aspn300 on 16/2/18.
 */
public class MainDetailAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater inflater;

    public MainDetailAdapter(Activity activity){
        this.mActivity = activity;
        inflater = LayoutInflater.from(mActivity);

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }



}
