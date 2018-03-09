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
import com.project.jingdong.bean.SearchBeanResult;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class SearchDetailAdapter extends RecyclerView.Adapter<SearchDetailAdapter.MyViewHolder> {
    private Context context;
    private List<SearchBeanResult.DataBean> list;
    private OnItemClickListener mOnItemClickListener;

    public SearchDetailAdapter(Context context, List<SearchBeanResult.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.classify_productslist_view, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getImages().split("[|]")[0]).into(holder.mIconProducts);
        holder.mBargainPriceProducts.setText(" ¥:" + list.get(position).getBargainPrice() + "");
        holder.mPriceProducts.setText(list.get(position).getPrice() + "");
        holder.mTitleProducts.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(list.get(position).getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIconProducts;
        private TextView mTitleProducts;
        private TextView mBargainPriceProducts;
        private TextView mPriceProducts;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mIconProducts = (ImageView) itemView.findViewById(R.id.products_icon);
            mTitleProducts = (TextView) itemView.findViewById(R.id.products_title);
            mBargainPriceProducts = (TextView) itemView.findViewById(R.id.products_bargainPrice);
            mPriceProducts = (TextView) itemView.findViewById(R.id.products_price);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
