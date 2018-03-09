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
import com.project.jingdong.bean.ProductCatagory;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:子列表适配器
 * Detail:
 */

public class CataGroyClassifyChildAdapter extends RecyclerView.Adapter<CataGroyClassifyChildAdapter.MyViewHolder> {
    private List<ProductCatagory.DataBean.ListBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public CataGroyClassifyChildAdapter(List<ProductCatagory.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.catagory_recyclerview_view, null);
        final MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //赋值
        holder.mRecyclerviewTitleCatagory.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.mRecyclerviewImageCatagory);
        final int pscid = list.get(position).getPscid();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(pscid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mRecyclerviewImageCatagory;
        private TextView mRecyclerviewTitleCatagory;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mRecyclerviewImageCatagory = (ImageView) itemView.findViewById(R.id.catagory_recyclerview_image);
            mRecyclerviewTitleCatagory = (TextView) itemView.findViewById(R.id.catagory_recyclerview_title);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    /**
     * 提供方法供外部调用
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
