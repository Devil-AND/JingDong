package com.project.jingdong.presenter;

import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.ProductCatagory;
import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.ProductsListBean;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.model.ClassifyLoadData;
import com.project.jingdong.model.LoadDataCataGroyListener;
import com.project.jingdong.model.LoadDataProductCataGroy;
import com.project.jingdong.model.LoadDataProductsList;
import com.project.jingdong.model.ProductsListModel;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.view.ClassifyView;
import com.project.jingdong.view.Classify_ProductView;
import com.project.jingdong.view.ProductDetailView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/8.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class ClassifyPresenter {

    //商品导航栏信息
    public void showCatagroyData(ClassifyLoadData classifyLoadData, final ClassifyView classifyView) {
        classifyLoadData.getCatagroy(Constant.getCatagory_Url, new LoadDataCataGroyListener() {
            @Override
            public void loadCatagroySuccess(CatagoryBean catagoryBean) {
                classifyView.showCatagroy(catagoryBean);
            }

            @Override
            public void loadCatagroyFailed(CatagoryBean catagoryBean) {

            }
        });
    }

    //商品分类信息
    public void showProductCataGroy(String cid, ClassifyLoadData classifyLoadData, final ClassifyView classifyView) {
        classifyLoadData.getProductCataGroy(Constant.getCatagory_Url, cid, new LoadDataProductCataGroy() {
            @Override
            public void loadSuccess(ProductCatagory productCatagory) {
                List<ProductCatagory.DataBean> data = productCatagory.getData();
                List<List<ProductCatagory.DataBean.ListBean>> childList = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    List<ProductCatagory.DataBean.ListBean> list = data.get(i).getList();
                    childList.add(list);
                }
                classifyView.showProductCataCroy(productCatagory, childList);
            }

            @Override
            public void loadFailed(ProductCatagory productCatagory) {

            }
        });
    }

    //分类列表
    public void getProductsList(String pscid, ProductsListModel productsListModel, final Classify_ProductView classifyProductView) {
        productsListModel.getProductsList(Constant.Products_Url, pscid, new LoadDataProductsList() {
            @Override
            public void loadSuccess(ProductsListBean productsListBean) {
                classifyProductView.loadProductsData(productsListBean);
            }

            @Override
            public void loadFailed(ProductsListBean productsListBean) {

            }

            @Override
            public void loadDetailFailed(ProductDetailBean productDetailBean) {

            }

            @Override
            public void loadDetailSuccess(ProductDetailBean productDetailBean) {

            }
        });
    }

    //详情列表
    public void getProductDetail(String pid, ProductsListModel productsListModel, final ProductDetailView productDetailView) {
        productsListModel.getProductDetail(Constant.Products_Detail, pid, new LoadDataProductsList() {
            @Override
            public void loadSuccess(ProductsListBean productsListBean) {

            }

            @Override
            public void loadFailed(ProductsListBean productsListBean) {

            }

            @Override
            public void loadDetailSuccess(ProductDetailBean productDetailBean) {
                productDetailView.loadProductDetail(productDetailBean);
            }

            @Override
            public void loadDetailFailed(ProductDetailBean productDetailBean) {

            }
        });
    }

    //获取用户登录信息
    public void getUserMessage(ProductsListModel productsListModel, final ProductDetailView productDetailView) {
        productsListModel.getUserInfo(Constant.getUserInfo, new LoadNetDataListener<UserInfoDetail>() {
            @Override
            public void loadSuccess(UserInfoDetail data) {
                productDetailView.userLogin(data);
            }

            @Override
            public void loadFailed(UserInfoDetail data) {

            }
        });
    }

    //添加购物车
    public void addGoodsToCart(Map<String, String> map, ProductsListModel productsListModel, final ProductDetailView productDetailView) {
        productsListModel.addToCart(Constant.AddToCart, map, new LoadNetDataListener<AddToCartBena>() {
            @Override
            public void loadSuccess(AddToCartBena data) {
                productDetailView.addResult(data);
            }

            @Override
            public void loadFailed(AddToCartBena data) {

            }
        });
    }

}
