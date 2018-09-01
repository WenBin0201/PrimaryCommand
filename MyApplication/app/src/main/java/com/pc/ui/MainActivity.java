package com.pc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pc.BaseActivity;
import com.pc.R;
import com.pc.ui.dailymanage.DailyManageActivity;
import com.pc.ui.scenemanage.SceneManageActivity;
import com.pc.ui.systemmanage.SystemManageActivity;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class MainActivity extends BaseActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_mainactivity);

        findViewById(R.id.ll_main_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        findViewById(R.id.ll_main_xcgl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage(SceneManageActivity.class);

            }
        });
        findViewById(R.id.ll_main_rcgl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage(DailyManageActivity.class);
            }
        });
        findViewById(R.id.ll_main_xtgl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPage(SystemManageActivity.class);

            }
        });
    }
}
