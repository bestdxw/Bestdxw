package com.blue.bestdxw.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blue.bestdxw.R;
import com.blue.bestdxw.domain.GirlList;
import com.blue.bestdxw.utils.ImageUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

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
        ImageUtil.loadImageViewFromeNet(mContext,item.getUrl(),R.mipmap.bg,(ImageView)helper.getView(R.id.item_img));
    }
}
