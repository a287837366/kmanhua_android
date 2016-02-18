package kankan.km.com.manhupro.tools.httptools;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

@SuppressLint("NewApi")
public class BitmapCache implements ImageCache {
    private LruCache<String, Bitmap> mCache;
    public static BitmapCache bitmapCache;

    public BitmapCache() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public static BitmapCache getBitmapCache() {

        if (bitmapCache == null) {
            bitmapCache = new BitmapCache();
        }

        return bitmapCache;
    }


    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        if (null == getBitmap(url)){
            mCache.put(url, bitmap);
        }
    }

}
