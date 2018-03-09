package com.project.jingdong.view;

import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.ProductCatagory;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface ClassifyView {
    //显示分类数据
    void showCatagroy(CatagoryBean catagoryBean);

    //显示列表信息
    void showProductCataCroy(ProductCatagory productCatagory,List<List<ProductCatagory.DataBean.ListBean>> list);
}
