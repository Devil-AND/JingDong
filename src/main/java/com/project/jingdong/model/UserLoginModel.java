package com.project.jingdong.model;

import com.project.jingdong.bean.UserRegisterBean;
import com.project.jingdong.constant.LoadNetDataListener;

import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface UserLoginModel {
    //登录用户
    void userLogin(String url, Map<String, String> params, UserLoginListener userLoginListener);

    //注册用户
    void userReg(String url, Map<String, String> params, LoadNetDataListener<UserRegisterBean> loadNetDataListener);
}
