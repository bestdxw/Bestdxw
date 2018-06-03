package com.blue.bestdxw.model;

import android.os.CountDownTimer;

import com.blue.bestdxw.contract.WelcomeContract;
import com.blue.bestdxw.presenter.WelcomePresenter;
import com.blue.customutil.util.LogUtil;

/**
 * 欢迎页数据处理
 * @author Blue
 * @date 2018/6/3 0003 11:14
 */
public class WelcomeModel implements WelcomeContract.Model {
    private CountDownTimer countDownTimer = null;
    @Override
    public void startCount(final WelcomePresenter welcomePresenter) {
        if (null == countDownTimer){
            countDownTimer = new CountDownTimer(3200,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    LogUtil.d("dxw",""+millisUntilFinished/1000);
                    welcomePresenter.changeText("跳过"+(millisUntilFinished/1000));
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
        if (null != countDownTimer){
            countDownTimer.cancel();
            welcomePresenter.onSuccess();
        }
    }
}
