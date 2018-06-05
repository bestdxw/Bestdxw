package com.blue.bestdxw.base;

import android.support.v4.app.Fragment;

import com.blue.bestdxw.view.fragment.HomeFragment;
import com.blue.bestdxw.view.fragment.MineFragment;
import com.blue.bestdxw.view.fragment.ZoneFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * main fragment工厂
 * @author Blue
 * @date 2018/6/5 0005 22:41
 */
public class MainFactory {
    /**
     * 全部
     */
    private static final int TAB_HOME = 0;
    /**
     * 待处理
     */
    private static final int TAB_ZONE = 1;
    /**
     * 已完成
     */
    private static final int TAB_MINE = 2;

    private static Map<Integer, Fragment> mFragments = new HashMap<Integer, Fragment>();

    public static Fragment createFragment(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case TAB_HOME:
                    fragment = new HomeFragment();
                    break;
                case TAB_ZONE:
                    fragment = new ZoneFragment();
                    break;
                case TAB_MINE:
                    fragment = new MineFragment();
                    break;
                default:
                    break;
            }
            mFragments.put(position, fragment);
        }
        return fragment;
    }

    /**
     * 用于销毁activity时候，清除fragment缓存
     */
    public static void removeFragment() {
        mFragments.clear();
    }
}
