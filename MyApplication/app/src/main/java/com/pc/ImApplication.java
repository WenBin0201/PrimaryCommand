package com.pc;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.pc.bean.VersionBean;

import java.util.List;


public class ImApplication extends MultiDexApplication {

    public static Context context;
    public static List<VersionBean> bersionList;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initHttpOkGo();
    }
    private void initHttpOkGo(){
        OkGo.init(this);
        try{
            OkGo.getInstance()
                    .debug("okgo")
                    .setConnectTimeout(5*1000)
                    .setRetryCount(8)
                    .setCookieStore(new PersistentCookieStore())
                    .setCertificates();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
