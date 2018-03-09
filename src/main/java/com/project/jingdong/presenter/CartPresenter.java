package com.project.jingdong.presenter;

import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.bean.UpdateCartBean;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.model.CartsListsModel;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.model.LoadTuijianListener;
import com.project.jingdong.view.CartView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartPresenter {
    private CartsListsModel cartsListsModel;
    private CartView cartView;

    public CartPresenter(CartView cartView) {
        this.cartsListsModel = new CartsListsModel();
        this.cartView = cartView;
    }

    //为你推荐
    public void tuijianProduct(HomeLoadData homeLoadData, final CartView cartView) {
        homeLoadData.getTuijian(Constant.Poster_Url, new LoadTuijianListener() {
            @Override
            public void loadSuccess(HomePosterBean homePosterBean) {
                cartView.showTuijian(homePosterBean);
            }

            @Override
            public void loadFailed(HomePosterBean homePosterBean) {

            }
        });
    }

    //获取购物车
    public void getCartLists(String uid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("uid", uid);
        cartsListsModel.getCartsList(Constant.GetCarts, map, new LoadNetDataListener<CartListBean>() {
            @Override
            public void loadSuccess(CartListBean data) {
                cartView.showCartList(data);
            }

            @Override
            public void loadFailed(CartListBean data) {

            }
        });
    }

    /**
     * 刷新购物车
     */

    public void updateCart(HashMap<String, String> params) {
        cartsListsModel.updateCartData(Constant.GetCarts, params, new LoadNetDataListener<UpdateCartBean>() {
            @Override
            public void loadSuccess(UpdateCartBean data) {
                cartView.updateCartData();
            }

            @Override
            public void loadFailed(UpdateCartBean data) {

            }
        });
    }

    /**
     * 显示数量和价钱
     */
    public void showCountAndPrice(List<CartListBean.DataBean> list) {
        double v = cartsListsModel.settleAcount(list);
        double round = round(v, 2);
        cartView.priceAndNum(round);//将结果展示在页面
    }

    /**
     * 删除购物车
     */
    public void deleteCart(String pid) {
        cartsListsModel.deleteCartData(Constant.GetCarts, pid, new LoadNetDataListener<UpdateCartBean>() {
            @Override
            public void loadSuccess(UpdateCartBean data) {
                cartView.updateCartData();
            }

            @Override
            public void loadFailed(UpdateCartBean data) {

            }
        });
    }

    //精准到小数两位
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
