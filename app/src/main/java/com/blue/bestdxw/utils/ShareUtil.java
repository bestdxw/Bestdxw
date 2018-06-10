package com.blue.bestdxw.utils;

import android.content.Context;

import com.blue.bestdxw.R;

import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.mob.tools.utils.Strings.getString;

/**
 * @author: Administrator
 * @date: 2018/6/10 0010 12:16
 */
public class ShareUtil {
    public static void showShare(Context context,String content){
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(context);
    }
}
