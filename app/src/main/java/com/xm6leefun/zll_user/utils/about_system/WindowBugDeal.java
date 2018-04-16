package com.xm6leefun.zll_user.utils.about_system;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/9/25 0025.
 */

public class WindowBugDeal {
    public static void checkDeviceHasNavigationBar(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
//            Window window = context.getWindow();
//            context.getWindow().addFlags(View.SYSTEM_UI_FLAG_IMMERSIVE);
//            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//            context.getWindow().addFlags(WindowManager.LayoutParams.IMMERSIVE);//A
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION );
////            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//////                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE );
////            context.getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE );
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B//
//            context.getWindow().addFlags(View.SYSTEM_UI_FLAG_VISIBLE);
        }
    }
    public static void SetTop(Activity context){
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar","bool","android");
        if (id > 0)
        {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
        }
    }
}
