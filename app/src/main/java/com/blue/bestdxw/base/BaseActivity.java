package com.blue.bestdxw.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

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
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.out(BaseActivity.this);
    }

}
