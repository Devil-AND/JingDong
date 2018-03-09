package com.project.jingdong.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.MyApp;
import com.project.jingdong.R;
import com.project.jingdong.bean.AddToCartBena;
import com.project.jingdong.bean.ProductDetailBean;
import com.project.jingdong.bean.UserInfoDetail;
import com.project.jingdong.model.ProductsListModel;
import com.project.jingdong.presenter.ClassifyPresenter;
import com.project.jingdong.utils.GlideImageLoader;
import com.project.jingdong.view.ProductDetailView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.Arrays;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity implements View.OnClickListener, ProductDetailView {

    private static final String TAG = "详情页面";
    private TextView mTopBack;
    private TextView mTitleProductsdetail;
    private TextView mBargainPriceProductsdetail;
    private TextView mPriceProductsdetail;
    private TextView mAddgoodtocart;
    private String pid;
    private ClassifyPresenter classifyPresenter;
    private Banner mNbannerProductsdetail;
    private ProductsListModel productsListModel;
    //设置图片宽高比
    float scale = (float) 1960 / (float) 1080;
    int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initView();
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
    }

    @Override
    protected void onResume() {
        super.onResume();
        classifyPresenter.getProductDetail(pid, productsListModel, this);
    }

    private void initView() {
        mTopBack = (TextView) findViewById(R.id.back_top);
        mTopBack.setOnClickListener(this);
        mTitleProductsdetail = (TextView) findViewById(R.id.productsdetail_title);
        mBargainPriceProductsdetail = (TextView) findViewById(R.id.productsdetail_bargainPrice);
        mPriceProductsdetail = (TextView) findViewById(R.id.productsdetail_price);
        mAddgoodtocart = (TextView) findViewById(R.id.addgoodtocart);
        mAddgoodtocart.setOnClickListener(this);
        classifyPresenter = new ClassifyPresenter();
        productsListModel = new ProductsListModel();
        mNbannerProductsdetail = (Banner) findViewById(R.id.productsdetail_nbanner);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_top:
                finish();
                break;
            case R.id.addgoodtocart:
                //加入购物车钱判断用户是否登录,如果登录,那就加入购物车,如果没有,那就跳转到登录页面
                boolean flag = MyApp.flag;
                if (flag == false) {//用户未登录
                    Intent intent = new Intent(this, Login_regActivity.class);
                    startActivity(intent);
                } else {//添加购物车
                    String uid = LoadData();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("source", "android");
                    map.put("uid", uid);
                    map.put("pid", pid);
                    classifyPresenter.addGoodsToCart(map, new ProductsListModel(), this);
                }
                break;
            default:
                break;
        }
    }

    public String LoadData() {
        //指定操作的文件名称
        SharedPreferences share = getSharedPreferences("userinfo", MODE_PRIVATE);
        String uid = share.getString("uid", "71");
        return uid;
    }

    //显示商品详情信息
    @Override
    public void loadProductDetail(ProductDetailBean productDetailBean) {
        String title = productDetailBean.getData().getTitle();
        double bargainPrice = productDetailBean.getData().getBargainPrice();
        int price = productDetailBean.getData().getPrice();
        mPriceProductsdetail.setText("原价  ¥" + price + "");//设置价格
        mBargainPriceProductsdetail.setText("¥" + bargainPrice + "[白条支付],享三期免息");//优惠价
        mTitleProductsdetail.setText(title + "");//标题
        String images = productDetailBean.getData().getImages();
        String[] split = images.split("[|]");
        startbanner(split);
    }

    @Override
    public void userLogin(UserInfoDetail userino) {
        String code = userino.getCode();
        Log.e(TAG, "用户登录信息 " + code);

    }

    @Override
    public void addResult(AddToCartBena addToCartBena) {
        Toast.makeText(this, "" + addToCartBena.getMsg(), Toast.LENGTH_SHORT).show();
    }

    private void startbanner(String[] split) {
        //获取屏幕宽度
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        screenWidth = point.x;

        int viewHeight = Math.round(screenWidth / scale);
        ViewGroup.LayoutParams layoutParams = mNbannerProductsdetail.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = viewHeight;
        mNbannerProductsdetail.setLayoutParams(layoutParams);

        mNbannerProductsdetail.setImages(Arrays.asList(split));
        //设置图片加载器
        mNbannerProductsdetail.setImageLoader(new GlideImageLoader());
        mNbannerProductsdetail.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        mNbannerProductsdetail.isAutoPlay(false);
        //设置轮播时间
        mNbannerProductsdetail.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        mNbannerProductsdetail.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mNbannerProductsdetail.start();//开启轮播
    }
}
