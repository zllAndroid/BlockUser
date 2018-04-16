package com.xm6leefun.zll_user.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.main_code.popular_activity.GoH5Activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class IntentUtils {
    /**
     * 打电话
     *
     * @param phoneNum
     */
    public static void call(String phoneNum) {
        Intent intent = new Intent();
        // 启动电话程序
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        HelpUtils.activity.startActivity(intent);
    }
    public static void JumpFinishTo(Class<?> resultactivity) {
        Intent intent = new Intent( HelpUtils.activity, resultactivity);
        HelpUtils.activity.startActivity(intent);
        AppManager.getAppManager().finishActivity();
    }
    public static void JumpTo(Class<?> resultactivity) {
        Intent intent = new Intent( HelpUtils.activity, resultactivity);
        HelpUtils.activity.startActivity(intent);
    }
    public static void JumpToHaveOne(Class<?> resultactivity,String key,String value) {
        Intent intent = new Intent(HelpUtils.activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putString(key,value);
        intent.putExtras(bundle);
        HelpUtils.activity.startActivity(intent);
    }
    public static void JumpToHaveTwo(Class<?> resultactivity,String key1,String value1,String key2,String value2) {
        Intent intent = new Intent(HelpUtils.activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putString(key1,value1);
        bundle.putString(key2,value2);
        intent.putExtras(bundle);
        HelpUtils.activity.startActivity(intent);
    }
    public static void JumpToHaveObj(Class<?> resultactivity,String key,Serializable value) {
        Intent intent = new Intent(HelpUtils.activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key,value);
        intent.putExtras(bundle);
        HelpUtils.activity.startActivity(intent);
    }
    public static void JumpToHaveObjAndStr(Class<?> resultactivity,String key,Serializable value,String key1,String value1) {
        Intent intent = new Intent(HelpUtils.activity, resultactivity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key,value);
        bundle.putString(key1,value1);
        intent.putExtras(bundle);
        HelpUtils.activity.startActivity(intent);
    }
    public static  void  JumpGoH5(String tital,String net_url)
    {
        Intent intent = new Intent();
        intent.setClass(HelpUtils.activity, GoH5Activity.class);

        Bundle mBundle = new Bundle();
        mBundle.putString(AppAllKey.GOH5_TITAL_KEY, tital);
        mBundle.putString(AppAllKey.GOH5_ARTICAL_KEY, net_url);
        intent.putExtras(mBundle);
        HelpUtils.activity.startActivity(intent);
    }

}
