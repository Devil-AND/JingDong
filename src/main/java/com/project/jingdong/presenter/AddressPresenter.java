package com.project.jingdong.presenter;

import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.model.AddressManager;
import com.project.jingdong.view.AddressView;

import java.util.HashMap;

/**
 * Author:AND
 * Time:2018/2/14.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class AddressPresenter {
    private AddressManager addressManager;
    private AddressView addressView;

    public AddressPresenter(AddressView addressView) {
        this.addressManager = new AddressManager();
        this.addressView = addressView;
    }

    /**
     * 获取收货地址列表信息
     *
     * @param uid
     */
    public void getAddressList(String uid) {
        addressManager.getAddressList(Constant.GetAddress, uid, new LoadNetDataListener<ShippingAddresBean>() {
            @Override
            public void loadSuccess(ShippingAddresBean data) {
                addressView.getShippingSuccess(data);
            }

            @Override
            public void loadFailed(ShippingAddresBean data) {

            }
        });
    }

    /**
     * 添加新的收货地址
     *
     * @param userInfo
     */
    public void addNewAddress(HashMap<String, String> userInfo) {
        addressManager.addNewsAddress(Constant.AddNewsAddress, userInfo, new LoadNetDataListener<AddNewAddress>() {
            @Override
            public void loadSuccess(AddNewAddress data) {
                addressView.addNewAddressSuccess(data);
            }

            @Override
            public void loadFailed(AddNewAddress data) {

            }
        });

    }
}
