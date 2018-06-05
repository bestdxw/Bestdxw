package com.blue.bestdxw.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blue.bestdxw.R;
import com.blue.bestdxw.base.BaseActivity;
import com.blue.bestdxw.base.MainFactory;
import com.blue.bestdxw.contract.MainContract;
import com.blue.bestdxw.customview.MainViewPager;
import com.blue.bestdxw.utils.CustomeCodeUtil;
import com.blue.customutil.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 *
 * @author Blue
 * @date 2018/6/2 12:03
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, MainContract.View {

    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottomNavigation;
    @BindView(R.id.view_pager)
    MainViewPager viewPager;
    /**
     * viewPager适配器
     */
    protected FragmentPagerAdapter mAdapter;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = MainActivity.this;
        bottomNavigation.setBarBackgroundColor(R.color.white);
        bottomNavigation.setActiveColor(R.color.pink);
        bottomNavigation.addItem(new BottomNavigationItem(R.mipmap.girl_false, R.string.girl)
                .setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.mipmap.zone_false, R.string.zone)
                        .setActiveColorResource(R.color.pink))
                .addItem(new BottomNavigationItem(R.mipmap.mine_false, R.string.mine)
                        .setActiveColorResource(R.color.pink))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigation.setTabSelectedListener(this);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MainFactory.createFragment(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
        viewPager.setAdapter(mAdapter);
        viewPager.setAnimationCacheEnabled(false);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0, false);
    }

    @Override
    public void onTabSelected(int position) {
        viewPager.setCurrentItem(position, false);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 声明一个long类型变量：用于存放上一点击“返回键”的时刻
     */
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtil.showToastNotRepeat(activity,"再按一次退出程序", CustomeCodeUtil.TOAST_TIME);
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
