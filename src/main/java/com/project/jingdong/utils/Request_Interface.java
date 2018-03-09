package com.project.jingdong.utils;

import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.DisplayNewsBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.bean.NewsBean;
import com.project.jingdong.bean.ProductCatagory;
import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.ProductsListBean;
import com.project.jingdong.bean.SearchBeanResult;
import com.project.jingdong.bean.ShippingAddresBean;
import com.project.jingdong.bean.UpdateAddressBean;
import com.project.jingdong.bean.UpdateCartBean;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.bean.UserRegisterBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface Request_Interface {
    //获取轮播图数据
    @GET("ad/getAd")
    Observable<HomePosterBean> getPoster();

    //获取商品分类数据
    @GET("product/getCatagory")
    Observable<CatagoryBean> getProductCatagory();

    //获取为你推荐数据
    @GET("ad/getAd")
    Observable<HomePosterBean> getTuijian();

    //获取登录信息
    @POST("user/login")
    @FormUrlEncoded
    Observable<UserInfoDetail> userLogin(@FieldMap Map<String, String> params);

    //获取注册信息
    @POST("user/reg")
    @FormUrlEncoded
    Observable<UserRegisterBean> userRegister(@FieldMap Map<String, String> params);

    //获取秒杀信息
    @GET("ad/getAd")
    Observable<HomePosterBean> miaosha();

    //获取商品分类信息
    @GET("product/getProductCatagory")
    Observable<ProductCatagory> getProductCataGory(@Query("cid") String cid);

    //获取商品列表信息
    @GET("product/getProducts")
    Observable<ProductsListBean> getProductsList(@Query("pscid") String pscid);

    //商品详情页面
    @GET("product/getProductDetail")
    Observable<ProductDetailBean> getProductsDetail(@QueryMap Map<String, String> map);

    //发现页面获取新闻数据
    @GET("toutiao/index?type=top&key=61005cfc63a8075c88d5d408ba90aff9")
    Observable<NewsBean> getDissplayNewsBean();

    //获取用户信息
    @GET("user/getUserInfo")
    Observable<UserInfoDetail> getUserInfo(@Query("uid") String uid);

    //添加到购物车
    @GET("product/addCart")
    Observable<AddToCartBena> addToCart(@QueryMap Map<String, String> map);

    //获取购物车列表
    @GET("product/getCarts")
    Observable<CartListBean> getCartListMessage(@QueryMap Map<String, String> map);

    //搜索商品
    @GET("product/searchProducts")
    Observable<SearchBeanResult> getSearchResult(@QueryMap Map<String, String> map);

    //发现页
    @GET("data/Android/10/")
    Observable<DisplayNewsBean> getAndroidData(@Query("") int page); //发现页

    //更新购物车
    @GET("product/updateCarts")
    Observable<UpdateCartBean> updateCartData(@QueryMap Map<String, String> params);

    //删除购物车
    @GET("product/deleteCart?uid=71")
    Observable<UpdateCartBean> deleteCartData(@Query("pid") String pid);

    //获取收货地址列表
    @GET("user/getAddrs")
    Observable<ShippingAddresBean> getAddress(@Query("uid") String uid);

    //添加收货地址
    @POST("user/addAddr")
    @FormUrlEncoded
    Observable<AddNewAddress> addNewAddress(@FieldMap Map<String, String> params);

    //更改收货地址
    @POST("user/updateAddr")
    @FormUrlEncoded
    Observable<UpdateAddressBean> updateAddress(@FieldMap Map<String, String> params);

}
