package com.xm6leefun.zll_user.main_code.mains;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.donkingliang.banner.CustomBanner;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.xm6leefun.model.DataHome;
import com.xm6leefun.model.DataMineUserInfo;
import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.adapter.NewMainAdapter;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.AppManager;
import com.xm6leefun.zll_user.base.BaseActivity;
import com.xm6leefun.zll_user.base.CommonParameter;
import com.xm6leefun.zll_user.base.MyApplication;
import com.xm6leefun.zll_user.base.URL_GET;
import com.xm6leefun.zll_user.main_code.about_init.isLogin;
import com.xm6leefun.zll_user.main_code.about_login.WXLoginActivity;
import com.xm6leefun.zll_user.main_code.about_new_main.NewMainDataList;
import com.xm6leefun.zll_user.main_code.about_new_main.SecuritySaylsActivity;
import com.xm6leefun.zll_user.main_code.about_person_user.PersonAlreadyActivatedActivity;
import com.xm6leefun.zll_user.main_code.home_order.H5TestActivity;
import com.xm6leefun.zll_user.main_code.home_order.ScanCodeActivity;
import com.xm6leefun.zll_user.ui_custom.CusScrollView;
import com.xm6leefun.zll_user.ui_custom.about_scroll.MoveLayout;
import com.xm6leefun.zll_user.utils.DataCleanManager;
import com.xm6leefun.zll_user.utils.DialogUtils;
import com.xm6leefun.zll_user.utils.HelpUtils;
import com.xm6leefun.zll_user.utils.IntentUtils;
import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.NoDoubleClickUtils;
import com.xm6leefun.zll_user.utils.SPUtils;
import com.xm6leefun.zll_user.utils.ScreenUtils;
import com.xm6leefun.zll_user.utils.StrUtils;
import com.xm6leefun.zll_user.utils.Tip;
import com.xm6leefun.zll_user.utils.ToastUtil;
import com.xm6leefun.zll_user.utils.VersionCheckUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class NewMainActivity extends BaseActivity {
    public  static  final  String SHOW_XL = "xl";
    public  static  final  String SHOW_SHOP = "shop";
    public  static  final  String SHOW_MONITOR = "monitor";
    public  static  final  String SHOW_FW = "fw";
    public  static  final  String SHOW_QKL = "qkl";
    public  static  final  String SHOW_CODE = "code";
    public  static  final  String SHOW_NFC = "nfc";
    @BindView(R.id.new_banner)
    CustomBanner<String> banner;

    @BindView(R.id.new_recyc_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.new_myscroll)
    CusScrollView mCusScrollView;

    @BindView(R.id.new_iv_top_logo)
    ImageView newIvTopLogo;
    @BindView(R.id.new_main_header_iv_hua)
    ImageView newIvHeqderHua;
    //    @BindView(R.id.new_main_header_relative_top)
//    RelativeLayout mRelativeTop;
    //    @BindView(R.id.sys_top_margin25)
//    TextView mTopState;
    @BindView(R.id.new_tv_top_name)
    TextView newTvTopName;
    @BindView(R.id.new_move)
    MoveLayout newMove;
    @BindView(R.id.new_iv_top_person)
    ImageView newIvTopPerson;
    @BindView(R.id.new_main_lin_top)
    LinearLayout mLinTop;
    @BindView(R.id.new_lin_top)
    LinearLayout mLinTitleTop;
    boolean isCaChe;
    //    @BindView(R.id.new_main_header_lin)
//    LinearLayout mLinearLayout;
    private Drawer resultAppended = null;
    ArrayList<String> mImageUrl = new ArrayList<>();
    ArrayList<NewMainDataList> mData = new ArrayList<>();
    String url1 = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3778456200,3076998411&fm=23&gp=0.jpg";
    Bundle savedInstanceState = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            NewMainActivityPermissionsDispatcher.needperWithCheck(this);
        isCaChe = isLogin.initCaChe();
    }
    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_new_main;
    }
    @Override
    protected void initBaseView() {
        ButterKnife.bind(this);
        initData();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(NewMainActivity.this,2));
        initScroll();
//        initHttp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCaChe)
            initHttpData();
    }

    private void initHttpData() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(CommonParameter.LodingNormal, URL_GET.getUserData(URL_GET.USER_ID), new NetWorkUtlis.OnNetWork() {
            @Override
            public void onNetSuccess(String result) {
//                String s = HelpUtils.HttpIsSucess(result);
                DataMineUserInfo dataMineUserInfo = JSON.parseObject(result, DataMineUserInfo.class);
                DataMineUserInfo.RecordBean record = dataMineUserInfo.getRecord();
                DataMineUserInfo.RecordBean.UserInfoBean userInfo = record.getUserInfo();
                if (userInfo!=null)
                {
                    try {
                        if (header!=null) {
                            if (!StrUtils.isEmpty(userInfo.getHeadPortrait())) {
                                ImageView mIvHead = (ImageView) header.findViewById(R.id.head_iv_img);
                                Glide.with(AppManager.getAppManager().currentActivity()).load(userInfo.getHeadPortrait()).
                                        bitmapTransform(new CropCircleTransformation(AppManager.getAppManager().currentActivity())).crossFade(1000).into(mIvHead);
                            }
                            if (!StrUtils.isEmpty(userInfo.getNickName())) {
                                TextView mTvName = (TextView) header.findViewById(R.id.head_tv_name);
                                mTvName.setText(userInfo.getNickName());
                            }
                            if (!StrUtils.isEmpty(userInfo.getMobile())) {
                                TextView mTvPhone = (TextView) header.findViewById(R.id.head_tv_phone);
                                mTvPhone.setText(userInfo.getMobile());
                            }
                            if (!StrUtils.isEmpty(userInfo.getCoinSum())) {
                                resultAppended.updateBadge(1, new StringHolder(userInfo.getCoinSum() + ""));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void needper() {
        ToastUtil.show("权限通过执行");
    }
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void need() {
        IntentUtils.JumpTo(ScanCodeActivity.class);
//        ToastUtil.show("权限通过执行");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        NewMainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    PermissionRequest requests =null;
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onshow(final PermissionRequest request) {
        requests=request;
        DialogUtils.showDialogOne("信任是美好的开始，请授权相机和打电话权限，让我们更好的为您服务", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                request.proceed();
            }
        });
    }
    //拒绝
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onper() {
        DialogUtils.showDialogOne("应用需要使用相机和打电话权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
//                        AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivityForResult(intent,0);
                    startActivity(intent);
                }
            }
        });
    }
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE})
    void onnever() {
//        ToastUtil.show("OnNeverAskAgain");
        DialogUtils.showDialogOne("应用需要使用相机和打电话权限，否则不能正常使用", new DialogUtils.OnClickSureListener() {
            @Override
            public void onClickSure() {
                if (requests!=null)
                    requests.proceed();
                else {
//                    AppStartActivityPermissionsDispatcher.needperWithCheck(AppStartActivity.this);
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + getPackageName()));
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivityForResult(intent,0);
                    startActivity(intent);
                }
            }
        });
    }
    private void initHttp() {
        NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
        netWorkUtlis.setOnNetWork(CommonParameter.LodingNormal,URL_GET.getIndex(), this);
    }
    @Override
    public void onHandleMessage(Message msg) {
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
    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //继承了Activity的onTouchEvent方法，直接监听点击事件
//        if(event.getAction() == MotionEvent.ACTION_DOWN) {
//            //当手指按下的时候
//            x1 = event.getX();
//            y1 = event.getY();
//        }
//        if(event.getAction() == MotionEvent.ACTION_UP) {
//            //当手指离开的时候
//            x2 = event.getX();
//            y2 = event.getY();
//            if(y1 - y2 > 50) {
//                ToastUtil.show("向上滑");
//            } else if(y2 - y1 > 50) {
//                ToastUtil.show("向下滑");
//            } else if(x1 - x2 > 50) {
//                ToastUtil.show("向左滑");
//            } else if(x2 - x1 > 50) {
//                ToastUtil.show("向右滑");
//            }
//        }
//        return super.onTouchEvent(event);
//    }

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
    private void initScroll() {
        mCusScrollView.setOnScrollListener(new CusScrollView.OnScrollListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onScroll(final  int scrollY) {
                mCusScrollView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                x1 = event.getX();
                                y1 = event.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
//                                setScroll(scrollY,false);
                                break;
                            case MotionEvent.ACTION_UP:
//                                x2 = event.getX();
//                                y2 = event.getY();
//                                if(y1 - y2 > 50) {
////                                    ToastUtil.show("向上滑");
//                                    setScroll(ScreenUtils.getScreenHeight(NewMainActivity.this),true);
//                                    break;
//                                }
//                                else if(y2 - y1 > 50) {
//                                    ToastUtil.show("向下滑");
//                                } else if(x1 - x2 > 50) {
//                                    ToastUtil.show("向左滑");
//                                } else if(x2 - x1 > 50) {
//                                    ToastUtil.show("向右滑");
//                                }

                                if (scrollY>0&&newMove.getVisibility()==View.VISIBLE)
                                    setScroll(scrollY,false);
                                break;
                        }
                        return false;
                    }

                });

            }
        });
    }
    public  void setScroll(@NonNull  int scrollY,boolean isClick) {
        Log.e("newMove",  "newMove="+newMove.getHeight());
        int scroll = 0;
        int scrollStart = 0;
        if (scrollY>((ScreenUtils.getScreenHeight(NewMainActivity.this)/4)*1))
        {
            scroll = ScreenUtils.getScreenHeight(NewMainActivity.this);
            scrollStart = scrollY;
        }else
        {
            scroll =-scrollY;
        }
        if (isClick) {
            scrollStart = 0;
            scroll = ScreenUtils.getScreenHeight(NewMainActivity.this);
        }
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(scrollStart,scroll);
        valueAnimator.setDuration(800);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCusScrollView.scrollTo(0,(Integer)animation.getAnimatedValue());
                if ((Integer)animation.getAnimatedValue()==ScreenUtils.getScreenHeight(NewMainActivity.this))
                {
                    newMove.setVisibility(View.GONE);
                    mCusScrollView.scrollTo(0,0);
                    try {
                        initDrawer(savedInstanceState);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Animation mShowAction = AnimationUtils.loadAnimation(NewMainActivity.this, R.anim.alpha_in);
                    mLinTitleTop.setVisibility(View.VISIBLE);
                    mShowAction.setDuration(200);
                    mLinTitleTop.startAnimation(mShowAction);
                }
            }
        });
        valueAnimator.start();
    }
    //public void wxLoginResult(){
// getMove().setVisibility(View.GONE);
//        mLinTitleTop.setVisibility(View.VISIBLE);
//    }
//    public MoveLayout getMove(){
//        return newMove;
//    }
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
                .startTurning(2000);
    }
    private void initAdapter() {
        NewMainAdapter newMainAdapter = new NewMainAdapter(NewMainActivity.this, mData);
        mRecyclerView.setAdapter(newMainAdapter);
        newMainAdapter.setOnItemClickListener(adapterClick);
    }
    /**
     * 布局点击事件
     */
    BaseQuickAdapter.OnItemClickListener adapterClick =  new BaseQuickAdapter.OnItemClickListener()
    {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            NewMainDataList item = (NewMainDataList)adapter.getItem(position);
            String type = item.getType();
            switch (type)
            {
                case SHOW_XL: ToastUtil.show("该功能还在建设中，敬请期待");
                    break;
                case SHOW_MONITOR: ToastUtil.show("该功能还在建设中，敬请期待");
                    break;
                case SHOW_FW:
                    if (NoDoubleClickUtils.isDoubleClick())
                        IntentUtils.JumpTo(SecuritySaylsActivity.class);
                    break;
                case SHOW_QKL:
//                    ToastUtil.show("该功能还在建设中，敬请期待");
                    if (!(isLogin.initCaChe()))
                        IntentUtils.JumpTo(WXLoginActivity.class);
                    else {
                        ToastUtil.show("已经微信登录");
                    }
                    break;
                case SHOW_CODE:

                    if (NoDoubleClickUtils.isDoubleClick())
                    {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            NewMainActivityPermissionsDispatcher.needWithCheck(NewMainActivity.this);
                        else
                            IntentUtils.JumpTo(ScanCodeActivity.class);
                    }
                    break;
                case SHOW_NFC:

                    IntentUtils.JumpTo(H5TestActivity.class);
//                    StringUtils.substring("dskabcee", 3);
//                    if ()
//                    NetWorkUtlis netWorkUtlis = new NetWorkUtlis();
//                    netWorkUtlis.setOnNetWork(URL_GET.ttt(), new NetWorkUtlis.OnNetWork() {
//                        @Override
//                        public void onNetSuccess(String result) {
//                        }
//                    });
//                    ToastUtil.show("该功能还在建设中，敬请期待");
                    break;
                case SHOW_SHOP: ToastUtil.show("该功能还在建设中，敬请期待");
                    break;
                default:ToastUtil.show("该功能还在建设中，敬请期待");
                    break;
            }
        }
    };


    /**
     * 初始化数据
     */
    private void initData() {
        ViewGroup.LayoutParams layoutParams = newMove.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenHeight(NewMainActivity.this);
//        layoutParams.height = ScreenUtils.getScreenHeight(NewMainActivity.this)-ScreenUtils.getStatusHeight(NewMainActivity.this);
        newMove.setLayoutParams(layoutParams);
//        mLinearLayout.setLayoutParams(layoutParams);
//        设置gif
        Glide.with(NewMainActivity.this).load(R.raw.main_hua).into((ImageView) newIvHeqderHua);

        NewMainDataList newMainDataList1 = new NewMainDataList();
        newMainDataList1.setImg(R.drawable.new_xianlaing);
        newMainDataList1.setTital("限量产品展示");
        newMainDataList1.setType(SHOW_XL);

        NewMainDataList newMainDataList2 = new NewMainDataList();
        newMainDataList2.setImg(R.drawable.new_shengchang);
        newMainDataList2.setTital("生产监控");
        newMainDataList2.setType(SHOW_MONITOR);

        NewMainDataList newMainDataList3 = new NewMainDataList();
        newMainDataList3.setImg(R.drawable.new_shuoming);
        newMainDataList3.setTital("防伪说明");
        newMainDataList3.setType(SHOW_FW);

        NewMainDataList newMainDataList4 = new NewMainDataList();
        newMainDataList4.setImg(R.drawable.new_liulanqi);
        newMainDataList4.setTital("区块链浏览器");
        newMainDataList4.setType(SHOW_QKL);

        NewMainDataList newMainDataList5 = new NewMainDataList();
        newMainDataList5.setImg(R.drawable.new_erweima);
        newMainDataList5.setTital("二维码溯源");
        newMainDataList5.setType(SHOW_CODE);

        NewMainDataList newMainDataList6 = new NewMainDataList();
        newMainDataList6.setImg(R.drawable.new_nfc);
        newMainDataList6.setTital("NFC鉴伪");
        newMainDataList6.setType(SHOW_NFC);

        NewMainDataList newMainDataList0 = new NewMainDataList();
        newMainDataList0.setImg(R.drawable.new_shop);
        newMainDataList0.setTital("商城");
        newMainDataList0.setType(SHOW_SHOP);

        mData.clear();

//        mData.add(newMainDataList0);
//        mData.add(newMainDataList1);
//        mData.add(newMainDataList2);
        mData.add(newMainDataList3);
        mData.add(newMainDataList4);
        mData.add(newMainDataList5);
        mData.add(newMainDataList6);
//        mData.add(newMainDataList6);
//        mData.add(newMainDataList6);
//        mData.add(newMainDataList6);
        initAdapter();

        /**
         * 轮播图数据
         */
        mImageUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3778456200,3076998411&fm=23&gp=0.jpg");
        mImageUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3535338527,4000198595&fm=23&gp=0.jpg");
        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1017904219,2460650030&fm=23&gp=0.jpg");
        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2863927798,667335035&fm=23&gp=0.jpg");
        mImageUrl.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3885596348,1190704919&fm=23&gp=0.jpg");
        mImageUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1597254274,1405139366&fm=23&gp=0.jpg");
        setBean(mImageUrl);
    }
    String totalCacheSize =null;
    private void initAppData() {
        /**
         * 获取版本号，并更新到侧滑页面；
         */
        try {
            String versionName = HelpUtils.getLocalVersionName();
            resultAppended.updateBadge(4, new StringHolder("v"+versionName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 获取缓存并更新
         */
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            if (totalCacheSize!=null)
                resultAppended.updateBadge(3, new StringHolder(totalCacheSize));
//            personTvCache.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initDrawer(Bundle savedInstanceState) {
        resultAppended = new DrawerBuilder()
                .withActivity(this)
                .withSavedInstance(savedInstanceState)
                .withHeader(R.layout.person_header)
                .withFooter(R.layout.person_footer)
//                .withDisplayBelowStatusBar(true)
//                .withTranslucentStatusBar(true)
                .withDrawerWidthPx((ScreenUtils.getScreenWidth(NewMainActivity.this)/10)*7)
                .withSliderBackgroundColor(getResources().getColor(R.color.grayeee))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_mine)
                                .withIcon(R.drawable.new_per_mine)
                                .withIdentifier(1).withSelectable(true)
                                .withTextColor(getResources().getColor(R.color.gray666))
                                .withBadge("10金3银")
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(getResources().getColor(R.color.gray333))
                                ),
//                        分割线
//                        new DividerDrawerItem(),
                        /**
                         * 已激活产品列表
                         */
                        new PrimaryDrawerItem().withName(R.string.drawer_item_jihuo).withIcon(R.drawable.new_per_yihuo)
                                .withIdentifier(2).withSelectable(true)
                                .withTextColor(getResources().getColor(R.color.gray666)),
                        /**
                         * 清除缓存
                         * .withColorRes(R.color.white)  后面字体背景色
                         */
                        new PrimaryDrawerItem().withName(R.string.drawer_item_clear)
                                .withIcon(R.drawable.new_per_clear)
                                .withIdentifier(3).withSelectable(true)
                                .withBadge("0KB")
                                .withTextColor(getResources().getColor(R.color.gray666))
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(getResources().getColor(R.color.gray333))
                                ),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_updata)
                                .withIcon(R.drawable.new_per_updata)
                                .withIdentifier(4).withSelectable(true)
                                .withBadge("v1.0.0")
                                .withTextColor(getResources().getColor(R.color.gray666))
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(getResources().getColor(R.color.gray333))
                                ),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_fankui)
                                .withIcon(R.drawable.new_per_updata)
                                .withIdentifier(5).withSelectable(false)
                                .withBadge("")
                                .withTextColor(getResources().getColor(R.color.gray666))
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(getResources().getColor(R.color.gray333))
                                ),
                        new PrimaryDrawerItem().withName("")
//                                .withIcon(R.drawable.new_per_updata)
                                .withIdentifier(6).withSelectable(false)
                                .withBadge(R.string.drawer_item_email)
                                .withTextColor(getResources().getColor(R.color.gray666))
                                .withBadgeStyle(new BadgeStyle()
                                        .withTextColor(getResources().getColor(R.color.gray333))
                                )
                        //字体颜色淡点
//                        new SecondaryDrawerItem().withName(R.string.drawer_item_jihuo).withIcon(FontAwesome.Icon.faw_cog),
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            String identifier = drawerItem.getIdentifier()+"";
                            switch (identifier)
                            {
                                //     我的唯物币
                                case "1":
                                    break;
                                //已激活列表
                                case "2":
                                    if (NoDoubleClickUtils.isDoubleClick())
                                        IntentUtils.JumpTo(PersonAlreadyActivatedActivity.class);
                                    break;
//                                    清除缓存
                                case "3":
                                    if (!totalCacheSize.equals("0KB")) {
                                        DialogUtils.showDialog("确认清理" + totalCacheSize + "缓存？", new DialogUtils.OnClickSureListener() {
                                            @Override
                                            public void onClickSure() {
                                                cleanCaChe();
                                            }
                                        });
                                    }else {
                                        ToastUtil.show("暂无缓存");
                                    }
                                    break;
//                                    版本
                                case "4":
                                    VersionCheckUtils.initUpdata(true);
                                    break;
                                default:
                                    break;
                            }
                        }
                        return false;
                    }
                })
//                添加仿qq的侧滑，滑动时，主布局也跟着滑动
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
//                        ToastUtil.show("开启");
                    }
                    @Override
                    public void onDrawerClosed(View drawerView) {
//                        ToastUtil.show("关闭");
                    }
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        int top = 0;
//                        if (Build.VERSION.SDK_INT >Build.VERSION_CODES.KITKAT){
//                            top = ScreenUtils.getStatusHeight(NewMainActivity.this);
//                        }
                        //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
//                        右滑
//                        mLinTop.layout(drawerView.getRight(), 0, drawerView.getRight() + display.getWidth(), display.getHeight());
//                        左滑
                        mLinTop.layout(-( ScreenUtils.getScreenWidth(NewMainActivity.this)-drawerView.getLeft() ),top
                                ,drawerView.getLeft(),ScreenUtils.getScreenHeight(NewMainActivity.this));
//                        ToastUtil.show("onDrawerSlide");
                    }
                })
                .withDrawerGravity(Gravity.END)
                .build();
        /**
         * 点击侧滑顶部
         */
        header = resultAppended.getHeader();
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewMainActivity.this, "点击了头部", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 点击侧滑底部的退出
         */
        View footer = resultAppended.getFooter();
        footer.findViewById(R.id.foot_lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NoDoubleClickUtils.isDoubleClick()) {
                    Tip.getDialog(NewMainActivity.this, "是否确定退出登录", new Tip.OnClickSureListener() {
                        @Override
                        public void onClickSure() {
                            try {
//                                TODO  在登录成功后添加一个SP_USER_ID的参数
                                String userId = (String) SPUtils.get(NewMainActivity.this, AppAllKey.SP_USER_ID, "");
                                new NetWorkUtlis().setOnNetWork(CommonParameter.NORMAL, URL_GET.loguot(userId), new NetWorkUtlis.OnNetWork() {
                                    @Override
                                    public void onNetSuccess(String result) {}});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            AppManager.getAppManager().onAppExit(MyApplication.getAppContext());
                        }
                    });
                }
            }
        });
        initAppData();
        if (isCaChe)
            initHttpData();
    }
    View header =null;
    //    清理缓存
    private void cleanCaChe() {
        DataCleanManager.clearAllCache(MyApplication.getAppContext());
        try {
            totalCacheSize = DataCleanManager.getTotalCacheSize(MyApplication.getAppContext());
            resultAppended.updateBadge(3, new StringHolder(totalCacheSize));
            ToastUtil.show("清理缓存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.new_iv_top_person,R.id.new_main_header_iv_hua})
    public void onViewClicked(View view) {
        switch (view.getId())
        {
            case R.id.new_iv_top_person:
                try {
                    if(resultAppended!=null)
                        resultAppended.openDrawer();
                    else
                    {
                        initDrawer(savedInstanceState);
                        resultAppended.openDrawer();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.new_main_header_iv_hua:
//                ToastUtil.show("点击了滑动");
                setScroll(ScreenUtils.getScreenHeight(NewMainActivity.this),true);
                break;
        }

    }

    @Override
    protected boolean isTopBack() {
        return false;
    }
    @Override
    protected boolean isSupportSwipeBack() {
        return false;
    }
    boolean isExit;
    Handler mHandler = new Handler();
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            escApp();
            // return super.onKeyDown(keyCode, event);
            // 拦截系统按键
        }
        return true;
    }

    private void escApp() {
        // 双击退出
        if (isExit)
        {
            AppManager.getAppManager().onAppExit(NewMainActivity.this);
        } else
        {
            isExit = true;
            ToastUtil.show("再按一次退出应用");
            mHandler.postDelayed(new Runnable()
            {
                public void run()
                {
                    isExit = false;
                }
            }, 2000);
        }
    }
}
