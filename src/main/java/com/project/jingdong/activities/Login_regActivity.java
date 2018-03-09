package com.project.jingdong.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.MyApp;
import com.project.jingdong.R;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.model.UserLogin_Reg;
import com.project.jingdong.presenter.UserLoginPresenter;
import com.project.jingdong.view.UserloginView;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class Login_regActivity extends AppCompatActivity implements View.OnClickListener, UserloginView {
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private EditText mMobileUser;
    private EditText mPasswordUser;
    private Button mLogin;
    private ImageView mQqlogin;
    private UserLoginPresenter userLoginPresenter;
    private Tencent mTencent;
    private static final String TAG = "登录系统";
    private UserInfo mUserInfo;
    private BaseUiListener mIUiListener;
    private TextView mReguser;
    private TextView mLoginEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);
        initView();
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID, Login_regActivity.this.getApplicationContext());
        mLoginEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMobileUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                } else {

                }
            }
        });
        mMobileUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                mLogin.setClickable(false);
                mLogin.setBackgroundColor(Color.parseColor("#e6dddd"));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mLogin.setClickable(true);
                mLogin.setBackgroundColor(Color.parseColor("#ff0000"));
                if (mMobileUser.getText().toString() == "" || mPasswordUser.getText().toString() == "") {
                    mLogin.setClickable(false);
                    mLogin.setBackgroundColor(Color.parseColor("#e6dddd"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void initView() {
        mMobileUser = (EditText) findViewById(R.id.user_mobile);
        mPasswordUser = (EditText) findViewById(R.id.user_password);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mQqlogin = (ImageView) findViewById(R.id.qqlogin);
        mQqlogin.setOnClickListener(this);
        userLoginPresenter = new UserLoginPresenter();
        mReguser = (TextView) findViewById(R.id.reguser);
        mReguser.setOnClickListener(this);
        mLoginEdit = (TextView) findViewById(R.id.edit_login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (mMobileUser.getText().toString().equals("") || mPasswordUser.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写登录信息", Toast.LENGTH_SHORT).show();
                } else {
                    //登录方法
                    userLoginPresenter.userLogin(new UserLogin_Reg(), this);
                }
                break;
            case R.id.qqlogin:
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(Login_regActivity.this, "all", mIUiListener);
                break;

            case R.id.reguser:// TODO 18/02/13
                Intent intent = new Intent(this, RegisterUserActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void SaveData(UserInfoDetail userInfoDetail) {
        //指定操作的文件名称
        SharedPreferences share = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor edit = share.edit(); //编辑文件
        edit.putString("uid", userInfoDetail.getData().getUid() + "");         //根据键值对添加数据
        edit.commit();  //保存数据信息
    }

    @Override
    public String getUserMobile() {
        return mMobileUser.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mPasswordUser.getText().toString();
    }

    @Override
    public void loginSuccess(UserInfoDetail userInfoDetail) {
        String msg = userInfoDetail.getMsg();
        Toast.makeText(Login_regActivity.this, "" + msg, Toast.LENGTH_SHORT).show();//吐司
        String username = userInfoDetail.getData().getUsername();
        Intent intent = new Intent(Login_regActivity.this, HomePageActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        MyApp.flag = true;//判断是否登录
        SaveData(userInfoDetail);//保存用户登录信息
    }

    @Override
    public void loginFailed(UserInfoDetail userInfoDetail) {
        String msg = userInfoDetail.getMsg();
        Toast.makeText(Login_regActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(Login_regActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG, "登录成功" + response.toString());
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG, "登录失败" + uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG, "登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finish();
            MyApp.flag = true;//判断是否登录
            //指定操作的文件名称
            SharedPreferences share = getSharedPreferences("userinfo", MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit(); //编辑文件
            edit.putString("uid", "12248");         //根据键值对添加数据
            edit.commit();  //保存数据信息

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(Login_regActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(Login_regActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
