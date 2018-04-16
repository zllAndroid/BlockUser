package com.xm6leefun.zll_user.main_code.mains;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.xm6leefun.model.DataMineUserInfo;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.BaseFragment;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.MyApplication;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.about_person_user.PersonAlreadyActivatedActivity;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.DataCleanManager;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.NoDoubleClickUtils;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.Tip;
import com.xm6leefun.zll_user.utils.ToastUtil;
import com.xm6leefun.zll_user.utils.VersionCheckUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.main_view_top)
    View mainViewTop;
    @BindView(R.id.main_view_title)
    TextView mainViewTitle;
    @BindView(R.id.mine_btn_esc)
    Button mineBtnEsc;
    @BindView(R.id.person_head)
    ImageView personHead;
    @BindView(R.id.per_tv_name)
    TextView perTvName;
    @BindView(R.id.per_tv_phone)
    TextView perTvPhone;
    @BindView(R.id.per_tv_weiwubi)
    TextView perTvWeiwubi;
    @BindView(R.id.person_tv_cache)
    TextView personTvCache;
    @BindView(R.id.person_tv_version)
    TextView personTvVersion;

    public PersonFragment() {
    }

    View view;
    private ACache mCache =null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_person_mine, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        mainViewTitle.setText("个人中心");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mainViewTop != null)
                mainViewTop.setVisibility(View.VISIBLE);
        }
        initBaseData();
//        initCaChe();
        if (MainActivity.isFirst)
              initHttpData();
//        initUIData();
        return view;
    }
//    private void initCaChe() {
//        mCache = ACache.get(getActivity());
//        URL_GET.USER_TOKEN ="";
//        if (mCache!=null){
//            String asString = mCache.getAsString(AppAllKey.WX_KEY);
//            if (!StrUtils.isEmpty(asString))
//            {
//                Log.e("result",asString.toString()+"token信息");
//                WXUserInfo wxResponse = JSON.parseObject(asString,WXUserInfo.class);
//                String headimgurl = wxResponse.getHeadimgurl();
//                initUI(wxResponse);
//
//            }
//        }
//    }

//    private void initUI(WXUserInfo wxResponse) {
//        perTvName.setText(wxResponse.getNickname());
//        Glide.with(getActivity()).load(wxResponse.getHeadimgurl()).into(personHead);
////        perTvPhone.setText(wxResponse.get());
//
//    }

    private void initBaseData() {
        try {
            String versionName = HelpUtils.getLocalVersionName();
            personTvVersion.setText("v"+versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            personTvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initHttpData() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(CommonParameter.LodingNormal, URL_GET.getUserData(URL_GET.USER_ID), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
//                String s = HelpUtils.HttpIsSucess(result);
                DataMineUserInfo dataMineUserInfo = JSON.parseObject(result, DataMineUserInfo.class);
                DataMineUserInfo.RecordBean record = dataMineUserInfo.getRecord();
                DataMineUserInfo.RecordBean.UserInfoBean userInfo = record.getUserInfo();
                if (userInfo!=null)
                {
                    if (!StrUtils.isEmpty(userInfo.getHeadPortrait()))
                    {
                        Glide.with(AppManager.getAppManager().currentActivity()).load(userInfo.getHeadPortrait()).
                                bitmapTransform(new CropCircleTransformation(AppManager.getAppManager().currentActivity())).crossFade(1000).into(personHead);
                    }
                    if (!StrUtils.isEmpty(userInfo.getNickName())) {
                        perTvName.setText(userInfo.getNickName());
                    }
                    if (!StrUtils.isEmpty(userInfo.getMobile())) {
                        perTvPhone.setText(userInfo.getMobile());
                    }
                    if (!StrUtils.isEmpty(userInfo.getCoinSum())) {
                        perTvWeiwubi.setText(userInfo.getCoinSum());
                    }
                }
            }
        });
    }

    //    private void initUIData() {
//        try {
//            String name = (String) SPUtils.get(getActivity(), AppAllKey.User_REAL_NAME, "");
//            personTvName.setText(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            String phone = (String) SPUtils.get(getActivity(), AppAllKey.SP_LOGIN_ACCOUNT, "");
//            personTvPhone.setText(phone);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        view = null;
    }
    @OnClick({R.id.mine_btn_esc,R.id.person_lin_ji_goods, R.id.mine_lin_cache, R.id.mine_lin_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person_lin_ji_goods:
                if (NoDoubleClickUtils.isDoubleClick())
                    IntentUtils.JumpTo(PersonAlreadyActivatedActivity.class);
                break;
            case R.id.mine_lin_cache:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    if (!personTvCache.getText().toString().equals("0KB")) {
                        DialogUtils.showDialog("确认清理" + personTvCache.getText().toString() + "缓存？", new DialogUtils.OnClickSureListener() {
                            @Override
                            public void onClickSure() {
                                cleanCaChe();
                            }
                        });
                    }else {
                        ToastUtil.show("暂无缓存");
                    }
                }
                break;
            case R.id.mine_lin_version:
                if (NoDoubleClickUtils.isDoubleClick())
                    VersionCheckUtils.initUpdata(true);
                break;
            case R.id.mine_btn_esc:
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Tip.getDialog(getActivity(), "是否确定退出登录", new Tip.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            try {
//                                TODO  在登录成功后添加一个SP_USER_ID的参数
                                String userId = (String) SPUtils.get(getActivity(), AppAllKey.SP_USER_ID, "");
                                new NetWorkUtlis().setOnNetWork(CommonParameter.NORMAL, URL_GET.loguot(userId), new NetWorkUtlis.OnNetWork() {
                                    @Override
                                    public void onNetSuccess(String result) {}});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AppManager.getAppManager().onAppExit(MyApplication.getAppContext());
//                            Intent intent_recharge = new Intent(getActivity(), LoginActivity.class);
//                            startActivity(intent_recharge);
//                            ACache.get(getActivity()).clear();
//                            AppManager.getAppManager().finishAllExceptCurrentActivity(LoginActivity.class);
                        }
                    });
                }
                break;
        }
    }
    //    清理缓存
    private void cleanCaChe() {
        DataCleanManager.clearAllCache(MyApplication.getAppContext());
        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            personTvCache.setText(totalCacheSize);
            ToastUtil.show("清理缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
