package com.project.jingdong.model;

import com.project.jingdong.bean.ProductCatagory;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadDataProductCataGroy {
    void loadSuccess(ProductCatagory productCatagory);//加载成功

    void loadFailed(ProductCatagory productCatagory);//加载失败
}
