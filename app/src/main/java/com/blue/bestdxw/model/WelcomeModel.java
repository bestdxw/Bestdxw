package com.blue.bestdxw.model;

import android.os.CountDownTimer;

import com.blue.bestdxw.contract.WelcomeContract;
import com.blue.bestdxw.domain.WelcomeGirl;
import com.blue.bestdxw.presenter.WelcomePresenter;
import com.blue.bestdxw.utils.CustomeCodeUtil;
import com.blue.bestdxw.utils.RetrofitUtil;
import com.blue.customutil.util.LogUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 欢迎页数据处理
 *
 * @author Blue
 * @date 2018/6/3 0003 11:14
 */
public class WelcomeModel implements WelcomeContract.Model {
    private CountDownTimer countDownTimer = null;
    private Retrofit retrofit;

    @Override
    public void startCount(final WelcomePresenter welcomePresenter) {
        if (null == countDownTimer) {
            countDownTimer = new CountDownTimer(3200, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    LogUtil.d("dxw", "" + millisUntilFinished / 1000);
                    welcomePresenter.changeText("跳过" + (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    welcomePresenter.onSuccess();
                }
            };
        }
        countDownTimer.start();
    }

    @Override
    public void endCount(WelcomePresenter welcomePresenter) {
        if (null != countDownTimer) {
            countDownTimer.cancel();
            welcomePresenter.onSuccess();
        }
    }

    @Override
    public void loadGirlFromUrl(final WelcomePresenter welcomePresenter) {
        //创建retrofit实例
        retrofit = new Retrofit
                .Builder()
                .baseUrl(CustomeCodeUtil.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitUtil retrofitUtil = retrofit.create(RetrofitUtil.class);
        Call<WelcomeGirl> call = retrofitUtil.getGirl("1");
        call.enqueue(new Callback<WelcomeGirl>() {

            @Override
            public void onResponse(Call<WelcomeGirl> call, Response<WelcomeGirl> response) {
                if (response.isSuccessful()) {
                    List<WelcomeGirl.ResultsBean> resultsBeen = response.body().getResults();
                    welcomePresenter.showGirl(resultsBeen.get(0).getUrl());
                }

            }

            @Override
            public void onFailure(Call<WelcomeGirl> call, Throwable t) {
                // TODO: 2018/6/5 添加无网络和异常时候的回调
            }
        });
    }
}
