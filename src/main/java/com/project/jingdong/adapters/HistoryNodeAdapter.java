package com.project.jingdong.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.jingdong.R;
import com.project.jingdong.bean.HistorySearchBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class HistoryNodeAdapter extends BaseAdapter {
    private List<HistorySearchBean> list;
    private Context context;

    public HistoryNodeAdapter(List<HistorySearchBean> list, Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.histroy_search_view, null);
        }
        TextView histroy_search_view = convertView.findViewById(R.id.histroy_search_view);
        histroy_search_view.setText(list.get(position).getSearchName() + "");
        return convertView;
    }
}
