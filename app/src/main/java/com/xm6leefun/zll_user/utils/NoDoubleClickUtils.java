package com.xm6leefun.zll_user.utils;


import com.xm6leefun.zll_user.base.AppManager;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class NoDoubleClickUtils {
    private static long lastClickTime;
    private final static int SPACE_TIME = 500;

    public static void initLastClickTime() {
        lastClickTime = 0;
    }
//    boolean netWorkAvailable = HelpUtils.isNetWorkAvailable(LoginActivity.this);
public synchronized static boolean isDoubleClick() {
//    boolean networkable = HelpUtils.isNetworkable();
//    if (!networkable)
//    {
//        Tip.getDialog(AppManager.getAppManager().currentActivity(),"请检查网络设置");
//        return false;
//    }
    long currentTime = System.currentTimeMillis();
//        long lastClickTime=0;
    long time =currentTime - lastClickTime;
    lastClickTime = currentTime;
    if (time > SPACE_TIME) {
        return true;
//            if (HelpUtils.isNetWorkAvailable())
//            {
//                return true;
////                有网络
//            }
//            else
//            {
//                Tip.getDialog(AppManager.getAppManager().currentActivity(),CommonParameter.ERROR);
//                return false;
//            }
    } else {
        Tip.getDialog(AppManager.getAppManager().currentActivity(),"点击太频繁，请稍后");
        return false;
    }
}
}
