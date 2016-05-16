package kankan.km.com.manhupro.tools.tools.AlbumTools;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 16/5/16.
 */
public class Bimp {

    public static int max = 0;
    public static boolean act_bool = true;
    public static List<Bitmap> bmp = new ArrayList<Bitmap>();
    public static List<String> drr = new ArrayList<String>();
    public static int drrlength = 9;

    public static boolean hasPath(String path){

        for(int p =0;p<drr.size();p++){
            if(path.equalsIgnoreCase(drr.get(p))){
                return true;
            }
        }
        return false;
    }

    public static boolean removePath(String path){

        for(int p =0;p<drr.size();p++){
            if(path.equalsIgnoreCase(drr.get(p))){
                drr.remove(p);
                return true;
            }
        }
        return false;
    }
}
