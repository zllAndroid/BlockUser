package com.xm6leefun.zll_user.main_code.about_new_main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataHistoryDetails;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.StrUtils;

import java.util.List;

import butterknife.BindView;

//防伪说明
public class SecuritySaylsActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
//    @BindView(R.id.his_details_recyc)
//    RecyclerView mRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_details);
//        ButterKnife.bind(this);
        includeTopTvTital.setText("防伪说明");
//        initHttpData();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_security;
    }
    private void initHttpData() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            String taskId = intent.getStringExtra("taskId");
            String id = (String) SPUtils.get(SecuritySaylsActivity.this, AppAllKey.User_ID, "");
//            NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//            netWorkUtlis.setOnNetWork(URL_GET.getHistoryTaskData(id,taskId), this);
        }
    }
    @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())&& HelpUtils.IsSucessRecord(msg.obj.toString())) {
//                    try {
                    DataHistoryDetails dataHistoryDetails = JSON.parseObject(msg.obj.toString(), DataHistoryDetails.class);
                    DataHistoryDetails.RecordBean.OrderExpressBean orderExpress = dataHistoryDetails.getRecord().getOrderExpress();
                    if (orderExpress!=null)
                    {
                        List<DataHistoryDetails.RecordBean.OrderExpressBean.ExpressDataBean> datas = orderExpress.getExpressData();
                    }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
                break;
        }
    }
}
