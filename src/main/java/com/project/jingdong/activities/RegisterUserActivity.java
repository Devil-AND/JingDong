package com.project.jingdong.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.bean.UserRegisterBean;
import com.project.jingdong.model.UserLogin_Reg;
import com.project.jingdong.presenter.UserLoginPresenter;
import com.project.jingdong.view.UserRegisterView;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener, UserRegisterView {

    private static final String TAG = "注册";
    private EditText mMobileUser;
    private EditText mPasswordUser;
    private Button mRegister;
    private UserLoginPresenter userLoginPresenter;
    private TextView mLoginEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initView();
        mLoginEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        mMobileUser = (EditText) findViewById(R.id.user_mobile);
        mPasswordUser = (EditText) findViewById(R.id.user_password);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        userLoginPresenter = new UserLoginPresenter();
        mLoginEdit = (TextView) findViewById(R.id.edit_login);
        mLoginEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (mMobileUser.getText().toString().equals("") || mPasswordUser.getText().toString().equals("")) {
                    Toast.makeText(this, "请填写完整的注册信息", Toast.LENGTH_SHORT).show();
                } else {
                    //注册方法
                    userLoginPresenter.userRegister(new UserLogin_Reg(), this);

                }
                break;
            case R.id.edit_login:// TODO 18/03/08
                break;
            default:
                break;
        }
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
    public void registerSuccess(UserRegisterBean userRegisterBean) {
        Log.e(TAG, "成功");
        String msg = userRegisterBean.getMsg();
        Toast.makeText(RegisterUserActivity.this, "" + msg, Toast.LENGTH_SHORT).show();//吐司
        finish();
    }

    @Override
    public void registerFailed(UserRegisterBean userRegisterBean) {
        String msg = userRegisterBean.getMsg();
        Toast.makeText(RegisterUserActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }


}
