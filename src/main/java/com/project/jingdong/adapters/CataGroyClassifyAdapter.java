package com.project.jingdong.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.jingdong.R;
import com.project.jingdong.bean.CatagoryBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CataGroyClassifyAdapter extends BaseAdapter {
    private List<CatagoryBean.DataBean> list;
    private Context context;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public CataGroyClassifyAdapter(List<CatagoryBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.catatroy_view, null);
        }
        TextView title = convertView.findViewById(R.id.catagroy_title_list);
        title.setText(list.get(position).getName());
        if (color == position) {
            title.setBackgroundColor(Color.parseColor("#F3F5F7"));
            title.setTextColor(Color.RED);
        } else {
            title.setBackgroundColor(Color.WHITE);
            title.setTextColor(Color.BLACK);
        }
        return convertView;
    }
}
