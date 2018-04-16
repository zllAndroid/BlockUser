package com.xm6leefun.zll_user.main_code.about_init;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataWeResult;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.StrUtils;

/**
 * 创建人：zll .
 * 创建时间：2018/4/13 0013.
 * 作用：
 */

public  class isLogin {
    public static boolean initCaChe() {
        ACache  mCache = ACache.get(AppManager.getAppManager().currentActivity());
        URL_GET.USER_TOKEN ="";
        if (mCache!=null){
            String asString = mCache.getAsString(AppAllKey.TOKEN_KEY_NEW);
            if (!StrUtils.isEmpty(asString))
            {
                DataWeResult.RecordBean.UserInfoBean userInfo = JSON.parseObject(asString, DataWeResult.RecordBean.UserInfoBean.class);
//                DataWeResult.RecordBean record = dataWeResult.getRecord();
//                DataWeResult.RecordBean.UserInfoBean userInfo = record.getUserInfo();
                if (!StrUtils.isEmpty(userInfo.toString())) {
                    URL_GET.USER_TOKEN = userInfo.getToken();
                    URL_GET.USER_HEAD_URL = userInfo.getHeadPortrait();
                    URL_GET.USER_ID = userInfo.getUserId();
                    URL_GET.USER_NI_NAME = userInfo.getNickName();
                    return true;
                }
            }
        }
        return false;
    }
}
