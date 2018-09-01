package com.pc.ui.scenemanage;

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

public class SceneManageActivity extends BaseActivity {


    private AdvancedPagerSlidingTabStrip tabs;
    private CustomViewPager pager;
    private SceneManageFragmentAdapter pf;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scenemanageactivity);
        findViewById(R.id.ll_sceneback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabs =  findViewById(R.id.tabs);
        pager =  findViewById(R.id.pager);
        pf = new SceneManageFragmentAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(pf);
        pager.setCanScroll(false);
        tabs.setViewPager(pager);
        pager.setCurrentItem(0);
        tabs.setSelectItem(0);
    }

    class SceneManageFragmentAdapter extends FragmentPagerAdapter {

        private  final  String[] TITLES = {"事件绑定","作战编成","临时编组","作战文书"};

        private SceneFragment1 sceneFragment1;
        private SceneFragment2 sceneFragment2;
        private SceneFragment3 sceneFragment3;
        private SceneFragment4 sceneFragment4;


        public SceneManageFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    if (sceneFragment1 ==null){
                        sceneFragment1 = new SceneFragment1();
                    }
                    return sceneFragment1;

                case 1:
                    if (sceneFragment2 ==null) {
                        sceneFragment2 = new SceneFragment2();
                    }
                    return sceneFragment2;
                case 2:
                    if (sceneFragment3 ==null){
                        sceneFragment3 = new SceneFragment3();
                    }
                    return sceneFragment3;
                case 3:
                    if (sceneFragment4 ==null){
                        sceneFragment4 = new SceneFragment4();
                    }
                    return sceneFragment4;
                    default:
                        if (sceneFragment1 ==null){
                            sceneFragment1 = new SceneFragment1();
                        }
                        return sceneFragment1;
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
