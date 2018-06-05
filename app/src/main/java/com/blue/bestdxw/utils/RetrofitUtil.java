package com.blue.bestdxw.utils;

import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.domain.WelcomeGirl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: Administrator
 * @date: 2018/6/4 23:43
 */
public interface RetrofitUtil {
    @GET("福利/{number}")
    Call<WelcomeGirl> getGirl(@Path("number") String number);

    @GET("福利/{number}/{pager}")
    Call<GirlList> getGirlList(@Path("number") String number, @Path("pager") String pager);


}
