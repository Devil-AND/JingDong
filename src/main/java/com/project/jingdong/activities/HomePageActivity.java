package com.project.jingdong.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.project.jingdong.R;
import com.project.jingdong.fragments.CartFragment;
import com.project.jingdong.fragments.ClassifyFragment;
import com.project.jingdong.fragments.DisplayFragment;
import com.project.jingdong.fragments.HomeFragment;
import com.project.jingdong.fragments.MyselfFragment;

public class HomePageActivity extends AppCompatActivity {

    private FrameLayout mFrame;
    private RadioButton mHome;
    private RadioButton mClassify;
    private RadioButton mDisplay;
    private RadioButton mCart;
    private RadioButton mMyself;
    private RadioGroup mRadiogroup;
    private FragmentManager fragmentManager;
    private HomeFragment homeFragment;
    private ClassifyFragment classfiyFragment;
    private DisplayFragment displayFragment;
    private CartFragment cartFragment;
    private MyselfFragment myselfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();//初始化界面
        FragmentTransaction tra = fragmentManager.beginTransaction();//开启事物
        //显示一个fragment
        homeFragment = new HomeFragment();
        tra.add(R.id.frame, homeFragment).commit();
        //点击切换页面
        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFragments();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.home:
                        fragmentTransaction.show(homeFragment).commit();
                        break;
                    case R.id.classify:
                        if (classfiyFragment == null) {
                            classfiyFragment = new ClassifyFragment();
                            fragmentTransaction.add(R.id.frame, classfiyFragment).commit();
                        } else {
                            fragmentTransaction.show(classfiyFragment).commit();
                        }

                        break;
                    case R.id.display:
                        if (displayFragment == null) {
                            displayFragment = new DisplayFragment();
                            fragmentTransaction.add(R.id.frame, displayFragment).commit();
                        } else {
                            fragmentTransaction.show(displayFragment).commit();
                        }
                        break;
                    case R.id.cart:
                        if (cartFragment == null) {
                            cartFragment = new CartFragment();
                            fragmentTransaction.add(R.id.frame, cartFragment).commit();
                        } else {
                            fragmentTransaction.show(cartFragment).commit();
                        }
                        break;
                    case R.id.myself:
                        if (myselfFragment == null) {
                            myselfFragment = new MyselfFragment();
                            fragmentTransaction.add(R.id.frame, myselfFragment).commit();
                        } else {
                            fragmentTransaction.show(myselfFragment).commit();
                        }
                        break;
                }
            }
        });
    }

    private void initView() {
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mHome = (RadioButton) findViewById(R.id.home);
        mClassify = (RadioButton) findViewById(R.id.classify);
        mDisplay = (RadioButton) findViewById(R.id.display);
        mCart = (RadioButton) findViewById(R.id.cart);
        mMyself = (RadioButton) findViewById(R.id.myself);
        mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        fragmentManager = getSupportFragmentManager();
    }



    //隐藏所有Fragment的方法
    public void hideFragments() {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (homeFragment != null && homeFragment.isAdded()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (classfiyFragment != null && classfiyFragment.isAdded()) {
            fragmentTransaction.hide(classfiyFragment);
        }
        if (displayFragment != null && displayFragment.isAdded()) {
            fragmentTransaction.hide(displayFragment);
        }
        if (cartFragment != null && cartFragment.isAdded()) {
            fragmentTransaction.hide(cartFragment);
        }
        if (myselfFragment != null && myselfFragment.isAdded()) {
            fragmentTransaction.hide(myselfFragment);
        }
        fragmentTransaction.commit();

    }
}
