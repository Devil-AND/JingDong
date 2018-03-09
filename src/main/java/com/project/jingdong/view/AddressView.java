package com.project.jingdong.view;

import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;

/**
 * Author:AND
 * Time:2018/2/14.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public interface AddressView {
    //显示收货地址列表
    void getShippingSuccess(ShippingAddresBean shippingAddresBean);

    void addNewAddressSuccess(AddNewAddress addNewAddress);


}
