package kankan.km.com.manhupro.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;

/**
 * Created by aspn300 on 16/5/2.
 */
public class DetailImageAdapter extends BaseAdapter{

    private ArrayList<String> imageLists;
    private Context context;

    private LayoutInflater inflater;
    private int icon_weight;

    public DetailImageAdapter(Context context, ArrayList<String> imageList){

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();

        icon_weight = ((screenWidth - 120) / 4);

        this.imageLists = imageList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return imageLists.size();
    }

    @Override
    public Object getItem(int position) {
        return imageLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_detailimage, parent, false);
            viewHolder = new ViewHolder(convertView);

            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) viewHolder.image_icon.getLayoutParams();

            linearParams.height = icon_weight;
            linearParams.width = icon_weight;

            viewHolder.image_icon.setLayoutParams(linearParams);


            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.image_icon.setImageUrl(imageLists.get(position), VolleyTool.getInstance(context).getmImageLoader());

        return convertView;
    }

    //普通的Item
    private class ViewHolder{

        public NetworkImageView image_icon;

        public ViewHolder(View view){

            image_icon = (NetworkImageView) view.findViewById(R.id.imageView_icon);

        }

    }
}
