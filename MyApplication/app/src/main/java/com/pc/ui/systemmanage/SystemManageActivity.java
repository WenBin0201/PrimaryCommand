package com.pc.ui.systemmanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.pc.BaseActivity;
import com.pc.R;
import com.pc.utils.CustomViewPager;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class SystemManageActivity extends BaseActivity{

    private AdvancedPagerSlidingTabStrip tabs;
    private CustomViewPager pager;
    private SystemManageFragmentAdapter pf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_systemmanageactivity);

        findViewById(R.id.ll_sceneback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabs =  findViewById(R.id.tabs);
        pager =  findViewById(R.id.pager);
        pf = new SystemManageFragmentAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pf);
        pager.setCanScroll(false);
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);
        tabs.setSelectItem(0);
    }

    class SystemManageFragmentAdapter extends FragmentPagerAdapter {

        private  final  String[] TITLES = {"绑定设备","操作说明","软件版本"};

        private SystemFragment1 systemFragment1;
        private SystemFragment2 systemFragment2;
        private SystemFragment3 systemFragment3;


        public SystemManageFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    if (systemFragment1 ==null){
                        systemFragment1 = new SystemFragment1();
                    }
                    return systemFragment1;

                case 1:
                    if (systemFragment2 ==null) {
                        systemFragment2 = new SystemFragment2();
                    }
                    return systemFragment2;
                case 2:
                    if (systemFragment3 ==null){
                        systemFragment3 = new SystemFragment3();
                    }
                    return systemFragment3;
                default:
                    if (systemFragment1 ==null){
                        systemFragment1 = new SystemFragment1();
                    }
                    return systemFragment1;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
