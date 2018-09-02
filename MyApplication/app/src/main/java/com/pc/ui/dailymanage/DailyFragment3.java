package com.pc.ui.dailymanage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pc.BaseFragment;
import com.pc.R;
import com.pc.adapter.AdapterWar1Training;
import com.pc.bean.TrainingQualificationBean;
import com.pc.bean.TrainingWar2Bean;
import com.pc.utils.HttpManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class DailyFragment3 extends BaseFragment implements View.OnClickListener{

    private Spinner sp_war1_techang;
    private Button bt_war1_select;
    private ListView lv_war1;
    private static final int GET_COMBAT_TRAINING = 0;
    private static final int GET_STAFF_COMBAT_TRAINING = 1;
    private List<TrainingQualificationBean> trainingDatas;
    private List<TrainingWar2Bean> trainingWar1Datas;
    private AdapterWar1Training adapterWar1Training;
    @Override
    public int getLayoutId() {
        return R.layout.view_dailyfragment3;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        lv_war1 = view.findViewById(R.id.lv_war1);
        bt_war1_select = view.findViewById(R.id.bt_war1_select);
        sp_war1_techang = view.findViewById(R.id.sp_war1_techang);
        bt_war1_select.setOnClickListener(this);
        trainingWar1Datas = new ArrayList<>();
        adapterWar1Training = new AdapterWar1Training(getBaseActivity(),trainingWar1Datas);
        lv_war1.setAdapter(adapterWar1Training);
        HttpManager.get_String(HttpManager.GET_COMBAT_TRAINING + "02",null,GET_COMBAT_TRAINING,callBack);
    }
    public void getData(String strTrainingId){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("StaffId","");
        map.put("SubjectCode",strTrainingId);
        HttpManager.get_String(HttpManager.GET_STAFF_COMBAT_TRAINING,map,GET_STAFF_COMBAT_TRAINING,callBack);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_war1_select:
                getData(trainingDatas.get(sp_war1_techang.getSelectedItemPosition()).getSubjectCode());
                break;
        }

    }
    private void setSpinnerNameAdapter(){
        List<String> spinnerData = new ArrayList<>();
        for (TrainingQualificationBean trainingQualificationBean : trainingDatas){
            spinnerData.add(trainingQualificationBean.getSubjectName());
        }
        ArrayAdapter<String> depsAdapter = new ArrayAdapter<String>(getBaseActivity(), android.R.layout.simple_spinner_item, spinnerData);
        depsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_war1_techang.setAdapter(depsAdapter);
    }
    private HttpManager.OnOkGoResultCallBack callBack = new HttpManager.OnOkGoResultCallBack() {
        @Override
        public void OnSuccess(String s, int code) {
            try {
                JSONObject jb = new JSONObject(s);
                if (!jb.getString("Message").equals("success")) {
                    Toast.makeText(getBaseActivity(), "访问失败：" + jb.getString("Data"), Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (code) {
                    case GET_COMBAT_TRAINING:
                        JSONObject jsonObject1 = new JSONObject(s);
                        JSONArray jsonArray2 = jsonObject1.getJSONArray("Data");
                        if (jsonArray2 != null && jsonArray2.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            trainingDatas = new ArrayList<>();
                            Type type=new TypeToken<List<TrainingQualificationBean>>(){}.getType();
                            trainingDatas = gson.fromJson(jsonArray2.toString(),type);
                            setSpinnerNameAdapter();
                            getData(trainingDatas.get(0).getSubjectCode());
                        }
                        break;
                    case GET_STAFF_COMBAT_TRAINING:
                        JSONObject jsonObject3 = new JSONObject(s);
                        JSONArray jsonArray3 = jsonObject3.getJSONArray("Data");
                        trainingWar1Datas = new ArrayList<>();
                        if (jsonArray3 != null && jsonArray3.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            Type type=new TypeToken<List<TrainingWar2Bean>>(){}.getType();
                            trainingWar1Datas = gson.fromJson(jsonArray3.toString(),type);
                        }
                        adapterWar1Training.notifyDataSetChanged(trainingWar1Datas);
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
}
