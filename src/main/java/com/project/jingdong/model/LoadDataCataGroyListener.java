package com.project.jingdong.model;

import com.project.jingdong.bean.CatagoryBean;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadDataCataGroyListener {
    void loadCatagroySuccess(CatagoryBean catagoryBean);//加载成功

    void loadCatagroyFailed(CatagoryBean catagoryBean);//加载失败
}
