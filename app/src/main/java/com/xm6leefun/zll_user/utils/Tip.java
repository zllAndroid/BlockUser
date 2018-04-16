package com.xm6leefun.zll_user.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.ui_custom.CustomDialog;


/**
 * Created by Administrator on 2017/7/26.
 */

public class Tip {
    public interface OnClickSureListener {
        void onClickSure();
    }
    public interface OnClickCancleListener {
        void onClickCancle();
    }

    public static void getDialog(Context mc,String message,final  OnClickSureListener listener) {
        CustomDialog mDialog;
        CustomDialog.Builder  mBuilder;
        mBuilder = new CustomDialog.Builder(mc);
        mBuilder.setMessage(message);
        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickSure();
                dialog.dismiss();
            }
        });
        mBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mDialog = mBuilder.create();
        mDialog.setCancelable(false);
        mDialog.show();
    }
    public static void getDialogOne(Context mc,String message,final  OnClickSureListener listener) {
        CustomDialog mDialog;
        CustomDialog.Builder  mBuilder;
        mBuilder = new CustomDialog.Builder(mc);
        mBuilder.setMessage(message);
        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickSure();
                dialog.dismiss();
            }
        });
        mDialog = mBuilder.create();
        mDialog.setCancelable(false);
        mDialog.show();
    }
    public static void getDialog(Context mc,String message,final  OnClickSureListener listener,final OnClickCancleListener onClickCancleListener) {
        CustomDialog mDialog;
        CustomDialog.Builder  mBuilder;
        mBuilder = new CustomDialog.Builder(mc);
        mBuilder.setMessage(message);
        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onClickSure();
                dialog.dismiss();
            }
        });
        mBuilder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onClickCancleListener.onClickCancle();
                dialogInterface.dismiss();
            }
        });
        mDialog = mBuilder.create();
        mDialog.setCancelable(false);
        mDialog.show();
    }
    public static  DialogInterface dialogs =null;
    public static CustomDialog mDialog = null;
    public static void getDialogOneClick(Context mc,String message,final  OnClickSureListener listeners) {
        if (mDialog!=null&&mDialog.isShowing()&&dialogs!=null)
        {
            dialogs.dismiss();
        }
        CustomDialog.Builder  mBuilder;
        mBuilder = new CustomDialog.Builder(mc);
        mBuilder.setMessage(message);
        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogs=dialog;
                listeners.onClickSure();
                dialog.dismiss();
            }
        });
        if (mDialog==null) {
            mDialog = mBuilder.create();
            mDialog.setCancelable(false);
            mDialog.show();
        }else {
            try {
                mDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void getDialog( Context mc,String message,final boolean Isfinish) {
        CustomDialog mDialog;
        if (mBuilder==null)
        mBuilder = new CustomDialog.Builder(mc);
        mBuilder.setMessage(message);
        mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Isfinish)
                {
                    AppManager.getAppManager().finishActivity();
                }
                dialog.dismiss();

            }
        });
        mDialog = mBuilder.create();
        mDialog.setCancelable(false);
        mDialog.show();
    }
   static CustomDialog mDialogzll =null;
    static  CustomDialog.Builder  mBuilder=null;
    public static void getDialog(Context mc, String message) {
//        CustomDialog mDialog;
//        CustomDialog.Builder  mBuilder;
        if (mBuilder==null) {
            mBuilder = new CustomDialog.Builder(mc);
        }
            mBuilder.setMessage(message);
            mBuilder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            mDialogzll = mBuilder.create();
            mDialogzll.setCancelable(false);
            mDialogzll.show();
    }
    /**
     * 网络连接错误
     */
//    public static void getError( String message) {
//        Activity activity = AppManager.getAppManager().currentActivity();
//        if (activity != null) {
//            TipPopWindow tipPopWindow = new TipPopWindow(activity);
//            tipPopWindow.getstyle("知道了", null);
//            if (message == null) {
//                tipPopWindow.setTitle_message("提示", a.worry);
//            } else {
//                if (message.equals("")) {
//                    tipPopWindow.setTitle_message("提示", CommonParameter.worry);
//                } else {
//                    tipPopWindow.setTitle_message("提示", message);
//                }
//            }
//        }
//    }
}
