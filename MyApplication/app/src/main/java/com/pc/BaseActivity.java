package com.pc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pc.utils.WaitProgressDialog;

public class BaseActivity extends AppCompatActivity{

    private WaitProgressDialog waitProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startPage(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }

    // 显示等待框
    public void showWaitProgress(final String message) {
        if (waitProgressDialog == null) {
            waitProgressDialog = new WaitProgressDialog(BaseActivity.this);
        }
        //如果当前正在显示则关闭，避免OOM
        if(waitProgressDialog.isShowing()){
            waitProgressDialog.hideProgress();
        }
        waitProgressDialog.showProgress(message);
    }

    //更新等待框进度
    public void updateWaitProgress(String message) {
        waitProgressDialog.updateMsg(message);
    }

    // 隐藏等待框
    public void hideWaitProgress() {
        if (waitProgressDialog != null) {
            waitProgressDialog.hideProgress();
        }
    }
}
