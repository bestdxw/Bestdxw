package com.blue.bestdxw.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.blue.bestdxw.R;
import com.blue.bestdxw.base.BaseActivity;
import com.blue.bestdxw.utils.ImageUtil;
import com.bm.library.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 小姐姐顯示頁面
 *
 * @author Blue
 * @date 2018/6/7 0007 22:44
 */
public class ShowGirlActivity extends BaseActivity {
    @BindView(R.id.show_girl)
    PhotoView showGirl;
    private Intent intent;
    private String url = "";

    private Activity activity;
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

        ImageUtil.loadImageViewFromeNet(activity,url,R.mipmap.bg,showGirl);
        // 启用图片缩放功能
        showGirl.enable();
        // 获取/设置 最大缩放倍数
        showGirl.setMaxScale(2);
    }
}
