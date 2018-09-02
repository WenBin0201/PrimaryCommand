package com.pc.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.pc.R;
import com.pc.bean.TrainingWar2Bean;
import com.pc.ui.ShowImageActivity;
import com.pc.utils.HttpManager;

import java.util.List;

/**
 * Created by Administrator on 2018-02-28.
 */

public class AdapterWarTraining extends BaseAdapter {

    private Context context;
    private List<TrainingWar2Bean> datas;
    private LayoutInflater mInflater;

    public AdapterWarTraining(Context context, List<TrainingWar2Bean> datas) {
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view == null){
            viewholder = new Viewholder();
            view = mInflater.inflate(R.layout.item_war2_training,null);
            viewholder.tv_war_training_id = view.findViewById(R.id.tv_war_training_id);
            viewholder.tv_war_training_name = view.findViewById(R.id.tv_war_training_name);
            viewholder.tv_war_training_training = view.findViewById(R.id.tv_war_training_training);
            viewholder.tv_war_training_content = view.findViewById(R.id.tv_war_training_content);
            viewholder.tv_war_training_image = view.findViewById(R.id.tv_war_training_image);
            viewholder.tv_war_training_update = view.findViewById(R.id.tv_war_training_update);
            viewholder.tv_war_training_delete = view.findViewById(R.id.tv_war_training_delete);
            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }
        TrainingWar2Bean bean = datas.get(i);
//        viewholder.tv_war_training_id.setText(i+1);
        viewholder.tv_war_training_name.setText(bean.get姓名());
        viewholder.tv_war_training_training.setText(bean.getSubjectName());
        viewholder.tv_war_training_content.setText(bean.getResultDesc());
        viewholder.tv_war_training_image.setOnClickListener(new MyAdapterListener(i));
        viewholder.tv_war_training_update.setOnClickListener(new MyAdapterListener(i));
        viewholder.tv_war_training_delete.setOnClickListener(new MyAdapterListener(i));
        return view;
    }
    class MyAdapterListener implements View.OnClickListener {

        private int position;

        public MyAdapterListener(int pos) {
            position = pos;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_war_training_image:
                    String picture = datas.get(position).getResultPicture();
                    if (picture == null || picture.equals("")){
                        Toast.makeText(context,"暂无图片",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setClass(context, ShowImageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "2");
                    bundle.putString("bitmap", HttpManager.LOAD_URL + datas.get(position).getResultPicture());
                    intent.putExtra("bitmap",bundle);
                    context.startActivity(intent);
                    break;
                case R.id.tv_war_training_update:
                    break;
                case R.id.tv_war_training_delete:
                    String id = datas.get(position).getID()+"";
                    HttpManager.get_String(HttpManager.GET_DEL_STAFF_COMBAT_TRAINING + id, null, 1, new HttpManager.OnOkGoResultCallBack() {
                        @Override
                        public void OnSuccess(String s, int code) {
                            Toast.makeText(context,"删除成功!!!",Toast.LENGTH_SHORT).show();
                            datas.remove(position);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void OnError(Exception e, int code) {
                            Toast.makeText(context,"删除失败!!!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }



        }

    }

    public void notifyDataSetChanged(List<TrainingWar2Bean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }
    public class Viewholder{
        TextView tv_war_training_id;
        TextView tv_war_training_name;
        TextView tv_war_training_training;
        TextView tv_war_training_content;
        TextView tv_war_training_image;
        TextView tv_war_training_update;
        TextView tv_war_training_delete;

    }
}
