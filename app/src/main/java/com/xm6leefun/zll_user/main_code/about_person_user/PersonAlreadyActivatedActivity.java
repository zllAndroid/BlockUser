package com.xm6leefun.zll_user.main_code.about_person_user;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataAlreadyJiHuo;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.adapter.Person_alAcAdapter;
import com.xm6leefun.zll_user.adapter.Person_test;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

//商户入驻
public class PersonAlreadyActivatedActivity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    @BindView(R.id.per_already_recyc)
    RecyclerView mRecyclerview;
    String id =null;
    int page = 1;
    boolean isHave = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_history_details);
//        ButterKnife.bind(this);
        includeTopTvTital.setText("已激活列表");
        id = (String) SPUtils.get(PersonAlreadyActivatedActivity.this, AppAllKey.User_ID, "");

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(PersonAlreadyActivatedActivity.this));
//
//        isHave = true;
//        initHttpData();
//        UpLoding();
//
//        initHttpData();
        initAdapters();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_per_already_activated;
    }
    private void initHttpData() {
        if (id==null)
        {
            id = (String) SPUtils.get(PersonAlreadyActivatedActivity.this, AppAllKey.User_ID, "");
        }
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(URL_GET.getActivateList(id,page+""), this);
    }
    DataAlreadyJiHuo.RecordBean record=null;
    List<DataAlreadyJiHuo.RecordBean.ActivateListBean> mList = new ArrayList<>();
        @Override
    public void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                if (!StrUtils.isEmpty(msg.obj.toString())&& HelpUtils.IsSucessRecord(msg.obj.toString())) {
                    try {
                        DataAlreadyJiHuo dataAlreadyJiHuo = JSON.parseObject(msg.obj.toString(), DataAlreadyJiHuo.class);
                        record = dataAlreadyJiHuo.getRecord();
                        List<DataAlreadyJiHuo.RecordBean.ActivateListBean> activateList = record.getActivateList();
                        if (activateList!=null)
                        {
                            if (isHave) {
                                mList.clear();
                                isHave = false;
                            }
                            mList.addAll(activateList);
                            initAdapter();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    Person_alAcAdapter homeAdapter = null;
    Person_test person_test = null;
    private void initAdapter() {
//        加线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(PersonAlreadyActivatedActivity.this,DividerItemDecoration.VERTICAL));
        homeAdapter = new Person_alAcAdapter(mList);
        mRecyclerview.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }
    public void UpLoding() {
        mRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    // 判断是否滑动到了最后一个Item，并且是向左滑动
                    if (lastItemPosition == (itemCount - 1)) {
                        // 加载更多
                        if ( record!=null) {
                            if ( record.getPageNo() < record.getTotalPage()) {
                                page = record.getPageNo();
                                page++;
                                initHttpData();
                            }else {
                                if (mList.size()!=0)
                                    ToastUtil.show("只有这么多了");
                            }
                        }
                    }
                }
            }
        });
    }
    private void initAdapters() {
        List<String> mList = new ArrayList<>();
        for (int i=0;i<=30;i++)
        {
            mList.add("i"+i);
        }
//        加线
        mRecyclerview.addItemDecoration(new DividerItemDecoration(PersonAlreadyActivatedActivity.this,DividerItemDecoration.VERTICAL));
        person_test = new Person_test(mList);
        mRecyclerview.setAdapter(person_test);
        person_test.notifyDataSetChanged();
    }

}
