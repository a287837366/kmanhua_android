package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import kankan.km.com.manhupro.R;


/**
 * Created by apple on 16/5/23.
 */
public class ChooseTypesAdapter extends BaseAdapter{

    private Activity mActivity;
    private int[] list;


    private int selectedPosion;

    public ChooseTypesAdapter(Activity mAcvitiy) {

        selectedPosion = 0;

        this.mActivity = mAcvitiy;
        list = new int[4];

        list[0] = R.mipmap.zhanpin_defualt_img;
        list[1] = R.mipmap.qiuzhi_defualt_img;
        list[2] = R.mipmap.fangcan_defualt_img;
        list[3] = R.mipmap.congwu_defualt_img;

    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHodler holder;

        if (convertView == null) {
            convertView = View.inflate(this.mActivity, R.layout.item_imagechoice_grid, null);
            holder = new ViewHodler(convertView);

            convertView.setTag(holder);

        } else {

            holder = (ViewHodler) convertView.getTag();
        }

        holder.setView(position);

        return convertView;
    }


    public void setSelectedPosion(int posion){

        this.selectedPosion = posion;

    }

    public int getSelectedPosion(){

        return selectedPosion;
    }

    private class ViewHodler{

        private ImageView mainImageView;
        private ImageView selectImageView;


        public ViewHodler(View view){

            mainImageView        = (ImageView) view.findViewById(R.id.image);
            selectImageView      = (ImageView) view.findViewById(R.id.isselected);
        }


        public void setView(int position){


            mainImageView.setImageResource(list[position]);

            if (position == selectedPosion){
                selectImageView.setVisibility(View.VISIBLE);

            }  else {
                selectImageView.setVisibility(View.INVISIBLE);

            }


        }



    }
}
