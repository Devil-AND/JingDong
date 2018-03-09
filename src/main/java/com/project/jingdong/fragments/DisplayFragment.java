package com.project.jingdong.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.project.jingdong.R;
import com.project.jingdong.activities.WebViewLoad;
import com.project.jingdong.adapters.DisplayAdapter;
import com.project.jingdong.bean.DisplayNewsBean;
import com.project.jingdong.model.DisplayDataModel;
import com.project.jingdong.presenter.DisplayPresenter;
import com.project.jingdong.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:首页
 * Detail:
 */

public class DisplayFragment extends Fragment {

    //安卓处理数据
    private String url = "http://gank.io/api/data/Android/10/";
    private static final String TAG = "发现";
    private XRecyclerView mXrelDisplay;
    private DisplayPresenter displayPresenter;
    private DisplayDataModel displayDataModel;
    private List<DisplayNewsBean.ResultsBean> list = new ArrayList<>();
    private int anInt = 1;
    private DisplayAdapter displayAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.display_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mXrelDisplay = (XRecyclerView) itemView.findViewById(R.id.display_xrel);
        mXrelDisplay.setLoadingMoreEnabled(true);//允许加载更多
        mXrelDisplay.setPullRefreshEnabled(true);//允许刷新
        mXrelDisplay.isScrollbarFadingEnabled();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //xrecyclerview滑动监听
        mXrelDisplay.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getNetData(1);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mXrelDisplay.refreshComplete();
                    }
                });

            }

            @Override
            public void onLoadMore() {
                anInt+=1;
                getNetData(anInt);
                mXrelDisplay.loadMoreComplete();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        //实例化asynctask类
        getNetData(anInt);
        displayAdapter = new DisplayAdapter(list, getActivity());

        displayAdapter = new DisplayAdapter(list, getActivity());
        mXrelDisplay.setAdapter(displayAdapter);
        mXrelDisplay.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加条目的变更动画(这个动画也是系统默认的,也可以自定义)
        mXrelDisplay.setItemAnimator(new DefaultItemAnimator());
        displayAdapter.notifyDataSetChanged();
        displayAdapter.setOnItemClickListener(new DisplayAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                String url = list.get(position).getUrl();
                Intent intent = new Intent(getActivity(), WebViewLoad.class);
                intent.putExtra("url", url);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }
        });

    }

    private void getNetData(int i) {
        //实例化asynctask类
        new MAsyncTask().execute(url + i);
    }


    /**
     * String 传递的url连接
     * Void  进度值
     * String   接口请求成功返回的数据
     */
    private class MAsyncTask extends AsyncTask<String, Void, String> {
        //此方法在子线程中运行,主要执行联网操作
        //String  可变参数
        @Override
        protected String doInBackground(String... strings) {
            String urltemp = strings[0];
            //使用utils解析json
            String getmsg = NetUtils.getmsg(urltemp);
            //把转换的json串返回给前台方法
            return getmsg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //实例化gson对象
            Gson gson = new Gson();
            //解析json数据
            DisplayNewsBean displayNewsBean = gson.fromJson(s, DisplayNewsBean.class);
            //封装进集合
            List<DisplayNewsBean.ResultsBean> results = displayNewsBean.getResults();
            //添加进 另一个集合
            list.addAll(results);
            //刷新适配器
            displayAdapter.notifyDataSetChanged();
        }
    }
}
