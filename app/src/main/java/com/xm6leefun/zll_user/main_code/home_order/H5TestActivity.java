package com.xm6leefun.zll_user.main_code.home_order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xm6leefun.zll_user.R;
import com.xm6leefun.zll_user.base.MyBaseActivity;
import com.xm6leefun.zll_user.utils.WebHost;

//商户入驻
public class H5TestActivity extends MyBaseActivity {

//    @BindView(R.id.include_top_tv_tital)
//    TextView includeTopTvTital;
    //    @BindView(R.id.include_top_lin_back)
//    LinearLayout includeTopLinBack;
//    @BindView(R.id.his_details_recyc)
//    RecyclerView mRecyclerview;
//    private WebView webView;
//    private LinearLayout ll_root;
//    private EditText et_user;

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history_details);
//        ButterKnife.bind(this);
//        includeTopTvTital.setText("555");
//        ll_root = (LinearLayout) findViewById(R.id.ll_root);
//        et_user = (EditText) findViewById(R.id.et_user);
//        initWebView();
//        mRecyclerview.setHasFixedSize(true);
//        mRecyclerview.setNestedScrollingEnabled(false);
//        mRecyclerview.setLayoutManager(new LinearLayoutManager(H5TestActivity.this));
//        initHttpData();
        webView = (WebView) findViewById(R.id.webView1);

        webView.getSettings().setJavaScriptEnabled(true);
        //JS映射
        webView.addJavascriptInterface(new WebHost(this), "js");

        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //显示本地静态页
        webView.loadUrl("http://trace.xm6leefun.com/html/demo.html");
//        webView.loadUrl("http://trace.xm6leefun.com/origin/html/origin.html");
}
    @Override
    public int getLayoutId() {
        return R.layout.activity_h5_test;
    }
    //初始化WebView

//    private void initWebView() {
//        //动态创建一个WebView对象并添加到LinearLayout中
//        webView = new WebView(getApplication());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        webView.setLayoutParams(params);
//        ll_root.addView(webView);
//        //不跳转到其他浏览器
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        WebSettings settings = webView.getSettings();
//        //支持JS
//        settings.setJavaScriptEnabled(true);
//        //加载本地html文件
//        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
//        webView.addJavascriptInterface(new JSInterface(),"Android");
//    }
//
//    //按钮的点击事件
//    public void click(View view){
//        //java调用JS方法
//        webView.loadUrl("javascript:javaCallJs(" + "'" + et_user.getText().toString()+"'"+")");
//    }
//
//    //在页面销毁的时候将webView移除
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ll_root.removeView(webView);
//        webView.stopLoading();
//        webView.removeAllViews();
//        webView.destroy();
//        webView = null;
//    }
//
//    private class JSInterface {
//        //JS需要调用的方法
//        @JavascriptInterface
//        public void showToast(String arg){
//            Toast.makeText(H5TestActivity.this,arg,Toast.LENGTH_SHORT).show();
//        }
//    }
}
