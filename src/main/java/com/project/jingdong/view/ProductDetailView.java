package com.project.jingdong.view;

import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.UserInfoDetail;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface ProductDetailView {
    //展示商品详情
    void loadProductDetail(ProductDetailBean productDetailBean);

    //显示登录信息
    void userLogin(UserInfoDetail userino);

    //显示添加结果
    void addResult(AddToCartBena addToCartBena);
}
