package com.project.jingdong.model;

import com.project.jingdong.bean.HomePosterBean;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadDataPosterListener {
    void loadDataSuccess(HomePosterBean homePosterBean);  //数据加载成功

    void loadDataFailed(HomePosterBean homePosterBean);    //数据加载失败
}
