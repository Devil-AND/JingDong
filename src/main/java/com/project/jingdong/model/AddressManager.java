package com.project.jingdong.model;

import android.util.Log;

import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;
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
 * Time:2018/2/14.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class AddressManager implements AddressModel {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //获取收货地址列表
    @Override
    public void getAddressList(String url, String uid, final LoadNetDataListener<ShippingAddresBean> loadNetDataListener) {
        //封装retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<ShippingAddresBean> address = request_interface.getAddress(uid);
        address.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ShippingAddresBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShippingAddresBean shippingAddresBean) {
                loadNetDataListener.loadSuccess(shippingAddresBean);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("订单列表", "onError: ----------------------" + e.toString());

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //添加收货地址
    @Override
    public void addNewsAddress(String url, Map<String, String> map, final LoadNetDataListener<AddNewAddress> loadNetDataListener) {
        //封装retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<AddNewAddress> updateCartBeanObservable = request_interface.addNewAddress(map);
        updateCartBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AddNewAddress>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AddNewAddress addNewAddress) {
                loadNetDataListener.loadSuccess(addNewAddress);
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
