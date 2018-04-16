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

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.about_system.WindowBugDeal;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public class NewBaseActivity extends AppCompatActivity implements NetWorkUtlis.OnNetWork {
    public Handler mHandler =null;

    String simpleName;
    View mtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initSwipeBackFinish();
        simpleName = getClass().getSimpleName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            显示 内屏返回键
            if (!simpleName.equals("MainActivity")) {
                WindowBugDeal.checkDeviceHasNavigationBar(AppManager.getAppManager().currentActivity());
            } else
                WindowBugDeal.SetTop(AppManager.getAppManager().currentActivity());
        }

        if (mHandler==null)
            mHandler= new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    if (msg != null)
                        onHandleMessage(msg);
                    return false;
                }
            });

        setContentView(getLayoutViewId());
        ButterKnife.bind(this);
        initBaseView();
    }
    protected  void initBaseView(){}
    protected  int getLayoutViewId()
    {
        return 0;
    }
    /**
     * 初始化滑动返回
     */
    private void initSwipeBackFinish() {
        if (isSupportSwipeBack()) {
            initSwiBack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DialogUtils.isShow();
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
    @Override
    protected void onStart() {
        super.onStart();
//        String simpleName = getClass().getSimpleName();

    }


    public void onHandleMessage(Message msg) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getAppManager().finishActivity();
        }
        return super.onKeyDown(keyCode, event);
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
}
