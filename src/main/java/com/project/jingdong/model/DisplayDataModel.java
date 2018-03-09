package com.project.jingdong.model;

import com.project.jingdong.bean.DisplayNewsBean;
import com.project.jingdong.constant.LoadNetDataListener;
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
 * Time:2018/2/12.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class DisplayDataModel implements DisplayModel {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    @Override
    public void getAndridNewsData(String url, int page, final LoadNetDataListener<DisplayNewsBean> loadNetDataListener) {
        //获取retrofit对象
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        //封装请求方法
        Observable<DisplayNewsBean> androidData = request_interface.getAndroidData(page);
        //开始请求
        androidData.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DisplayNewsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DisplayNewsBean displayNewsBean) {
                loadNetDataListener.loadSuccess(displayNewsBean);
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
