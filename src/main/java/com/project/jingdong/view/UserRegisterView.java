package com.project.jingdong.view;

import com.project.jingdong.bean.UserRegisterBean;

/**
 * Author:AND
 * Time:2018/2/13.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface UserRegisterView {
    String getUserMobile();//获取用户手机号

    String getUserPassword();//获取用户密码

    void registerSuccess(UserRegisterBean userRegisterBean);

    void registerFailed(UserRegisterBean userRegisterBean);
}
