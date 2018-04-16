package com.xm6leefun.zll_user.ui_custom.about_scroll;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xm6leefun.zll_user.R;


public class MoveLayout extends RelativeLayout implements EasySlideInter {

	private ImageView text;
	RelativeLayout mRelativeLayout;
	LinearLayout mLinearLayout;
	private ObjectAnimator textAnimator;
	private ObjectAnimator textAnimator2;

	public MoveLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	public MoveLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public MoveLayout(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.include_newmain_header, this) ;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		text = (ImageView) findViewById(R.id.new_main_header_iv_logo);
		mRelativeLayout = (RelativeLayout) findViewById(R.id.new_main_header_relative_top);
		mLinearLayout = (LinearLayout) findViewById(R.id.new_main_header_lin);

		initAnimation();
	}
	
	
	@Override
	public void contentSlide(int progress) {
		textAnimator.setCurrentPlayTime(progress);
		textAnimator2.setCurrentPlayTime(progress);
	}

	@Override
	public void resetContent() {
		textAnimator.setCurrentPlayTime(100);
		textAnimator2.setCurrentPlayTime(100);
	}
	
	
	private void initAnimation(){
		textAnimator = ObjectAnimator.ofFloat(text, "scaleX", 0 , text.getScaleX() );
		textAnimator.setDuration(100)  ;
		textAnimator.setInterpolator(new LinearInterpolator()) ;

		textAnimator2 = ObjectAnimator.ofFloat(text, "scaleY", 0 , text.getScaleY());
		textAnimator2.setDuration(100)  ;
		textAnimator2.setInterpolator(new LinearInterpolator()) ;

//		textAnimator = ObjectAnimator.ofFloat(mRelativeLayout, "scaleX", 0 , mRelativeLayout.getScaleX() );
//		textAnimator.setDuration(100)  ;
//		textAnimator.setInterpolator(new LinearInterpolator()) ;
//		textAnimator2 = ObjectAnimator.ofFloat(mRelativeLayout, "scaleY", 0 , mRelativeLayout.getScaleY());
//		textAnimator2.setDuration(100);
//		textAnimator2.setInterpolator(new LinearInterpolator()) ;


	}
}
