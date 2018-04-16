package com.xm6leefun.zll_user.main_code.about_login;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.main_code.mains.MainActivity;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class AppStartActivity extends MyBaseActivity {

    //    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
//    @BindView(R.id.btn_sure)
//    Button mBtn;
    @BindView(R.id.welcome_iv)
    ImageView welcomeIv;
    Timer timer=null;
    @Override
    public int getLayoutId() {
        return R.layout.activity_app_start;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            AppStartActivityPermissionsDispatcher.needperWithCheck(this);
        else
            needper();
//        WindowBugDeal.checkDeviceHasNavigationBar(AppStartActivity.this);
////        Animation animation = new RotateAnimation(0, 359);
////        animation.setDuration(1000);
////        animation.setRepeatCount(8);//动画的重复次数
////        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
////        mBtn.startAnimation(animation);//开始动画
//
//        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
//        rotate.setDuration(1000);
//        mBtn.startAnimation(rotate);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(AppStartActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
//        mBtn.startAnimation(rotate);
//        if (rotate != null) {
//            mBtn.startAnimation(rotate);
//        }  else {
//            mBtn.setAnimation(rotate);
//            mBtn.startAnimation(rotate);
//        }
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            IntentUtils.JumpFinishTo(MainActivity.class);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null)
            timer.cancel();
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void needper() {
        ToastUtil.show("权限通过执行");
        if (timer==null) {
            timer = new Timer();
            timer.schedule(task, 2000);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppStartActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    PermissionRequest requests =null;
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onshow(final PermissionRequest request) {
        requests=request;
        DialogUtils.showDialogOne("信任是美好的开始，请授权相机和打电话权限，让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                request.proceed();
            }
        });
    }
    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onper() {
            DialogUtils.showDialogOne("应用需要使用相机和打电话权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {
                    if (requests!=null)
                        requests.proceed();
                    else {
//                        AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivityForResult(intent,0);
                        startActivity(intent);
                    }
                }
            });
    }
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onnever() {
//        ToastUtil.show("OnNeverAskAgain");
        DialogUtils.showDialogOne("应用需要使用相机和打电话权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
//                    AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivityForResult(intent,0);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ToastUtil.show("返回");
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
}
