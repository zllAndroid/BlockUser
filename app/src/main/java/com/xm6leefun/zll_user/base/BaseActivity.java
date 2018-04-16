package com.xm6leefun.zll_user.base;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.gyf.barlibrary.ImmersionBar;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.ScreenUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.about_system.WindowBugDeal;

import java.lang.reflect.Field;
/**
 * Created by thinkcool on 2015/11/20.
 */
public  abstract  class BaseActivity extends AppCompatActivity  implements NetWorkUtlis.OnNetWork{
    public Handler mHandler =null;
    String simpleName;
    private ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initSwipeBackFinish();
        simpleName = getClass().getSimpleName();
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();   //所有子类都将继承这些相同的属性
        ScreenUtils.setWindowStatusBarColor(AppManager.getAppManager().currentActivity(),R.color.app_theme);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            getWindow().getDecorView().setSystemUiVisibility(View.IMMERSIVE_STICKY);
//            WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
            WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
        }
//        mImmersionBar
////                .barColor(R.color.app_theme)
//                .statusBarColor(R.color.app_theme)
//                .fullScreen(true)
//                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
//                .hideBar(BarHide.FLAG_HIDE_BAR)
//                .init();

        setContentView(getLayoutViewId());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            View view = AppManager.getAppManager().currentActivity().findViewById(R.id.sys_top_margin25);
//            view.setVisibility(View.VISIBLE);
//            LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams)view.getLayoutParams();
//            layoutParams.height = ScreenUtils.getStatusHeight(AppManager.getAppManager().currentActivity());
//            view.setLayoutParams(layoutParams);
//        }
        init();
        initBaseView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        View view = AppManager.getAppManager().currentActivity().findViewById(R.id.sys_top_margin25);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
//        {
//            view.setVisibility(View.VISIBLE);
//            LinearLayout.LayoutParams layoutParams = ( LinearLayout.LayoutParams)view.getLayoutParams();
//            layoutParams.height = ScreenUtils.getStatusHeight(AppManager.getAppManager().currentActivity());
//            view.setLayoutParams(layoutParams);
////            Log.e("getStatusHeight","getStatusHeight="+ ScreenUtils.getStatusHeight(AppManager.getAppManager().currentActivity()));
//        }else
//        {
//            view.setVisibility(View.GONE);
//        }
    }

    private void init() {
        if (mHandler==null)
            mHandler= new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg != null)
                        onHandleMessage(msg);
                    return false;
                }
            });

    }
    public void onHandleMessage(Message msg) {}
    protected abstract int getLayoutViewId() ;
    protected abstract void initBaseView() ;
    /**
     * 是否支持滑动返回
     *
     * @return
     */
    protected boolean isSupportSwipeBack() {
        return true;
    }
    /**
     * 是否支持头部点击返回（是否有头部）
     *
     * @return
     */
    protected boolean isTopBack() {
        return true;
    }
    private void initSwipeBackFinish() {
        if (isSupportSwipeBack()) {
            initSwiBack();
        }
    }
    private void initSwiBack() {
        SlidingPaneLayout slidingPaneLayout =  new SlidingPaneLayout(this);
        //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
        //是32dp，现在给它改成0
        try {
            //属性
            Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            f_overHang.setAccessible(true);
            f_overHang.set(slidingPaneLayout, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelOpened(View panel) {
                AppManager.getAppManager().finishActivity();
                overridePendingTransition(0, R.anim.slide_out_right);
            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
        slidingPaneLayout.setSliderFadeColor(getResources().getColor(android.R.color.transparent));

        View leftView = new View(this);
        leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftView, 0);

        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
        decorChild.setBackgroundColor(getResources().getColor(android.R.color.white));
        decor.removeView(decorChild);
        decor.addView(slidingPaneLayout);
        slidingPaneLayout.addView(decorChild, 1);
    }
    @Override
    public void onNetSuccess(String msg) {
        Message message = new Message();
        if (!StrUtils.isEmpty(msg)) {
            message.what = CommonParameter.HANDLE_MSG_SUCCESS;
        }
        else {
            message.what = CommonParameter.HANDLE_MSG_FAILED;
        }
        message.obj = msg;
        mHandler.sendMessage(message);
    }
    /**
     * 拦截返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getAppManager().finishActivity();
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * onDestroyView中进行解绑操作
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogUtils.isShow();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

    }
}
