package com.blue.bestdxw.contract;

/**
 * Created blue  by Blue on 2018/6/4.
 */
public interface MainContract {
    interface Model {
        void judgeBack();
        void createFragmentView();
    }

    interface View {
        void addFragmentView();
        void quitSys();
    }

    interface Presenter {
        void FragmentViewSuccess();
    }
}
