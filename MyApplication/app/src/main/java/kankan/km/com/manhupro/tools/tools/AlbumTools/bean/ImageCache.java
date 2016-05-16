package kankan.km.com.manhupro.tools.tools.AlbumTools.bean;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by apple on 16/5/16.
 */
public class ImageCache {

    /**
     * 读取本地私有文件夹的图片
     *
     * @param
     * @param cxt
     * @return
     */
    private final String TAG = ImageCache.class.getSimpleName();
    //获取指定路径图片文件bitmap
    public static Bitmap getBitmap(String fileName, Context cxt) {
        String bitmapName = getimgsuffix(fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = cxt.openFileInput(bitmapName);
            ois = new ObjectInputStream(fis);
            byte[] byteArray = (byte[]) ois.readObject();
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
// 这里是读取文件产生异常
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
// fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
// ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
// 读取产生异常，返回null
        return null;
    }


    //通过这种方式保存在本地的图片，是可以看到的
    public static void saveBitmap(Bitmap mBitmap, String imageURL, Context cxt) {
        File savepath=cxt.getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File f = new File(savepath.getPath()+"/"+getimgsuffix(imageURL));
//        Utils.log("images-保存图片地址",f.getPath());
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
//            Utils.log("imagesave","保存异常");
            e.printStackTrace();
        }

    }

    //通过uri 或者url获取
    public static Bitmap getBitmap2(String fileName, Context cxt, boolean isUrl) {
        String bitmapName = fileName;
        if (isUrl) {
            File savepath=cxt.getExternalFilesDir(Environment.DIRECTORY_DCIM);
            bitmapName = savepath.getPath()+"/"+getimgsuffix(fileName);
        }
//        Utils.log("图片名","图片名>"+bitmapName);

        FileInputStream fis = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(bitmapName);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
// 读取产生异常，返回null
        return null;
    }

//判断缓存下是否存在

    public static  boolean getiExists(String filename, Context cxt){
        File savepath=cxt.getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File name = new File(savepath.getPath()+"/"+getimgsuffix(filename));

        if(name.exists()){

            return true;
        }else{

        }
        return false;
    }


    public static String getimgsuffix(String imgurl) {
        String suffix = imgurl.substring(imgurl.lastIndexOf(".") + 1);

        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest((imgurl.substring(imgurl.lastIndexOf("//") + 1)).getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        String httpafter = hash.toString();
        httpafter += "." + suffix;
        return httpafter;
    }

    public  String getfilename(String path) {
        String suffix = path.substring(path.lastIndexOf("/") + 1);
        return suffix;
    }

    public Bitmap getBitmapFromUri(Uri uri, Context thiss) {
        try {
            // 读取uri所在的图片
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(thiss.getContentResolver(), uri);
            Bitmap bitmap =getThumbnail(uri,1024,thiss);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getThumbnail(Uri uri,int size,Context thiss) throws FileNotFoundException, IOException{
        InputStream input = thiss.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither=true;//optional
        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1))
            return null;
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither=true;//optional
        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
        input = thiss.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();
        return bitmap;
    }
    private static int getPowerOfTwoForSampleRatio(double ratio){
        int k = Integer.highestOneBit((int)Math.floor(ratio));
        if(k==0) return 1;
        else return k;
    }

    //降低图片质量
    public Bitmap imageZoom(Bitmap bitMap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            bitMap.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        newOpts.inJustDecodeBounds = false;
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        newOpts.inPurgeable = true;
        newOpts.inInputShareable = true;

        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        int be = 1;//be=1表示不缩放

        if (w > h || w > ww) {
            final int heightRatio = Math.round((float) newOpts.outHeight  / (float) hh);
            final int widthRatio = Math.round((float) newOpts.outWidth / (float) ww);
            be = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        newOpts.inSampleSize = be;//设置缩放比例
        ByteArrayInputStream isBm  = new ByteArrayInputStream(baos.toByteArray());
        Bitmap  bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        //-------------------------
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitMap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        if( baos.toByteArray().length / 1024>1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
//            baos.reset();//重置baos即清空baos
//            bitMap.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
//        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = 800f;//这里设置高度为800f
//        float ww = 480f;//这里设置宽度为480f
//        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//
//        if (be <= 0)
//            be = 1;
//

//        newOpts.inSampleSize = be;//设置缩放比例
//        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        isBm = new ByteArrayInputStream(baos.toByteArray());
//        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight+200;
        final int width = options.outWidth+200;

        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height  / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize+1;
    }
    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @param
     * @return
     */
    @SuppressWarnings("static-access")
    public static Bitmap getSmallBitmap(String filePath) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);

    }




}
