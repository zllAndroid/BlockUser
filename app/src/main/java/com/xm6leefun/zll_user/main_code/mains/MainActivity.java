package com.xm6leefun.zll_user.main_code.mains;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTabHost;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataWeResult;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.about_login.WXLoginActivity;
import com.xm6leefun.zll_user.utils.ACache;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;

import butterknife.BindView;

public class MainActivity extends MyBaseActivity {
    int[] imgs = {R.drawable.tab_home, R.drawable.tab_mine};
//    int[] imgs = {R.drawable.tab_home, R.drawable.tab_tui,R.drawable.tab_order, R.drawable.tab_mine};
    private String[] tvtab ={ "首页", "我的" };
//    private String[] tvtab ={ "当前任务", "个人中心", "交易查询","我的" };

    @BindView(android.R.id.tabs)
    TabWidget tabs;

    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    public static int screenWidth;
    public static int screenHeight;

    private ACache mCache =null;
   public static  boolean isFirst ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        isFirst = initCaChe();

        //		获取屏幕分辨率
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
//        ButterKnife.bind(this);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//        去掉分割线
        mTabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);

        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndecator(0)), HomeFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getIndecator(0)), HomeUserFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("popu").setIndicator(getIndecator(1)), PopularizeFragment.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("order").setIndicator(getIndecator(2)), OrderFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getIndecator(1)), PersonFragment.class, null);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("mine")&&!isFirst)
                {
                    DialogUtils.showDialog("您还未登录，是否跳转到登录页面", new DialogUtils.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            IntentUtils.JumpFinishTo(WXLoginActivity.class);
                        }
                    });
//                    DialogUtils.showDialogOne("您还未登录，是否跳转到登录页面", new DialogUtils.OnClickSureListener() {
//                        @Override
//                        public void onClickSure() {
//                            IntentUtils.JumpFinishTo(WXLoginActivity.class);
//                        }
//                    });
                }
//                Log.e("tabId",tabId);
            }
        });
//        int localVersion = HelpUtils.getLocalVersion(MainActivity.this);
//        VersionCheckUtils.initUpdata(false);
    }
// SPUtils.put(WXLoginActivity.this, AppAllKey.User_REAL_NAME,userInfo.getNickName());
    private boolean initCaChe() {
        mCache = ACache.get(this);
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private View getIndecator(int index) {


        View view = getLayoutInflater().inflate(R.layout.layout_tabin, null);
        ImageView mImageView = (ImageView)view.findViewById(R.id.img_main_tab);
        final TextView mTextView =  (TextView)view.findViewById(R.id.tv_main_tab);

        try {
            mTextView.setText(tvtab[index]);
            mImageView.setImageResource(imgs[index]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
    boolean isExit;
    Handler mHandler = new Handler();
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            // 双击退出
            if (isExit)
            {
//                finish();
                AppManager.getAppManager().onAppExit(MainActivity.this);

            } else
            {
                isExit = true;
                ToastUtil.show("再按一次退出应用");
//                Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        isExit = false;
                    }
                }, 2000);
            }
            // return super.onKeyDown(keyCode, event);
            // 拦截系统按键
        }
        return true;
    }
//    拦截右滑退出
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
//    是否头部公共返回
    @Override
    protected boolean isTopBack() {
        return false;
    }
}

