package com.xm6leefun.zll_user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataAlreadyJiHuo;
import com.xm6leefun.zll_user.R;

import java.util.List;

/**
 * Created by zll on 2017/11/10 .
 */

public   class Person_alAcAdapter extends BaseQuickAdapter<DataAlreadyJiHuo.RecordBean.ActivateListBean, BaseViewHolder> {
    public Person_alAcAdapter(List<DataAlreadyJiHuo.RecordBean.ActivateListBean> data) {
        super(R.layout.item_per_already_activated, data);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);

    }

    @Override
    protected void convert(BaseViewHolder helper, DataAlreadyJiHuo.RecordBean.ActivateListBean item) {

    }
}
