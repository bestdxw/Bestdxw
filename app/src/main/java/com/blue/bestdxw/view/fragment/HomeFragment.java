package com.blue.bestdxw.view.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blue.bestdxw.R;
import com.blue.bestdxw.adapter.HomeFragmentAdapter;
import com.blue.bestdxw.base.BaseFragment;
import com.blue.bestdxw.contract.HomeFragmentContract;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.presenter.HomeFragmentPresenter;
import com.blue.bestdxw.utils.CustomeCodeUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页碎片
 *
 * @author BLUE
 * @time 2018/6/4 18:57
 */
public class HomeFragment extends BaseFragment implements HomeFragmentContract.View {
    RecyclerView rvList;
    SmartRefreshLayout refreshLayout;
    /**
     * 当前页
     */
    private int currentPageNo = 1;

    private HomeFragmentAdapter adapter;

    private HomeFragmentPresenter homeFragmentPresenter;

    private List<GirlList.ResultsBean> girlList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        rvList = view.findViewById(R.id.rv_list);
    }

    @Override
    protected void initData() {
        homeFragmentPresenter = new HomeFragmentPresenter(this);
        refreshLayout.autoRefresh();
        rvList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        rvList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new HomeFragmentAdapter(girlList);
        rvList.setAdapter(adapter);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currentPageNo = 1;
                homeFragmentPresenter.getGirlList(String.valueOf(currentPageNo));
                refreshLayout.setNoMoreData(false);
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                currentPageNo++;
                homeFragmentPresenter.getGirlList(String.valueOf(currentPageNo));
            }

        });
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void noVisibleData() {

    }

    @Override
    public void setGirlList(List<GirlList.ResultsBean> girlList) {
        if (null != girlList) {
            if (girlList.size() == CustomeCodeUtil.NUMBER) {
                if (currentPageNo == 1) {
                    adapter.setNewData(girlList);
                } else {
                    adapter.addData(girlList);
                }
            } else {
                if (currentPageNo == 1) {
                    adapter.setNewData(girlList);
                    refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    adapter.addData(girlList);
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        } else {
            refreshLayout.finishLoadMoreWithNoMoreData();
        }
    }


    @Override
    public void onSuccess() {
        refreshLayout.finishRefresh(100);
        refreshLayout.finishLoadMore(100);
    }

    @Override
    public void onFail() {
        refreshLayout.finishRefresh(100);
        refreshLayout.finishLoadMore(100);
    }
}
