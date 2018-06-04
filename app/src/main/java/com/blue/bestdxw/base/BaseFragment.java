package com.blue.bestdxw.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *懒加载基类
 *@author BLUE
 *@time 2018/6/4 18:55
 */
public abstract class BaseFragment extends Fragment{
    /**
     * 当前Fragment是否可见
     */
    private boolean isVisible = false;
    /**
     * 是否与View建立起映射关系
     */
    private boolean isInitView = false;
    /**
     * 是否是第一次加载数据
     */
    private boolean isFirstLoad = true;

    private View convertView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        convertView = inflater.inflate(getLayoutId(), container, false);
        initView(convertView);
        isInitView = true;
        lazyLoadData();
        return convertView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 可见加载
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser) {
            isVisible = true;
            lazyLoadData();

        } else {
            isVisible = false;
            noVisibleLoad();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * 懒加载
     */
    private void lazyLoadData() {
        //不是第一次加载，不可见，绑定view未完成的时候不加载数据
        if (!isVisible || !isInitView) {
            return;
        }
        //第一次加载刷新数据
        if (isFirstLoad) {
            initData();
            isFirstLoad = false;
        } else {
            //不是第一次加载，可见
            refreshData();
        }
    }

    /**
     * 不可见时加载
     */
    private void noVisibleLoad() {
        if (!isFirstLoad) {
            noVisibleData();
        }
    }

    /**
     * 加载页面布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 让布局中的view与fragment中的变量建立起映射
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 加载要显示的数据
     */
    protected abstract void initData();

    protected abstract void refreshData();

    protected abstract void noVisibleData();
}
