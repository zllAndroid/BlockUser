package com.xm6leefun.zll_user.base;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.xm6leefun.zll_user.utils.NetWorkUtlis;
import com.xm6leefun.zll_user.utils.StrUtils;

/**
 * 公共基类
 *   zll
 * @Time 2017-11-01
 */
public class BaseFragment extends Fragment implements NetWorkUtlis.OnNetWork {
	public Handler mHandler =null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public void onStart() {
		super.onStart();
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//				View viewById = AppManager.getAppManager().currentActivity().findViewById(R.id.main_view_top);
//				if (viewById!=null)
//				viewById.setVisibility(View.VISIBLE);
//		}

		if (mHandler==null) {
			mHandler= new Handler(new Handler.Callback() {
				@Override
				public boolean handleMessage(Message msg) {
					if (msg != null)
						onHandleMessage(msg);
					return false;
				}
			});
//			mHandler = new Handler() {
//				@Override
//				public void handleMessage(Message msg) {
//					if (msg != null)
//						onHandleMessage(msg);
//				}
//			};
		}
	}
	protected void onHandleMessage(Message msg) {
	}
	@Override
	public void onNetSuccess(String msg) {

		Message message = new Message();
		if (!StrUtils.isEmpty(msg)) {
			message.what = CommonParameter.HANDLE_MSG_SUCCESS;
		}
		else {
			message.what = CommonParameter.HANDLE_MSG_FAILED;
		}
		message.obj = msg;
		mHandler.sendMessage(message);
	}
}
