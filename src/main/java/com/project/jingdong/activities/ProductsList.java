package com.project.jingdong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.adapters.ProductsListAdapter;
import com.project.jingdong.bean.ProductsListBean;
import com.project.jingdong.model.ProductsListModel;
import com.project.jingdong.presenter.ClassifyPresenter;
import com.project.jingdong.view.Classify_ProductView;

import java.util.List;

public class ProductsList extends AppCompatActivity implements Classify_ProductView, View.OnClickListener {

    private static final String TAG = "分类列表";
    private RecyclerView mProductsRecyclerviewClassify;
    private String pscid;
    private ClassifyPresenter classifyPresenter;
    private ProductsListModel productsListModel;
    private TextView mHomeBack;
    private RadioButton mKindfenlei;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        initView();
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");

    }

    private void initView() {
        classifyPresenter = new ClassifyPresenter();
        productsListModel = new ProductsListModel();
        mProductsRecyclerviewClassify = (RecyclerView) findViewById(R.id.classify_products_recyclerview);
        mHomeBack = (TextView) findViewById(R.id.back_home);
        mHomeBack.setOnClickListener(this);
        mKindfenlei = (RadioButton) findViewById(R.id.kindfenlei);
        mKindfenlei.setOnClickListener(this);
    }

    //此处做网络请求
    @Override
    protected void onResume() {
        super.onResume();
        classifyPresenter.getProductsList(pscid, productsListModel, this);
    }

    //显示分类数据
    @Override
    public void loadProductsData(ProductsListBean productsListBean) {
        List<ProductsListBean.DataBean> data = productsListBean.getData();
        if (data.size() == 0) {
            Toast.makeText(this, "哎呀,好像没有数据了,逛逛别的吧!!", Toast.LENGTH_SHORT).show();
        } else {
            final ProductsListAdapter productsListAdapter = new ProductsListAdapter(data, ProductsList.this);
            mProductsRecyclerviewClassify.setLayoutManager(new LinearLayoutManager(this));
            //设置适配器
            mProductsRecyclerviewClassify.setAdapter(productsListAdapter);
            mKindfenlei.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag == false) {
                        mKindfenlei.setBackgroundResource(R.drawable.kind_liner);
                        mProductsRecyclerviewClassify.setLayoutManager(new LinearLayoutManager(ProductsList.this));
                        mProductsRecyclerviewClassify.setAdapter(productsListAdapter);
                        flag = true;
                    } else {
                        mKindfenlei.setBackgroundResource(R.drawable.kind_grid);
                        mProductsRecyclerviewClassify.setLayoutManager(new GridLayoutManager(ProductsList.this, 2, LinearLayoutManager.VERTICAL, false));
                        mProductsRecyclerviewClassify.setAdapter(productsListAdapter);
                        flag = false;
                    }
                }
            });
            productsListAdapter.setOnItemClickListener(new ProductsListAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //position:获取到的商品具体id编号
                    Intent intent = new Intent(ProductsList.this, ProductDetailActivity.class);
                    intent.putExtra("pid", position + "");
                    startActivity(intent);
                }

            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:
                finish();
                break;
            default:
                break;
        }
    }
}
