package com.project.jingdong.model;

import com.project.jingdong.bean.SearchBeanResult;
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
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class SearchGoodsModel implements SearchModel {
    private OkHttpClient okclient = new OkHttpClient.Builder().build();

    @Override
    public void searchGoods(String url, Map<String, String> map, final LoadNetDataListener<SearchBeanResult> loadNetDataListener) {
        Request_Interface request_interface = RetrofitManager.getInstance(url, okclient).setCreate(Request_Interface.class);
        Observable<SearchBeanResult> searchResult = request_interface.getSearchResult(map);
        searchResult.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SearchBeanResult>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(SearchBeanResult searchBeanResult) {
                loadNetDataListener.loadSuccess(searchBeanResult);
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
