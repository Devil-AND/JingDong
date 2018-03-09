package com.project.jingdong.presenter;

import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.model.LoadDataCataGroyListener;
import com.project.jingdong.model.LoadDataPosterListener;
import com.project.jingdong.model.LoadMiaoshaListener;
import com.project.jingdong.model.LoadTuijianListener;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.view.Home_View;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class Home_Presenter {
    private static final String TAG = "presenter";

    //开启轮播
    public void startBanner(HomeLoadData homeLoadData, final Home_View home_view) {
        homeLoadData.getPoster(Constant.Poster_Url, new LoadDataPosterListener() {
            @Override
            public void loadDataSuccess(HomePosterBean homePosterBean) {
                home_view.startBanner(homePosterBean);
            }

            @Override
            public void loadDataFailed(HomePosterBean homePosterBean) {

            }

        });
    }

    //商品九宫格分类
    public void catagory(HomeLoadData homeLoadData, final Home_View home_view) {
        homeLoadData.getCatagroy(Constant.getCatagory_Url, new LoadDataCataGroyListener() {
            @Override
            public void loadCatagroySuccess(CatagoryBean catagoryBean) {
                home_view.showCatagroy(catagoryBean);
            }

            @Override
            public void loadCatagroyFailed(CatagoryBean catagoryBean) {

            }
        });
    }

    //为你推荐
    public void tuijianProduct(HomeLoadData homeLoadData, final Home_View home_view) {
        homeLoadData.getTuijian(Constant.Poster_Url, new LoadTuijianListener() {
            @Override
            public void loadSuccess(HomePosterBean homePosterBean) {
                home_view.showTuijian(homePosterBean);
            }

            @Override
            public void loadFailed(HomePosterBean homePosterBean) {

            }
        });
    }

    //秒杀
    public void miaoshaMessage(HomeLoadData homeLoadData, final Home_View home_view) {
        homeLoadData.getMiaosha(Constant.Poster_Url, new LoadMiaoshaListener() {
            @Override
            public void loadSuccess(HomePosterBean homePosterBean) {
                home_view.miaoshaMessage(homePosterBean);
            }

            @Override
            public void loadFailed(HomePosterBean homePosterBean) {

            }
        });
    }
}
