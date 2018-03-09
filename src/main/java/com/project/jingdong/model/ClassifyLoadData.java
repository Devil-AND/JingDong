package com.project.jingdong.model;

import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.ProductCatagory;
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
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:分类页加载数据
 * Detail:
 */

public class ClassifyLoadData implements ClassifyModel {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //商品导航栏信息
    @Override
    public void getCatagroy(String url, final LoadDataCataGroyListener loadDataCataGroyListener) {
        //封装retrofit对象
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
    public void getProductCataGroy(String url, String cid, final LoadDataProductCataGroy loadDataProductCataGroy) {
        //封装retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<ProductCatagory> productCataGory = request_interface.getProductCataGory(cid);
        //开始请求
        productCataGory.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductCatagory>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductCatagory productCatagory) {
                //数据请求成功
                loadDataProductCataGroy.loadSuccess(productCatagory);
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
