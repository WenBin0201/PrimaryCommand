package com.pc.ui.systemmanage;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pc.BaseFragment;
import com.pc.ImApplication;
import com.pc.R;
import com.pc.bean.VersionBean;
import com.pc.utils.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class SystemFragment3 extends BaseFragment {
    private TextView tv_text1;
    private TextView tv_text2;
    private Button bt_upgrade_version;
    private static final int GET_VERSION_UPGRADE = 0;
    @Override
    public int getLayoutId() {
        return R.layout.view_systemfragment3;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        tv_text1 = view.findViewById(R.id.tv_text1);
        tv_text2 = view.findViewById(R.id.tv_text2);
        bt_upgrade_version = view.findViewById(R.id.bt_upgrade_version);
        HttpManager.get_String(HttpManager.GET_VERSION_UPGRADE, null, GET_VERSION_UPGRADE,callBack);



    }
    private HttpManager.OnOkGoResultCallBack callBack = new HttpManager.OnOkGoResultCallBack() {
        @Override
        public void OnSuccess(String s, int code) {
            try{
                JSONObject jb  = new JSONObject(s);
                if (!jb.getString("Message").equals("success")){
                    Toast.makeText(getBaseActivity(),"访问失败："+jb.getString("Data"),Toast.LENGTH_SHORT).show();
                    return;
                }
                JSONObject jsonObject;
                JSONArray jsonArray;
                switch (code){
                    case GET_VERSION_UPGRADE:
                        jsonObject = new JSONObject(s);
                        jsonArray = jsonObject.getJSONArray("Data");
                        if (jsonArray != null && jsonArray.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            Type type1=new TypeToken<List<VersionBean>>(){}.getType();
                            ImApplication.bersionList = gson.fromJson(jsonArray.toString(),type1);
                            tv_text1.setText("当前版本：" + getLocalVersionName(getBaseActivity()));
                            tv_text2.setText("最新版本：" + ImApplication.bersionList.get(0).getVersionName());
                            if (getLocalVersionName(getBaseActivity()).equals(ImApplication.bersionList.get(0).getVersionName())){
                                bt_upgrade_version.setBackgroundColor(getResources().getColor(R.color.huise));
                                bt_upgrade_version.setEnabled(false);
                            }
                            bt_upgrade_version.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //版本升级
                                    downloadApk(getBaseActivity(),ImApplication.bersionList.get(0));
                                }
                            });
//                            setVersionUpgrade();
                        }
                        break;
                }


            }catch (JSONException e){
                e.printStackTrace();
            }
        }

        @Override
        public void OnError(Exception e, int code) {

        }
    };

    private void setVersionUpgrade(){
        VersionBean versionBean = ImApplication.bersionList.get(0);
        if (!versionBean.getVersionCode().equals(getLocalVersionCode(getBaseActivity())) ||
                !versionBean.getVersionName().equals(getLocalVersionName(getBaseActivity()))){
            int UpdateType = versionBean.getUpdateType();
            if (UpdateType == 0){//强制更新
                showUpdateDialog1(versionBean);
            }else if (UpdateType == 1){//可忽略更新
                showUpdateDialog(versionBean);
            }
        }
    }
    /**
     * 弹出提示更新的dialog
     */
    private void showUpdateDialog1(final VersionBean versionBean) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getBaseActivity());
        dialog.setCancelable(false);
        dialog.setTitle("版本更新提示");
        dialog.setMessage("檢查到有最新版本,请立即更新?");
        dialog.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //从服务器端下载最新apk
                downloadApk(getBaseActivity(),versionBean);
            }
        });
        dialog.show();
    }
    /**
     * 弹出提示更新的dialog
     */
    private void showUpdateDialog(final VersionBean versionBean) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getBaseActivity());
        dialog.setCancelable(false);
        dialog.setTitle("版本更新提示");
        dialog.setMessage("检查到有最新版本,是否更新?");
        dialog.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //跳转到登录界面
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //从服务器端下载最新apk
                downloadApk(getBaseActivity(),versionBean);
            }
        });
        dialog.show();
    }
    /**
     * 从服务器端下载最新apk
     */
    public void downloadApk(Context context, VersionBean versionBean) {
        String apkUrl = versionBean.getVersionUrl();
        Uri uri = Uri.parse(apkUrl);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(request.NETWORK_MOBILE| request.NETWORK_WIFI);
        //设置是否允许漫游
        request.setAllowedOverRoaming(false);
        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(apkUrl));
        request.setMimeType(mimeString);
        //在通知栏中显示
        request.setNotificationVisibility(request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载中");
        request.setVisibleInDownloadsUi(true);
        //sdcard目录下的download文件夹
        request.setDestinationInExternalPublicDir("/download", "bobao.apk");
        // 将下载请求放入队列
        downloadManager.enqueue(request);

        //访问网络下载apk
//        VersionUpdateConfig.getInstance()//获取配置实例
//                .setContext(context)//设置上下文
//                .setDownLoadURL(versionBean.getVersionUrl())//设置文件下载链接
//                .setNewVersion(versionBean.getVersionCode())//设置即将下载的APK的版本号,避免重复下载
//                .setNotificationIconRes(R.drawable.logo)//设置通知图标
//                .setNotificationSmallIconRes(R.drawable.logo)//设置通知小图标
//                .setNotificationTitle("版本升级")//设置通知标题
//                .startDownLoad();
    }
    /**
     * 获取本地软件版本号
     */
    public  String getLocalVersionCode(Context ctx) {
        String localVersion = "0";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = String.valueOf(packageInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

}
