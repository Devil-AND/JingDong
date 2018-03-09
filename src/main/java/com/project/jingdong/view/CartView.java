package com.project.jingdong.view;

import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.HomePosterBean;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface CartView {
    //为你推荐数据
    void showTuijian(HomePosterBean homePosterBean);

    //显示购物车数据
    void showCartList(CartListBean cartListBean);

    //刷新购物车
    void updateCartData();


    void priceAndNum(double round);//结算
}
