package com.xm6leefun.zll_user.ui_custom;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class AlwaysMarqueeTextView  extends AppCompatTextView{
    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlwaysMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    //重写这个方法，返回为true，让TextView一直获取焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
