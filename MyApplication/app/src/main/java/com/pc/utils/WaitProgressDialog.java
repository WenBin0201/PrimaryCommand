/*
 * 	Copyright (c) 2012-2017 EasyDarwin.ORG.  All rights reserved.
 * 	Github: https://github.com/EasyDarwin
 * 	WEChat: EasyDarwin
 *	Website: http://www.easydarwin.org
 */

package com.pc.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.pc.R;


/**
 * 等待加载
 */
public class WaitProgressDialog extends Dialog {

    private LayoutInflater mInflater;
    private Dialog mDialog;
    private TextView mDialog_Meg;
    Activity activity;

    public WaitProgressDialog(Activity activity) {
        super(activity, true, null);
        mInflater = LayoutInflater.from(activity);
        this.activity = activity;
    }

    /**
     * 显示进度
     *
     * @param message
     */
    public void showProgress(final String message) {
        hideProgress();
        if (mDialog == null) {
            final View v1 = mInflater.inflate(R.layout.loading, null);
            mDialog = new Dialog(activity, R.style.WaitProgressDialogStyle1);
            mDialog.setCanceledOnTouchOutside(false);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
           // getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
            mDialog.setContentView(v1);
            Window window = mDialog.getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
//            // 以下这两句是为了保证按钮可以水平满屏
            params.width = dp2px(activity,120);
            params.height = dp2px(activity ,100);
            // 设置显示位置
            mDialog.onWindowAttributesChanged(params);
        }
        mDialog.setCancelable(true);
        mDialog_Meg = ((TextView) mDialog.findViewById(R.id.tv_loding));
        if (TextUtils.isEmpty(message)){
            mDialog_Meg.setText("请稍后...");
        }else{
            mDialog_Meg.setText(message);
        }
        mDialog.show();
    }

    public void updateMsg(String msg){
        if (mDialog_Meg!=null){
            mDialog_Meg.setText(msg);
        }
    }

    /**
     * @return the isShowing
     */
    public boolean isShowing() {
        return !(mDialog == null || !mDialog.isShowing());
    }

    /**
     * 取消等待框
     */
    public void hideProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
