package com.project.jingdong.model;

import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.UpdateCartBean;
import com.project.jingdong.constant.LoadNetDataListener;

import java.util.List;
import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface GetCartList {
    //加载购物车数据
    void getCartsList(String url, Map<String, String> map, LoadNetDataListener<CartListBean> loadNetDataListener);

    //更新购物车
    void updateCartData(String url, Map<String, String> params, LoadNetDataListener<UpdateCartBean> loadNetDataListener);

    //删除购物车
    void deleteCartData(String url, String pid, LoadNetDataListener<UpdateCartBean> loadNetDataListener);

    //结算购物车
    double settleAcount(List<CartListBean.DataBean> list);
}
