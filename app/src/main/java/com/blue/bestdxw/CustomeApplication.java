package com.blue.bestdxw;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mob.MobSDK;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 *
 * @author Blue
 * @date 2018/6/2 0002 12:02
 */
public class CustomeApplication extends LitePalApplication {
    static {
        //刷新
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //是否开启越界回弹
                layout.setEnableOverScrollBounce(true);
                return new MaterialHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        LitePal.initialize(this);
    }
}
