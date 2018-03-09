package com.project.jingdong.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.project.jingdong.R;
import com.project.jingdong.activities.SearchGoodsActivity;
import com.project.jingdong.adapters.CataGroyClassifyAdapter;
import com.project.jingdong.adapters.CataGroyClassifyExpandableAdapter;
import com.project.jingdong.bean.CatagoryBean;
import com.project.jingdong.bean.ProductCatagory;
import com.project.jingdong.model.ClassifyLoadData;
import com.project.jingdong.presenter.ClassifyPresenter;
import com.project.jingdong.view.ClassifyView;

import java.util.List;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:分类
 * Detail:
 */

public class ClassifyFragment extends Fragment implements ClassifyView {
    private static final String TAG = "分类层";
    private ListView mClassifycatagory;
    private ExpandableListView mGoodsclassify;
    private ClassifyPresenter classifyPresenter;
    private EditText mEditSearch;
    private LinearLayout mNestToolBarLl;
    private LinearLayout mLine;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        classifyPresenter.showProductCataGroy("1", new ClassifyLoadData(), ClassifyFragment.this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.classify_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        classifyPresenter = new ClassifyPresenter();
        //调用分类数据
        classifyPresenter.showCatagroyData(new ClassifyLoadData(), this);
        //搜索页面
        mEditSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchGoodsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(@NonNull final View itemView) {
        mClassifycatagory = (ListView) itemView.findViewById(R.id.classifycatagory);
        mGoodsclassify = (ExpandableListView) itemView.findViewById(R.id.goodsclassify);
        mEditSearch = (EditText) itemView.findViewById(R.id.search_edit);
        mNestToolBarLl = (LinearLayout) itemView.findViewById(R.id.ll_nest_toolBar);
        mLine = (LinearLayout) itemView.findViewById(R.id.line);
    }

    //显示分类数据
    @Override
    public void showCatagroy(CatagoryBean catagoryBean) {
        String code = catagoryBean.getCode();
        if (code.equals("0")) {
            final List<CatagoryBean.DataBean> data = catagoryBean.getData();
            final CataGroyClassifyAdapter cataGroyClassifyAdapter = new CataGroyClassifyAdapter(data, getActivity());
            cataGroyClassifyAdapter.setColor(0);
            mClassifycatagory.setAdapter(cataGroyClassifyAdapter);
            mClassifycatagory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int cid = data.get(position).getCid();
                    classifyPresenter.showProductCataGroy(cid + "", new ClassifyLoadData(), ClassifyFragment.this);
                    //条目点击变色
                    cataGroyClassifyAdapter.setColor(position);
                    cataGroyClassifyAdapter.notifyDataSetInvalidated();
                }
            });
        }

    }

    //显示详情分类
    @Override
    public void showProductCataCroy(ProductCatagory productCatagory, List<List<ProductCatagory.DataBean.ListBean>> childList) {
        //获取请求信息
        String msg = productCatagory.getMsg();
        //获取分类数据
        List<ProductCatagory.DataBean> data = productCatagory.getData();
        //获取二级列表适配器
        CataGroyClassifyExpandableAdapter cataGroyClassifyExpandableAdapter = new CataGroyClassifyExpandableAdapter(data, getActivity(), childList);
        //设置适配器
        mGoodsclassify.setAdapter(cataGroyClassifyExpandableAdapter);
        //展开二级列表
        for (int i = 0; i < cataGroyClassifyExpandableAdapter.getGroupCount(); i++) {
            mGoodsclassify.expandGroup(i);
        }

    }
}
