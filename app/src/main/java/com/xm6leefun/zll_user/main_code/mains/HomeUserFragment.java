package com.xm6leefun.zll_user.main_code.mains;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.xm6leefun.model.DataHome;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.BaseFragment;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.about_new_main.SecuritySaylsActivity;
import com.xm6leefun.zll_user.main_code.home_order.ScanCodeActivity;
import com.xm6leefun.zll_user.utils.ImageCycleView;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeUserFragment extends BaseFragment {

    Unbinder unbinder;
    //    @BindView(R.id.main_view_top)
//    View mainViewTop;
//    @BindView(R.id.main_view_title)
//    TextView mainViewTitle;
//    @BindView(R.id.home_tv_top)
//    AppCompatTextView mTvTop;
//    @BindView(R.id.home_re_parent)
//    RelativeLayout mainRe;
    @BindView(R.id.weshop_banner)
    ImageCycleView weshopBanner;

    private String imageUrl1 = "http://p5xhsnnl3.bkt.clouddn.com/banner.png";
    private String imageUrl2 = "http://p1cpgl5ic.bkt.clouddn.com/lurongxie.png";
    private String imageUrl3 = "http://p1cpgl5ic.bkt.clouddn.com/test_baishen.png";
    private ArrayList<String> mImageUrl = new ArrayList<String>();

    public HomeUserFragment() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_user_home, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
//        initHttp();
        initDataBanner();
        return view;
    }

    private void initHttp() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(URL_GET.getIndex(), this);
    }

    @Override
    protected void onHandleMessage(Message msg) {
        super.onHandleMessage(msg);
        switch (msg.what) {
            case CommonParameter.HANDLE_MSG_SUCCESS:
                DataHome dataHome = JSON.parseObject(msg.obj.toString(), DataHome.class);
                DataHome.RecordBean recordBean = dataHome.getRecord();
                List<DataHome.RecordBean.IndexBrandListBean> indexBrandList = recordBean.getIndexBrandList();
                initDataBanner(indexBrandList);
                break;
        }
    }

    private void initDataBanner(List<DataHome.RecordBean.IndexBrandListBean> indexBrandList) {
        if (mImageUrl != null || !mImageUrl.equals("") || !mImageUrl.equals("{}") || !mImageUrl.equals("[]")) {
            mImageUrl.clear();
        }
        try {
            if (indexBrandList.size() > 0) {
                //        if (indexBrandList != null || !indexBrandList.toString().equals("") || !indexBrandList.toString().equals("{}") || !indexBrandList.toString().equals("[]")) {
                for (int i = 0; i < indexBrandList.size(); i++) {
                    mImageUrl.add(indexBrandList.get(i).getImage());
                }
            } else {
                mImageUrl.add(imageUrl1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mImageUrl.add(imageUrl1);
//        mImageUrl.add(imageUrl2);
//        mImageUrl.add(imageUrl3);
        try {
            weshopBanner.setImageResources(mImageUrl, new ImageCycleView.ImageCycleViewListener() {
                @Override
                public void onImageClick(int position, View imageView) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDataBanner() {
        if (mImageUrl != null || !mImageUrl.equals("") || !mImageUrl.equals("{}") || !mImageUrl.equals("[]")) {
            mImageUrl.clear();
        }
//        try {
//            if (indexBrandList.size() > 0) {
//    //        if (indexBrandList != null || !indexBrandList.toString().equals("") || !indexBrandList.toString().equals("{}") || !indexBrandList.toString().equals("[]")) {
//                for (int i = 0; i < indexBrandList.size(); i++) {
//                    mImageUrl.add(indexBrandList.get(i).getImage());
//                }
//            } else {
//                mImageUrl.add(imageUrl1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        mImageUrl.add(imageUrl1);
        mImageUrl.add(imageUrl2);
        mImageUrl.add(imageUrl3);
        try {
            weshopBanner.setImageResources(mImageUrl, new ImageCycleView.ImageCycleViewListener() {
                @Override
                public void onImageClick(int position, View imageView) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.uhome_iv_goods_show, R.id.uhome_iv_production, R.id.uhome_iv_security, R.id.uhome_iv_ercode, R.id.uhome_iv_nfc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.uhome_iv_goods_show:
                ToastUtil.show("该功能还在建设中，敬请期待");
                break;
            case R.id.uhome_iv_production:
                ToastUtil.show("该功能还在建设中，敬请期待");
                break;
            case R.id.uhome_iv_security:
                IntentUtils.JumpTo(SecuritySaylsActivity.class);
                break;
            case R.id.uhome_iv_ercode:
                IntentUtils.JumpTo(ScanCodeActivity.class);
                break;
            case R.id.uhome_iv_nfc:

                break;
        }
    }
}
