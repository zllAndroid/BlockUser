package com.xm6leefun.zll_user.main_code.about_login;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.encrypt.des.Des;

import butterknife.BindView;
import butterknife.OnClick;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class AddPwdActivity extends MyBaseActivity {
    @BindView(R.id.tv_add_result)
    TextView tvAddResult;
    @BindView(R.id.tv_jie_result)
    TextView tvJieResult;
    @BindView(R.id.input_data)
    EditText inputData;


    public  static  final  String desKey = "zllAndroid";// 密钥，口号
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_pwd);
//        ButterKnife.bind(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_add_pwd;
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

    @OnClick({R.id.addpwd_btn_add, R.id.addpwd_btn_jie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addpwd_btn_add:
                //加密
                try {
                tvAddResult.setText(Des.encrypt(inputData.getText().toString(), desKey));
                } catch (Exception e) {
                e.printStackTrace();
            }
                break;
            case R.id.addpwd_btn_jie:
                //解密
                try {
                    tvJieResult.setText(Des.decrypt(tvAddResult.getText().toString(), desKey));
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
