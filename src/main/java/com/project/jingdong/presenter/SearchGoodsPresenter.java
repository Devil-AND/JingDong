package com.project.jingdong.presenter;

import com.project.jingdong.bean.SearchBeanResult;
import com.project.jingdong.model.SearchGoodsModel;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.view.SearchResultView;

import java.util.HashMap;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class SearchGoodsPresenter {
    //搜索商品
    public void searGoodsByName(String searchcontent, SearchGoodsModel searchGoodsModel, final SearchResultView searchResultView) {
        HashMap<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("keywords", searchcontent);
        map.put("page", "1");
        searchGoodsModel.searchGoods(Constant.AddToCart, map, new LoadNetDataListener<SearchBeanResult>() {
            @Override
            public void loadSuccess(SearchBeanResult data) {
                searchResultView.showSearchResult(data);
            }

            @Override
            public void loadFailed(SearchBeanResult data) {

            }
        });
    }
}
