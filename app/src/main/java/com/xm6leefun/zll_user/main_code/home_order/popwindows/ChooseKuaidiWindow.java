package com.xm6leefun.zll_user.main_code.home_order.popwindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xm6leefun.model.DataKuaidi;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.adapter.ChooseKuaidipopAdapter;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 物品选择
 */
public class ChooseKuaidiWindow extends PopupWindow {

    Context mContext;
    private LayoutInflater mInflater;
    private View mContentView;
    RecyclerView mRecyclerView;
    List<String> arrayList = new ArrayList<>();
    int heights;
    int height;
    int width;
    int widths;
//    传过来控件增加的高度以及减去的宽度
    public ChooseKuaidiWindow(Context context, int height, int width) {
        super(context);
        this.height=height;
        this.width=width;
       this.heights = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height,
                HelpUtils.activity.getResources().getDisplayMetrics());
       this.widths = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width,
                HelpUtils.activity.getResources().getDisplayMetrics());
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContentView = mInflater.inflate(R.layout.pop_choose_kuaidi, null);
        //设置View
        setContentView(mContentView);

        //设置宽与高
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        setHeight((MainActivity.screenHeight) / 4);
//        layoutParams.width = (LinearLayoutCompat.LayoutParams.MATCH_PARENT-width);
        setHeight(WindowManager.LayoutParams.MATCH_PARENT);

        /**
         * 设置进出动画
         */
//        setAnimationStyle(R.style.ChoosePopupWindow);

        /**
         * 设置背景只有设置了这个才可以点击外边和BACK消失
         */
        setBackgroundDrawable(new ColorDrawable());
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        /**
         * 设置可以获取集点
         */
        setFocusable(true);

        /**
         * 设置点击外边可以消失
         */
        setOutsideTouchable(true);

        /**
         *设置可以触摸
         */
        setTouchable(true);


        /**pop_lin_choose
         * 设置点击外部可以消失
         */
        mContentView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int top = mContentView.findViewById(R.id.pop_pjcp_top).getTop();
                int right = mContentView.findViewById(R.id.pop_pjcp_top).getRight();
//                int top =mContentView.getTop();
//                int pop_height = mContentView.getHeight();
//                int bottom =mContentView.getBottom();
//                int left =mContentView.getLeft();
//                int right =mContentView.getRight();
//                int y = (int) event.getY();
                float rawY = event.getRawY();
                float rawX = event.getRawX();

                int x= (int) event.getX();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (rawY < top) {
                        dismiss();
                    }
//                    if (y < (pop_height+top)) {
//                        dismiss();
//                    }
                    if (rawX > right) {
                        dismiss();
                    }
                }
//                int height = mContentView.findViewById(R.id.pop_pjcp_top).getTop();
//                int y = (int) event.getY();
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    if (y < height) {
//                        dismiss();
//                    }
//                }
                return true;
            }
        });
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                /**
                 * 判断是不是点击了外部
                 */
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    return true;
                }
                //不是点击外部
                return false;
            }
        });
        /**
         * 初始化View与监听器
         */
        initView();
    }
    private void initView() {
        TextView mTvBottom = mContentView.findViewById(R.id.pop_pjcp_tv_bottom);
//        LinearLayout mTvTop =mContentView.findViewById(R.id.pop_pjcp_top)
//        TextView mTvRight = mContentView.findViewById(R.id.pop_pjcp_tv_right);
        ViewGroup.LayoutParams layoutParams = mTvBottom.getLayoutParams();
        layoutParams.width = (LinearLayoutCompat.LayoutParams.MATCH_PARENT-widths);
        layoutParams.height = height;
        mTvBottom.setLayoutParams(layoutParams);
//        ViewGroup.LayoutParams layoutParamsright = mTvRight.getLayoutParams();
//        layoutParams.width = width;
//        layoutParams.height = LinearLayoutCompat.LayoutParams.MATCH_PARENT;
//        mTvRight.setLayoutParams(layoutParamsright);

        mRecyclerView = mContentView.findViewById(R.id.pop_pjcp_recyc);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,1));
        String id = (String) SPUtils.get(HelpUtils.activity, AppAllKey.User_ID, "");
//        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//        netWorkUtlis.setOnNetWork(URL_GET.getExpressList(id), new NetWorkUtlis.OnNetWork() {
//            @Override
//            public void onNetSuccess(String msg) {
//
//                DataKuaidi dataLeaExPeople = JSON.parseObject(msg, DataKuaidi.class);
//                List<DataKuaidi.RecordBean.ExpressBean> expressList = dataLeaExPeople.getRecord().getExpress();
//                initAdapter(expressList);
//            }
//        });
    }
    private void initAdapter(List<DataKuaidi.RecordBean.ExpressBean> expressList) {

        ChooseKuaidipopAdapter homeCompanyAdapter = new ChooseKuaidipopAdapter(expressList);
//        homeCompanyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        mRecyclerView.setAdapter(homeCompanyAdapter);
        homeCompanyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DataKuaidi.RecordBean.ExpressBean item = (DataKuaidi.RecordBean.ExpressBean)adapter.getItem(position);
                listener.ClickChooseWho(item);
            }
        });
    }

    public OnClickChooseWho listener = null; //事件回调接口

    public interface OnClickChooseWho {
        void ClickChooseWho(DataKuaidi.RecordBean.ExpressBean item);
    }

    //设置回调接口
    public void setOnClickChooseWho(OnClickChooseWho rockerListener) {
        listener = rockerListener;
    }
}