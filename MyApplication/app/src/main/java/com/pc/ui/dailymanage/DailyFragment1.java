package com.pc.ui.dailymanage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.pc.adapter.AdapterWar3Training;
import com.pc.bean.Result_carVO;
import com.pc.bean.TrainingQualificationBean;
import com.pc.bean.TrainingWar2Bean;
import com.pc.utils.GsonUtil;
import com.pc.utils.HttpManager;
import com.pc.utils.SPUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class DailyFragment1 extends BaseFragment implements View.OnClickListener{


    private Spinner sp_war3_leibie;
    private Spinner sp_war3_kemu;
    private Button bt_war3_select;
    private Button bt_war3_submit;
    private ListView lv_war3_info;
    private ListView lv_war3_content;
    private static final int GET_COMBAT_TRAINING = 0;
    private static final int GET_STAFF_COMBAT_TRAINING = 1;
    private static final int POST_ADD_ALL_STAFF_COMBAT_TRAINING = 2;
    private static final int GETMESSAGE = 3;
    private List<TrainingQualificationBean> trainingDatas;
    private static List<Result_carVO.DtStaffListBean> staffListBeanList;
    private List<TrainingWar2Bean> trainingWar1Datas;
    private AdapterWar1Training adapterWar1Training;
    private AdapterWar3Training adapterWar3Training;
    public static List<Map<String,String>> datas = new ArrayList<>();
    private List<String> spinnerLeibieData;
    public static String SubjectCode = "";
    private List<String> spinnerKemuData;
    @Override
    public int getLayoutId() {
        return R.layout.view_dailyfragment1;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        sp_war3_leibie = view.findViewById(R.id.sp_war3_leibie);
        sp_war3_kemu = view.findViewById(R.id.sp_war3_kemu);
        bt_war3_select = view.findViewById(R.id.bt_war3_select);
        bt_war3_submit = view.findViewById(R.id.bt_war3_submit);
        lv_war3_info = view.findViewById(R.id.lv_war3_info);
        lv_war3_content = view.findViewById(R.id.lv_war3_content);
        bt_war3_select.setOnClickListener(this);
        bt_war3_submit.setOnClickListener(this);
        sp_war3_leibie.setOnItemSelectedListener(onItemSelectedListener);
        sp_war3_kemu.setOnItemSelectedListener(onItemSelectedListener);
        trainingWar1Datas = new ArrayList<>();
        staffListBeanList = new ArrayList<>();
        adapterWar1Training = new AdapterWar1Training(getActivity(),trainingWar1Datas);
        lv_war3_info.setAdapter(adapterWar1Training);
        adapterWar3Training = new AdapterWar3Training(getActivity(),staffListBeanList,"");
        lv_war3_content.setAdapter(adapterWar3Training);
        HttpManager.get_String(HttpManager.GET_COMBAT_TRAINING + "00" ,null,GET_COMBAT_TRAINING,callBack);
        HttpManager.get_String(HttpManager.GET_MESSAGES + SPUtils.get(getActivity(),"department",""),null,GETMESSAGE,callBack);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_war3_select:
                for (int i = 0;i<trainingDatas.size();i++){
                    if (trainingDatas.get(i).getSubjectName().equals(sp_war3_kemu.getSelectedItem().toString())){
                        SubjectCode = trainingDatas.get(i).getSubjectCode();
                        getData(SubjectCode);
                    }
                }
                break;
            case R.id.bt_war3_submit:
                AddTraining();
                break;
        }
    }
    private void AddTraining(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("table", GsonUtil.obj2Json(datas));
        HttpManager.post_String(HttpManager.POST_ADD_ALL_STAFF_COMBAT_TRAINING,map,POST_ADD_ALL_STAFF_COMBAT_TRAINING,callBack);
    }
    private void setSpinnerLeibieAdapter(){
        spinnerLeibieData = new ArrayList<>();
        for (int i = 0;i<trainingDatas.size();i++){
            spinnerLeibieData.add(trainingDatas.get(i).getSubjectType());
        }
        HashSet h = new HashSet(spinnerLeibieData);
        spinnerLeibieData.clear();
        spinnerLeibieData.addAll(h);
        ArrayAdapter<String> depsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerLeibieData);
        depsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_war3_leibie.setAdapter(depsAdapter);
//        setSpinnerKemuAdapter(0);
    }
    private void setSpinnerKemuAdapter(int position){
        spinnerKemuData = new ArrayList<>();
        for (int i = 0;i<trainingDatas.size();i++){
            if (spinnerLeibieData.get(position).equals(trainingDatas.get(i).getSubjectType())){
                spinnerKemuData.add(trainingDatas.get(i).getSubjectName());
//                SubjectCode = trainingDatas.get(i).getSubjectCode();
            }
        }
        ArrayAdapter<String> depsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerKemuData);
        depsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sp_war3_kemu.setAdapter(depsAdapter);
    }
    public void getData(String strTrainingId){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("StaffId","");
        map.put("SubjectCode",strTrainingId);
        HttpManager.get_String(HttpManager.GET_STAFF_COMBAT_TRAINING,map,GET_STAFF_COMBAT_TRAINING,callBack);
    }
    private AdapterView.OnItemSelectedListener onItemSelectedListener  = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            switch (adapterView.getId()){
                case R.id.sp_war3_leibie:
                    setSpinnerKemuAdapter(position);
                    break;
                case R.id.sp_war3_kemu:
                    for (int i = 0;i<trainingDatas.size();i++){
                        if (spinnerKemuData.get(position).equals(trainingDatas.get(i).getSubjectName())){
                            SubjectCode = trainingDatas.get(i).getSubjectCode();
                            adapterWar3Training.notifyDataSetChanged(staffListBeanList,trainingDatas.get(i).getResultDesc());
                        }
                    }
                    break;
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private HttpManager.OnOkGoResultCallBack callBack = new HttpManager.OnOkGoResultCallBack() {
        @Override
        public void OnSuccess(String s, int code) {
            JSONObject jb = null;
            try {
                jb = new JSONObject(s);
                if (!jb.getString("Message").equals("success")) {
                    Toast.makeText(getActivity(), "访问失败：" + jb.getString("Data"), Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (code){
                    case GET_COMBAT_TRAINING:
                        JSONObject jsonObject1 = new JSONObject(s);
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("Data");
                        if (jsonArray1 != null && jsonArray1.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            trainingDatas = new ArrayList<>();
                            Type type=new TypeToken<List<TrainingQualificationBean>>(){}.getType();
                            trainingDatas = gson.fromJson(jsonArray1.toString(),type);
                            setSpinnerLeibieAdapter();
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
                        }else {
                            Toast.makeText(getActivity(),"暂无数据!!!",Toast.LENGTH_SHORT).show();
                        }
                        adapterWar1Training.notifyDataSetChanged(trainingWar1Datas);
                        break;
                    case POST_ADD_ALL_STAFF_COMBAT_TRAINING:
                        Toast.makeText(getActivity(),"提交成功!!!",Toast.LENGTH_SHORT).show();

                        break;
                    case GETMESSAGE:
                        JSONObject data = jb.getJSONObject("Data");
                        JSONArray jsonArray2 = data.getJSONArray("dtStaffList");
                        if (jsonArray2 != null && jsonArray2.length() != 0) {
                            Gson gson = new GsonBuilder().create();
                            staffListBeanList= new ArrayList<>();
                            Type type = new TypeToken<List<Result_carVO.DtStaffListBean>>() {}.getType();
                            staffListBeanList = gson.fromJson(jsonArray2.toString(), type);
//                            adapterWar3Training.notifyDataSetChanged(staffListBeanList,"");
                        }
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void OnError(Exception e, int code) {

        }
    };
    public static void saveEditData(int position, String str) {
        for (int i = 0;i < datas.size();i++){
            if (datas.get(i).get("StaffId").equals(staffListBeanList.get(position).get人员ID())){
                datas.remove(i);
            }
        }
        String StaffId = staffListBeanList.get(position).get人员ID();
        String TrainingResult = str;
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("StaffId",StaffId);
        hashMap.put("TrainingResult",TrainingResult);
        hashMap.put("SubjectCode",SubjectCode);
        hashMap.put("ResultDesc","");
        hashMap.put("ResultPicture","");
        datas.add(hashMap);
    }


}
