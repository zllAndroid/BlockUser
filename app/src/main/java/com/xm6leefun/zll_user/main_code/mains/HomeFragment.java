package com.xm6leefun.zll_user.main_code.mains;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.donkingliang.banner.CustomBanner;
import com.xm6leefun.model.DataHome;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.BaseFragment;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.about_new_main.SecuritySaylsActivity;
import com.xm6leefun.zll_user.main_code.home_order.ScanCodeActivity;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.NoDoubleClickUtils;
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
public class HomeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.banner)
    CustomBanner<String> banner;
    ArrayList<String> mImageUrl = new ArrayList<>();
    String url1 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3778456200,3076998411&fm=23&gp=0.jpg";
//    @BindView(R.id.main_view_top)
//    View mainViewTop;
//    @BindView(R.id.main_view_title)
//    TextView mainViewTitle;
//    @BindView(R.id.home_tv_top)
//    AppCompatTextView mTvTop;
//    @BindView(R.id.home_re_parent)
//    RelativeLayout mainRe;

    public HomeFragment() {
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home_security, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
//
//        mImageUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3778456200,3076998411&fm=23&gp=0.jpg");
//        mImageUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3535338527,4000198595&fm=23&gp=0.jpg");
//        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1017904219,2460650030&fm=23&gp=0.jpg");
//        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2863927798,667335035&fm=23&gp=0.jpg");
//        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3885596348,1190704919&fm=23&gp=0.jpg");
//        mImageUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1597254274,1405139366&fm=23&gp=0.jpg");
        initHttp();
        return view;
    }

    private void initHttp() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(CommonParameter.LodingNormal,URL_GET.getIndex(), this);
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
                for (int i = 0; i < indexBrandList.size(); i++) {
                    mImageUrl.add(indexBrandList.get(i).getImage());
                }
            } else {
                mImageUrl.add(url1);
                mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3885596348,1190704919&fm=23&gp=0.jpg");
                mImageUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1597254274,1405139366&fm=23&gp=0.jpg");
            }
            setBean(mImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //设置普通指示器
    private void setBean(final ArrayList beans) {
        banner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Glide.with(context).load(entity).into((ImageView) view);
            }
        }, beans)
                //设置指示器为数字指示器
                .setIndicatorStyle(CustomBanner.IndicatorStyle.NUMBER)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
                //设置指示器的方向
                .setIndicatorGravity(CustomBanner.IndicatorGravity.RIGHT)
                //设置指示器的指示点间隔
                .setIndicatorInterval(20)
                .setOnPageClickListener(new CustomBanner.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position, Object o) {
//                        ToastUtil.show("点击了"+position);
                    }
                })
//                设置自动翻页
                .startTurning(2500);
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
//                产品说明
                if (NoDoubleClickUtils.isDoubleClick())
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
