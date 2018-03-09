package com.project.jingdong.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.activities.ConfirmOrderActivity;
import com.project.jingdong.activities.ProductDetailActivity;
import com.project.jingdong.adapters.CartAdapter;
import com.project.jingdong.adapters.TuijianAdapter;
import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.customview.MyExpanableListView;
import com.project.jingdong.model.CartsListsModel;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.presenter.CartPresenter;
import com.project.jingdong.utils.GridSpacingItemDecoration;
import com.project.jingdong.view.CartView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:购物车页面
 * Detail:
 */

public class CartFragment extends Fragment implements View.OnClickListener, CartView {
    private static final String TAG = "购物车";
    private RecyclerView mTuijianCart;
    private CheckBox mCheckallgoods;
    private TextView mCartTotal;
    private TextView mGouToSum;
    private CartPresenter cartPresenter;
    private CartsListsModel cartsListsModel;
    private MyExpanableListView mCartlist;
    private CartAdapter cartAdapter;
    private SharedPreferences userinfo;
    private List<CartListBean.DataBean> cartList;
    private String uid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.cart_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(@NonNull final View itemView) {
        mTuijianCart = (RecyclerView) itemView.findViewById(R.id.cart_tuijian);
        mCheckallgoods = (CheckBox) itemView.findViewById(R.id.checkallgoods);
        mCartTotal = (TextView) itemView.findViewById(R.id.cartTotal);
        mGouToSum = (TextView) itemView.findViewById(R.id.gouToSum);
        mGouToSum.setOnClickListener(this);
        cartPresenter = new CartPresenter(this);
        mCartlist = (MyExpanableListView) itemView.findViewById(R.id.cartlistview);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //为你推荐数据
        cartPresenter.tuijianProduct(new HomeLoadData(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        userinfo = getActivity().getSharedPreferences("userinfo", MODE_PRIVATE);
        uid = userinfo.getString("uid", "0");
        if (uid.equals("0")) {
            Toast.makeText(getActivity(), "购物车还是空的,先登录吧!", Toast.LENGTH_SHORT).show();
        } else {
            //加载购物车数据
            cartPresenter.getCartLists(uid);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gouToSum:
                String uid = userinfo.getString("uid", "0");
                if (uid.equals("0")) {
                    Toast.makeText(getActivity(), "购物车还是空的,先登录吧!", Toast.LENGTH_SHORT).show();
                } else {
                    //去结算
                    Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    //显示推荐数据
    @Override
    public void showTuijian(HomePosterBean homePosterBean) {
        //解析数据
        HomePosterBean.TuijianBean tuijian = homePosterBean.getTuijian();
        final List<HomePosterBean.TuijianBean.ListBean> data = tuijian.getList();
        //获取适配器
        TuijianAdapter tuijianAdapter = new TuijianAdapter(data, getActivity());
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        mTuijianCart.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mTuijianCart.setAdapter(tuijianAdapter);
        mTuijianCart.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        //点击事件
        tuijianAdapter.setOnItemClickListener(new TuijianAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //获取详细信息
                int detailUrl = data.get(position).getPid();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("pid", detailUrl + "");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }

        });
    }

    //加载购物车数据
    @Override
    public void showCartList(CartListBean cartListBean) {
        List<CartListBean.DataBean> data = cartListBean.getData();
        cartAdapter = new CartAdapter(getActivity(), data, this,uid);
        mCartlist.setAdapter(cartAdapter);
        //展开父列表
        for (int i = 0; i < cartAdapter.getGroupCount(); i++) {
            mCartlist.expandGroup(i);
        }

    }

    //更新购物车
    @Override
    public void updateCartData() {
        cartAdapter.notifyDataSetChanged();//更新适配器
    }

    //结算
    @Override
    public void priceAndNum(double round) {
        mCartTotal.setText("合计: " + round);//结算
    }
}
