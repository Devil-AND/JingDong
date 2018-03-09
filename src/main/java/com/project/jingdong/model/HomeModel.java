package com.project.jingdong.model;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:请求逻辑
 * Detail:
 */

public interface HomeModel {
    //获取轮播数据
    void getPoster(String url, LoadDataPosterListener loadDataPosterListener);

    //获取商品分类数据
    void getCatagroy(String url, LoadDataCataGroyListener loadDataCataGroyListener);

    //获取为你推荐数据
    void getTuijian(String url, LoadTuijianListener loadTuijianListener);

    //获取秒杀数据
    void getMiaosha(String url, LoadMiaoshaListener loadMiaoshaListener);
}
