package com.xm6leefun.zll_user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.DataKuaidi;
import com.xm6leefun.zll_user.R;

import java.util.List;

/**
 * Created by zll on 2017/10/10 .
 */

public   class ChooseKuaidipopAdapter extends BaseQuickAdapter<DataKuaidi.RecordBean.ExpressBean, BaseViewHolder> {

    public ChooseKuaidipopAdapter(List<DataKuaidi.RecordBean.ExpressBean> data) {
        super(R.layout.item_pop_choose_kuaidi, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DataKuaidi.RecordBean.ExpressBean item) {
             helper.setText(R.id.item_pop_tv_people,item.getName());
//        helper.setText(R.id.item_company_tv_tital, item.getTitle());
////        helper.setText(R.id.item_company_tv_time, DateUtils.timesTwo(item.getCreateTime())+"");
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage((ImageView)helper.getView(R.id.item_company_iv_pic), item.getImage());
    }
}
