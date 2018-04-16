package com.xm6leefun.zll_user.main_code.retrofitrx;


import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/2/24.
 */

public class UserManager {
    /**
     * 登录接口
     */
    public static void getData(int start, int count, ResponseResultExtendListener<List<ResponseData>> callback){

        RequestData requestData = new RequestData();
        requestData.setStart(start);
        requestData.setCount(count);
        ResquestParent httpClient = new ResquestParent(requestData);

        Subscriber<ResponseParent<List<ResponseData>>> subscriber =
                new PostSubscriber<List<ResponseData>>().getSubscriber(callback);

        ApiClient.getApiService().getData(httpClient)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
