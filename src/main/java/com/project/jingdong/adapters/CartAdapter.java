package com.project.jingdong.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.jingdong.R;
import com.project.jingdong.bean.CartListBean;
import com.project.jingdong.customview.AmountView;
import com.project.jingdong.presenter.CartPresenter;
import com.project.jingdong.view.CartView;

import java.util.HashMap;
import java.util.List;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description://电商购物车
 * Detail:
 */

public class CartAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "购物车适配器";
    private Context context;
    private List<CartListBean.DataBean> list;
    private CartPresenter cartPresenter;
    private String uid;

    public CartAdapter(Context context, List<CartListBean.DataBean> list, CartView cartView, String uid) {
        this.context = context;
        this.list = list;
        this.uid = uid;
        this.cartPresenter = new CartPresenter(cartView);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.cart_group_view, null);
            CheckBox checkBox = convertView.findViewById(R.id.check_merchant);
            TextView textView = convertView.findViewById(R.id.merchant_name);
            groupViewHolder = new GroupViewHolder(checkBox, textView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        final CartListBean.DataBean dataBean = list.get(groupPosition);//获取商家数据
        groupViewHolder.merchantNmae.setText("" + dataBean.getSellerName());//控件赋值
        cartPresenter.showCountAndPrice(list);//结算选中商品
        groupViewHolder.checkmerchant.setChecked(dataBean.isGroup_flag());//设置父列表状态
        boolean checkAllChildGoods = isCheckAllChildGoods(list.get(groupPosition).getList());//判断商家下的商品是否被选中
        groupViewHolder.checkmerchant.setChecked(checkAllChildGoods);//根据判断结果给商家设置状态
        //父列表复选框点击事件
        groupViewHolder.checkmerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CartListBean.GoodsBean> childList = list.get(groupPosition).getList();//获取商家的商品集合
                boolean checked = groupViewHolder.checkmerchant.isChecked();//获取按钮点击事件
                dataBean.setGroup_flag(checked);//给bean类赋值

                changeMerchantGoods(checked, groupPosition);//全选反选商家下的商品

                cartPresenter.showCountAndPrice(list);//结算
            }
        });
        return convertView;
    }

    /**
     * 全选和反选商家下的商品
     */
    private void changeMerchantGoods(boolean checked, int groupPosition) {
        //获取当前商家下的商品数据
        List<CartListBean.GoodsBean> childList = list.get(groupPosition).getList();
        for (int i = 0; i < childList.size(); i++) {
            childList.get(i).setChild_flag(checked);
            if (checked == false) {
                childList.get(i).setSelected(0);
            } else if (checked == true) {
                childList.get(i).setSelected(1);
            }
            HashMap<String, String> map = new HashMap<>();//获取商品参数
            map.put("uid", "" + uid);
            map.put("sellerid", "" + childList.get(i).getSellerid());
            map.put("pid", "" + childList.get(i).getPid());
            map.put("selected", "" + childList.get(i).getSelected());
            map.put("num", "" + childList.get(i).getNum());
            cartPresenter.updateCart(map);
        }
    }

    /**
     * 判断是否选中当前商家下的商品
     */
    private boolean isCheckAllChildGoods(List<CartListBean.GoodsBean> childList) {
        //遍历字列表,判断是否全选所有的子列表的商品
        for (int i = 0; i < childList.size(); i++) {
            int selected = childList.get(i).getSelected();
            if (selected == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.cart_child_view, null);
            childViewHolder.goods_checkbox = convertView.findViewById(R.id.check_goods);
            childViewHolder.goodsTitle = convertView.findViewById(R.id.goods_title);
            childViewHolder.goodsPrice = convertView.findViewById(R.id.goods_price);
            childViewHolder.goodsImage = convertView.findViewById(R.id.goods_image);
            childViewHolder.add_down = convertView.findViewById(R.id.add_down);
            childViewHolder.delete = convertView.findViewById(R.id.delete);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        Button decrease = childViewHolder.add_down.findViewById(R.id.btnDecrease);
        Button increase = childViewHolder.add_down.findViewById(R.id.btnIncrease);
        EditText etAmount = childViewHolder.add_down.findViewById(R.id.etAmount);

        cartPresenter.showCountAndPrice(list);//结算
        //获取商品数据
        final CartListBean.GoodsBean goodsBean = list.get(groupPosition).getList().get(childPosition);
        //控件赋值
        childViewHolder.goodsTitle.setText(goodsBean.getTitle() + "");//标题
        childViewHolder.goodsPrice.setText(goodsBean.getPrice() + "");//价格
        Glide.with(context).load(list.get(groupPosition).getList().get(childPosition).getImages().split("[|]")[0]).into(childViewHolder.goodsImage);
        //根据selected值改变商品复选框状态
        switch (goodsBean.getSelected()) {
            case 0:
                childViewHolder.goods_checkbox.setChecked(false);
                goodsBean.setChild_flag(false);
                break;
            case 1:
                childViewHolder.goods_checkbox.setChecked(true);
                goodsBean.setChild_flag(true);
                break;
        }

        //商品点击事件
        childViewHolder.goods_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = childViewHolder.goods_checkbox.isChecked();//获取CheckBox的点击状态
                goodsBean.setChild_flag(checked);
                if (checked == true) {
                    goodsBean.setSelected(1);
                } else {
                    goodsBean.setSelected(0);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", uid);
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
                notifyDataSetChanged();
            }
        });
        //删除购物车
        childViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartPresenter.deleteCart(goodsBean.getPid() + "");//删除购物车
                list.get(groupPosition).getList().remove(childPosition);//删除bean类数据
                List<CartListBean.GoodsBean> childList = list.get(groupPosition).getList();
                if (childList == null || childList.isEmpty()) {
                    CartAdapter.this.list.remove(groupPosition);
                }
                cartPresenter.showCountAndPrice(CartAdapter.this.list);//结算
                notifyDataSetChanged();
            }
        });
        etAmount.setText(goodsBean.getNum() + "");//给加减器数量赋值
        //数量
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsBean.getNum();
                if (num == 1) {
                    goodsBean.setNum(1);
                } else {
                    num--;
                    goodsBean.setNum(num);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", uid);
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
            }
        });
        //数量加
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = goodsBean.getNum();
                if (num < 1) {
                    goodsBean.setNum(1);
                } else {
                    num++;
                    goodsBean.setNum(num);
                }
                HashMap<String, String> map = new HashMap<>();//获取商品参数
                //获取购物车的参数
                map.put("uid", uid);
                map.put("sellerid", "" + goodsBean.getSellerid());
                map.put("pid", "" + goodsBean.getPid());
                map.put("selected", "" + goodsBean.getSelected());
                map.put("num", "" + goodsBean.getNum());
                cartPresenter.updateCart(map);//刷新购物车
                map.clear();
                cartPresenter.showCountAndPrice(list);//结算
            }
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        private CheckBox checkmerchant;
        private TextView merchantNmae;

        public GroupViewHolder(CheckBox checkmerchant, TextView merchantNmae) {
            this.checkmerchant = checkmerchant;
            this.merchantNmae = merchantNmae;
        }
    }

    class ChildViewHolder {
        CheckBox goods_checkbox;
        TextView goodsTitle;
        TextView goodsPrice;
        ImageView goodsImage;
        AmountView add_down;
        Button delete;
    }
}
