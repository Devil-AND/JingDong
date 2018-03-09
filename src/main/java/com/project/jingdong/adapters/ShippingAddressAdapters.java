package com.project.jingdong.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.jingdong.R;
import com.project.jingdong.bean.ShippingAddresBean;

import java.util.List;

/**
 * Author:AND
 * Time:2018/3/9.
 * Email:2911743255@qq.com
 * Description://订单列表适配器
 * Detail:
 */

public class ShippingAddressAdapters extends RecyclerView.Adapter<ShippingAddressAdapters.MyShippingAddressViewholder> {
    private List<ShippingAddresBean.DataBean> addressList;
    private Context context;

    public ShippingAddressAdapters(List<ShippingAddresBean.DataBean> addressList, Context context) {
        this.addressList = addressList;
        this.context = context;
    }

    @Override
    public MyShippingAddressViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.shippingaddress_item, null);
        MyShippingAddressViewholder myShippingAddressViewholder = new MyShippingAddressViewholder(inflate);
        return myShippingAddressViewholder;
    }

    @Override
    public void onBindViewHolder(MyShippingAddressViewholder holder, int position) {
        holder.mAddressNameUser.setText(addressList.get(position).getName() + "");//控件赋值
        holder.mAddressMobileUser.setText(addressList.get(position).getMobile() + "");//控件赋值
        holder.mAddressDetailaddUser.setText(addressList.get(position).getAddr() + "");//控件赋值
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class MyShippingAddressViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mAddressEdit;
        private TextView mAddressNameUser;
        private TextView mAddressMobileUser;
        private TextView mAddressDetailaddUser;

        public MyShippingAddressViewholder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(@NonNull final View itemView) {
            mAddressEdit = (ImageView) itemView.findViewById(R.id.edit_address);
            mAddressEdit.setOnClickListener(this);
            mAddressNameUser = (TextView) itemView.findViewById(R.id.user_address_name);
            mAddressMobileUser = (TextView) itemView.findViewById(R.id.user_address_mobile);
            mAddressDetailaddUser = (TextView) itemView.findViewById(R.id.user_address_detailadd);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.edit_address:
                    Toast.makeText(context, "" + addressList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                    String name = addressList.get(getAdapterPosition()).getName();
                    int uid = addressList.get(getAdapterPosition()).getUid();
                    int addrid = addressList.get(getAdapterPosition()).getAddrid();
                    String mobile = addressList.get(getAdapterPosition()).getMobile();
                    String addr = addressList.get(getAdapterPosition()).getAddr();
                    break;
                default:
                    break;
            }
        }
    }
}
