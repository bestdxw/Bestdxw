package com.blue.bestdxw.contract;

import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.presenter.HomeFragmentPresenter;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2018/6/5 0005 23:42
 */
public interface HomeFragmentContract {
    interface Model {
        void getGirlList(HomeFragmentPresenter presenter,String pager);
    }

    interface View {
        void setGirlList(List<GirlList.ResultsBean> girlList);

        void onFail();
        void onSuccess();
    }

    interface Presenter {
        void setGirlList(List<GirlList.ResultsBean> girlList);
        void getGirlList(String pager);

        void onSuccess();
        void onFail();
    }
}
