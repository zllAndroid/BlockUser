package com.xm6leefun.zll_user.utils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.loopj.android.image.SmartImageView;
import com.xm6leefun.zll_user.R;

import java.util.ArrayList;

/**
 * 广告图片自动轮播控件</br>
 *
 */
public class ImageCycleView extends LinearLayout {
	/**
	 * 上下文
	 */
	private Context mContext;
	/**
	 * 图片轮播视图
	 *
	 * ViewPager用于实现多页面的切换效果，该类存在于Google的兼容包里面，所以
	 * 在引用时记得在BuilldPath中加入“android-support-v4.jar”。
	 *
	 * 使用ViewPager与ListView类似，也需要一个适配器，PagerAdapter。
	 */
	private ViewPager mAdvPager = null;
	/**
	 * 滚动图片视图适配
	 */
	private ImageCycleAdapter mAdvAdapter;
	/**
	 * 图片轮播指示器控件
	 *
	 * View是所有UI组件的基类，而 ViewGroup是容纳这些组件的容器，其
	 * 本身也是从View派生出来的.
	 */
	private ViewGroup mGroup;

	/**
	 * 图片轮播指示个图
	 */
	private ImageView mImageView = null;

	/**
	 * 滚动图片指示视图列表
	 */
	private ImageView[] mImageViews = null;

	/**
	 * 图片滚动当前图片下标
	 */
int inde=0;
	private boolean isStop;
	ArrayList<String> imageNameList;
	/**
	 * @param context
	 */
	public ImageCycleView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 *
	 * 写在xml里的 调用2个参数的 attr里边传过来的是 xml里边对应的height width等
	 * 参数，包括自己定义的参数，如果在xml里边写入自定义控件的话 必须要重写2个参数的构造函数
	 */
	public ImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		LayoutInflater.from(context).inflate(R.layout.ad_cycle_view, this);
		mAdvPager = (ViewPager) findViewById(R.id.adv_pager);
		mAdvPager.setOnPageChangeListener(new GuidePageChangeListener());
		mAdvPager.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						// 开始图片滚动
						startImageTimerTask();
						break;
					default:
						// 停止图片滚动
						stopImageTimerTask();
						break;
				}
				return false;
			}
		});
		// 滚动图片右下指示器视
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);
	}

	/**
	 * 装填图片数据
	 *
	 * @param imageUrlList
	 * @param imageCycleViewListener
	 */
	public void setImageResources(ArrayList<String> imageUrlList , ImageCycleViewListener imageCycleViewListener)
	{
		// 清除
		mGroup.removeAllViews();
		// 图片广告数量
		if (imageUrlList!=null){
			final int imageCount = imageUrlList.size();
			mImageViews = new ImageView[imageCount];
			for (int i = 0; i < imageCount; i++) {
				mImageView = new ImageView(mContext);
				LayoutParams params=new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.leftMargin=30;
				//FIT_XY不按比例缩放图片，目标是把图片塞满整个View
				mImageView.setScaleType(ScaleType.FIT_XY);
				mImageView.setLayoutParams(params);

				mImageViews[i] = mImageView;
				if (i == 0) {
					mImageViews[i].setBackgroundResource(R.drawable.tuoyuanshixin);
				} else {
					mImageViews[i].setBackgroundResource(R.drawable.tuoyuan);
				}
				mGroup.addView(mImageViews[i]);
			}

			mAdvAdapter = new ImageCycleAdapter(mContext, imageUrlList ,imageCycleViewListener);
			mAdvPager.setAdapter(mAdvAdapter);
			startImageTimerTask();
		}
	}

	/**
	 * 图片轮播(手动控制自动轮播与否，便于资源控件）
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播—用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 图片滚动任务
	 */
	private void startImageTimerTask() {
		stopImageTimerTask();
		// 图片滚动
		mHandler.postDelayed(mImageTimerTask, 2000);
	}
	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		isStop=true;
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {
		@Override
		public void run() {
			if (mImageViews != null) {
				mAdvPager.setCurrentItem(mAdvPager.getCurrentItem()+1);
				if(!isStop){  //if  isStop=true   //当你退出后 要把这个给停下来 不然 这个一直存在 就一直在后台循环 
					mHandler.postDelayed(mImageTimerTask, 3000);
				}

			}
		}
	};

	/**
	 * 轮播图片监听
	 *
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {
		@Override
		/**
		 * 此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（0，1，2）。arg0 ==1的时辰默示正在滑
		 * 动，arg0==2的时辰默示滑动完毕了，arg0==0的时辰默示什么都没做。当页面开始滑动的时候，三种状态
		 * 的变化顺序为（1，2，0）
		 */
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask();
		}
		@Override
		/**
		 * 当页面在滑动的时候会调用此方法，在滑动被停止之前，此方法回一直得到调用。其中三个参数的含义分别为：
		 arg0 :当前页面，及你点击滑动的页面
		 arg1:当前页面偏移的百分比
		 arg2:当前页面偏移的像素位置
		 */
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		@Override
		/**
		 * 此方法是页面跳转完后得到调用，index是你当前选中的页面的Position（位置编号）。
		 */
		public void onPageSelected(int index) {
			try {
				index=index%mImageViews.length;
				inde = index;
				// 设置当前显示的图片
				// 设置图片滚动指示器背
				mImageViews[index].setBackgroundResource(R.drawable.tuoyuanshixin);
				for (int i = 0; i < mImageViews.length; i++) {
					if (index != i) {
						mImageViews[i].setBackgroundResource(R.drawable.tuoyuan);
					}
				}
			} catch (Exception e) {
			}


		}
	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<SmartImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList<String> mAdList = new ArrayList<String>();

		/**
		 * 广告图片点击监听
		 */
		private ImageCycleViewListener mImageCycleViewListener;

		private Context mContext;
		public ImageCycleAdapter(Context context, ArrayList<String> adList , ImageCycleViewListener imageCycleViewListener) {
			this.mContext = context;
			this.mAdList = adList;
			mImageCycleViewListener = imageCycleViewListener;
			mImageViewCacheList = new ArrayList<SmartImageView>();
		}

		@Override
		public int getCount() {
//			return mAdList.size();
			return Integer.MAX_VALUE;
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			String imageUrl = null;
			if (mAdList!=null&&mAdList.size()>0) {
				imageUrl = mAdList.get(inde);
			}else {
//				imageUrl = mAdList.get(0);
			}

//			Log.i("imageUrl",imageUrl);
			//smartImageVIew，支持从URL和通讯录中获取图像
			SmartImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new SmartImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

				//test
				imageView.setScaleType(ScaleType.FIT_XY);
				//imageView.setImageUrl("http://imgs.xiuna.com/xiezhen/2014-9-25/2/5562900520140919100645087.jpg");
				// 设置图片点击监听
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (mAdList!=null&&mAdList.size()>0) {
							mImageCycleViewListener.onImageClick(inde, v);
						}

					}
				});
			}
			else
			{
				imageView = mImageViewCacheList.remove(0);
			}
			try {
				imageView.setTag(imageUrl);
				container.addView(imageView);
				imageView.setImageUrl(imageUrl);
				return imageView;
			} catch (Exception e) {
				return imageView;
			}
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			SmartImageView view = (SmartImageView) object;
			mAdvPager.removeView(view);
			mImageViewCacheList.add(view);

		}

	}

	/**
	 * 轮播控件的监听事件
	 *
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 单击图片事件
		 *
		 * @param position
		 * @param imageView
		 */
		public void onImageClick(int position, View imageView);
	}

}
