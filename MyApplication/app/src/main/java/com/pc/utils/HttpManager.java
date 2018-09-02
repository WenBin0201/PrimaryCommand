package com.pc.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017-12-22.
 */

public class HttpManager {
    public static final String TAG = "HttpManager_Log:";
//    /** 接口 **/
//    public static final String URL = "http://139.196.204.207:8046";
//    /** 后台 **/
//    public static final String LOAD_URL = "http://139.196.204.207:8045";

    /** 接口 **/
    public static final String URL = "http://139.196.204.207:8032";
    /** 后台 **/
    public static final String LOAD_URL = "http://139.196.204.207:8026";


//    public static final String URL1 = "http://192.168.18.13:8081";
//    public static final String URL = "http://139.196.204.207:8030";
//    public static final String LOAD_URL = "http://139.196.204.207:8041";
//    public static final String URL = "http://139.196.204.207:8042";
//    public static final String LOAD_URL = "http://139.196.204.207:8045";
//    public static final String URL = "http://139.196.204.207:8046";
//    public static final String URL = "http://139.196.204.207:8045";
//    public static final String URL = "http://139.196.204.207:8046";
//    public static final String URL = "http://139.196.204.207:8005";
//    public static final String LOAD_URL = "http://139.196.204.207:8004";
//    public static final String URL = "http://47.104.214.156:8001";
//    public static final String LOAD_URL = "http://47.104.214.156:8002";
//    public static final String URL = "http://47.104.214.156:8004";
//    public static final String LOAD_URL = "http://47.104.214.156:8003";

    /**  设置页面——获取所有部门列表 **/
    public static final String GET_ALL_DANWEI = URL + "/api/CarAPP/GetDepartmentList";
    /**  设置页面——平板绑定单位 **/
    public static final String BIND_DEPATMENT = URL + "/api/frads/GetBindDeptDTUid";
    /** 获得现场人员和车辆信息接口：*/
    public static final String GET_CAR_PERSOON_MESSAGE=URL+"/api/frads/GetEventCarAndStaffList?Aid=";
    /** 获得部门下车辆和人员信息接口：*/
    public static final String GET_DEPARTMENT_CAR_PERSOON_MESSAGE=URL+"/api/frads/GetCarStaffListByDeptID?DeptID=";
    /** 获得所有的特征值：*/
    public static final String GET_EVENTMESSAGE=URL+"/api/frads/GetAffairEigenvalue" ;
    /** 获得事件信息接口：*/
    public static final String GET_NOWEVENTMESSAGE=URL+"/api/frads/GetEventInfo";//?lat=31.254&lng=121.512
    /** 获得查询预案*/
    public static final String GET_YUAN=URL+"/api/frads/GetGetAffairReservePlan?ReservePlanName=";
    /**  主页面——获取事件信息 **/
    public static final String GET_EVENT_INFO = URL + "/api/frads/GetEventInfo";
    /**  主页面——获取事件信息 **/
    public static final String GET_EVENT_INFO_TIANQI = URL + "/api/frads/GetWeatherInfo?aid=";
    /**  发送GPS信息接口 **/
    public static final String SEND_GPS = URL + "/api/carapp/GetAddStaffGPS";
    /**  发送视频 **/
    public static final String POST_ADD_AFFAIR_VIDEO = URL + "/api/frads/PostAddAffairVedio";


    /**  现场管理页面——资源状态——获取车辆列表信息 **/
    public static final String GET_CAR_COMMAND_INFO = URL + "/api/frads/GetEventCarList?Aid=";
    /**  现场管理页面——资源状态——获取作战班组列表信息 **/
    public static final String GET_PERSON_COMMAND_INFO = URL + "/api/frads/GetEventStaffList?Aid=";
    /**  现场管理页面——资源状态——设置班组空呼超时提醒时间 **/
    public static final String GET_UPDATE_STAFF_BREATH_REMIND_TIME = URL + "/api/frads/GetUpdateStaffBreathRemindTime";
    /**  现场管理页面——资源部署——获取所有人员命令 **/
    public static final String GET_SEND_GROUP_COMMAND = URL + "/api/frads/GetSendGroupCommand";
    /**  现场管理页面——资源状态——获取车辆车载器材信息 **/
    public static final String GET_EQUIP_BY_CAR = URL + "/api/carapp/GetEquipInCarByCarNo?id=";
    /**  现场管理页面——资源状态——获取车辆车载器材信息——车载器材参数 **/
    public static final String GET_EQUIP_INFO = LOAD_URL + "/ERT_R/EquipParameterInTablet.aspx?rfid=";
    /**  现场管理页面——资源分布——获取所有资源信息 **/
    public static final String GET_DISTRIBUTION_INFO = URL + "/api/frads/GetGISList";
    /**  现场管理页面——资源分布——获取所有资源信息 **/
    public static final String ADD_STAFF_GROUP = URL + "/api/frads/GetAddStaffGroup";
    /**  现场管理页面——资源部署——获取所有人员命令 **/
    public static final String GET_ALL_COMMAND = URL + "/api/frads/GetStaffCommandTextList";
    /**  现场管理页面——资源部署——获取所有车辆命令 **/
    public static final String GET_CAR_COMMAND = URL + "/api/frads/GetCarCommandTextList";
    /**  现场管理页面——资源部署——获取所有编组名称 **/
    public static final String GET_STAFF_GROUP_COD = URL + "/api/frads/GetStaffGroupCod";
    /**  现场管理页面——资源部署——获取所有编组名称 **/
    public static final String GET_GROUP_STAFF_LIST = URL + "/api/frads/GetGroupStaffList";
    /**  现场管理页面——资源部署——获取现场图片信息 **/
    public static final String GET_AFFAIR_AV = URL + "/api/frads/GetAffairAV";
    /**  现场管理页面——资源部署——获取车牌号的车辆命令 **/
    public static final String GET_CAN_SEND_CAR_COMMAND_LIST = URL + "/api/frads/GetCanSendCarCommandList?CarNo=";
    /**  现场管理页面——作战编程——作战请求 **/
    public static final String GET_SEND_COMMAND_FROMeRJITOYIJI = URL + "/api/frads/GetSendCommandFromErjiToYiJi";
    /**  现场管理页面——作战编程——处置预案 **/
    public static final String GET_CONTINGENCYPLAN = URL + "/api/frads/GetContingencyPlan";
    /**  现场管理页面——现场视频 **/
    public static final String AFFAIR_VEDIOS_INTABLET = LOAD_URL + "/Event/ShowAffairFile/AffairVediosInTablet.aspx?aid=";


    /** 修改事件信息接口（post方式，提交的字段有aid表示事件ID,Eigenvalue表示特征值，OtherEigenvalue表示特征值里面有一个是输入文字的内容，
    Province表示省份，City表示城市，District表示区县，AAddress表示详细地址，一共7个参数）：*/
    public static final String POST_EVENTMESSAGE = URL + "/api/frads/PostGetUpdateAffair";
    /** 上传事件图片接口：
    参数为：Aid，Picture，UserId和Note，其中Aid为事件的id,Picture为图片的base64格式，多张图片用逗号隔开，Userid为部门id,Note为备注文字*/
    public static final String POST_EVENTPICTURE = URL +"/api/frads/PostAddAffairPicture";

    public static final String GET_MESSAGES = URL +"/api/frads/GetZDCarStaffListByDeptID?DEPTID=";

    /** 部门器材信息接口*/
    public static final String GET_EQUIP = URL +"/api/carapp/GetEquipInCarByCarNo?ID=";

    /**  现场管理页面——资源部署——发送人员命令 **/
    public static final String SEND_COMMAND_TO_PERSON = URL + "/api/frads/GetSendStaffCommand";
    /**  现场管理页面——资源部署——发送车辆命令 **/
    public static final String SEND_COMMAND_TO_CAR = URL + "/api/frads/GetSendCarCommand";


    /**  信息中心页面——历史指令——获取所有历史指令 **/
    public static final String GET_DEPT_COMMAND_LIST = URL + "/api/frads/GetDeptCommandList";
    /**  信息中心页面——历史指令——回复命令接口 **/
    public static final String GET_REPLY_COMMAND = URL + "/api/frads/GetReplyCommand";//?CmdId=44&ReplyContent=%E5%90%8C%E6%84%8F
    /**  信息中心页面——历史指令——回复命令接口 **/
    public static final String GET_EVENT_REPORTINTABLET = LOAD_URL + "/Event/EventHistoryReportInTablet.aspx?AId=";//?CmdId=44&ReplyContent=%E5%90%8C%E6%84%8F
    /**  信息中心页面——作战文书请求 **/
    public static final String GET_COMBAT_DOCUMENTS = URL + "/api/frads/GetZuoZhanWenShu?Aid=";


    /**  战训管理——获取培训资质科目  01是培训资质 02是特长  00是除去培训资质和特长以外的所有类别**/
    public static final String GET_COMBAT_TRAINING = URL + "/api/frads/GetCombatTrainingSubjectListByType?SubjectTypeCode=";
    /**  战训管理——根据姓名和资质类型获取所有人员资质信息 **/
    public static final String GET_STAFF_COMBAT_TRAINING = URL + "/api/frads/GetStaffCombatTraining";
    /**  战训管理——提交培训资质信息 **/
    public static final String POST_ADD_STAFF_COMBAT_TRAINING = URL + "/api/frads/PostAddStaffCombatTraining";
    /**  战训管理——删除培训资质信息 **/
    public static final String GET_DEL_STAFF_COMBAT_TRAINING = URL + "/api/frads/GetDelStaffCombatTraining?id=";
    /**  战训管理——提交训练数据 **/
    public static final String POST_ADD_ALL_STAFF_COMBAT_TRAINING = URL + "/api/frads/PostAddAllStaffCombatTraining";


    /**  设置页面——获取所有事件列表 **/
    public static final String GET_EVENT_INFO_LIST = URL + "/api/frads/GetEvnetInfoList";
    /**  设置页面——绑定事件接口 **/
    public static final String BIND_EVENT_INFO = URL + "/api/frads/GetEventInfo?aid=";
    /**  设置页面——登录接口 **/
    public static final String LOGIN_INFO = URL + "/api/frads/PostUserLogin";
    /**  设置页面——获取版本号和更新地址 **/
    public static final String GET_VERSION_UPGRADE = URL + "/api/frads/GetFradsAppVersion";
    /**  	绑定人员，发送设备ID **/
    public static final String SEND_PHOTO_ID = URL + "/api/carapp/GetBindEventStaff";





    /**
     *  有参访问
     * @param lm    添加参数
     * @param url   访问地址
     * @param code  标记
     * @param callBack
     */
    public static void get_String(String url, LinkedHashMap<String,String> lm, final int code, final OnOkGoResultCallBack callBack){
        if ( lm != null){
            StringBuffer sb = new StringBuffer("?");
            for (String key:lm.keySet()){
                if (sb.length()>3){
                    sb.append("&");
                }
                sb.append(key+"="+lm.get(key));
            }
            url = url + sb;
        }
        android.util.Log.e(TAG,"访问网址 = "+url);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                s = s.substring(1, s.length() - 1);
                s = s.replace("\\", "");
                android.util.Log.e(TAG,"onSuccess = "+s);
                callBack.OnSuccess(s,code);
            }

            @Override
            public void onAfter(String s, Exception e) {
//                callBack.OnError(e,code);
                super.onAfter(s, e);
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                android.util.Log.d(TAG,"onError = "+e);
                callBack.OnError(e,code);
                super.onError(call, response, e);
            }
        });
    }

    public static void post_String(String url, Map<String,String> map, final int code, final OnOkGoResultCallBack callBack){
        OkGo.post(url)
                .params(map,true)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        s = s.substring(1, s.length() - 1);
                        s = s.replace("\\", "");
                        android.util.Log.e(TAG,"onSuccess = "+s);
                        callBack.OnSuccess(s,code);
                    }

                    @Override
                    public void onCacheError(Call call, Exception e) {
                        super.onCacheError(call, e);
                        android.util.Log.d(TAG,"onError = "+e);
                        callBack.OnError(e,code);
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        android.util.Log.d(TAG,"onError = "+e);
                        callBack.OnError(e,code);
                        super.onError(call, response, e);
                    }
                });
    }

    public static void downLoad(Context context, String url, String destFileDir, String destFileName, final OnOkGoDownFileResultCallBack callBack){
        OkGo.get(url)//
                .tag(context)
                .execute(new FileCallback(destFileDir, destFileName) {  //文件下载时，可以指定下载的文件目录和文件名
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        // file 即为文件数据，文件保存在指定目录
                        callBack.OnSuccess(file.getAbsolutePath());
                    }
                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调下载进度(该回调在主线程,可以直接更新ui)
                        //currentSize totalSize以字节byte为单位
                        callBack.downloadProgress(currentSize,totalSize,progress,networkSpeed);
                    }
                });
    }

    public static void getBitmap(String url, final ImageView imageView, final OnOkGoBitmapCallBack callBack){

        OkGo.<Bitmap>get(url).execute(new BitmapCallback() {
            @Override
            public void onSuccess(Bitmap bitmap, Call call, Response response) {

                callBack.OnSuccess(bitmap,imageView);
            }
            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                callBack.OnError(e,imageView);
            }
        });
    }



    public interface OnOkGoResultCallBack{
        void OnSuccess(String s, int code);
        void OnError(Exception e, int code);
    }
    public interface OnOkGoDownFileResultCallBack{
        void OnSuccess(String s);
        void OnError(Exception e);
        void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed);
    }
    public interface OnOkGoBitmapCallBack{
        void OnSuccess(Bitmap bitmap, ImageView imageView);
        void OnError(Exception e, ImageView imageView);
    }
}
