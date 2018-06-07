package com.blue.bestdxw.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blue.bestdxw.R;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.utils.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

/**
 * @author: Administrator
 * @date: 2018/6/6 0006 00:44
 */
public class HomeFragmentAdapter extends BaseQuickAdapter<GirlList.ResultsBean, BaseViewHolder> {
    public HomeFragmentAdapter(@Nullable List<GirlList.ResultsBean> data) {
        super(R.layout.item_home_list,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GirlList.ResultsBean item) {
        //重新设置高度
        helper.getView(R.id.item_img).getLayoutParams().height = (new Random().nextInt(150)+400);
        ImageUtil.loadRadiusViewFromeNet(mContext,item.getUrl(),R.mipmap.bg,(ImageView)helper.getView(R.id.item_img),20);
        helper.addOnClickListener(R.id.item_img);
    }
}
