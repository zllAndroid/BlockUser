package com.xm6leefun.zll_user.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.ui_custom.loding.LoadingDialog;
import com.xm6leefun.zll_user.utils.about_volley.VolleyInterface;
import com.xm6leefun.zll_user.utils.about_volley.VolleyRequest;

import java.util.Timer;

/**
 * Created by Administrator on 2018/2/5 0005.
 */

public class NetWorkUtlis {


    OnNetWork onNetWork = null;
    Timer timerSuc=null;
    //    Context mContext;
    LoadingDialog.Speed speed = LoadingDialog.Speed.SPEED_TWO;
    private int repeatTime = 0;
    private long delayedTime = 200L;
    private long closeTime = 5000L;
    private int style = LoadingDialog.STYLE_LINE;
    String loadText = "加载中...";
    View view;
    public static final int LOAD_SUCCESS = 1;
    public static final int LOAD_FAILED = 2;
    public static final int LOADING = 3;
    public static final int LOAD_WITHOUT_ANIM_SUCCESS = 4;
    public static final int LOAD_WITHOUT_ANIM_FAILED = 5;
    public static final int SAVE_YOU = 6;
    //    是否拦截返回键  true 拦截
    private boolean intercept_back_event = true;
    String url;
    private LoadingDialog ld =null;
    public  interface  OnNetWork {
        void onNetSuccess(String result);
//        void onNetError(String msg);
    }
    /**
     *
     * @param type 0 默认加载，1 菊花加载框 ，2  gif加载
     * @param url
     * @param onNetWork
     */
    public  void  setOnNetWork(String type,String url,OnNetWork onNetWork)
    {
        this.onNetWork=onNetWork;
        this.url=url;
        switch (type)
        {
            case CommonParameter.LodingNormal:
                initNormalHttp();
                break;
            case CommonParameter.LodingFlower:
                initHttpFlower();
                break;
            case CommonParameter.LodingFlowerHaveResult:
                initHttpFlowerHaveResult();
                break;
            case CommonParameter.NORMAL:
                initHttp();
                break;
        }
    }

    public  void  setOnNetWork(OnNetWork onNetWork,String ... type)
    {
        this.onNetWork=onNetWork;
        this.url=url;
        if (type.length>0) {
            switch (type[0]) {
                case CommonParameter.LodingNormal:
                    initNormalHttp();
                    break;
                case CommonParameter.LodingFlower:
                    initHttpFlower();
                    break;
                case CommonParameter.LodingFlowerHaveResult:
                    initHttpFlowerHaveResult();
                    break;
                case CommonParameter.NORMAL:
                    initHttp();
                    break;
//            case CommonParameter.LodingGIF:
//                initGifHttp();
//                break;
            }
        }
    }
    //    正常加载
    public  void  setOnNetWork(String url,OnNetWork onNetWork)
    {
        this.onNetWork=onNetWork;
        this.url=url;
        initHttpFlower();
    }
    public  void  setOnNetWorkNormal(String url,OnNetWork onNetWork)
    {
        this.onNetWork=onNetWork;
        this.url=url;
        initNormalHttp();
    }
    public  void  setOnNetWorkLogin(String url,OnNetWork onNetWork)
    {
        this.onNetWork=onNetWork;
        this.url=url;
        loadText = "登陆中";
        initHttpFlower();
    }
    Handler h =
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case LOAD_SUCCESS:
//                    ld.loadSuccess();
                            ld.close();
                            break;
                        case LOAD_FAILED:
//                    ld.loadFailed();
                            ld.close();
                            break;
                        case LOADING:
                            ld.show();
                            break;
                        case LOAD_WITHOUT_ANIM_FAILED:
                            ld.loadFailed();
                            break;
                        case LOAD_WITHOUT_ANIM_SUCCESS:
                            ld.loadSuccess();
                            break;
                        case SAVE_YOU:
//                    ld.loadSuccess();
                            ld.close();
//                    ld.loadFailed();
                            break;
                    }
                }
            };
    private void saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(boolean intercept_back_event) {
        if (intercept_back_event) {
            h.sendEmptyMessageDelayed(LOAD_FAILED, closeTime);
        }
    }
    private void initHttpFlower() {
        final Activity activity = AppManager.getAppManager().currentActivity();
        if ((ld != null))
            ld.close();
        ld = new LoadingDialog(activity);
        ld.setLoadingText(loadText)
                .setSuccessText("加载成功")
                .setInterceptBack(intercept_back_event)
                .setLoadSpeed(speed)
                .setRepeatCount(repeatTime)
//                .setDrawColor(Color.WHITE)
                .setLoadStyle(style)
                .show();
        saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
//        Activity activity = AppManager.getAppManager().currentActivity();
        Log.e("result","url----------=="+url);
        VolleyRequest.RequestGet(activity,url, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                h.sendEmptyMessageDelayed(SAVE_YOU, 50);
                Log.e("result","result----------=="+result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(CommonParameter.CODE_SUCCESS))
                {
                    onNetWork.onNetSuccess(result);
                }else if (sucess.equals(CommonParameter.CODE_TIMEOUT))
                {
                    //超时
//                    h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
                }else {
                    ToastUtil.show(sucess);
//                    onNetWork.onNetSuccess("");
//                    h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
                }

            }
            @Override
            public void onError(VolleyError result) {
//                Tip.getError(CommonParameter.ERROR);
                ToastUtil.show(CommonParameter.ERROR);
                h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
//                ToastUtil.show(CommonParameter.ERROR);


            }
        });
    }
    //  返回值未处理 （失败返回空）
    private void initHttpFlowerHaveResult() {
        final Activity activity = AppManager.getAppManager().currentActivity();
        if ((ld != null))
            ld.close();
        ld = new LoadingDialog(activity);
        ld.setLoadingText("加载中...")
                .setSuccessText("加载成功")
                .setInterceptBack(intercept_back_event)
                .setLoadSpeed(speed)
                .setRepeatCount(repeatTime)
                .setLoadStyle(style)
                .show();
        saveForThesePeopleWhoDoNotCallCloseAndUseInterceptBackMethod(intercept_back_event);
        VolleyRequest.RequestGet(activity,url, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(CommonParameter.CODE_SUCCESS))
                {
                    h.sendEmptyMessageDelayed(LOAD_SUCCESS, delayedTime);
                    onNetWork.onNetSuccess(result);
                }else if (sucess.equals(CommonParameter.CODE_TIMEOUT))
                {
                    //超时
                    h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
                }else {
                    ToastUtil.show(sucess);
                    onNetWork.onNetSuccess("");
                    h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
                }
            }
            @Override
            public void onError(VolleyError result) {
                ToastUtil.show(CommonParameter.ERROR);
                h.sendEmptyMessageDelayed(LOAD_FAILED, delayedTime);
            }
        });
    }
    //默认加载
    private void initNormalHttp() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Log.e("result","url----------=="+url);
        VolleyRequest.RequestGet(activity,url, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                Log.e("result","请求结果result----------=="+result);
                final String sucess = HelpUtils.HttpIsSucess(result);
                if (sucess.equals(CommonParameter.CODE_SUCCESS))
                {
                    onNetWork.onNetSuccess(result);
                }else if (sucess.equals(CommonParameter.CODE_TIMEOUT))
                {
                    //超时
                }else {
                    ToastUtil.show(sucess);
//                    onNetWork.onNetSuccess("");
                }
            }
            @Override
            public void onError(VolleyError result) {
                ToastUtil.show(CommonParameter.ERROR);
//                Tip.getError(CommonParameter.ERROR);
            }
        });
    }
    private void initHttp() {
        Activity activity = AppManager.getAppManager().currentActivity();
        Log.e("result","url----------=="+url);
        VolleyRequest.RequestGet(activity,url, new VolleyInterface(VolleyInterface.listener,VolleyInterface.errorListener) {
            @Override
            public void onSuccess(final String result) {
                    onNetWork.onNetSuccess(result);
            }
            @Override
            public void onError(VolleyError result) {
                ToastUtil.show(CommonParameter.ERROR);
//                Tip.getError(CommonParameter.ERROR);
            }
        });
    }

}
