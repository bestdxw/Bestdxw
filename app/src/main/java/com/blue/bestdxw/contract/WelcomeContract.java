package com.blue.bestdxw.contract;


import com.blue.bestdxw.presenter.WelcomePresenter;

/**
 * 集合
 * @author Blue
 * @date 2018/6/3 16:49
 */
public interface WelcomeContract {
    interface Model {
        void startCount(WelcomePresenter welcomePresenter);
        void endCount(WelcomePresenter welcomePresenter);
    }

    interface View {
        void changeText(String s);
        void onSuccess();
    }

    interface Presenter {
        void onSuccess();
        void onStop();
        void changeText(String s);
    }
}
