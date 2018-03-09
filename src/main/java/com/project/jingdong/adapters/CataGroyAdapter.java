package com.project.jingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.jingdong.R;
import com.project.jingdong.bean.CatagoryBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:商品分类适配器
 */

public class CataGroyAdapter extends RecyclerView.Adapter<CataGroyAdapter.MyViewholder> {
    private Context context;
    private List<CatagoryBean.DataBean> list;

    public CataGroyAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.catagory_recyclerview_view, null);
        MyViewholder myViewholder = new MyViewholder(inflate);
        return myViewholder;
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.mRecyclerviewImageCatagory);
        holder.mRecyclerviewTitleCatagory.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {

        private ImageView mRecyclerviewImageCatagory;
        private TextView mRecyclerviewTitleCatagory;

        public MyViewholder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {

            mRecyclerviewImageCatagory = (ImageView) itemView.findViewById(R.id.catagory_recyclerview_image);
            mRecyclerviewTitleCatagory = (TextView) itemView.findViewById(R.id.catagory_recyclerview_title);
        }
    }
}
