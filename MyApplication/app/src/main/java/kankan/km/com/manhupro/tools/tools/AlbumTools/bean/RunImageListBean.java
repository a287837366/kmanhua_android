package kankan.km.com.manhupro.tools.tools.AlbumTools.bean;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import kankan.km.com.manhupro.tools.tools.AlbumTools.Bimp;

/**
 * Created by apple on 16/5/16.
 */
public class RunImageListBean {

    private int thearmax=3;
    private int newmax=0;
    public Handler returnHandler;
    public List<BitmapBean> returnList;
    public final Handler handlers = new Handler() {
        public void handleMessage(Message msg) {

            BitmapBean bitbean = (BitmapBean) msg.obj;

            returnList.add(bitbean);

            if(returnList.size()==Bimp.drr.size()){
                Message listmesage = new Message();
                listmesage.obj=returnList;
                returnHandler.sendMessage(listmesage);
            }

            if(Bimp.drr.size()>newmax) {

                startPath(newmax);
                newmax+=1;
            }
        }
    };


    public  void  readList(android.os.Handler handler){
        returnHandler=handler;
        returnList = new ArrayList<BitmapBean>();

        if(Bimp.drr.size()<thearmax) {
            newmax=Bimp.drr.size();
        }else{
            newmax=thearmax;
        }

        for(int p = 0; p < newmax; p++) {

            startPath(p);
            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public void startPath(int p){
        Threads thread = new Threads(handlers, Bimp.drr.get(p));
        thread.start();
    }

    class Threads extends Thread{
        private Handler handlers;
        private String path;
        public Threads(Handler handlers,String path) {
            this.handlers=handlers;
            this.path=path;
        }

        @Override
        public void run() {
            //压缩然后返回message
            ImageCache news = new ImageCache();
            Bitmap newbm = news.getSmallBitmap(path);
            BitmapBean niu = new BitmapBean();
            niu.setBitmap(newbm);
            niu.setFileName(news.getfilename(path));
            Message message = new Message();
            message.obj=niu;
            handlers.sendMessage(message);
        }
    }

}
