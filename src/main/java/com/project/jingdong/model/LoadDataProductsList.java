package com.project.jingdong.model;

import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.ProductsListBean;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadDataProductsList {
    void loadSuccess(ProductsListBean productsListBean);//加载成功

    void loadFailed(ProductsListBean productsListBean);//加载失败

    void loadDetailSuccess(ProductDetailBean productDetailBean);//详情信息加载成功

    void loadDetailFailed(ProductDetailBean productDetailBean);//详情信息加载失败
}
