package com.xm6leefun.zll_user.utils;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.VolleyError;
import com.xm6leefun.model.DataUpVersion;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.utils.about_volley.VolleyInterface;
import com.xm6leefun.zll_user.utils.about_volley.VolleyRequest;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class VersionCheckUtils {
//    static DataUpVersion.RecordBean record = null;
    static DataUpVersion.RecordBean.EditionBean edition = null;
    public static final int localVersion = HelpUtils.getLocalVersion();
    public static  void initUpdata(final  boolean isReturn) {
        StrUtils.getData();
        Log.e("url", "url----------==" + URL_GET.getEdition());
        VolleyRequest.RequestGet(AppManager.getAppManager().currentActivity(), URL_GET.getEdition(), new VolleyInterface(VolleyInterface.listener, VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                Log.e("result", "result----------==" + result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(CommonParameter.CODE_SUCCESS)) {
//                    是否主动请求，无版本更新会弹出提示   首页请求
                        initJsonReturn(result,isReturn);
                } else if (sucess.equals(CommonParameter.CODE_TIMEOUT)) {
                    //超时
                } else {
                    ToastUtil.show(sucess);
                }
            }
            @Override
            public void onError(VolleyError result) {
                ToastUtil.show(CommonParameter.ERROR);
            }
        });
    }
    public static void initJsonReturn(String msg,boolean isReturn) {
        if (!StrUtils.isEmpty(msg))
        {
            try {
                DataUpVersion dataUpVersion = JSON.parseObject(msg, DataUpVersion.class);
//                record = dataUpVersion.getRecord();
                edition = dataUpVersion.getRecord().getEdition();
                if (edition != null&&(edition.getNum() > localVersion)) {
                        if (edition.getIsForce().equals("1")) {
                            try {
//                                强制
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), edition.getUrl(), false, edition.getRemark());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                new UpDataUtils(AppManager.getAppManager().currentActivity(), edition.getUrl(), true, edition.getRemark());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                }else {
//                    是否主动点击更新 ,会弹出提示  isReturn =true ,否 ，，，，|| （是否弹出提示）   true  是
                    if (isReturn) {
                        Tip.getDialog(AppManager.getAppManager().currentActivity(), "已经是最新版本");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
