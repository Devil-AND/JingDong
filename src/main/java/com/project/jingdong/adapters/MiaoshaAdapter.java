package com.project.jingdong.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.jingdong.R;
import com.project.jingdong.bean.HomePosterBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class MiaoshaAdapter extends RecyclerView.Adapter<MiaoshaAdapter.MyviewHolder> {
    private List<HomePosterBean.MiaoshaBean.ListBeanX> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public MiaoshaAdapter(List<HomePosterBean.MiaoshaBean.ListBeanX> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.miaosha_view, null);
        MyviewHolder myviewHolder = new MyviewHolder(inflate);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("[|]");
        Glide.with(context).load(split[0]).into(holder.mImageMiaosha);
        holder.mBargainPrice.setText("¥" + list.get(position).getBargainPrice());//优惠价
        holder.mBargainPrice.setTextColor(Color.RED);//设置字体颜色
        holder.price.setText("¥" + list.get(position).getPrice());
        holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageMiaosha;
        private TextView mBargainPrice;
        private TextView price;

        public MyviewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {

            mImageMiaosha = (ImageView) itemView.findViewById(R.id.miaosha_image);
            mBargainPrice = (TextView) itemView.findViewById(R.id.bargainPrice);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);

        void onLongClick(int position);
    }

    public void setOnItemClickListener(MiaoshaAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
