package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;
import kankan.km.com.manhupro.tools.tools.AlbumTools.bean.BitmapBean;

/**
 * Created by apple on 16/5/16.
 */
public class CreateImageAdapter extends BaseAdapter{

    private ArrayList<BitmapBean> imageLists;
    private Activity mAcvitiy;
    private LayoutInflater inflater;

    private int icon_weight;

    public CreateImageAdapter(Activity mAcvitiy, ArrayList<BitmapBean> imageLists){
        this.mAcvitiy = mAcvitiy;
        this.imageLists = imageLists;

        inflater = LayoutInflater.from(mAcvitiy);

        WindowManager wm = (WindowManager) mAcvitiy.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();

        icon_weight = ((screenWidth - 120) / 4);
    }

    @Override
    public int getCount() {

        if (imageLists.size() == 0){

            return 1;
        }

        if (imageLists.size() == 9){


            return 9;
        }

        return imageLists.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_manhua_createimage, parent, false);
            viewHolder = new ViewHolder(convertView);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) viewHolder.image_icon.getLayoutParams();

            linearParams.height = icon_weight;
            linearParams.width = icon_weight;

            viewHolder.image_icon.setLayoutParams(linearParams);
            viewHolder.defualt_image.setLayoutParams(linearParams);


            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }




        if (imageLists.size() == 9){

            viewHolder.defualt_image.setVisibility(View.GONE);
            viewHolder.image_icon.setVisibility(View.VISIBLE);

            viewHolder.image_icon.setImageBitmap(this.imageLists.get(position).getBitmap());

        } else {

            if (position == imageLists.size()){

                viewHolder.defualt_image.setVisibility(View.VISIBLE);
                viewHolder.image_icon.setVisibility(View.GONE);

            } else {

                viewHolder.image_icon.setImageBitmap(this.imageLists.get(position).getBitmap());

                viewHolder.defualt_image.setVisibility(View.GONE);
                viewHolder.image_icon.setVisibility(View.VISIBLE);

            }


        }


        return convertView;
    }

    private class ViewHolder{

        public ImageView image_icon;
        public RelativeLayout defualt_image;

        public ViewHolder(View view){

            image_icon = (ImageView) view.findViewById(R.id.imageView_icon);
            defualt_image = (RelativeLayout) view.findViewById(R.id.defualt_image);

        }

    }
}

