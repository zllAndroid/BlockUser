package com.xm6leefun.zll_user.ui_custom;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xm6leefun.zll_user.ui_custom.about_scroll.EasySlideInter;

/*
 * ScrollView并没有实现滚动监听，所以我们必须自行实现对ScrollView的监听，
 * 我们很自然的想到在onTouchEvent()方法中实现对滚动Y轴进行监听
 * ScrollView的滚动Y值进行监听
 */

public class CusScrollView extends ScrollView {
    private OnScrollListener onScrollListener;
    /**
     * 主要是用在用户手指离开MyScrollView，MyScrollView还在继续滑动，我们用来保存Y的距离，然后做比较
     */
    private int lastScrollY;

    public CusScrollView(Context context)
    {
        super(context);
    }

    public CusScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs, 0);
    }

    public CusScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    /**
     * 滑动事件
     */
    @Override
    public void fling(int velocityY) {
        super.fling(velocityY / 6);
    }

    private LinearLayout contentLayout ;
    private int childCount ;
    private int width ;
    private int height ;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = getChildAt(0 );
        if (!(view instanceof LinearLayout)) {
            throw new RuntimeException("This Layout  must is  LinearLayout ") ;
        }
        contentLayout  = (LinearLayout) view ;
        childCount = contentLayout.getChildCount() ;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth() ;
        height = getHeight() ;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        int currentBottom = t + height ;
        int currentTop = t ;

        for (int i = 0; i < childCount; i++) {
            View childView = contentLayout.getChildAt(i )  ;
            if (!(childView  instanceof EasySlideInter)) {
                continue ;
            }
            int childTop = childView.getTop() ;
            int childBottom = childView.getBottom() ;
            int childHeight = childView.getHeight() ;
            EasySlideInter inter = (EasySlideInter) childView ;
            if ( currentTop > childTop && currentTop < childBottom ) {
                inter.contentSlide(countProgress(currentTop, childBottom, childHeight));
            }else if (currentBottom > childTop && currentBottom < childBottom ) {
                inter.contentSlide(100 - countProgress(currentBottom, childBottom, childHeight));
            }else if(childTop >= currentTop && childBottom <= currentBottom){
                inter.resetContent();
            }
        }
    }

    private int countProgress(int top  ,int childBottom ,  int childHeight){
        return ( childBottom - top ) * 100  / childHeight ;
    }


    /**
     * 设置滚动接口
     *
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener)
    {
        this.onScrollListener = onScrollListener;
    }

    /**
     * 用于用户手指离开MyScrollView的时候获取MyScrollView滚动的Y距离，然后回调给onScroll方法中
     */
    private Handler handler = new Handler()
    {

        public void handleMessage(android.os.Message msg)
        {
            int scrollY = CusScrollView.this.getScrollY();

            // 此时的距离和记录下的距离不相等，在隔5毫秒给handler发送消息
            if (lastScrollY != scrollY)
            {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null)
            {
                onScrollListener.onScroll(scrollY);
            }

        }

    };


    /**
     * 重写onTouchEvent， 当用户的手在MyScrollView上面的时候，
     * 直接将MyScrollView滑动的Y方向距离回调给onScroll方法中，当用户抬起手的时候，
     * MyScrollView可能还在滑动，所以当用户抬起手我们隔5毫秒给handler发送消息，在handler处理
     * MyScrollView滑动的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (onScrollListener != null)
        {
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 滚动的回调接口
     */
    public interface OnScrollListener
    {
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         */
         void onScroll(int scrollY);

    }
}
