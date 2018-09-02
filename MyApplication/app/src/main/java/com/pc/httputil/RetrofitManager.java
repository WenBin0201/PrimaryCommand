package com.pc.httputil;


import com.pc.entity.LoginEntity;
import com.pc.utils.GsonUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by wenbinbin on 2018/7/14.
 */

public class RetrofitManager {

    private RetrofitService retrofitService;
    private static RetrofitManager RetrofitManager;

    private static boolean DEBUG = true;

    private  RetrofitManager(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (DEBUG){
            builder.addInterceptor(new LogingInterceptor());//使用自定义的Log拦截器
        }
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10,TimeUnit.SECONDS);
        builder.writeTimeout(10,TimeUnit.SECONDS);
        OkHttpClient client =  builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.196.204.207:8032/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitManager getInstance(){
        if (RetrofitManager ==null){
            RetrofitManager = new RetrofitManager();
        }
        return RetrofitManager;
    }

    public void login(Map<String,String> params, final CallBack callBack){
        retrofitService.login(params).compose(TransformUtils.<String>defaultSchedulers())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.Fail(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(String s) {
                        callBack.Success(GsonUtil.json2Obj(s, LoginEntity.class));

                    }
                });
    }
}
