package com.pc.ui.dailymanage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pc.BaseFragment;
import com.pc.R;
import com.pc.adapter.AdapterWarTraining;
import com.pc.bean.Result_carVO;
import com.pc.bean.TrainingQualificationBean;
import com.pc.bean.TrainingWar2Bean;
import com.pc.utils.Base64BitmapUtil;
import com.pc.utils.HttpManager;
import com.pc.utils.SPUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wenbinbin on 2018/9/1.
 */

public class DailyFragment2 extends BaseFragment implements View.OnClickListener{

    private Spinner sp_name;
    private Spinner sp_techang;
    private Button bt_war_select;
    private Button bt_war_add;
    private ListView lv_war2;
    private List<String> sp_name_datas;
    private List<String> sp_techang_datas;
    private List<Result_carVO.DtStaffListBean> staffListBeanList;
    private List<TrainingQualificationBean> trainingDatas;
    private List<TrainingWar2Bean> trainingWar2Datas;
    private static final int GETMESSAGE = 0;
    private static final int GET_COMBAT_TRAINING = 1;
    private static final int POST_ADD_STAFF_COMBAT_TRAINING = 2;
    private static final int GET_STAFF_COMBAT_TRAINING = 3;
    private EditText et_add_training_content;
    private ImageView iv_war_image;
    private AlertDialog dialog;
    private static final int IMAGE_OPEN = 101;
    private static final int TAKE_PHOTO = 102;
    private String photoPath;
    private Uri photoUri;
    private Bitmap addbmp;
    private Spinner sp_add_name;
    private Spinner sp_add_techang;
    private AdapterWarTraining adapterWarTraining;
    @Override
    public int getLayoutId() {
        return R.layout.view_dailyfragment2;
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        sp_name = view.findViewById(R.id.sp_name);
        sp_techang = view.findViewById(R.id.sp_techang);
        bt_war_select = view.findViewById(R.id.bt_war_select);
        bt_war_add = view.findViewById(R.id.bt_war_add);
        lv_war2 = view.findViewById(R.id.lv_war2);
        bt_war_select.setOnClickListener(this);
        bt_war_add.setOnClickListener(this);
        sp_name_datas = new ArrayList<>();
        sp_techang_datas = new ArrayList<>();
        trainingWar2Datas = new ArrayList<>();
        adapterWarTraining = new AdapterWarTraining(getActivity(),trainingWar2Datas);
        lv_war2.setAdapter(adapterWarTraining);
        initData();
        getData("","01");
    }
    private void initData(){
        HttpManager.get_String(HttpManager.GET_MESSAGES + SPUtils.get(getActivity(),"department",""),null,GETMESSAGE,callBack);
        HttpManager.get_String(HttpManager.GET_COMBAT_TRAINING + "01" ,null,GET_COMBAT_TRAINING,callBack);
    }
    public void getData(String strId,String strTrainingId){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("StaffId",strId);
        map.put("SubjectCode",strTrainingId);
        HttpManager.get_String(HttpManager.GET_STAFF_COMBAT_TRAINING,map,GET_STAFF_COMBAT_TRAINING,callBack);
    }
    private void initName(){
        for (int i = 0;i<staffListBeanList.size();i++){
            sp_name_datas.add(staffListBeanList.get(i).get姓名());
        }
        List<String> spinnerData = sp_name_datas;
        spinnerData.add(0,"全部人员");
        setSpinnerNameAdapter(sp_name,spinnerData);
    }
    private void initTechang(){
        for (int i = 0;i<trainingDatas.size();i++){
            sp_techang_datas.add(trainingDatas.get(i).getSubjectName());
        }
        List<String> spinnerData = sp_techang_datas;
        spinnerData.add(0,"全部资质");
        setSpinnerTechangAdapter(sp_techang,spinnerData);
    }
    private void setSpinnerNameAdapter(Spinner spinner, List<String> spinnerData ){
        if (spinnerData.size() != sp_name_datas.size()){
            spinnerData.remove(0);
        }
        ArrayAdapter<String> depsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerData);
        depsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(depsAdapter);

    }

    private void setSpinnerTechangAdapter(Spinner spinner,List<String> spinnerData){
        if (sp_techang_datas.size() != spinnerData.size()){
            spinnerData.remove(0);
        }
        ArrayAdapter<String> depsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerData);
        depsAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(depsAdapter);

    }


    private HttpManager.OnOkGoResultCallBack  callBack = new HttpManager.OnOkGoResultCallBack() {
        @Override
        public void OnSuccess(String s, int code) {
            try {
                JSONObject jb = new JSONObject(s);
                if (!jb.getString("Message").equals("success")) {
                    Toast.makeText(getActivity(), "访问失败：" + jb.getString("Data"), Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (code){
                    case GETMESSAGE:
                        JSONObject data = jb.getJSONObject("Data");
                        JSONArray jsonArray1 = data.getJSONArray("dtStaffList");
                        if (jsonArray1 != null && jsonArray1.length() != 0) {
                            Gson gson = new GsonBuilder().create();
                            staffListBeanList= new ArrayList<>();
                            Type type = new TypeToken<List<Result_carVO.DtStaffListBean>>() {}.getType();
                            staffListBeanList = gson.fromJson(jsonArray1.toString(), type);
                            initName();
                        }
                        break;
                    case GET_COMBAT_TRAINING:
                        JSONObject jsonObject1 = new JSONObject(s);
                        JSONArray jsonArray2 = jsonObject1.getJSONArray("Data");
                        if (jsonArray2 != null && jsonArray2.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            trainingDatas = new ArrayList<>();
                            Type type=new TypeToken<List<TrainingQualificationBean>>(){}.getType();
                            trainingDatas = gson.fromJson(jsonArray2.toString(),type);
                            initTechang();
                        }
                        break;
                    case POST_ADD_STAFF_COMBAT_TRAINING:
                        Toast.makeText(getActivity(),"添加成功!!!",Toast.LENGTH_SHORT).show();
//                        String strId = staffListBeanList.get(sp_name.getSelectedItemPosition()-1).get人员ID();
//                        String strTrainingId = trainingDatas.get(sp_techang.getSelectedItemPosition()-1).getSubjectCode();
                        getData("","01");
                        break;
                    case GET_STAFF_COMBAT_TRAINING:
                        JSONObject jsonObject3 = new JSONObject(s);
                        JSONArray jsonArray3 = jsonObject3.getJSONArray("Data");
                        trainingWar2Datas = new ArrayList<>();
                        if (jsonArray3 != null && jsonArray3.length() != 0){
                            Gson gson = new GsonBuilder().create();
                            Type type=new TypeToken<List<TrainingWar2Bean>>(){}.getType();
                            trainingWar2Datas = gson.fromJson(jsonArray3.toString(),type);
                        }
                        adapterWarTraining.notifyDataSetChanged(trainingWar2Datas);
                        break;
                }

            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
        @Override
        public void OnError(Exception e, int code) {

        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_war_select:
                String strId = "";
                String strTrainingId = "01";
                if (sp_name.getSelectedItemPosition() != 0){
                    strId = staffListBeanList.get(sp_name.getSelectedItemPosition()-1).get人员ID();
                }
                if (sp_techang.getSelectedItemPosition() != 0){
                    strTrainingId = trainingDatas.get(sp_techang.getSelectedItemPosition()-1).getSubjectCode();
                }
                getData(strId,strTrainingId);
                break;
            case R.id.bt_war_add://展示添加页面
                showDialog();
                break;
            case R.id.bt_add_war://添加资质信息
                dialog.dismiss();
                AddTraining();
                break;
            case R.id.bt_add_cancel://取消添加
                dialog.dismiss();
                break;
            case R.id.iv_war_image://添加图片
                AddImageDialog();
                break;
        }
    }

    private void AddTraining(){
        if (sp_add_name.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(),"请选择人员",Toast.LENGTH_SHORT).show();
            return;
        }
        if (sp_add_techang.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(),"请选择特长",Toast.LENGTH_SHORT).show();
            return;
        }
        String strId = staffListBeanList.get(sp_add_name.getSelectedItemPosition()-1).get人员ID();
        String strTrainingId = trainingDatas.get(sp_add_techang.getSelectedItemPosition()-1).getSubjectCode();
        String content = et_add_training_content.getText().toString().trim();
        String base64Bmp = "";
        if (addbmp != null){
            base64Bmp = Base64BitmapUtil.bitmapToBase64(addbmp);
        }
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("StaffId",strId);
        map.put("SubjectCode",strTrainingId);
        map.put("TrainingResult","1");
        map.put("ResultDesc",content);
        map.put("ResultPicture",base64Bmp);
        HttpManager.post_String(HttpManager.POST_ADD_STAFF_COMBAT_TRAINING,map,POST_ADD_STAFF_COMBAT_TRAINING,callBack);
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_add_training, null);//获取自定义布局
        builder.setView(view);
        sp_add_name = view.findViewById(R.id.sp_add_name);
        sp_add_techang = view.findViewById(R.id.sp_add_techang);
        et_add_training_content = view.findViewById(R.id.et_add_training_content);
        iv_war_image = view.findViewById(R.id.iv_war_image);
        view.findViewById(R.id.bt_add_war).setOnClickListener(this);
        view.findViewById(R.id.bt_add_cancel).setOnClickListener(this);
        iv_war_image.setOnClickListener(this);
        setSpinnerNameAdapter(sp_add_name,sp_name_datas);
        setSpinnerTechangAdapter(sp_add_techang,sp_techang_datas);
        dialog = builder.create();
        dialog.show();
    }
    /**
     * 添加图片 可通过本地添加、拍照添加
     */
    protected void AddImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加图片");
        builder.setIcon(R.drawable.project);
        builder.setCancelable(true); //不响应back按钮
        builder.setItems(new String[]{"本地相册选择", "手机相机添加", "取消"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0: //本地相册
                                dialog.dismiss();
                                //Intent intent=new Intent(Intent.ACTION_PICK);
                                Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                startActivityForResult(intent,IMAGE_OPEN);
                                break;
                            case 1: //手机相机
                                dialog.dismiss();
                                Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                File photoFile=createImgFile();
                                // 判断版本大于等于7.0
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    photoUri = FileProvider.getUriForFile(getActivity(), "com.bobao.primarycommand.fileProvider", photoFile);
                                } else {
                                    photoUri = Uri.fromFile(photoFile);
                                }
//                                photoUri=Uri.fromFile(photoFile);
                                intent1.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                                startActivityForResult(intent1,TAKE_PHOTO);
                                break;
                            case 2: //取消添加
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                    }
                });
        //显示对话框
        builder.create().show();
    }

    //获取图片路径 响应startActivityForResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case TAKE_PHOTO://相机
                    setImageBitmap();
                    galleryAddPic();
                    break;
                case IMAGE_OPEN:
                    //data中自带有返回的uri
                    photoUri=data.getData();
                    //获取照片路径
                    String[] filePathColumn={MediaStore.Audio.Media.DATA};
                    Cursor cursor=getActivity().getContentResolver().query(photoUri,filePathColumn,null,null,null);
                    cursor.moveToFirst();
                    photoPath=cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                    cursor.close();
                    //有了照片路径，之后就是压缩图片，和之前没有什么区别
                    setImageBitmap();
                    break;
            }
        }
    }
    /**
     * 自定义图片名，获取照片的file
     */
    private File createImgFile(){
        //确定文件名
        String fileName="img_"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())+".jpg";
//        File dir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File dir=Environment.getExternalStorageDirectory();
        File dir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            dir=Environment.getExternalStorageDirectory();
        }else{
            dir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        File tempFile=new File(dir,fileName);
        try{
            if(tempFile.exists()){
                tempFile.delete();
            }
            tempFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        //获取文件路径
        photoPath=tempFile.getAbsolutePath();
        return tempFile;
    }
    /**
     * 压缩图片
     */
    private void setImageBitmap(){
        //获取imageview的宽和高
        int targetWidth=iv_war_image.getWidth();
        int targetHeight=iv_war_image.getHeight();

        //根据图片路径，获取bitmap的宽和高
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(photoPath,options);
        int photoWidth=options.outWidth;
        int photoHeight=options.outHeight;

        //获取缩放比例
        int inSampleSize=1;
        if(photoWidth>targetWidth||photoHeight>targetHeight){
            int widthRatio=Math.round((float)photoWidth/targetWidth);
            int heightRatio=Math.round((float)photoHeight/targetHeight);
            inSampleSize=Math.min(widthRatio,heightRatio);
        }

        //使用现在的options获取Bitmap
        options.inSampleSize=inSampleSize;
        options.inJustDecodeBounds=false;
        addbmp=BitmapFactory.decodeFile(photoPath,options);
        iv_war_image.setImageBitmap(addbmp);
    }
    //将图片添加进手机相册
    private void galleryAddPic(){
        Intent mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(photoUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }
}
