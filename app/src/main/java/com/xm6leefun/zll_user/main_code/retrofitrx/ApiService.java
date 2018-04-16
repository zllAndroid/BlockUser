package com.xm6leefun.zll_user.main_code.retrofitrx;


import com.xm6leefun.model.DataLogin;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/2/24.
 */

public interface ApiService {

    @POST("v2/movie/top250")
    Observable<ResponseParent<List<ResponseData>>> getData(@Body ResquestParent httpClient);

    @POST("userLogin")
    Observable<ResponseParent<DataLogin>> getLogin(@Query("username") String username,
                                                   @Query("password") String password);
}
