package com.project.jingdong.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.project.jingdong.customview.CustomView;
import com.project.jingdong.R;
import com.project.jingdong.activities.ProductDetailActivity;
import com.project.jingdong.activities.SearchGoodsActivity;
import com.project.jingdong.activities.WebViewLoad;
import com.project.jingdong.adapters.CataGroyAdapter;
import com.project.jingdong.adapters.MiaoshaAdapter;
import com.project.jingdong.adapters.TuijianAdapter;
import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.customview.NoticeView;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.presenter.Home_Presenter;
import com.project.jingdong.utils.GridSpacingItemDecoration;
import com.project.jingdong.view.Home_View;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:首页
 * Detail:
 */

public class HomeFragment extends Fragment implements Home_View {
    private static final String TAG = "滑动";
    private ArrayList<String> bannerList = new ArrayList<String>();
    private ArrayList<String> bannerUrlList = new ArrayList<String>();
    private Banner mBanner;
    private RecyclerView catagoryRecyclerview;
    private HomeLoadData homeLoadData;
    private RecyclerView mTuijianrecyclerview;
    private TextView mTime;
    private RecyclerView miaosharecyclerview;
    private ScrollView homescrollview;
    private long mHour = 02;
    private long mMin = 15;
    private long mSecond = 36;
    String hourStr;
    String minuteStr;
    String secondStr;
    private CustomView mHomescrollview;
    private LinearLayout line;
    private int imageHeight = 200; //设置渐变高度，一般为导航图片高度，自己控制
    private EditText mEditSearch;
    private ScrollView mScrollviewHomw;
    private Home_Presenter home_presenter;
    private LinearLayout mNestToolBarLl;
    private NoticeView mViewNotice;
    private TextView mMiaoshaTv;
    private TextView mMiaoshaTimeTv;
    private TextView mMiaoshaShiTv;
    private TextView mMiaoshaMinterTv;
    private TextView mMiaoshaSecondTv;

    //使用handler用于更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            countDown();
            sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_fragment, container, false);
        homeLoadData = new HomeLoadData();
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //搜索框在布局最上面
        line.bringToFront();
        mHomescrollview.setScrollViewListener(new CustomView.ScrollViewListener() {
            @Override
            public void onScrollChanged(CustomView customView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    //获取ScrollView向下滑动图片消失的比例
                    float scale = (float) y / imageHeight;
                    //更加这个比例,让标题颜色由浅入深
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    line.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                }
            }
        });
        mEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchGoodsActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }
        });
        init();
        startCountDown();
    }

    /**
     * 开启秒杀
     */
    private void countDown() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Log.d("ccc", substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            mMiaoshaTimeTv.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            mMiaoshaTimeTv.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            java.util.Date date = df.parse(totime);
            java.util.Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            mMiaoshaShiTv.setText("0" + hours + "");
            if (minute >= 10) {
                mMiaoshaMinterTv.setText(minute + "");
            } else {
                mMiaoshaMinterTv.setText("0" + minute + "");
            }
            if (second >= 10) {
                mMiaoshaSecondTv.setText(second + "");
            } else {
                mMiaoshaSecondTv.setText("0" + second + "");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void startCountDown() {
        handler.sendEmptyMessage(0);
    }

    private void init() {
        NoticeView noticeView = (NoticeView) getActivity().findViewById(R.id.notice_view);
        List<String> notices = new ArrayList<>();
        notices.add("小晴,我想和你一直在一起");
        notices.add("大促销下单拆福袋，亿万新年红包随便拿");
        notices.add("家电五折团，抢十亿无门槛现金红包");
        notices.add("星球大战剃须刀首发送200元代金券");
        noticeView.addNotice(notices);
        noticeView.startFlipping();
    }

    @Override
    public void onResume() {
        super.onResume();
        home_presenter = new Home_Presenter();
        //开启轮播图
        home_presenter.startBanner(homeLoadData, this);


    }

    private void initView(@NonNull final View itemView) {
        mBanner = (Banner) itemView.findViewById(R.id.banner);
        catagoryRecyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        mTuijianrecyclerview = (RecyclerView) itemView.findViewById(R.id.tuijianrecyclerview);
        miaosharecyclerview = itemView.findViewById(R.id.miaosharecyclerview);
        mTime = (TextView) itemView.findViewById(R.id.time);
        mHomescrollview = (CustomView) itemView.findViewById(R.id.homescrollview);
        line = (LinearLayout) itemView.findViewById(R.id.line);
        mEditSearch = (EditText) itemView.findViewById(R.id.search_edit);
        mScrollviewHomw = (ScrollView) itemView.findViewById(R.id.homw_scrollview);
        mScrollviewHomw.setVerticalScrollBarEnabled(false);
        mNestToolBarLl = (LinearLayout) itemView.findViewById(R.id.ll_nest_toolBar);
        mViewNotice = (NoticeView) itemView.findViewById(R.id.notice_view);
        mMiaoshaTv = (TextView) itemView.findViewById(R.id.tv_miaosha);
        mMiaoshaTimeTv = (TextView) itemView.findViewById(R.id.tv_miaosha_time);
        mMiaoshaShiTv = (TextView) itemView.findViewById(R.id.tv_miaosha_shi);
        mMiaoshaMinterTv = (TextView) itemView.findViewById(R.id.tv_miaosha_minter);
        mMiaoshaSecondTv = (TextView) itemView.findViewById(R.id.tv_miaosha_second);
    }

    //开启轮播
    @Override
    public void startBanner(HomePosterBean homePosterBean) {
        //解析数据
        List<HomePosterBean.DataBean> data = homePosterBean.getData();
        if (bannerList.isEmpty() || bannerList.size() == 0) {
            for (int i = 0; i < data.size(); i++) {
                String icon = data.get(i).getIcon();
                bannerList.add(icon);
                bannerUrlList.add(data.get(i).getUrl());
            }
        }
        mBanner.setImageLoader(new ImageLoader() {//配置重写好的加载图片的类
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage((String) path, imageView);
            }
        });
        mBanner.setImages(bannerList);//传入一个图片的集合
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(4500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();//开启轮播
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewLoad.class);
                intent.putExtra("url", bannerUrlList.get(position));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }
        });
        //商品分类九宫格
        home_presenter.catagory(homeLoadData, this);
    }

    //显示九宫格分类
    @Override
    public void showCatagroy(CatagoryBean catagoryBean) {
        List<CatagoryBean.DataBean> data = catagoryBean.getData();
        CataGroyAdapter cataGroyAdapter = new CataGroyAdapter(getActivity(), data);
        catagoryRecyclerview.setAdapter(cataGroyAdapter);//设置适配器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
        catagoryRecyclerview.setLayoutManager(gridLayoutManager);
        //京东秒杀
        home_presenter.miaoshaMessage(homeLoadData, this);

    }

    /**
     * 为你推荐
     */
    @Override
    public void showTuijian(HomePosterBean homePosterBean) {
        //解析数据
        HomePosterBean.TuijianBean tuijian = homePosterBean.getTuijian();
        final List<HomePosterBean.TuijianBean.ListBean> data = tuijian.getList();
        //获取适配器
        TuijianAdapter tuijianAdapter = new TuijianAdapter(data, getActivity());
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        mTuijianrecyclerview.setAdapter(tuijianAdapter);
        mTuijianrecyclerview.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mTuijianrecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        //删除事件
        tuijianAdapter.setOnItemClickListener(new TuijianAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //获取详细信息
                int pid = data.get(position).getPid();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }

        });
    }

    //京东秒杀
    @Override
    public void miaoshaMessage(HomePosterBean homePosterBean) {

        final List<HomePosterBean.MiaoshaBean.ListBeanX> list = homePosterBean.getMiaosha().getList();
        //获取适配器
        MiaoshaAdapter miaoshaAdapter = new MiaoshaAdapter(list, getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        miaosharecyclerview.setAdapter(miaoshaAdapter);
        miaosharecyclerview.setLayoutManager(layoutManager);

        //点击事件
        miaoshaAdapter.setOnItemClickListener(new MiaoshaAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                int pid = list.get(position).getPid();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("pid", pid + "");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        //为你推荐
        home_presenter.tuijianProduct(homeLoadData, this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }
}
