package com.xm6leefun.zll_user.main_code.about_mine.mine_set;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.include_top_lin_back)
    LinearLayout includeTopLinBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        includeTopTvTital.setText("更改手机号");
//        includeTopLinBack.setBackgroundColor(getResources().getColor(R.color.app_theme));
    }

    @OnClick(R.id.include_top_iv_back)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity();
    }
}
