package com.project.jingdong.model;

import com.project.jingdong.bean.UserInfoDetail;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface UserLoginListener {
    void loginSuccess(UserInfoDetail userInfoDetail);//登录成功

    void loginFailed(UserInfoDetail userInfoDetail);//登录失败
}
