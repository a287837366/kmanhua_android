package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;

/**
 * Created by apple on 16/5/3.
 */
public class ImageStatusAdapter extends PagerAdapter{

    private Activity mActivity;
    private LayoutInflater mInflater;

    private ArrayList<String> list;
    private View.OnClickListener listener;

    public ImageStatusAdapter(Activity activity){
        this.mActivity = activity;

        mInflater = LayoutInflater.from(activity);

    }


    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {

            return -1;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position < getCount()) {
            return onCreateView(container, position);
        }
        return super.instantiateItem(container, position);
    }

    private ViewGroup onCreateView(ViewGroup container, int position) {
//        Utils.log(TAG, "onCreateView()");

        RelativeLayout layout = (RelativeLayout) mInflater.inflate(R.layout.item_imagestatus_page, null);

        NetworkImageView photo = (NetworkImageView)layout.findViewById(R.id.listview_feed_item_product_page);

        photo.setImageUrl(list.get(position), VolleyTool.getInstance(mActivity).getmImageLoader());

        container.addView(layout);

        photo.setOnClickListener(listener);

        return layout;
    }

    public void setImageClickListner(View.OnClickListener listner){
        this.listener = listner;
    }
    public void setList(ArrayList<String> lists) {
        list = lists;
    }

    public boolean hasMovieList() {
        boolean hasList = true;
        if (list == null || list.isEmpty())
            hasList = false;

        return hasList;
    }

}
