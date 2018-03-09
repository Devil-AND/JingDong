package com.project.jingdong.model;


import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.utils.Request_Interface;
import com.project.jingdong.utils.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:业务具体逻辑实现
 * Detail:
 */

public class HomeLoadData implements HomeModel {
    private static final String TAG = "model层";
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //获取首页轮播图数据
    @Override
    public void getPoster(String url, final LoadDataPosterListener loadDataPosterListener) {
        //创建retrofit
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<HomePosterBean> poster = request_interface.getPoster();
        //开始请求
        poster.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomePosterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomePosterBean homePosterBean) {
                loadDataPosterListener.loadDataSuccess(homePosterBean);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void getCatagroy(String url, final LoadDataCataGroyListener loadDataCataGroyListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<CatagoryBean> productCatagory = request_interface.getProductCatagory();
        //开始请求
        productCatagory.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CatagoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CatagoryBean catagoryBean) {
                loadDataCataGroyListener.loadCatagroySuccess(catagoryBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void getTuijian(String url, final LoadTuijianListener loadTuijianListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<HomePosterBean> tuijian = request_interface.getTuijian();
        //开始请求
        tuijian.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomePosterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomePosterBean homePosterBean) {
                loadTuijianListener.loadSuccess(homePosterBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //获取秒杀数据
    @Override
    public void getMiaosha(String url, final LoadMiaoshaListener loadMiaoshaListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<HomePosterBean> miaosha = request_interface.miaosha();
        miaosha.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<HomePosterBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomePosterBean homePosterBean) {
                //请求成功
                loadMiaoshaListener.loadSuccess(homePosterBean);
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
