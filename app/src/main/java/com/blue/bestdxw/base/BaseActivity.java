package com.blue.bestdxw.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blue.customutil.util.ActivityUtil;

/**
 * 基类
 * @author Blue
 * @date 2018/6/2 12:41
 */
public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.out(BaseActivity.this);
    }

}
