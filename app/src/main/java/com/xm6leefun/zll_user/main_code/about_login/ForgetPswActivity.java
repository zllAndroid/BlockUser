package com.xm6leefun.zll_user.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.main_code.mains.MainActivity;
import com.xm6leefun.zll_user.utils.BtnToEditListenerUtils;
import com.xm6leefun.zll_user.utils.EditCheckUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NoDoubleClickUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.Tip;

import butterknife.BindView;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class ForgetPswActivity extends MyBaseActivity {
    public static int screenWidth;
    public static int screenHeight;
    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.forget_ed_user)
    EditText forgetEdUser;
    @BindView(R.id.forget_ed_code)
    EditText forgetEdCode;
    @BindView(R.id.forget_tv_send_code)
    TextView forgetTvSendCode;
    @BindView(R.id.forget_ed_psw)
    EditText forgetEdPsw;
    @BindView(R.id.forget_btn_sure)
    Button forgetBtnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_psw);
//        ButterKnife.bind(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        includeTopTvTital.setText("忘记密码");
        BtnToEditListenerUtils.getInstance()
                .setBtn(forgetBtnSure)
                .addEditView(forgetEdUser)
                .addEditView(forgetEdCode)
                .addEditView(forgetEdPsw)
                .build();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_psw;
    }

    private CountDownTimer timer =new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long l) {
            forgetTvSendCode.setText((l / 1000) + "s");
            forgetTvSendCode.setClickable(false);
            forgetTvSendCode.setBackgroundResource(R.drawable.btn_stroke_nor_b5);
        }
        @Override
        public void onFinish() {
            forgetTvSendCode.setEnabled(true);
            forgetTvSendCode.setClickable(true);
            forgetTvSendCode.setText("获取验证码");
            forgetTvSendCode.setBackgroundResource(R.drawable.btn_stroke_sel);
        }
    };
    private void initSendSms() {
        String phone = forgetEdUser.getText().toString().trim();
        if (StrUtils.isEmpty(phone)) {
            Tip.getDialog(this,"手机号不能为空");
            return;
        }
        if (!EditCheckUtils.isMobileNO(phone)) {
            Tip.getDialog(this,"手机号输入有误");
            return;
        }
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(URL_GET.getNoteCode(phone, "resetPwd"), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//                if (!StrUtils.isEmpty(msg))
//                    timer.start();
//            }
//        });
    }


    private void initNext() {
        final String phone = forgetEdUser.getText().toString().trim();
        String code = forgetEdCode.getText().toString().trim();
        String psw = forgetEdPsw.getText().toString().trim();
        if (!EditCheckUtils.isMobileNO(phone)) {
            Tip.getDialog(ForgetPswActivity.this,getResources().getString(R.string.phone_is_error));
            return;
        }
        if (StrUtils.isEmpty(code)) {
            Tip.getDialog(ForgetPswActivity.this,"验证码不得为空");
            return;
        }
        if (StrUtils.isEmpty(psw)) {
            Tip.getDialog(ForgetPswActivity.this,"密码不得为空");
            return;
        }
        if (psw.length()<6) {
            Tip.getDialog(ForgetPswActivity.this,"密码至少为六位");
            return;
        }
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(URL_GET.resetPwd(phone,psw,code), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String result) {
//
//                SPUtils.put(ForgetPswActivity.this, AppAllKey.SP_LOGIN_ACCOUNT,phone);
//                Tip.getDialogOneClick(ForgetPswActivity.this, "修改密码成功,请重新登录", new Tip.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        Intent intent_recharge = new Intent(ForgetPswActivity.this, LoginActivity.class);
////                        把密码忘记
//                        SPUtils.put(ForgetPswActivity.this, AppAllKey.SP_LOGIN_PSW, "");
//                        ACache.get(MyApplication.getAppContext()).clear();
//                        AppManager.getAppManager().finishAllActivity();
//                        startActivity(intent_recharge);
//                    }
//                });
////                Tip.getDialog(ForgetPswActivity.this,"找回密码成功",true);
//            }
//        });
    }


    @OnClick({R.id.forget_tv_send_code, R.id.forget_btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_tv_send_code:
                timer.start();
                break;
            case R.id.forget_btn_sure:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(MainActivity.class);
                break;
        }
    }

}
