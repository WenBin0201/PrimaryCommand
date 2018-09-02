package com.pc.httputil;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wenbinbin on 2018/7/14.
 */

public interface RetrofitService {


//    @GET("api/frads/GetLastStaffGPS")
//    Observable<String> getLocation(@Query("RFAdd") String RFAdd);
//
//    @GET("http://api.map.baidu.com/geocoder/v2/?&output=json&ak=IRkDCEToH3B7DgGkmuPQmvzdf6bnMU1K&mcode=E9:68:15:C7:5C:65:D3:F3:AB:A2:8E:0E:F0:42:7B:00:F8:34:9D:31;com.wb.demo")
//    Observable<BaiDuAEntity> getAddressInfo(@Query("location") String location);

//    http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=31.329547,121.27888&destinations=31.2458333333333,121.6175&ak=IRkDCEToH3B7DgGkmuPQmvzdf6bnMU1K

    @FormUrlEncoded
    @POST("api/frads/PostUserLogin")
    Observable<String> login(@FieldMap Map<String,String> map);
}
