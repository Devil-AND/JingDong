package com.project.jingdong.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.project.jingdong.activities.ProductsList;
import com.project.jingdong.R;
import com.project.jingdong.bean.ProductCatagory;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:二级列表适配器
 * Detail:
 */


public class CataGroyClassifyExpandableAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "列表选项";
    private List<ProductCatagory.DataBean> list;
    private List<List<ProductCatagory.DataBean.ListBean>> childList;
    private Context context;

    public CataGroyClassifyExpandableAdapter(List<ProductCatagory.DataBean> list, Context context, List<List<ProductCatagory.DataBean.ListBean>> childList) {
        this.list = list;
        this.context = context;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder1 viewHolder1;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.groupview, null);
            viewHolder1 = new ViewHolder1();
            viewHolder1.groupTitle = convertView.findViewById(R.id.group_title);
            convertView.setTag(viewHolder1);
        } else {
            viewHolder1 = (ViewHolder1) convertView.getTag();
        }
        viewHolder1.groupTitle.setText(list.get(groupPosition).getName() + "");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderChild viewHolderChild;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.childview, null);
            viewHolderChild = new ViewHolderChild();
            viewHolderChild.recyclerView = convertView.findViewById(R.id.catagory_recyclerview);
            convertView.setTag(viewHolderChild);
        } else {
            viewHolderChild = (ViewHolderChild) convertView.getTag();
        }
        final List<ProductCatagory.DataBean.ListBean> listBeans = childList.get(groupPosition);
        //创建二级列表实例
        CataGroyClassifyChildAdapter cataGroyChildAdapter = new CataGroyClassifyChildAdapter(listBeans, context);
        viewHolderChild.recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        viewHolderChild.recyclerView.setAdapter(cataGroyChildAdapter);
        cataGroyChildAdapter.setOnItemClickListener(new CataGroyClassifyChildAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(context, ProductsList.class);
                intent.putExtra("pscid", position + "");
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //父列表
    class ViewHolder1 {
        TextView groupTitle;
    }

    //字列表
    class ViewHolderChild {
        RecyclerView recyclerView;
    }

}
