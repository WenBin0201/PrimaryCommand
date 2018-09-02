package com.pc.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.pc.R;
import com.pc.bean.Result_carVO;
import com.pc.ui.dailymanage.DailyFragment1;

import java.util.List;

/**
 * Created by Administrator on 2018-02-28.
 */

public class AdapterWar3Training extends BaseAdapter {

    private Context context;
    private List<Result_carVO.DtStaffListBean> datas;
    private LayoutInflater mInflater;
    private String desc;

    public AdapterWar3Training(Context context, List<Result_carVO.DtStaffListBean> datas,String desc) {
        this.context = context;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
        this.desc = desc;
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
            view = mInflater.inflate(R.layout.item_war3_training,null);
            viewholder.tv_war3_id = view.findViewById(R.id.tv_war3_id);
            viewholder.tv_war3_name = view.findViewById(R.id.tv_war3_name);
            viewholder.tv_war3_training = view.findViewById(R.id.tv_war3_training);
            viewholder.tv_war3_danwei = view.findViewById(R.id.tv_war3_danwei);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        Result_carVO.DtStaffListBean bean = datas.get(i);
        viewholder.tv_war3_id.setText(1+i+"");
        viewholder.tv_war3_name.setText(bean.get姓名());
        viewholder.tv_war3_training.setTag(i);
        viewholder.tv_war3_danwei.setText(desc);
        if (!TextUtils.isEmpty(bean.getInfo())) {
            viewholder.tv_war3_training.setText(bean.getInfo());
        } else {
            viewholder.tv_war3_training.setText("");
        }
        viewholder.tv_war3_training.addTextChangedListener(new TextSwitcher(viewholder));
        return view;
    }
    class TextSwitcher implements TextWatcher {
        private Viewholder mHolder;

        public TextSwitcher(Viewholder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.tv_war3_training.getTag();//取tag值
            DailyFragment1.saveEditData(position, s.toString());
            Result_carVO.DtStaffListBean bean = datas.get(position);
            bean.setInfo(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public void notifyDataSetChanged(List<Result_carVO.DtStaffListBean> datas,String desc){
        if (desc != null){
            this.desc = desc.substring(3,4);
        }
        this.datas = datas;
        notifyDataSetChanged();
    }
    public class Viewholder{
        TextView tv_war3_id;
        TextView tv_war3_name;
        EditText tv_war3_training;
        TextView tv_war3_danwei;
    }
}
