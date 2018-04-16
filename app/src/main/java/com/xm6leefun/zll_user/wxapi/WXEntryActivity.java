package com.xm6leefun.zll_user.wxapi;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.vise.log.ViseLog;
import com.xm6leefun.model.DataWeResult;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.Config;
import com.xm6leefun.zll_user.base.MyApplication;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.entity.WXAccessTokenEntity;
import com.xm6leefun.zll_user.entity.WXBaseRespEntity;
import com.xm6leefun.zll_user.entity.WXUserInfo;
import com.xm6leefun.zll_user.main_code.mains.NewMainActivity;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * description ：
 * project name：CCloud
 * author : Vincent
 * creation date: 2017/6/9 18:13
 *
 * @version 1.0
 */

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler{

    /**
     * 微信登录相关
     */
    private ACache mCache =null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCache = ACache.get(this);
        //如果没回调onResp，八成是这句没有写
        MyApplication.mWxApi.handleIntent(getIntent(), this);
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
//        try {
//            boolean result =  api.handleIntent(getIntent(), this);
//            if(!result){
//                ViseLog.d("参数不合法，未被SDK处理，退出");
//                finish();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    //    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        WXLoginActivity.mWxApi.handleIntent(data,this);
////        IntentUtils.JumpToHaveOne(MainActivity.class,"data",data.toString());
//    }
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        WXLoginActivity.mWxApi.handleIntent(intent, this);
////        finish();
//    }
    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq baseReq) {
        ViseLog.d("baseReq:"+ JSON.toJSONString(baseReq));
    }

    @Override
    public void onResp(BaseResp baseResp) {

        WXBaseRespEntity entity = JSON.parseObject(JSON.toJSONString(baseResp),WXBaseRespEntity.class);
        String result = "";
        switch(baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result ="微信登录成功";
//                showDialog("正在获取个人资料..");
                //现在请求获取数据 access_token https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
                OkHttpUtils.get().url("https://api.weixin.qq.com/sns/oauth2/access_token")
                        .addParams("appid",Config.APP_ID_WX)
                        .addParams("secret",Config.APP_SECRET_WX)
                        .addParams("code",entity.getCode())
                        .addParams("grant_type","authorization_code")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(okhttp3.Call call, Exception e, int id) {
                                ViseLog.d("请求错误..");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                ViseLog.d("response:"+response);
                                Log.e("response",response);
                                WXAccessTokenEntity accessTokenEntity = JSON.parseObject(response,WXAccessTokenEntity.class);
                                if(accessTokenEntity!=null){
                                    getUserInfo(accessTokenEntity);
                                }else {
                                    ViseLog.d("获取失败");
                                }
                            }
                        });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "发送取消";
                ViseLog.d("发送取消");
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "发送被拒绝";
                ViseLog.d("发送被拒绝");
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                result = "签名错误";
                ViseLog.d("签名错误");
                break;
            default:
                result = "发送返回";
//                showMsg(0,result);
                finish();
                break;
        }
        Toast.makeText(WXEntryActivity.this,result, Toast.LENGTH_LONG).show();

    }

    /**
     * 获取个人信息
     * @param accessTokenEntity
     */
    private void getUserInfo(final  WXAccessTokenEntity accessTokenEntity) {
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
        OkHttpUtils.get()
                .url("https://api.weixin.qq.com/sns/userinfo")
                .addParams("access_token",accessTokenEntity.getAccess_token())
                .addParams("openid",accessTokenEntity.getOpenid())//openid:授权用户唯一标识
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(okhttp3.Call call, Exception e, int id) {
                        ViseLog.d("获取错误..");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        WXUserInfo wxResponse = JSON.parseObject(response,WXUserInfo.class);
//                        String headUrl = wxResponse.getHeadimgurl();
//                        MyApplication.getShared().putString("headUrl",headUrl);
//
//                        SPUtils.put(WXEntryActivity.this,"response",response);
//                        SPUtils.put(WXEntryActivity.this,"access_token",accessTokenEntity.getAccess_token());
//                        SPUtils.put(WXEntryActivity.this,"openid",accessTokenEntity.getOpenid());
//                        ToastUtil.show("有东西");
//                        String json = JSON.toJSON(response).toString();
//                        mCache.clear();
//                        mCache.put(AppAllKey.WX_KEY, json);
//                        Log.e("response",response);
//                        Log.e("response","openid="+accessTokenEntity.getOpenid());
//                        Log.e("response","headUrl="+headUrl);
//                        IntentUtils.JumpTo(MainActivity.class);
//                        IntentUtils.JumpToHaveObj(WXLoginActivity.class,"WXUserInfo",wxResponse);
//                        AppManager.getAppManager().finishActivity();
                        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
                        netWorkUtlis.setOnNetWork(URL_GET.wechatLogin(wxResponse.getOpenid(), wxResponse.getNickname(), wxResponse.getHeadimgurl()), new NetWorkUtlis.OnNetWork() {
                            @Override
                            public void onNetSuccess(String result) {
                                Log.e("result","wxresult="+result);
                                DataWeResult dataWeResult = JSON.parseObject(result, DataWeResult.class);
                                DataWeResult.RecordBean record = dataWeResult.getRecord();
                                DataWeResult.RecordBean.UserInfoBean userInfo = record.getUserInfo();
                                if (!StrUtils.isEmpty(userInfo.toString()))
                                {
                                    SaveLoginResultData(userInfo);
                                    IntentUtils.JumpFinishTo(NewMainActivity.class);
                                    finish();
                                    overridePendingTransition(0,0);
//                                    AppManager.getAppManager().finishActivity();
                                }
                            }
                        });
//                        Intent intent = getIntent();
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("WXUserInfo",wxResponse);
//                        intent.putExtras(bundle);
//                        WXEntryActivity.this.setResult(0,intent);
//                        finish();
                    }
                });
    }
    private void SaveLoginResultData(DataWeResult.RecordBean.UserInfoBean userInfo) {
        URL_GET.USER_TOKEN = userInfo.getToken();
        SPUtils.put(WXEntryActivity.this, AppAllKey.User_REAL_NAME,userInfo.getNickName());
        SPUtils.put(WXEntryActivity.this,AppAllKey.User_ID,userInfo.getUserId());
        SPUtils.put(WXEntryActivity.this,AppAllKey.User_HEAD_URL,userInfo.getHeadPortrait());
        String json = JSON.toJSON(userInfo).toString();
        if (mCache!=null)
            mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY_NEW, json);
    }
}
