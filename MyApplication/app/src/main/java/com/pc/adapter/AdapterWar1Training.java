package com.pc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pc.R;
import com.pc.bean.TrainingWar2Bean;

import java.util.List;


/**
 * Created by Administrator on 2018-02-28.
 */

public class AdapterWar1Training extends BaseAdapter {

    private Context context;
    private List<TrainingWar2Bean> datas;
    private LayoutInflater mInflater;

    public AdapterWar1Training(Context context, List<TrainingWar2Bean> datas) {
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
            view = mInflater.inflate(R.layout.item_war1_training,null);
            viewholder.tv_war1_id = view.findViewById(R.id.tv_war1_id);
            viewholder.tv_war1_name = view.findViewById(R.id.tv_war1_name);
            viewholder.tv_war1_training = view.findViewById(R.id.tv_war1_training);
            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }
        TrainingWar2Bean bean = datas.get(i);
        viewholder.tv_war1_id.setText(1+i+"");
        viewholder.tv_war1_name.setText(bean.get姓名());
        viewholder.tv_war1_training.setText(bean.getStaffScore()+"");
        return view;
    }

    public void notifyDataSetChanged(List<TrainingWar2Bean> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }
    public class Viewholder{
        TextView tv_war1_id;
        TextView tv_war1_name;
        TextView tv_war1_training;


    }
}
