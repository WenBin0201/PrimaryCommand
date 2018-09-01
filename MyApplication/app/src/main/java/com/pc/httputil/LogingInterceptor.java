package com.pc.httputil;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by liangfujie on 17/8/2.
 */

public class LogingInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();//请求发起的时间;
        String method = request.method();//请求方式
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.e("HttpUtil",sb.toString());
            }
            Log.e("HttpUtil",request.url().toString());
        }else{
            Log.e("HttpUtil","\n"+request.url().toString());
        }

        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        //Log.e("hf", String.format("接收响应:", response.request().url(), responseBody.string(), (endTime - startTime) / 1e6d, response.headers()));
        Log.e("HttpUtil","----------------------返回参数-------------------");
        Log.e("HttpUtil",responseBody.string());
        Log.e("HttpUtil","----------------------------------------------------");
        Log.e("HttpUtil",response.headers().toString());
        Log.e("HttpUtil",(float)(endTime-startTime)/100+"秒");

        return response;
    }
}
