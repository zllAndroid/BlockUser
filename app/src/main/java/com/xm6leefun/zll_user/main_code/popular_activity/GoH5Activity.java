package com.xm6leefun.zll_user.main_code.popular_activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.AppAllKey;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoH5Activity extends MyBaseActivity {

    @BindView(R.id.include_top_tv_tital)
    TextView includeTopTvTital;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
    @BindView(R.id.mywebView)
    WebView mWebView;
    //    private LoadingDialog ld =null;
    String artical =null;
    String tital =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
//        ld = new LoadingDialog(GoH5Activity.this);
//        ld.setBac(GoH5Activity.this.getResources().getColor(R.color.white));
//        ld.setLoadingText("加载中...")
//                .setSuccessText("加载成功")
////                .setLoadSpeed(speed)
//                .setRepeatCount(0)
////                .setDrawColor(Color.WHITE)
//                .setLoadStyle( LoadingDialog.STYLE_LINE)
//                .show();
//        ld.setLoadSpeed(LoadingDialog.Speed.SPEED_TWO)
//                .setRepeatCount(0)
//                .show();
        Bundle mBundle = getIntent().getExtras();
        if (mBundle!=null)
        {
            tital = mBundle.getString(AppAllKey.GOH5_TITAL_KEY);
            artical = mBundle.getString(AppAllKey.GOH5_ARTICAL_KEY);
        }
        if (tital!=null)
        includeTopTvTital.setText(tital);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.requestFocus();
//		webview必须设置支持获取手势焦点。
//		mWebView.requestFocusFromTouch();
        if (artical!=null)
        mWebView.loadUrl(artical);
//        mWebView.loadUrl("http://aihl.ren:8084/api/index/getArticleById?id=12&type=5");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                DialogUtils.showUpdateDialog(GoH5Activity.this, "加载中", "请稍后。。。");
//                Dialog.
//                if (ld!=null)
//                {
//
//                }
                ToastUtil.show("加载中...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                DialogUtils.hideDialog();
//                if (ld!=null)
//                {
//                    ld.close();
//                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
//                Toast.makeText(GoH5Activity.this, "网页加载错误，请稍后重试", Toast.LENGTH_SHORT).show();
//                if (ld!=null)
//                {
//                    ld.loadFailed();
//                }
                ToastUtil.show("网页加载错误，请稍后重试");
            }



        });
    }

//    @OnClick(R.id.include_top_lin_back)
//    public void onViewClicked() {
////        finish();
//        AppManager.getAppManager().finishActivity();
//    }
    //    拦截右滑退出
//    @Override
//    protected boolean isSupportSwipeBack() {
//        return false;
//    }
}
