package com.project.jingdong.presenter;

import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.model.LoadTuijianListener;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.view.MySelfView;

/**
 * Author:AND
 * Time:2018/2/7.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class MySelfPresenter {
    //为你推荐
    public void tuijianProduct(HomeLoadData homeLoadData, final MySelfView mySelfView) {
        homeLoadData.getTuijian(Constant.Poster_Url, new LoadTuijianListener() {
            @Override
            public void loadSuccess(HomePosterBean homePosterBean) {
                mySelfView.showTuijian(homePosterBean);
            }

            @Override
            public void loadFailed(HomePosterBean homePosterBean) {

            }
        });
    }
}
