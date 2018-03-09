package com.project.jingdong.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.jingdong.R;

public class UpdateAddressActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTopBack;
    private EditText mShippingname;
    private EditText mShippingmobile;
    private EditText mShippingaddress;
    private Button mSaveanduse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
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
                // TODO 18/03/09
                finish();//退出当前页面
                break;
            case R.id.saveanduse:
                // TODO 18/03/09

                break;
            default:
                break;
        }
    }
}
