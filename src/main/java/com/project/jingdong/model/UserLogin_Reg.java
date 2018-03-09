package com.project.jingdong.model;

import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.bean.UserRegisterBean;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.utils.Request_Interface;
import com.project.jingdong.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class UserLogin_Reg implements UserLoginModel {
    private static final String TAG = "注册model";
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //登录用户
    @Override
    public void userLogin(String url, Map<String, String> params, final UserLoginListener userLoginListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UserInfoDetail> userInfoObservable = request_interface.userLogin(params);
        //开始请求
        userInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserInfoDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserInfoDetail userInfoDetail) {
                userLoginListener.loginSuccess(userInfoDetail);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //注册用户
    @Override
    public void userReg(String url, Map<String, String> params, final LoadNetDataListener<UserRegisterBean> loadNetDataListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UserRegisterBean> userInfoObservable = request_interface.userRegister(params);
        //开始请求
        userInfoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UserRegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(UserRegisterBean userRegisterBean) {
                loadNetDataListener.loadSuccess(userRegisterBean);
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
