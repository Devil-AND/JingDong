package com.project.jingdong.model;

import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.UpdateCartBean;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.utils.Request_Interface;
import com.project.jingdong.utils.RetrofitManager;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class CartsListsModel implements GetCartList {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    //加载购物车
    @Override
    public void getCartsList(String url, Map<String, String> map, final LoadNetDataListener<CartListBean> loadNetDataListener) {
        //封装retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<CartListBean> cartListMessage = request_interface.getCartListMessage(map);
        //开始请求
        cartListMessage.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CartListBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CartListBean cartListBean) {
                loadNetDataListener.loadSuccess(cartListBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    //更新购物车
    @Override
    public void updateCartData(String url, Map<String, String> params, final LoadNetDataListener<UpdateCartBean> loadNetDataListener) {
        //创建retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UpdateCartBean> updateCartBeanObservable = request_interface.updateCartData(params);
        updateCartBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpdateCartBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(UpdateCartBean updateCartBean) {
                loadNetDataListener.loadSuccess(updateCartBean);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 删除购物车
     */
    @Override
    public void deleteCartData(String url, String pid, final LoadNetDataListener<UpdateCartBean> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<UpdateCartBean> updateCartBeanObservable = request_interface.deleteCartData(pid);
        //开始请求
        updateCartBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<UpdateCartBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(UpdateCartBean updateCartBean) {
                loadNetDataListener.loadSuccess(updateCartBean);
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
    public double settleAcount(List<CartListBean.DataBean> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            List<CartListBean.GoodsBean> childList = list.get(i).getList();
            for (int j = 0; j < childList.size(); j++) {
                int selected = childList.get(j).getSelected();
                int num = childList.get(j).getNum();
                double price = childList.get(j).getPrice();
                if (selected == 1) {
                    sum = sum + (num * price);
                }
            }
        }
        return sum;
    }
}
