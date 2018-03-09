package com.project.jingdong.constant;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface LoadNetDataListener<T> {
    void loadSuccess(T data);//加载成功

    void loadFailed(T data);//加载失败
}
