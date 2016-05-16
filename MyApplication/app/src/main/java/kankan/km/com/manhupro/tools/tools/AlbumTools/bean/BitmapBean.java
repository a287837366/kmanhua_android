package kankan.km.com.manhupro.tools.tools.AlbumTools.bean;

import android.graphics.Bitmap;

/**
 * Created by apple on 16/5/16.
 */
public class BitmapBean {


    private Bitmap bitmap;
    private String fileName;
    private String id;
    private int sort;
    private String url;


    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
