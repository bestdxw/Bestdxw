package com.blue.bestdxw.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.blue.bestdxw.R;
import com.blue.bestdxw.contract.MainContract;

/**
 * 主页
 * @author Blue
 * @date 2018/6/2 12:03
 */
public class MainActivity extends AppCompatActivity implements MainContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void addFragmentView() {

    }

    @Override
    public void quitSys() {
        System.exit(0);
    }
}
