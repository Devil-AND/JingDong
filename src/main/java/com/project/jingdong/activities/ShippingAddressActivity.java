package com.project.jingdong.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.adapters.ShippingAddressAdapters;
import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;
import com.project.jingdong.presenter.AddressPresenter;
import com.project.jingdong.view.AddressView;

import java.util.List;

public class ShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, AddressView {

    private static final String TAG = "收货地址";
    private RecyclerView mAddressrecl;
    private Button mAddnewshippingaddress;
    private AddressPresenter addressPresenter;
    private TextView mHomeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_address);
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 添加新的收货地址
        String userInfo = getUserInfo();
        if (userInfo.equals("71")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            mAddnewshippingaddress.setClickable(false);
        } else {
            //请求数据
            Log.e(TAG, "onClick: " + userInfo);
            addressPresenter = new AddressPresenter(this);
            addressPresenter.getAddressList(userInfo);//用户已登录,获取用户的收货地址列表
            mAddnewshippingaddress.setClickable(true);
        }
    }

    private void initView() {
        mAddressrecl = (RecyclerView) findViewById(R.id.addressrecl);
        mAddnewshippingaddress = (Button) findViewById(R.id.addnewshippingaddress);
        mAddnewshippingaddress.setOnClickListener(this);

        mHomeBack = (TextView) findViewById(R.id.back_home);
        mHomeBack.setOnClickListener(this);
    }

    private String getUserInfo() {
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        String uid = userinfo.getString("uid", "71");
        return uid;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addnewshippingaddress:
                //新增收货地址
                Intent intent = new Intent(this, AddNewShippingAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.back_home:// TODO 18/03/09
                finish();//退出当前页面
                break;
            default:
                break;
        }
    }

    /**
     * 获取订单列表
     *
     * @param shippingAddresBean
     */
    @Override
    public void getShippingSuccess(ShippingAddresBean shippingAddresBean) {
        List<ShippingAddresBean.DataBean> addressList = shippingAddresBean.getData();
        ShippingAddressAdapters shippingAddressAdapters = new ShippingAddressAdapters(addressList, this);
        mAddressrecl.setAdapter(shippingAddressAdapters);//添加适配器
        mAddressrecl.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void addNewAddressSuccess(AddNewAddress updateCartBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
