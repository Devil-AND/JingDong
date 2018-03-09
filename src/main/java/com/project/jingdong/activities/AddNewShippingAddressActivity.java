package com.project.jingdong.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.bean.AddNewAddress;
import com.project.jingdong.bean.ShippingAddresBean;
import com.project.jingdong.presenter.AddressPresenter;
import com.project.jingdong.view.AddressView;

import java.util.HashMap;

public class AddNewShippingAddressActivity extends AppCompatActivity implements View.OnClickListener, AddressView {

    private TextView mTopBack;
    private EditText mShippingname;
    private EditText mShippingmobile;
    private EditText mShippingaddress;
    private Button mSaveanduse;
    private AddressPresenter addressPresenter = new AddressPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shipping_address);
        initView();
    }

    private void initView() {
        mTopBack = (TextView) findViewById(R.id.back_top);
        mTopBack.setOnClickListener(this);
        mShippingname = (EditText) findViewById(R.id.shippingname);
        mShippingmobile = (EditText) findViewById(R.id.shippingmobile);
        mShippingaddress = (EditText) findViewById(R.id.shippingaddress);
        mSaveanduse = (Button) findViewById(R.id.saveanduse);
        mSaveanduse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_top:
                // TODO 18/02/14
                finish();
                break;
            case R.id.saveanduse:
                // TODO 18/02/14
                //提交添加的数据
                String shippingName = mShippingname.getText().toString();
                String shippingAddress = mShippingaddress.getText().toString();
                String shippingMobile = mShippingmobile.getText().toString();
                if (shippingName == "" || shippingAddress == "" || shippingMobile == "") {
                    Toast.makeText(this, "请填写完整信息后再提交!!", Toast.LENGTH_SHORT).show();
                } else {
                    String uid = getUserInfo();
                    HashMap<String, String> userInfo = new HashMap<>();
                    userInfo.put("uid", uid);
                    userInfo.put("addr", shippingAddress);
                    userInfo.put("mobile", shippingMobile);
                    userInfo.put("name", shippingName);
                    addressPresenter.addNewAddress(userInfo);//开始添加地址到服务器
                }
                break;
            default:
                break;
        }
    }

    private String getUserInfo() {
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        String uid = userinfo.getString("uid", "0");
        return uid;
    }

    @Override
    public void getShippingSuccess(ShippingAddresBean shippingAddresBean) {

    }

    /**
     * 添加成功
     */
    @Override
    public void addNewAddressSuccess(AddNewAddress addNewAddress) {
        Toast.makeText(this, "" + addNewAddress.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
