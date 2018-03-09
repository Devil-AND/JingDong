package com.project.jingdong.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.jingdong.R;
import com.project.jingdong.bean.ProductsListBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.MyViewHolder> {
    private List<ProductsListBean.DataBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public ProductsListAdapter(List<ProductsListBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.classify_productslist_view, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //赋值
        holder.mTitleProducts.setText(list.get(position).getTitle() + "");
        holder.mPriceProducts.setText("¥" + list.get(position).getPrice() + "");
        holder.mTexttitle.setText("查看同款拍拍二手");
        holder.mPriceProducts.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        holder.mBargainPriceProducts.setText("¥" + list.get(position).getBargainPrice() + "   秒杀,分期免息");
        String images = list.get(position).getImages();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.mIconProducts);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = list.get(position).getPid();
                mOnItemClickListener.onClick(pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleProducts;
        private TextView mBargainPriceProducts;
        private TextView mPriceProducts;
        private ImageView mIconProducts;
        private TextView mTexttitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mTitleProducts = (TextView) itemView.findViewById(R.id.products_title);
            mBargainPriceProducts = (TextView) itemView.findViewById(R.id.products_bargainPrice);
            mPriceProducts = (TextView) itemView.findViewById(R.id.products_price);
            mIconProducts = (ImageView) itemView.findViewById(R.id.products_icon);
            mTexttitle = (TextView) itemView.findViewById(R.id.products_paipai);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
