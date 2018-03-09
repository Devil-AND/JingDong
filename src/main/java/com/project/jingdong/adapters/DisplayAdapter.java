package com.project.jingdong.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.project.jingdong.R;
import com.project.jingdong.bean.DisplayNewsBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/12.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {
    private List<DisplayNewsBean.ResultsBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public DisplayAdapter(List<DisplayNewsBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.display_item_view, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        List<String> images = list.get(position).getImages();
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //设置uri,加载本地的gif资源
                .setUri(Uri.parse("http://img.gank.io/e0d29181-282e-4465-9965-1da81e0557d9"))//设置uri
                .build();
        //设置Controller
        holder.m1Sdv.setController(mDraweeController);
        holder.mAuthorNews.setText("Author: " + list.get(position).getWho() + "");
        holder.mTimeNews.setText("发布时间:" + list.get(position).getPublishedAt() + "");
        holder.mDescNews.setText("描述:" + list.get(position).getDesc() + "");
        //点击事件
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

        private SimpleDraweeView m1Sdv;
        private TextView mTimeNews;
        private TextView mAuthorNews;
        private TextView mDescNews;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            m1Sdv = (SimpleDraweeView) itemView.findViewById(R.id.sdv_1);
            mTimeNews = (TextView) itemView.findViewById(R.id.news_time);
            mAuthorNews = (TextView) itemView.findViewById(R.id.news_author);
            mDescNews = (TextView) itemView.findViewById(R.id.news_desc);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
