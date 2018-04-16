package com.xm6leefun.zll_user.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.xm6leefun.model.DataWeResult;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.MyApplication;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class WXLoginActivity extends MyBaseActivity {

    private ACache mCache =null;

    /**
     * 微信登录相关
     */
//    public static IWXAPI mWxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
//        mWxApi = WXAPIFactory.createWXAPI(this, Config.APP_ID_WX,true);
////        //将应用的appid注册到微信
//        mWxApi.registerApp(Config.APP_ID_WX);
        findViewById(R.id.wx_btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wxLogin();
            }
        });
    }
    public void wxLogin() {
        if (!MyApplication.mWxApi.isWXAppInstalled()) {
            ToastUtil.show("您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_微信登录";
        MyApplication.mWxApi.sendReq(req);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_login;
    }

    //    @Override
//    protected void onResume() {
//        super.onResume();
//        Glide.with(WXLoginActivity.this).load(MyApplication.getShared().getString("headUrl","")).into(ivHead);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(resultCode == 0){
//            WXUserInfo wxUserInfo = (WXUserInfo)data.getSerializableExtra("WXUserInfo");
//            if (wxUserInfo!=null)
//            {
//                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//                netWorkUtlis.setOnNetWork(URL_GET.wechatLogin(wxUserInfo.getOpenid(), wxUserInfo.getNickname(), wxUserInfo.getHeadimgurl()), new NetWorkUtlis.OnNetWork() {
//                    @Override
//                    public void onNetSuccess(String result) {
//                        Log.e("result","wxresult="+result);
//                        DataWeResult dataWeResult = JSON.parseObject(result, DataWeResult.class);
//                        DataWeResult.RecordBean record = dataWeResult.getRecord();
//                        DataWeResult.RecordBean.UserInfoBean userInfo = record.getUserInfo();
//                        if (!StrUtils.isEmpty(userInfo.toString()))
//                        {
//                            SaveLoginResultData(userInfo);
//                            IntentUtils.JumpFinishTo(NewMainActivity.class);
//                        }
//                    }
//                });
//
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    private void SaveLoginResultData(DataWeResult.RecordBean.UserInfoBean userInfo) {
        URL_GET.USER_TOKEN = userInfo.getToken();
        SPUtils.put(WXLoginActivity.this, AppAllKey.User_REAL_NAME,userInfo.getNickName());
        SPUtils.put(WXLoginActivity.this,AppAllKey.User_ID,userInfo.getUserId());
        SPUtils.put(WXLoginActivity.this,AppAllKey.User_HEAD_URL,userInfo.getHeadPortrait());
        String json = JSON.toJSON(userInfo).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY_NEW, json);
    }
    //    拦截右滑退出
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
    @Override
    protected boolean isTopBack() {
        return false;
    }
}
