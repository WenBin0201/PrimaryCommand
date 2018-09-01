package com.pc.ui.dailymanage;

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

public class DailyManageActivity extends BaseActivity{

    private AdvancedPagerSlidingTabStrip tabs;
    private CustomViewPager pager;
    private DailyManageFragmentAdapter pf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dailymanageactivity);
        findViewById(R.id.ll_sceneback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabs =  findViewById(R.id.tabs);
        pager =  findViewById(R.id.pager);
        pf = new DailyManageFragmentAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(pf);
        pager.setCanScroll(false);
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);
        tabs.setSelectItem(0);
    }

    class DailyManageFragmentAdapter extends FragmentPagerAdapter {

        private  final  String[] TITLES = {"体能训练","培训经历","特长查询","力量编成"};

        private DailyFragment1 dailyFragment1;
        private DailyFragment2 dailyFragment2;
        private DailyFragment3 dailyFragment3;
        private DailyFragment4 dailyFragment4;


        public DailyManageFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    if (dailyFragment1 ==null){
                        dailyFragment1 = new DailyFragment1();
                    }
                    return dailyFragment1;

                case 1:
                    if (dailyFragment2 ==null) {
                        dailyFragment2 = new DailyFragment2();
                    }
                    return dailyFragment2;
                case 2:
                    if (dailyFragment3 ==null){
                        dailyFragment3 = new DailyFragment3();
                    }
                    return dailyFragment3;
                case 3:
                    if (dailyFragment4 ==null){
                        dailyFragment4 = new DailyFragment4();
                    }
                    return dailyFragment4;
                default:
                    if (dailyFragment1 ==null){
                        dailyFragment1 = new DailyFragment1();
                    }
                    return dailyFragment1;
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
