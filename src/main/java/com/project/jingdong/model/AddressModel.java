package com.project.jingdong.model;

import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;
import com.project.jingdong.constant.LoadNetDataListener;

import java.util.Map;

/**
 * Author:AND
 * Time:2018/2/14.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface AddressModel {
    //获取收货地址列表
    void getAddressList(String url, String uid, LoadNetDataListener<ShippingAddresBean> loadNetDataListener);

    //添加新的收货地址
    void addNewsAddress(String url, Map<String, String> map, LoadNetDataListener<AddNewAddress> loadNetDataListener);
}
