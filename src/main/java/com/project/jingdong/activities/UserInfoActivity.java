package com.project.jingdong.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.project.jingdong.R;
import com.project.jingdong.bean.EventBusUpdateAddress;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.presenter.UserInfoPresenter;
import com.project.jingdong.view.UserInfoView;

import org.greenrobot.eventbus.EventBus;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener, UserInfoView {

    private SimpleDraweeView mImageUser;
    private TextView mNameUser;
    private TextView mNameNameUser;
    private TextView mLookUserMoreInfo;
    private Button mUserLoginExit;
    private TextView mHomeBack;
    private UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
    private LinearLayout mGetuserMoreinfo;
    private EventBusUpdateAddress eventBusUpdateAddress;
    private EventBusUpdateAddress.DataBean userInfo;
    private UserInfoDetail.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String uid = getUserInfo();
        userInfoPresenter.getUserInfo(uid);//网络请求获取信息
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    private String getUserInfo() {
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        String uid = userinfo.getString("uid", "0");
        return uid;
    }

    private void initView() {
        mImageUser = (SimpleDraweeView) findViewById(R.id.user_image);
        mNameUser = (TextView) findViewById(R.id.user_name);
        mNameNameUser = (TextView) findViewById(R.id.user_name_name);
        mLookUserMoreInfo = (TextView) findViewById(R.id.lookUserMoreInfo);
        mUserLoginExit = (Button) findViewById(R.id.exit_user_login);
        mUserLoginExit.setOnClickListener(this);
        mHomeBack = (TextView) findViewById(R.id.back_home);
        mGetuserMoreinfo = (LinearLayout) findViewById(R.id.getuserMoreinfo);
        mGetuserMoreinfo.setOnClickListener(this);
    }

    @Override
    public void getUserInfo(UserInfoDetail userInfoDetail) {
        Log.e("000000", "手机号*/*/*/*/*/*/*: " + userInfoDetail.getData().getMobile());
        data = userInfoDetail.getData();
        String username = data.getUsername();
        String icon = data.getIcon();
        DraweeController retryImageDraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(icon)//找个错误地址
                .setTapToRetryEnabled(true)//允许重新加载
                .setOldController(mImageUser.getController())
                .build();
        mImageUser.setController(retryImageDraweeController);
        mNameUser.setText(username);
        mNameNameUser.setText("用户名:" + username);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_user_login:
                // TODO 18/03/09
                break;
            case R.id.back_home:// TODO 18/03/09
                //退出当前界面
                finish();
                break;
            case R.id.getuserMoreinfo:// TODO 18/03/09
                EventBus.getDefault().postSticky(data);

                Intent intent = new Intent(this, UpdateUserInfoActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
