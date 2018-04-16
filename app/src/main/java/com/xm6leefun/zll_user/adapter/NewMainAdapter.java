package com.xm6leefun.zll_user.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.main_code.about_new_main.NewMainDataList;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class NewMainAdapter extends BaseQuickAdapter<NewMainDataList, BaseViewHolder> {
    Context context;
    public NewMainAdapter(Context context,List<NewMainDataList> data) {
        super(R.layout.item_new_main, data);
         this.context=context;
    }
    @Override
    protected void convert(BaseViewHolder helper,NewMainDataList item) {

        ImageView ivbac =  helper.getView(R.id.item_new_iv_img);
        Glide.with(context).load(item.getImg()).into((ImageView) helper.getView(R.id.item_new_iv_img));
//        Log.e("getOrderNum",item.getOrderNum());
        helper.setText(R.id.item_new_tv_name, item.getTital());
//        helper.setText(R.id.item_his_tv_order_num, item.getOrderNum());
//        helper.setText(R.id.item_mfl_tv_clear_type, item.getClear_type());
    }
}
