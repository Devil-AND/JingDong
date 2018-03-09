package com.project.jingdong.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.adapters.SearchDetailAdapter;
import com.project.jingdong.bean.SearchBeanResult;
import com.project.jingdong.model.SearchGoodsModel;
import com.project.jingdong.presenter.SearchGoodsPresenter;
import com.project.jingdong.view.SearchResultView;

import java.util.List;

public class SearchDetailActivity extends AppCompatActivity implements SearchResultView, View.OnClickListener {

    private static final String TAG = "搜索结果";
    boolean flag = false;
    private SearchGoodsPresenter searchGoodsPresenter;
    private SearchGoodsModel searchGoodsModel;
    private TextView mHomeBack;
    private EditText mEditSearch;
    private TextView mBtnSearch;
    private LinearLayout mNestToolBarLl;
    private LinearLayout mLine2;
    private RecyclerView mResultReclSearch;
    private RadioButton mKindfenlei;
    private SearchDetailAdapter searchDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        initView();
        Intent intent = getIntent();
        String searchcontent = intent.getStringExtra("searchcontent");
        //加载数据
        initdata(searchcontent);
    }

    private void initdata(String searchcontent) {
        //搜索商品
        searchGoodsPresenter = new SearchGoodsPresenter();
        searchGoodsModel = new SearchGoodsModel();
        searchGoodsPresenter.searGoodsByName(searchcontent, searchGoodsModel, this);

    }


    private void initView() {
        mHomeBack = (TextView) findViewById(R.id.back_home);
        mHomeBack.setOnClickListener(this);
        mEditSearch = (EditText) findViewById(R.id.search_edit);
        mBtnSearch = (TextView) findViewById(R.id.search_btn);
        mNestToolBarLl = (LinearLayout) findViewById(R.id.ll_nest_toolBar);
        mLine2 = (LinearLayout) findViewById(R.id.line2);
        mResultReclSearch = (RecyclerView) findViewById(R.id.search_result_recl);
        mKindfenlei = (RadioButton) findViewById(R.id.kindfenlei);
        mKindfenlei.setOnClickListener(this);
    }

    @Override
    public void showSearchResult(SearchBeanResult searchBeanResult) {
        List<SearchBeanResult.DataBean> data = searchBeanResult.getData();
        int size = data.size();
        Log.e(TAG, "长度: " + size);
        if (size == 0 || size < 0) {
            Toast.makeText(this, "暂时没有该商品", Toast.LENGTH_SHORT).show();
            return;
        }
        if (size > 0) {
            //搜索到商品,显示数据
            List<SearchBeanResult.DataBean> searchList = searchBeanResult.getData();
            searchDetailAdapter = new SearchDetailAdapter(this, searchList);
            mResultReclSearch.setAdapter(searchDetailAdapter);
            mResultReclSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            searchDetailAdapter.setOnItemClickListener(new SearchDetailAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position) {
                    //跳转到详情页面
                    Intent intent = new Intent(SearchDetailActivity.this, ProductDetailActivity.class);
                    intent.putExtra("pid", position + "");
                    startActivity(intent);
                }
            });
        }
        mKindfenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == false) {
                    mResultReclSearch.setLayoutManager(new GridLayoutManager(SearchDetailActivity.this, 2, GridLayoutManager.VERTICAL, false));
                    mResultReclSearch.setAdapter(searchDetailAdapter);
                    flag = true;
                } else {
                    mResultReclSearch.setAdapter(searchDetailAdapter);
                    mResultReclSearch.setLayoutManager(new LinearLayoutManager(SearchDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                    flag = false;
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_home:
                finish();
                break;
            case R.id.search_btn:
                break;
            case R.id.kindfenlei:// TODO 18/02/11
                break;
            default:
                break;
        }
    }
}
