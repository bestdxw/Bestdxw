package com.blue.bestdxw.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blue.bestdxw.R;
import com.blue.bestdxw.contract.MainContract;
import com.blue.bestdxw.view.fragment.HomeFragment;
import com.blue.bestdxw.view.fragment.MineFragment;
import com.blue.bestdxw.view.fragment.ZoneFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页
 *
 * @author Blue
 * @date 2018/6/2 12:03
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener, MainContract.View {

    @BindView(R.id.bottom_navigation)
    BottomNavigationBar bottomNavigation;
    @BindView(R.id.main_content)
    FrameLayout mineContent;

    private HomeFragment homeFragment;
    private ZoneFragment zoneFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content,homeFragment);
        transaction.commit();

        bottomNavigation.setTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if(null == homeFragment){
                    homeFragment = new HomeFragment();
                }
                transaction.replace(R.id.main_content,homeFragment);
            case 1:
                if(null == zoneFragment){
                    zoneFragment = new ZoneFragment();
                }
                transaction.replace(R.id.main_content,zoneFragment);
                break;
            case 2:
                if(null == mineFragment){
                    mineFragment = new MineFragment();
                }
                transaction.replace(R.id.main_content,mineFragment);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void addFragmentView() {

    }

    @Override
    public void quitSys() {
        System.exit(0);
    }
}
