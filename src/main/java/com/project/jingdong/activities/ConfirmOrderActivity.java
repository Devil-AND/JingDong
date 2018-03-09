package com.project.jingdong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.jingdong.R;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTopBack;
    private TextView mUsername;
    private TextView mUsermobile;
    private TextView mTitleUsergoods;
    private TextView mPriceUsergoods;
    private TextView mPrice2Usergoods;
    private TextView mPrice3Usergoods;
    private TextView mSubmitorder;
    private LinearLayout mUpdateaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        initView();

    }

    private void initView() {
        mTopBack = (TextView) findViewById(R.id.back_top);
        mUsername = (TextView) findViewById(R.id.username);
        mUsermobile = (TextView) findViewById(R.id.usermobile);
        mTitleUsergoods = (TextView) findViewById(R.id.usergoods_title);
        mPriceUsergoods = (TextView) findViewById(R.id.usergoods_price);
        mPrice2Usergoods = (TextView) findViewById(R.id.usergoods_price2);
        mPrice3Usergoods = (TextView) findViewById(R.id.usergoods_price3);
        mSubmitorder = (TextView) findViewById(R.id.submitorder);
        mSubmitorder.setOnClickListener(this);
        mUpdateaddress = (LinearLayout) findViewById(R.id.updateaddress);
        mUpdateaddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitorder:
                // TODO 18/02/12
                break;
            case R.id.updateaddress:// TODO 18/02/14
                //更改收货地址
                Intent intent = new Intent(ConfirmOrderActivity.this, ShippingAddressActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
