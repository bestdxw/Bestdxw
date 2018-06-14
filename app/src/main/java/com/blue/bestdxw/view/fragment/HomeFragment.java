package com.blue.bestdxw.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blue.bestdxw.R;
import com.blue.bestdxw.adapter.HomeFragmentAdapter;
import com.blue.bestdxw.base.BaseFragment;
import com.blue.bestdxw.contract.HomeFragmentContract;
import com.blue.bestdxw.dao.Girls;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.presenter.HomeFragmentPresenter;
import com.blue.bestdxw.utils.SysCodeUtil;
import com.blue.bestdxw.view.ShowGirlActivity;
import com.blue.customutil.util.ToastUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页碎片
 *
 * @author BLUE
 * @time 2018/6/4 18:57
 */
public class HomeFragment extends BaseFragment implements HomeFragmentContract.View, View.OnClickListener {
    private RecyclerView rvList;
    private SmartRefreshLayout refreshLayout;
    /**
     * 当前页
     */
    private int currentPageNo = 1;

    private HomeFragmentAdapter adapter;

    private HomeFragmentPresenter homeFragmentPresenter;

    private List<GirlList.ResultsBean> girlList = new ArrayList<>();

    private Intent intent;

    private FloatingActionButton fab;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        rvList = view.findViewById(R.id.rv_list);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        homeFragmentPresenter = new HomeFragmentPresenter(this);
        refreshLayout.autoRefresh();
        rvList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvList.setItemAnimator(new DefaultItemAnimator());
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
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                intent = new Intent(getActivity(), ShowGirlActivity.class);
                intent.putExtra("img", girlList.get(position).getUrl());
                startActivity(intent);
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
    public void setGirlList(List<GirlList.ResultsBean> mList) {
        if (null != mList) {
            if (mList.size() == SysCodeUtil.NUMBER) {
                if (currentPageNo == 1) {
                    girlList = new ArrayList<>();
                    girlList = mList;
                    adapter.setNewData(mList);
                } else {
                    girlList.addAll(mList);
                    adapter.addData(mList);
                }
            } else {
                if (currentPageNo == 1) {
                    girlList = new ArrayList<>();
                    girlList = mList;
                    adapter.setNewData(mList);
                } else {
                    girlList.addAll(mList);
                    adapter.addData(mList);
                }
                refreshLayout.finishLoadMoreWithNoMoreData();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                //滑动到指定位置
                rvList.smoothScrollToPosition(0);
                ToastUtil.showToastNotRepeat(getActivity()
                        , "长度："+LitePal.findAll(Girls.class).size()+"第一条数据："+LitePal.findAll(Girls.class).get(0).getImageUrl()
                        ,SysCodeUtil.TOAST_TIME);
                break;
            default:
                break;
        }
    }
}
