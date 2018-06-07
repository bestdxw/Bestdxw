package com.blue.bestdxw.view.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.blue.bestdxw.view.ShowGirlActivity;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
    private PhotoView photoView;
    private Info mInfo;
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

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        rvList = view.findViewById(R.id.rv_list);
        photoView = view.findViewById(R.id.photoView);
    }

    @Override
    protected void initData() {
        photoView.enable();


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
                intent.putExtra("img",girlList.get(position).getUrl());
                startActivity(intent);

//                mInfo = PhotoView.getImageViewInfo((ImageView) view);
//                photoView.setVisibility(View.VISIBLE);
//                photoView.animaFrom(mInfo);
            }
        });
//        photoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                photoView.animaTo(mInfo, new Runnable() {
//                    @Override
//                    public void run() {
//                        photoView.setVisibility(View.GONE);
//                    }
//                });
//            }
//        });
    }

    @Override
    protected void refreshData() {

    }

    @Override
    protected void noVisibleData() {

    }

    @Override
    public void setGirlList(List<GirlList.ResultsBean> mList) {
        if (null != girlList) {
            if (girlList.size() == CustomeCodeUtil.NUMBER) {
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
                    refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    girlList.addAll(mList);
                    adapter.addData(mList);
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
