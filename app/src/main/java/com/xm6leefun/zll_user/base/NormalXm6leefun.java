package com.xm6leefun.zll_user.base;

import android.util.Log;

import com.xm6leefun.zll_user.utils.MD5Utils;
import com.xm6leefun.zll_user.utils.StringUnicode;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Administrator on 2017/11/6 0006.
 */

public class NormalXm6leefun {

    public static String getSing(ArrayList<String> mList) {
        // 头部URL
        String headTemp = "";
        Collections.sort(mList);
        try {
            for (int i = 0; i < mList.size(); i++) {
                if (i==0)
                {
                    headTemp += mList.get(i);
                }else
                {
                    headTemp += "&" +mList.get(i) ;
                }
            }
        } catch (Exception e) {

        }
        Log.e("MD5Utils","mList=="+mList.toString());
        String replace = mList.toString().replace("[", "{").replace("]", "}");
//        String substring = mList.toString().substring(1, (mList.toString().length()-1));
//        String results = "{"+substring+"}";
        Log.e("MD5Utils","---replace=="+replace);
        String s = MD5Utils.encryptMD5(StringUnicode.decode(MD5Utils.encryptMD5(StringUnicode.decode(replace)) + "xm6leefun"));
        String s1 = "&outsideSign=" + s;
//        outsideSign
        if (URL_GET.USER_TOKEN.equals("")) {
            return
//                    MD5Utils.encryptMD5(StringUnicode.decode(MD5Utils.encryptMD5(StringUnicode.decode(headTemp)) + "xm6leefun"));
                    StringUnicode.decode(headTemp)+s1;
        } else {
            return StringUnicode.decode(headTemp) + "&token=" + URL_GET.USER_TOKEN;
        }
    }

    public static String enUrlCode(String text) throws UnsupportedEncodingException {
        return java.net.URLEncoder.encode(text, "utf-8");
    }
}
