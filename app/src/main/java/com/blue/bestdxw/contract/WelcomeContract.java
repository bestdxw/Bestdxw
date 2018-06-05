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
        void cancelCount(WelcomePresenter welcomePresenter);

        void loadGirlFromUrl(WelcomePresenter welcomePresenter);
    }

    interface View {
        void changeText(String s);
        void jump();

        void showGirl(String url);
    }

    interface Presenter {
        void changeText(String s);
        void onSuccess();
        void onStop();
        void onCancel();

        void showGirl(String url);
        void getGrilFromGank();
    }
}
