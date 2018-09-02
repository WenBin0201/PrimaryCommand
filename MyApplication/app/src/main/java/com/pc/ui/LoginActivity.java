package com.pc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.pc.BaseActivity;
import com.pc.R;
import com.pc.utils.HttpManager;
import com.pc.utils.SPUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;



/**
 * Created by wenbinbin on 2018/9/1.
 */

public class LoginActivity extends BaseActivity{

    private EditText editText_yonghuming;
    private EditText editText_mima;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        editText_yonghuming = findViewById(R.id.editText_yonghuming);
        editText_mima = findViewById(R.id.editText_mima);
        findViewById(R.id.bt_denglu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editText_yonghuming.getText().toString().trim();
                String passWord = editText_mima.getText().toString().toLowerCase();
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this,"请输入用户名!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passWord)){
                    Toast.makeText(LoginActivity.this,"请输入密码!!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                showWaitProgress("正在登陆...");

                OkGo.<String>post(HttpManager.LOGIN_INFO)
                        .params("UserName",userName)
                        .params("UserPwd",passWord)
                        .execute(new StringCallback() {

                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                hideWaitProgress();
                                try{
                                    s = s.substring(1, s.length() - 1);
                                    s = s.replace("\\", "");
                                    JSONObject jb  = new JSONObject(String.valueOf(s));
                                    if (!jb.getString("Message").equals("success")){
                                        Toast.makeText(LoginActivity.this,"登录失败："+jb.getString("Data"),Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    JSONArray jsonArray = jb.getJSONArray("Data");
                                    if (jsonArray != null && jsonArray.length() != 0) {
                                        JSONObject jb1 = jsonArray.getJSONObject(0);
                                        String DeptID = jb1.getString("DeptId");
                                        String userId = jb1.getString("账户ID");
                                        String DeptName = jb1.getString("DeptName");
                                        String ParentDeptList = jb1.getString("ParentDeptList");
                                        //0没有权限，1有权限
                                        String permisionSend = jb1.getString("发送命令");
                                        String permisionReception = jb1.getString("命令回复");
                                        if (editText_yonghuming.getText().toString().trim() !=null){
                                            SPUtils.put(LoginActivity.this,"userName",editText_yonghuming.getText().toString().trim());
                                        }
                                        if (editText_mima.getText().toString().trim() !=null){
                                            SPUtils.put(LoginActivity.this,"passWord",editText_mima.getText().toString().trim());
                                        }
                                        if (DeptID !=null){
                                            SPUtils.put(LoginActivity.this,"department",DeptID);
                                        }
                                        if (ParentDeptList !=null){
                                            SPUtils.put(LoginActivity.this,"ParentDeptList",ParentDeptList);
                                        }
                                        if (permisionSend !=null){
                                            SPUtils.put(LoginActivity.this,"permisionSend",permisionSend);
                                        }
                                        if (permisionReception !=null){
                                            SPUtils.put(LoginActivity.this,"permisionReception",permisionReception);
                                        }
                                        if (userId !=null){
                                            SPUtils.put(LoginActivity.this,"userId",userId);
                                        }
                                        if (DeptName !=null){
                                            SPUtils.put(LoginActivity.this,"DeptName",DeptName);
                                        }
                                        startPage(MainActivity.class);
                                        LoginActivity.this.finish();
                                    }

                                }catch (JSONException e){
                                    hideWaitProgress();
                                    e.printStackTrace();
                                }
                            }
                        });

            }
        });
    }
}
