package com.xm6leefun.zll_user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.zll_user.R;

import java.util.List;

/**
 * Created by zll on 2017/11/10 .
 */

public   class Person_test extends BaseQuickAdapter<String, BaseViewHolder> {
    public Person_test(List<String> data) {
        super(R.layout.item_per_already_activated, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
