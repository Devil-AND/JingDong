package com.project.jingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.project.jingdong.R;

import java.util.ArrayList;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:热搜适配器
 * Detail:
 */

public class HotSearchAdapters extends RecyclerView.Adapter<HotSearchAdapters.MyViewHolder> {
    private Context context;
    private ArrayList<String> list;
    private OnItemClickListener mOnItemClickListener;

    public HotSearchAdapters(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.hot_searci_view, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mSearchBtnHot.setText(list.get(position));
        holder.mSearchBtnHot.setOnClickListener(new View.OnClickListener() {
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

        private Button mSearchBtnHot;

        public MyViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mSearchBtnHot = (Button) itemView.findViewById(R.id.hot_search_btn);
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
