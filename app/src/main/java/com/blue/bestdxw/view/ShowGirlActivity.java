package com.blue.bestdxw.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.blue.bestdxw.R;
import com.blue.bestdxw.base.BaseActivity;
import com.blue.bestdxw.utils.ImageUtil;
import com.blue.bestdxw.utils.ShareUtil;
import com.bm.library.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 小姐姐加载页面
 *
 * @author Blue
 * @date 2018/6/9 0009 20:11
 */
public class ShowGirlActivity extends BaseActivity {
    @BindView(R.id.show_girl)
    PhotoView showGirl;
    @BindView(R.id.btn_share)
    ImageView btnShare;
    @BindView(R.id.btn_love)
    LottieAnimationView btnLove;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Intent intent;
    private String url = "";

    private Activity activity;

    private boolean isLove = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_girl);
        ButterKnife.bind(this);
        activity = ShowGirlActivity.this;
        intent = getIntent();
        if (null != intent) {
            url = intent.getStringExtra("img");
        }
        ImageUtil.loadImageViewFromeNet(activity, url, R.mipmap.bg, showGirl);
        // 启用图片缩放功能
        showGirl.enable();
        // 获取/设置 最大缩放倍数
        showGirl.setMaxScale(2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.show_girl, R.id.btn_share, R.id.btn_love})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_girl:
                finish();
                break;
            case R.id.btn_share:
                ShareUtil.showShare(activity,"好图");
                break;
            case R.id.btn_love:
                if (isLove) {
                    isLove = false;
                    btnLove.cancelAnimation();
                } else {
                    isLove = true;
                    btnLove.playAnimation();
                }
                break;
            default:
                break;
        }
    }
}
