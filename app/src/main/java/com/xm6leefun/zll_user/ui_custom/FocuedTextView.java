package com.xm6leefun.zll_user.ui_custom;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/11/7 0007.
 */

public class FocuedTextView extends AppCompatTextView {
    public FocuedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FocuedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocuedTextView(Context context) {
        super(context);

    }

    @Override
    public boolean isFocused() {
        return true;   //返回一个ture就可以获取焦点
    }
}
