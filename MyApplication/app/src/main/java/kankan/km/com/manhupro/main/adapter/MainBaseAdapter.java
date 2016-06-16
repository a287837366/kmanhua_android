package kankan.km.com.manhupro.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import kankan.km.com.manhupro.R;
import kankan.km.com.manhupro.main.module.ManhuaModel;
import kankan.km.com.manhupro.tools.httptools.VolleyTool;

/**
 * Created by apple on 16/2/14.
 */
public class MainBaseAdapter extends BaseAdapter{
    private String TAG = MainBaseAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private Activity mActivity;

    private final int TYPE_B = 1;       //基本Item
    private final int TYPE_T = 3;       //3个图片的Item
    private final int TYPE_D = 2;       //最下面刷新
    private final int TYPE_A = 4;       //最上面的

    private String refreshString = "";
    private String adsUrl = "";

    private ArrayList<ManhuaModel> news;

    private int icon_weight;
    private int screenWidth;

    private View.OnClickListener typeListener;

    public MainBaseAdapter(Activity mActivity, ArrayList<ManhuaModel> newLists) {

        WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();

        icon_weight = ((screenWidth - 20) / 3);

        this.news = newLists;
        this.mActivity = mActivity;
        inflater = LayoutInflater.from(mActivity);
    }

    @Override
    public int getCount() {

        if (news.size() == 0) {
            return 0;
        }

        return this.news.size() + 2;
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
//        Log.d(">>>>>", position + "    "  + news.size());

        if (position == 0) {

            return TYPE_A;

        } else {

            if(position == news.size() + 1) {


                return TYPE_D;

            }

            else {

                if (news.get(position - 1).getImages() == null){

                    return TYPE_B;
                } else {

                    return TYPE_T;
                }

            }
        }


    }

    @Override
    public int getViewTypeCount() {

        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ViewBottomHolder bottomHolder = null;
        ThreeViewHodel threeViewHodel = null;
        ViewAdsHolder adsHolder = null;


        int type = getItemViewType(position);

        if (convertView == null) {


            switch (type){

                case TYPE_A:

                    convertView = inflater.inflate(R.layout.item_main_typeads, parent, false);
                    adsHolder = new ViewAdsHolder(convertView);




                    convertView.setTag(adsHolder);

                    break;

                case TYPE_B:

                    convertView = inflater.inflate(R.layout.listitem_mainbase, parent, false);
                    viewHolder = new ViewHolder(convertView);


                    convertView.setTag(viewHolder);

                    break;

                case TYPE_D:

                    convertView = inflater.inflate(R.layout.listitem_nomaldata, parent, false);
                    bottomHolder = new ViewBottomHolder(convertView);


                    convertView.setTag(bottomHolder);
                    break;

                case TYPE_T:

                    convertView = inflater.inflate(R.layout.listitem_threeimage, parent, false);

                    threeViewHodel = new ThreeViewHodel(convertView);

                    LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) threeViewHodel.linear_image.getLayoutParams();

                    linearParams.height = icon_weight;
//                    linearParams.width = icon_weight;

                    threeViewHodel.linear_image.setLayoutParams(linearParams);

                    convertView.setTag(threeViewHodel);

                    break;

                default:

                    break;

            }


        } else {

            switch (type) {

                case TYPE_A:
                    adsHolder = (ViewAdsHolder) convertView.getTag();
                    break;

                case TYPE_B:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;

                case TYPE_D:
                    bottomHolder = (ViewBottomHolder) convertView.getTag();
                    break;

                case TYPE_T:
                    threeViewHodel = (ThreeViewHodel) convertView.getTag();
                    break;

                default:
                    break;
            }

        }

        switch (type) {

            case TYPE_A:
                adsHolder.setView(position);
                break;

            case TYPE_B:
                viewHolder.setData(news.get(position - 1));
                break;

            case TYPE_D:
                bottomHolder.text_nomal.setText(refreshString);
                break;

            case TYPE_T:
                threeViewHodel.setView(position - 1);
                break;

            default:
                break;
        }

        return convertView;
    }

    public void setAdsUrl(String url){

        this.adsUrl = url;


    }

    public void setClickHandler(View.OnClickListener listener){

        this.typeListener = listener;

    }

    public void refreshByType(int type){

        if (type == 0) {

            refreshString = "没有更多";

        } else if (type == 1)  {

            refreshString = "点击获取更多";

        } else {

            refreshString = "正在加载";
        }

        this.notifyDataSetChanged();

    }


    private class ThreeViewHodel{

        LinearLayout linear_image;

        NetworkImageView image_1;
        NetworkImageView image_2;
        NetworkImageView image_3;

        TextView text_title;
        TextView text_data;
        ImageView image_icon;

        public ThreeViewHodel(View view){

            linear_image = (LinearLayout) view.findViewById(R.id.linear_image);


            image_1 = (NetworkImageView) view.findViewById(R.id.image_1);
            image_2 = (NetworkImageView) view.findViewById(R.id.image_2);
            image_3 = (NetworkImageView) view.findViewById(R.id.image_3);

            text_title = (TextView) view.findViewById(R.id.text_title);
            text_data = (TextView) view.findViewById(R.id.text_data);
            image_icon = (ImageView) view.findViewById(R.id.image_icon);

        }

        public void setView(int position){

            ManhuaModel model = news.get(position);



            text_title.setText(model.getM_title());
            text_data.setText(model.getM_createTime());


            ImageLoader imageLoader = VolleyTool.getInstance(mActivity).getmImageLoader();


            if (model.getM_type().toString().equals("1")){
                image_icon.setImageResource(R.mipmap.zhanpin_defualt_img);


            } else if (model.getM_type().toString().equals("2")){
                image_icon.setImageResource(R.mipmap.qiuzhi_defualt_img);


            } else if (model.getM_type().toString().equals("3")){
                image_icon.setImageResource(R.mipmap.fangcan_defualt_img);


            } else if (model.getM_type().toString().equals("4")){
                image_icon.setImageResource(R.mipmap.congwu_defualt_img);


            } else {
                image_icon.setImageResource(R.mipmap.qita_defualt_img);


            }

//            return;
            image_1.setImageUrl(model.getImages().get(0), imageLoader);

            if (model.getImages().size() < 2){
                image_2.setImageBitmap(null);
                return;
            }


            image_2.setImageUrl(model.getImages().get(1), imageLoader);

            if (model.getImages().size() < 3){
                image_3.setImageBitmap(null);
                return;
            }



            image_3.setImageUrl(model.getImages().get(2), imageLoader);




        }

    }


    private class ViewAdsHolder {


        public LinearLayout typeView;
        public RelativeLayout topView;

        private boolean isShow;

        public ViewAdsHolder(View v){

            isShow = false;

            typeView = (LinearLayout) v.findViewById(R.id.typeView);
            topView = (RelativeLayout) v.findViewById(R.id.topView);

            v.findViewById(R.id.btn_zhaopin).setOnClickListener(typeListener);
            v.findViewById(R.id.btn_qiuzhi).setOnClickListener(typeListener);
            v.findViewById(R.id.btn_fangcan).setOnClickListener(typeListener);
            v.findViewById(R.id.btn_congwu).setOnClickListener(typeListener);

            RelativeLayout.LayoutParams paramsType = (RelativeLayout.LayoutParams) typeView.getLayoutParams();
            paramsType.height = (screenWidth + 80) / 4;

            typeView.setLayoutParams(paramsType);

        }

        public void setView(int postion){

            if (adsUrl.equals("")){

                return;
            }

            if (isShow) {

                return;
            }


            RelativeLayout.LayoutParams paramsTop = (RelativeLayout.LayoutParams) topView.getLayoutParams();
            paramsTop.height = (screenWidth + 80) / 4 + screenWidth / 5;

            topView.setLayoutParams(paramsTop);

            isShow = true;
        }

    }

    //普通的Item
    private class ViewHolder{

        TextView text_time;
        TextView text_title;

        NetworkImageView imageView_icon;

        public ViewHolder(View v){


            imageView_icon = (NetworkImageView) v.findViewById(R.id.imageView_icon);
            text_time = (TextView) v.findViewById(R.id.text_time);
            text_title = (TextView) v.findViewById(R.id.text_title);

        }

        public void setData(ManhuaModel model){

            text_title.setText(model.getM_title());
            text_time.setText(model.getM_createTime());
            ImageLoader imageLoader = VolleyTool.getInstance(mActivity).getmImageLoader();

            if (model.getM_type().toString().equals("1")){
                imageView_icon.setDefaultImageResId(R.mipmap.zhanpin_defualt_img);

            } else if (model.getM_type().toString().equals("2")){

                imageView_icon.setDefaultImageResId(R.mipmap.qiuzhi_defualt_img);

            } else if (model.getM_type().toString().equals("3")){

                imageView_icon.setDefaultImageResId(R.mipmap.fangcan_defualt_img);

            } else if (model.getM_type().toString().equals("4")){

                imageView_icon.setDefaultImageResId(R.mipmap.congwu_defualt_img);

            } else {
                imageView_icon.setDefaultImageResId(R.mipmap.qita_defualt_img);

            }

            imageView_icon.setImageUrl(model.getM_icon(), imageLoader);

        }


    }

    private class ViewBottomHolder{

        TextView text_nomal;



        public ViewBottomHolder(View v){
            text_nomal = (TextView) v.findViewById(R.id.text_nomal);


        }






    }



}
