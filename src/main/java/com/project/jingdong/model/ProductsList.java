package com.project.jingdong.model;

import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.constant.LoadNetDataListener;

import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface ProductsList {
    //加载列表信息
    void getProductsList(String url, String pscid, LoadDataProductsList loadDataProductsList);

    //加载详情信息
    void getProductDetail(String url, String pid, LoadDataProductsList loadDataProductsList);

    //获取登录信息
    void getUserInfo(String url, LoadNetDataListener<UserInfoDetail> loadNetDataListener);

    //添加到购物车
    void addToCart(String url, Map<String, String> params, LoadNetDataListener<AddToCartBena> loadNetDataListener);
}
