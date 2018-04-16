package com.xm6leefun.zll_user.ui_custom;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.xm6leefun.zll_user.R;


/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class TipPopWindow {
    private View mView;
    private View middle_line;
    private TextView pop_tv_ok, pop_tv_no, pop_tv_message, pop_tv_title;
    private TipPopWindowLintener tipPopWindowLintener;
    private TipNoLintener noLintener;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private EditText pop_et_message;
    private Context mContext;
    private Window window;


    public TipPopWindow(Context context) {
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.tip_popwindow, null);
        init();
        builder = new AlertDialog.Builder(context, R.style.CustomDialog);
        dialog = builder.create();
        dialog.setView(mView);
        window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(dw);
        try{
            if(mContext!=null){
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        pop_tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noLintener == null) {
                    cancel();
                } else {
                    cancel();
                    noLintener.setnoLintener();
                }
            }
        });
        pop_tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
                tipPopWindowLintener.setSelectLintener();
            }
        });
    }

    private void init() {
        middle_line = mView.findViewById(R.id.middle_line);
        pop_tv_ok = (TextView) mView.findViewById(R.id.pop_tv_ok);
        pop_tv_no = (TextView) mView.findViewById(R.id.pop_tv_no);
        pop_tv_message = (TextView) mView.findViewById(R.id.pop_tv_message);
        pop_tv_title = (TextView) mView.findViewById(R.id.pop_tv_title);
        pop_et_message = (EditText) mView.findViewById(R.id.pop_et_message);
    }

    public void sethints(String str) {
        pop_et_message.setHint(str);
    }

    public void cancel() {

        dialog.dismiss();
    }

    /**
     * 设置主题和消息内容
     *
     * @param title
     * @param message
     */
    public void setTitle_message(String title, String message) {

        pop_tv_title.setText(title);
        pop_tv_message.setText(message);
    }
    public void setMessage(String message) {
        pop_tv_message.setVisibility(View.VISIBLE);
        pop_et_message.setVisibility(View.GONE);
        pop_tv_message.setText(message);
    }

    /**
     * 当position为1的时候，只有一个按钮
     */
    public void getstyle(String btn_no, String btn_ok) {
        if (btn_ok == null) {
            pop_tv_ok.setVisibility(View.GONE);
        }
        if (pop_tv_no == null) {
            pop_tv_no.setVisibility(View.GONE);
        }
        pop_tv_ok.setText(btn_ok);
        pop_tv_no.setText(btn_no);
    }

    /**
     * 调出ok按钮提供用户定义事件
     *
     * @param tipPopWindowLintener
     */
    public void setTipOnclick(TipPopWindowLintener tipPopWindowLintener) {

        this.tipPopWindowLintener = tipPopWindowLintener;
    }

    public interface TipPopWindowLintener {
        void setSelectLintener();
    }

    /**
     * 调no按钮的的点击事件
     */
    public void setNoOnclick(TipNoLintener noOnclick) {
        this.noLintener = noOnclick;
    }

    public interface TipNoLintener {
        void setnoLintener();
    }
}
