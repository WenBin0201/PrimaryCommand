package com.pc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.pc.BaseActivity;
import com.pc.R;
import com.pc.entity.LoginEntity;
import com.pc.httputil.CallBack;
import com.pc.httputil.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        findViewById(R.id.bt_denglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showWaitProgress("正在登陆...");
                Map<String,String> params = new HashMap<>();
                params.put("UserName","aa1");
                params.put("UserPwd","111");
                RetrofitManager.getInstance().login(params, new CallBack() {
                    @Override
                    public void Success(Object o) {

                        hideWaitProgress();
                        LoginEntity loginEntity = (LoginEntity) o;
                        Log.e("HttpUtil",loginEntity.getData().get(0).getDeptName());
                        startPage(MainActivity.class);
                    }

                    @Override
                    public void Fail(String err) {
                        hideWaitProgress();
                    }
                });


//
            }
        });
    }
}
