package com.xm6leefun.zll_user.main_code.retrofitrx;


import rx.Subscriber;

/**
 * Created by Administrator on 2017/4/8.
 */

public class PostSubscriber<T> {

//    public Subscriber getSubscriber(final ResponseResultListener<T> listener){
//        Subscriber subscriber = new Subscriber<ResponseParent<T>>() {
//            @Override
//            public void onCompleted() {
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                try {
//                    checkHttpException(e);
//                    listener.fialed("",e.getMessage());
//                }catch (Exception ex){
//                    listener.fialed("", "");
//                }
//            }
//
//            @Override
//            public void onNext(ResponseParent<T> httpResult) {
//                if (httpResult.getReturnCode().equals("0000")){
//                    //成功
//                    listener.success(httpResult.getReturnData());
//                }else if (httpResult.getReturnCode().equals("4004")){
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                    ToastUtil.showToast(httpResult.getReturnMsg());
//                }else if (httpResult.getReturnCode().equals("1002")){
//                    //token过期，跳到登录界面
//                    ToastUtil.showToast("您已太久未登录了，请重新登录");
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                    EventBus.getDefault().post(new AcitivtyFinishListener(true));
//                }else if (httpResult.getReturnCode().equals("9999")){
//                    //未知错误
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }else{
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }
//            }
//        };
//        return subscriber;
//    }



    public Subscriber getSubscriber(final ResponseResultExtendListener<T> listener){
        Subscriber subscriber = new Subscriber<ResponseParent<T>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                try {
                    checkHttpException(e);
                    listener.fialed("",e.getMessage());
                }catch (Exception ex){
                    listener.fialed("", "");
                }
            }

            @Override
            public void onNext(ResponseParent<T> httpResult) {
//                if (httpResult.getReturnCode().equals("0000")){
//                    //成功
//                    listener.success(httpResult.getReturnData(), httpResult.getReturnSize(), "", "");
//                }else if (httpResult.getReturnCode().equals("4004")){
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                    ToastUtil.showToast(httpResult.getReturnMsg());
//                }else if (httpResult.getReturnCode().equals("1002")){
//                    //token过期，跳到登录界面
//                    ToastUtil.showToast("您已太久未登录了，请重新登录");
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }else{
//                    listener.fialed(httpResult.getReturnCode(), httpResult.getReturnMsg());
//                }
                if (httpResult.getSubjects() !=null){
                    listener.success(httpResult.getSubjects());
                }else{
                    listener.fialed("","");
                }
            }
        };
        return subscriber;
    }


    public static void checkHttpException(Throwable mThrowable) {
//        if (NetUtil.getNetWorkState(MyApplication.getInstance()) == -1) {
//            ToastUtil.showToast("没有网络");
//            return;
//        }
//        if ((mThrowable instanceof UnknownHostException)) {
//            ToastUtil.showToast("网络异常");
//        } else if (mThrowable instanceof JsonSyntaxException) {
//            ToastUtil.showToast("数据异常");
//        } else if (mThrowable instanceof SocketTimeoutException) {
//            ToastUtil.showToast("连接超时");
//        } else if (mThrowable instanceof ConnectException) {
//            ToastUtil.showToast("连接服务器失败");
//        } else {
//            ToastUtil.showToast("操作失败");
//        }/* else if (mThrowable instanceof RuntimeException) {
//            ToastUtil.showToast(mContext,"程序出错");
//        } else {
//            ToastUtil.showToast(mContext,"网络异常");
//        }*/
    }
}
