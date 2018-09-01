package com.pc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pc.BaseActivity;
import com.pc.R;

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
                startPage(MainActivity.class);
            }
        });
    }
}
