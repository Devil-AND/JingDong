package com.project.jingdong.presenter;

import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.model.UserInfoManager;
import com.project.jingdong.view.UserInfoView;

/**
 * Author:AND
 * Time:2018/3/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class UserInfoPresenter {
    private UserInfoManager userInfoManager;
    private UserInfoView userInfoView;

    public UserInfoPresenter(UserInfoView userInfoView) {
        this.userInfoManager = new UserInfoManager();
        this.userInfoView = userInfoView;
    }

    /**
     * 获取用户登录信息
     */
    public void getUserInfo(String uid) {
        userInfoManager.getUserInfoData(Constant.getUserInfo, uid, new LoadNetDataListener<UserInfoDetail>() {
            @Override
            public void loadSuccess(UserInfoDetail data) {
                userInfoView.getUserInfo(data);
            }

            @Override
            public void loadFailed(UserInfoDetail data) {

            }
        });

    }
}
