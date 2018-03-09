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
import com.project.jingdong.bean.HomePosterBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class TuijianAdapter extends RecyclerView.Adapter<TuijianAdapter.MyViewHolder> {
    private static final String TAG = "适配器";
    private List<HomePosterBean.TuijianBean.ListBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public TuijianAdapter(List<HomePosterBean.TuijianBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.home_tuijian_view, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] split = images.split("[|]");
        //开始赋值
        holder.mTitleTuijian.setText(list.get(position).getTitle());
        Glide.with(context).load(split[0]).into(holder.mImageTuijian);

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


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageTuijian;
        private TextView mTitleTuijian;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }


        private void initView(@NonNull final View itemView) {
            mImageTuijian = (ImageView) itemView.findViewById(R.id.tuijian_image);
            mTitleTuijian = (TextView) itemView.findViewById(R.id.tuijian_title);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}


