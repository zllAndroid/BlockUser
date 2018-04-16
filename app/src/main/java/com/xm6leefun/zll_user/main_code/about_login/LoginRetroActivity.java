package com.xm6leefun.zll_user.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataLogin;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.mains.MainActivity;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.EditCheckUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.NoDoubleClickUtils;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.Tip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class LoginRetroActivity extends MyBaseActivity {
    public static int screenWidth;
    public static int screenHeight;
    @BindView(R.id.login_ed_user)
    EditText loginEdUser;
    @BindView(R.id.login_ed_psw)
    EditText loginEdPsw;
    @BindView(R.id.login_tv_forget_psw)
    TextView loginTvForgetPsw;
    @BindView(R.id.login_btn_ok)
    Button loginBtnOk;
    private ACache mCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_retro);
        ButterKnife.bind(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

    }
    private void initHttp() {
        final String phone = loginEdUser.getText().toString().trim();
        final  String psw = loginEdPsw.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            Tip.getDialog(LoginRetroActivity.this,getResources().getString(R.string.phone_is_null));
            return;
        }
        if (!EditCheckUtils.isMobileNO(phone)) {
            Tip.getDialog(LoginRetroActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            Tip.getDialog(LoginRetroActivity.this,"密码不得为空");
            return;
        }


        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(URL_GET.login(phone, psw), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String msg) {
                if (!StrUtils.isEmpty(msg)) {
                    try {
                        SPUtils.put(LoginRetroActivity.this, AppAllKey.SP_LOGIN_ACCOUNT, phone);
                        SPUtils.put(LoginRetroActivity.this, AppAllKey.SP_LOGIN_PSW, "");
                        DataLogin dataLogin = JSON.parseObject(msg, DataLogin.class);
                        DataLogin.RecordBean.ShippingClerkBean shippingClerk = dataLogin.getRecord().getShippingClerk();
                        if (shippingClerk != null) {
                            SaveLoginResultData(shippingClerk);
                            IntentUtils.JumpFinishTo(MainActivity.class);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void SaveLoginResultData( DataLogin.RecordBean.ShippingClerkBean dataBean) {
        URL_GET.USER_TOKEN = dataBean.getToken();
        SPUtils.put(LoginRetroActivity.this,AppAllKey.User_REAL_NAME,dataBean.getRealName());
        SPUtils.put(LoginRetroActivity.this,AppAllKey.User_ID,dataBean.getId());

        String json = JSON.toJSON(dataBean).toString();
        mCache.clear();
        mCache.put(AppAllKey.TOKEN_KEY, json);
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        try {
//            String account = (String) SPUtils.get(LoginRetroActivity.this,AppAllKey.SP_LOGIN_ACCOUNT, "");
//            loginEdUser.setText(account);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            String psw = (String)SPUtils.get(LoginRetroActivity.this,AppAllKey.SP_LOGIN_PSW, "");
//            loginEdPsw.setText(psw);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    //    拦截右滑退出
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    protected boolean isTopBack() {
        return false;
    }

    @OnClick({R.id.login_tv_forget_psw, R.id.login_btn_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_forget_psw:
//                ToastUtil.show("true");
//                PassWord(true);
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(ForgetPswActivity.class);
                break;
            case R.id.login_btn_ok:
//                ToastUtil.show("false");
//                PassWord(false);

                if (NoDoubleClickUtils.isDoubleClick())
                    initHttp();
                break;
        }
    }

//    private void PassWord( boolean isDesEncrypt) {
//        String data = "我是俊哥";
//        String desKey = "青龙偃月刀";// 密钥，口号
////        boolean isDesEncrypt = false;
//            try {
//                if(isDesEncrypt){
//                    //解密
//                    textView.setText(Des.decrypt(textView.getText().toString(), desKey));
//                }else {
//                    //加密
//                    textView.setText(Des.encrypt(loginEdUser.getText().toString(), desKey));
//                }
////                isDesEncrypt = !isDesEncrypt;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    }


}
