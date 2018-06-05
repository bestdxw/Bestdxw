package com.blue.bestdxw.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue.bestdxw.R;
import com.blue.bestdxw.base.BaseActivity;
import com.blue.bestdxw.contract.WelcomeContract;
import com.blue.bestdxw.presenter.WelcomePresenter;
import com.blue.bestdxw.utils.ImageUtil;
import com.blue.customutil.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页
 *
 * @author Blue
 * @date 2018/6/2 12:01
 */
public class WelcomeActivity extends BaseActivity implements WelcomeContract.View {

    @BindView(R.id.welcome_time)
    TextView welcomeTime;
    @BindView(R.id.welcome_img)
    ImageView welcomeImg;

    private Intent intent;
    private Activity activity;
    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        activity = WelcomeActivity.this;
        welcomePresenter = new WelcomePresenter(this);
        welcomePresenter.getGrilFromGank();
        welcomePresenter.startTime();
    }

    @OnClick(R.id.welcome_time)
    public void onViewClicked() {
        welcomePresenter.onStop();
    }

    @Override
    public void changeText(String s) {
        welcomeTime.setText(s);
    }

    @Override
    public void jump() {
        finish();
        intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        ActivityUtil.in(activity);
    }

    @Override
    public void showGirl(String url) {
        ImageUtil.loadImageViewFromeNet(activity,url,R.mipmap.bg,welcomeImg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        welcomePresenter.onCancel();
    }
}
