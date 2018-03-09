package com.project.jingdong.model;

import com.project.jingdong.bean.DisplayNewsBean;
import com.project.jingdong.constant.LoadNetDataListener;

/**
 * Author:AND
 * Time:2018/2/12.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface DisplayModel {
    //请求发现页数据
    void getAndridNewsData(String url,int page, LoadNetDataListener<DisplayNewsBean> loadNetDataListener);
}
