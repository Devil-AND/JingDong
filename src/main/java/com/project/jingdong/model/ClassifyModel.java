package com.project.jingdong.model;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface ClassifyModel {
    //获取商品分类数据
    void getCatagroy(String url, LoadDataCataGroyListener loadDataCataGroyListener);

    //获取商品列表数据
    void getProductCataGroy(String url, String cid, LoadDataProductCataGroy loadDataProductCataGroy);
}
