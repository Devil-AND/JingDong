package com.project.jingdong.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.project.jingdong.R;
import com.project.jingdong.bean.UserInfoDetail;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UpdateUserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mHomeBack;
    private SimpleDraweeView mIconUser;
    private TextView mUsername;
    private TextView mUsernickname;
    private TextView mUserbirthdaytime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);
        EventBus.getDefault().register(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        mHomeBack = (TextView) findViewById(R.id.back_home);
        mHomeBack.setOnClickListener(this);
        mIconUser = (SimpleDraweeView) findViewById(R.id.user_icon);
        mUsername = (TextView) findViewById(R.id.username);
        mUsernickname = (TextView) findViewById(R.id.usernickname);
        mUserbirthdaytime = (TextView) findViewById(R.id.userbirthdaytime);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void GetUser(UserInfoDetail.DataBean data) {
        Log.e("", "password: " + data.getPassword());
        String icon = data.getIcon();
        String username = data.getUsername();
        String nickname = data.getNickname();
        String createtime = data.getCreatetime();
        Log.e("", "信息: " + icon + username + nickname + createtime);
      /*  if (nickname == "" || nickname == null) {
            nickname = "您还没有填写昵称哦";
        }
        DraweeController retryImageDraweeController = Fresco.newDraweeControllerBuilder()
                .setUri(icon)//找个错误地址
                .setTapToRetryEnabled(true)//允许重新加载
                .setOldController(mIconUser.getController())
                .build();
        mIconUser.setController(retryImageDraweeController);
        mUsername.setText(username);
        mUsernickname.setText(nickname);
        mUserbirthdaytime.setText(createtime);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:
                // TODO 18/03/09
                finish();
                break;
            default:
                break;
        }
    }
}
