package com.project.jingdong.model;

import com.project.jingdong.bean.HomePosterBean;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadMiaoshaListener {
    //请求成功
    void loadSuccess(HomePosterBean homePosterBean);

    //请求失败
    void loadFailed(HomePosterBean homePosterBean);
}
