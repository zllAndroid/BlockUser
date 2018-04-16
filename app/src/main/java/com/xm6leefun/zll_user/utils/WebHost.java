package com.xm6leefun.zll_user.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class WebHost {
    public Context context;

    public WebHost(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void acllJs(){
        Toast.makeText(context, "点击了登录按钮！wahadfojewfogowfjoef", Toast.LENGTH_SHORT).show();
//        activationBtn
        Log.e("acllJs","点击了登录按");
    }
}
