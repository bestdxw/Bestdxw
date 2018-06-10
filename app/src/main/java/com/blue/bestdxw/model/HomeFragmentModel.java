package com.blue.bestdxw.model;

import com.blue.bestdxw.contract.HomeFragmentContract;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.presenter.HomeFragmentPresenter;
import com.blue.bestdxw.utils.SysCodeUtil;
import com.blue.bestdxw.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Administrator
 * @date: 2018/6/5 0005 23:42
 */
public class HomeFragmentModel implements HomeFragmentContract.Model {
    private Retrofit retrofit;
    @Override
    public void getGirlList(final HomeFragmentPresenter presenter, String pager) {

        //创建retrofit实例
        retrofit = new Retrofit
                .Builder()
                .baseUrl(SysCodeUtil.API_BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitUtil retrofitUtil = retrofit.create(RetrofitUtil.class);
        Call<GirlList> call = retrofitUtil.getGirlList(String.valueOf(SysCodeUtil.NUMBER),pager);
        call.enqueue(new Callback<GirlList>() {

            @Override
            public void onResponse(Call<GirlList> call, Response<GirlList> response) {
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        List<GirlList.ResultsBean> resultsBeen = response.body().getResults();
                        presenter.setGirlList(resultsBeen);
                        presenter.onSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<GirlList> call, Throwable t) {
                presenter.onFail();
            }
        });
    }
}
