package com.project.jingdong.model;

import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.utils.Request_Interface;
import com.project.jingdong.utils.RetroFitManagetUpdate;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:AND
 * Time:2018/3/9.
 * Email:2911743255@qq.com
 * Description:用户登录请求层
 * Detail:
 */

public class UserInfoManager {
    //获取用户信息
    public void getUserInfoData(String url, String uid, final LoadNetDataListener<UserInfoDetail> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetroFitManagetUpdate.getInstance(url).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UserInfoDetail> userInfo = request_interface.getUserInfo(uid);
        userInfo.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserInfoDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserInfoDetail userInfoDetail) {
                loadNetDataListener.loadSuccess(userInfoDetail);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
