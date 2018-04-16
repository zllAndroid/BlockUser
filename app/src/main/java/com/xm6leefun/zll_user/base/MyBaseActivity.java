package com.xm6leefun.zll_user.base;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
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
import android.widget.TextView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;
import com.xm6leefun.zll_user.utils.about_system.WindowBugDeal;

import java.lang.reflect.Field;

import butterknife.ButterKnife;


public class MyBaseActivity extends AppCompatActivity implements NetWorkUtlis.OnNetWork {
    public Handler mHandler =null;

    String simpleName;
    TextView mtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initSwipeBackFinish();
        simpleName = getClass().getSimpleName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            显示 内屏返回键
            if (!simpleName.equals("MainssActivity")) {
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

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initBaseView();
    }

    protected void initBaseView() {
    }

    protected  int getLayoutId()
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
    /**
     * 是否开启nfc
     *
     * @return
     */
    protected boolean isNFC() {
        return false;
    }
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    /**
     * 获得焦点，按钮可以点击
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (isNFC()) {
            //设置处理优于所有其他NFC的处理
            if (mNfcAdapter != null)
                mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }
    /**
     * 暂停Activity，界面获取焦点，按钮可以点击
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (isNFC()) {
            //恢复默认状态
            if (mNfcAdapter != null)
                mNfcAdapter.disableForegroundDispatch(this);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (isNFC())
        {
            initNfc();

        }



//        String simpleName = getClass().getSimpleName();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isTopBack())
            {
                try {
                    findViewById(R.id.include_top_iv_back).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AppManager.getAppManager().finishActivity();
                        }
                    });
                    mtv = (TextView) AppManager.getAppManager().currentActivity().findViewById(R.id.include_top_margin10);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        mtv.setVisibility(View.VISIBLE);
                    }else
                    {
                        mtv.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initNfc() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            ToastUtil.show("设备不支持NFC！");
//                Toast.makeText(this, "设备不支持NFC！", Toast.LENGTH_LONG).show();
//                finish();
            return ;
        }
        if (!mNfcAdapter.isEnabled()) {
            ToastUtil.show("请在系统设置中先启用NFC功能！");
//                Toast.makeText(this, "请在系统设置中先启用NFC功能！", Toast.LENGTH_LONG).show();
//                finish();
            return ;
        }
        //一旦截获NFC消息，就会通过PendingIntent调用窗口
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()), 0);
        return ;
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
