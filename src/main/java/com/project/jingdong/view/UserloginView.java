package com.project.jingdong.view;

import com.project.jingdong.bean.UserInfoDetail;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface UserloginView {
    String getUserMobile();//获取用户手机号

    String getUserPassword();//获取用户密码

    void loginSuccess(UserInfoDetail userInfoDetail);//登录成功

    void loginFailed(UserInfoDetail userInfoDetail);//登录失败


}
