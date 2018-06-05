package com.blue.bestdxw.presenter;

import com.blue.bestdxw.contract.HomeFragmentContract;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.model.HomeFragmentModel;

import java.util.List;

/**
 * @author: Administrator
 * @date: 2018/6/5 0005 23:42
 */
public class HomeFragmentPresenter implements HomeFragmentContract.Presenter {
    private HomeFragmentContract.View mView;
    private HomeFragmentModel homeFragmentModel;

    public HomeFragmentPresenter(HomeFragmentContract.View view) {
        mView = view;
        homeFragmentModel = new HomeFragmentModel();
    }

    @Override
    public void setGirlList(List<GirlList.ResultsBean> girlList) {
        mView.setGirlList(girlList);
    }

    @Override
    public void getGirlList(String pager) {
        homeFragmentModel.getGirlList(this, pager);
    }

    @Override
    public void onSuccess() {
        mView.onSuccess();
    }

    @Override
    public void onFail() {
        mView.onFail();
    }
}
