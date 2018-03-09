package com.project.jingdong.view;

import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.HomePosterBean;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface Home_View {
    //开启轮播
    void startBanner(HomePosterBean homePosterBean);

    //商品分类
    void showCatagroy(CatagoryBean catagoryBean);

    //为你推荐数据
    void showTuijian(HomePosterBean homePosterBean);

    //秒杀数据
    void miaoshaMessage(HomePosterBean homePosterBean);
}
