package com.pc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract  class BaseFragment extends Fragment{

    protected BaseActivity mActivity;

    private boolean loaded = false;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(getLayoutId(), container, false);
        initView(view,savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isVisible()){
            onVisible();
            if (!loaded){
                lazyLoad();
                loaded = true;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getUserVisibleHint()){
            onVisible();
            if (!loaded) {
                lazyLoad();
                loaded = true;
            }
        }
    }

    public BaseActivity getBaseActivity(){
        return mActivity;
    };


    public abstract int getLayoutId();
    public abstract void initView(View view,Bundle savedInstanceState);
    protected void lazyLoad() {}//加载数据,只执行一次。
    protected void onVisible(){};//对用户可见
}
