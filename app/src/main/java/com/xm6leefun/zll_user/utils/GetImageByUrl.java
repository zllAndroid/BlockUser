package com.xm6leefun.zll_user.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/9/18 0018.
 *  @use:  GetImageByUrl getImageByUrl = new GetImageByUrl();
    getImageByUrl.setImage((ImageView)helper.getView(R.id.item_company_iv_pic), item.getImage());
 */

public class GetImageByUrl {
    private PicHandler pic_hdl;
    private ImageView imgView;
    private String url;


    /**
     * 通过图片url路径获取图片并显示到对应控件上
     *
     * @param imgView
     * @param url
     */
    public void setImage(ImageView imgView, String url) {
        this.url = url;
        this.imgView = imgView;
        pic_hdl = new PicHandler();
        Thread t = new LoadPicThread();
        t.start();
    }


    class LoadPicThread extends Thread {
        @Override
        public void run() {
            Bitmap img = getUrlImage(url);
            System.out.println(img + "---");
            Message msg = pic_hdl.obtainMessage();
            msg.what = 0;
            msg.obj = img;
            pic_hdl.sendMessage(msg);
        }
    }

    class PicHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Bitmap myimg = null;
            try {
                myimg = (Bitmap) msg.obj;
                if (myimg!=null)
                    imgView.setImageBitmap(myimg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Bitmap getUrlImage(String url) {
        Bitmap img = null;
        try {
            URL picurl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) picurl
                    .openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            img = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}
