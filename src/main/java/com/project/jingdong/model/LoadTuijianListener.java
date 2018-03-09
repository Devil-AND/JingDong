package com.project.jingdong.model;

import com.project.jingdong.bean.HomePosterBean;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadTuijianListener {
    //加载成功
    void loadSuccess(HomePosterBean homePosterBean);

    //加载失败
    void loadFailed(HomePosterBean homePosterBean);
}
