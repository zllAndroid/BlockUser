package com.xm6leefun.zll_user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.model.RealmTaskResule;
import com.xm6leefun.zll_user.R;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class RealmAdapter extends BaseQuickAdapter<RealmTaskResule, BaseViewHolder> {

    public RealmAdapter(List<RealmTaskResule> data) {
        super(R.layout.item_realm, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, RealmTaskResule item) {

//        ImageView ivbac =  helper.getView(R.id.item_iv_bank_bac);
//        Glide.with(AppManager.getAppManager().currentActivity()).load(item.get()).into((ImageView) helper.getView(R.id.item_bank_iv_logo));

        helper.setText(R.id.item_realm_tv_id, item.getQrcode());
        helper.setText(R.id.item_realm_tv_num, item.getNfcUid()+"");
        helper.addOnClickListener(R.id.item_realm_tv_play);
//        helper.setText(R.id.item_hm_tv_total, item.getSumNum());
//        helper.setText(R.id.item_hm_tv_wancheng, item.getFinishNum());
//        helper.setText(R.id.item_mfl_tv_clear_type, item.getClear_type());
//
//        helper.setText(R.id.item_mfl_tv_feilv, item.getRate());
//        helper.setText(R.id.item_mfl_tv_jiesuan, item.getHand_charge());
//        helper.setText(R.id.item_mfl_tv_danbi, item.getSingel_limit());
//        helper.setText(R.id.item_mfl_tv_dantian, item.getDay_limit());
//        helper.setText(R.id.item_company_tv_time, DateUtils.timesTwo(item.getCreateTime())+"");
//        GetImageByUrl getImageByUrl = new GetImageByUrl();
//        getImageByUrl.setImage((ImageView)helper.getView(R.id.item_company_iv_pic), item.getImage());
    }
}
