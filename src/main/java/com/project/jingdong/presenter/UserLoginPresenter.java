package com.project.jingdong.presenter;

import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.bean.UserRegisterBean;
import com.project.jingdong.model.UserLoginListener;
import com.project.jingdong.model.UserLogin_Reg;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.view.UserRegisterView;
import com.project.jingdong.view.UserloginView;

import java.util.HashMap;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class UserLoginPresenter {

    private static final String TAG = "注册P层";

    public void userLogin(UserLogin_Reg userLogin_reg, final UserloginView userloginView) {
        //获取用户输入的信息
        String userMobile = userloginView.getUserMobile();
        String userPassword = userloginView.getUserPassword();
        //将信息转换为参数
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", userMobile);
        map.put("password", userPassword);
        userLogin_reg.userLogin(Constant.Login_Url, map, new UserLoginListener() {
            @Override
            public void loginSuccess(UserInfoDetail userInfoDetail) {
                String code = userInfoDetail.getCode();
                if (code.equals("0")) {//登录成功
                    userloginView.loginSuccess(userInfoDetail);
                    return;
                }
                if (code.equals("1")) {
                    userloginView.loginFailed(userInfoDetail);
                    return;
                }
            }

            @Override
            public void loginFailed(UserInfoDetail userInfoDetail) {
            }
        });
    }

    public void userRegister(UserLogin_Reg userLogin_reg, final UserRegisterView userRegisterView) {
        //获取用户输入的信息
        String userMobile = userRegisterView.getUserMobile();
        String userPassword = userRegisterView.getUserPassword();
        //将信息转换为参数
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", userMobile);
        params.put("password", userPassword);
        userLogin_reg.userReg(Constant.Reg_Url, params, new LoadNetDataListener<UserRegisterBean>() {
            @Override
            public void loadSuccess(UserRegisterBean data) {
                String code = data.getCode();
                if (code.equals("0")) {//登录成功
                    userRegisterView.registerSuccess(data);
                    return;
                }
                if (code.equals("1")) {
                    userRegisterView.registerFailed(data);
                    return;
                }
            }

            @Override
            public void loadFailed(UserRegisterBean data) {

            }
        });
    }
}
