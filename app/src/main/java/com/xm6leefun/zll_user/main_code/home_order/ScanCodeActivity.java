package com.xm6leefun.zll_user.main_code.home_order;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.xm6leefun.model.DataScan;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.main_code.home_order.about_scan.ExScanActivityHandler;
import com.xm6leefun.zll_user.main_code.home_order.about_scan.about_decode.InactivityTimer;
import com.xm6leefun.zll_user.ui_custom.camera_view.CameraManager;
import com.xm6leefun.zll_user.ui_custom.camera_view.ViewfinderView;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.Tip;
import com.xm6leefun.zll_user.utils.ToastUtil;

import java.io.IOException;
import java.util.Vector;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ScanCodeActivity extends MyBaseActivity implements SurfaceHolder.Callback {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;

    @BindView(R.id.preview_view)
    SurfaceView surfaceView;

    @BindView(R.id.viewfinder_view)
    ViewfinderView viewfinderView;
    @BindView(R.id.scan_check_open)
    CheckBox mCheckOpen;

    private ExScanActivityHandler handler;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
//    private RealmHelper mRealmHelper;

    public static   int surfaceView_width =0;
    public static int surfaceView_height =0;
    @OnCheckedChanged(R.id.scan_check_open)
    public void onCheckedChanged() {
        if (camera==null)
        {
            ToastUtil.show("摄像头未初始化，请稍后");
            ScanCodeActivityPermissionsDispatcher.takeScanWithCheck(this);
            return;
        }
        if (mCheckOpen.isChecked())
        {
            try {
//            camera = Camera.open();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
                camera.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
            statusFlag = false;
            //看得见
        }else {
            try {
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
                camera.setParameters(parameters);
                statusFlag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        includeTopTvTital.setText("扫一扫");
//        mRealmHelper = new RealmHelper(this);
        surfaceView_width = surfaceView.getWidth();
        surfaceView_height = surfaceView.getHeight();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            ScanCodeActivityPermissionsDispatcher.takeScanWithCheck(this);
        }
        else
        {
            initScan();
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_code;
    }
    /**
     * 处理扫描结果
     * @param result
     * @param barcode
     */
    String lastTime = "";
    String qrCode =null;
    String resultScan =null;
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        resultScan = result.getText();
        if (StrUtils.isEmpty(resultScan)) {
            ToastUtil.show("扫码失败");
        } else {
            viewfinderView.isRefresh = false;
//                lastTime = resultScan;
            playBeepSoundAndVibrate();
            qrCode = resultScan.substring((resultScan.indexOf("=") + 1));
//            RealmTaskResule realmTaskResule = mRealmHelper.queryResultByQrcode(qrCode);
            Log.e("RealmTaskResule","qrCode="+qrCode);
            DialogUtils.showDialog("扫描成功，即将打开该页面\n" + qrCode, new DialogUtils.OnClickSureListener() {
                @Override
                public void onClickSure() {

                }
            });
//            MakeDialog("扫描成功，该二维码可以使用\n",qrCode);
//            if (!mRealmHelper.isHaveExist(qrCode))
//            {
////                NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
////                String id = (String) SPUtils.get(ScanCodeActivity.this, AppAllKey.User_ID, "");
////                netWorkUtlis.setOnNetWork(CommonParameter.NORMAL, URL_GET.checkQrCode(id, qrCode), this);
//            }else
//            {
//                DialogUtils.showDialog("该二维码已被使用，请更换", new DialogUtils.OnClickSureListener() {
//                    @Override
//                    public void onClickSure() {
//                        resultScan=null;
//                        reScan();
//                    }
//                });
//            }
        }
    }
    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())) {
                    final String sucess = HelpUtils.HttpIsSucess(msg.obj.toString());
                    if (sucess.equals(CommonParameter.CODE_SUCCESS))
                    {
                        DataScan dataHomeDetails = JSON.parseObject(msg.obj.toString(), DataScan.class);
                        DataScan.RecordBean taskData = dataHomeDetails.getRecord();
                        if (taskData.getFlag().equals("0"))
                        {
                            MakeDialog("扫描成功，该二维码可以使用\n",qrCode);
                        }else
                        {
                            DialogUtils.showDialog(taskData.getQrMsg(), new DialogUtils.OnClickSureListener() {
                                @Override
                                public void onClickSure() {
                                    resultScan=null;
                                    reScan();
                                }
                            });
                        }
                    }else {
                        ToastUtil.show(sucess);
                    }
                }
                break;
        }
    }
    private void MakeDialog(final String tital,final String qrCode) {
        Tip.getDialog(ScanCodeActivity.this, tital, new Tip.OnClickSureListener() {
            @Override
            public void onClickSure() {

//                IntentUtils.JumpToHaveOne(HomeNFCActivity.class,"qrCode",qrCode);
//                AppManager.getAppManager().finishActivity();
            }
        }, new Tip.OnClickCancleListener() {
            @Override
            public void onClickCancle() {
                resultScan=null;
                reScan();
            }
        });
    }

    private void openFlashLight() {
        if (statusFlag) {
            camera = Camera.open();
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
            camera.setParameters(parameters);
            statusFlag = false;
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
            camera.setParameters(parameters);
            statusFlag = true;
            camera.release();
        }
    }
    private Camera camera = null;
    private Camera.Parameters parameters = null;
    public static boolean statusFlag = true;
    public void CloseApp() { // 关闭程序
//        try {
//            android.os.Process.killProcess(android.os.Process.myPid());// 关闭进程
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (statusFlag) {// 开关关闭时
//
//        } else if (!statusFlag) {// 开关打开时
//
//            try {
//                camera.setPreviewCallback(null);
//                camera.release();
//                CameraManager.get().setC();
////                camera.setPreviewCallback(null);
////                camera.stopPreview();
////                camera.release();
//                android.os.Process.killProcess(android.os.Process.myPid());// 关闭进程
//                statusFlag = true;// 避免，打开开关后退出程序，再次进入不打开开关直接退出时，程序错误
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }


    private void reScan() {
        viewfinderView.isRefresh = true;
        try {
            handler.initrequestAutoFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            handler.restartPreviewAndDecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void takeScan() {
        initScan();
//        initOnResume1();
    }
    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForRecord(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        initScan();
//                        initOnResume1();
                        request.proceed();
                    }
                })
                .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("扫描二维码需要相机权限，应用将要申请使用相机权限")
                .show();
    }
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showRecordDenied() {
        ToastUtil.show("权限被拒绝,退出该页面");
        try {
            AppManager.getAppManager().finishActivity();
            inactivityTimer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onRecordNeverAskAgain() {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 打开系统应用设置
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("您已经禁止了相机权限,是否现在去开启")
                .show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ScanCodeActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }


    private void initScan() {
        viewfinderView.isRefresh = true;
        CameraManager.init(getApplication());
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);

    }
    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
            camera = CameraManager.get().getC();
            parameters = camera.getParameters();
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new ExScanActivityHandler(ScanCodeActivity.this, decodeFormats, characterSet);
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }
    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }
    public Handler getHandler() {
        return handler;
    }
    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }
    /**
     * 初始化声音资源
     */
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);
            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.ring);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);//设置音量
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        initOnResume1();
    }
    private void initOnResume1() {
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        initOnPause2();
    }

    private void initOnPause2() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        try {
            CameraManager.get().closeDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            inactivityTimer.shutdown();
            CloseApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static final long VIBRATE_DURATION = 200L;
    /**
     * 响铃和震动
     */
    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}
