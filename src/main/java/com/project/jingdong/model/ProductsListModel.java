package com.project.jingdong.model;

import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.ProductsListBean;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.utils.Request_Interface;
import com.project.jingdong.utils.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Author:AND
 * Time:2018/2/9.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class ProductsListModel implements ProductsList {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //加载分类列表数据
    @Override
    public void getProductsList(String url, String pscid, final LoadDataProductsList loadDataProductsList) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<ProductsListBean> productsList = request_interface.getProductsList(pscid);
        productsList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<ProductsListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductsListBean productsListBean) {
                        loadDataProductsList.loadSuccess(productsListBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //获取详情信息
    @Override
    public void getProductDetail(String url, String pid, final LoadDataProductsList loadDataProductsList) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        HashMap<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("pid", pid);
        Observable<ProductDetailBean> productsDetail = request_interface.getProductsDetail(map);
        //开始请求
        productsDetail.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ProductDetailBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductDetailBean productDetailBean) {
                loadDataProductsList.loadDetailSuccess(productDetailBean);
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
    public void getUserInfo(String url, final LoadNetDataListener<UserInfoDetail> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装
        Observable<UserInfoDetail> userInfo = request_interface.getUserInfo("");
        //开始请求
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

    @Override
    public void addToCart(String url, Map<String, String> params, final LoadNetDataListener<AddToCartBena> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<AddToCartBena> addToCartBenaObservable = request_interface.addToCart(params);
        //开始请求
        addToCartBenaObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AddToCartBena>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddToCartBena addToCartBena) {
                loadNetDataListener.loadSuccess(addToCartBena);
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
