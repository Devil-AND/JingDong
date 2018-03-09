package com.project.jingdong.model;

import com.project.jingdong.bean.SearchBeanResult;
import com.project.jingdong.constant.LoadNetDataListener;

import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface SearchModel {
    //搜索商品
    void searchGoods(String url, Map<String, String> map, LoadNetDataListener<SearchBeanResult> loadNetDataListener);
}
