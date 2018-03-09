package com.project.jingdong.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.jingdong.R;
import com.project.jingdong.activities.Login_regActivity;
import com.project.jingdong.activities.ProductDetailActivity;
import com.project.jingdong.activities.UserInfoActivity;
import com.project.jingdong.adapters.TuijianAdapter;
import com.project.jingdong.bean.HomePosterBean;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.model.HomeLoadData;
import com.project.jingdong.presenter.MySelfPresenter;
import com.project.jingdong.utils.GridSpacingItemDecoration;
import com.project.jingdong.view.MySelfView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Author:AND
 * Time:2018/2/6.
 * Email:2911743255@qq.com
 * Description:我的页面
 * Detail:
 */

public class MyselfFragment extends Fragment implements MySelfView, View.OnClickListener {
    private static final String TAG = "相机";
    private ImageView mImageUser;
    private RecyclerView mWeinituijian;
    private HomeLoadData homeLoadData;
    private TextView mLoginUser;
    private MySelfPresenter mySelfPresenter;
    private String uid;
    private LinearLayout mMyshipping;
    private ImageView mGetuserMoreinfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.myself_fragment, container, false);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取用户登录与否信息
        uid = getUserInfo();
        mySelfPresenter = new MySelfPresenter();
        //展示为你推荐数据
        mySelfPresenter.tuijianProduct(homeLoadData, this);

        final Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        if (username == null || username == "") {
            mLoginUser.setText("登录/注册>");
        } else {
            mLoginUser.setText(username);
        }
        //uid为0,说明用户并未登录
        if (uid.equals("0")) {
            mGetuserMoreinfo.setClickable(false);
            //跳转登录页面
            mLoginUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_regActivity.class);
                    startActivity(intent);
                }
            });
            mImageUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Login_regActivity.class);
                    startActivity(intent);
                }
            });

        } else {//用户已经登录
            mGetuserMoreinfo.setClickable(true);
            mLoginUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent1);
                }
            });
            //头像的上传操作
            mImageUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    String[] titles = {"拍照", "从相册选择"};
                    builder.setItems(titles, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0:
                                    Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //系统常量， 启动相机的关键
                                    startActivityForResult(openCameraIntent, 1); // 参数常量为自定义的request code, 在取返回结果时有用
                                    //获取文件存储路径
                                    String absolutePath2 = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/vt4.jpg";
                                    //设置为文件
                                    File file2 = new File(absolutePath2);
                                    //添加上传所需参数
                                    HashMap<String, Object> params2 = new HashMap<>();
                                    params2.put("uid", uid + "");
                                    upLoadFile(Constant.UpFile_Url, params2, file2);
                                    break;
                                case 1:
                                    Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                                    intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                    startActivityForResult(intent1, 2);
                                    break;

                            }
                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
        }
    }

    private String getUserInfo() {
        //获取用户是否登录的信息
        SharedPreferences userinfo = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String uid = userinfo.getString("uid", "0");
        return uid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选取相册图片
        switch (requestCode) {
            case 1:
                Bitmap bm = (Bitmap) data.getExtras().get("data");
                Object data1 = data.getExtras().get("data");
                Log.e(TAG, "onActivityResult: " + data1);
                mImageUser.setImageBitmap(bm);
                break;
            case 2:
                cropPhoto(data.getData());// 裁剪图片
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap data2 = extras.getParcelable("data");
                    if (data2 != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(data2);// 保存在SD卡中
                        mImageUser.setImageBitmap(data2);// 用ImageView显示出来
                    }
                } else {

                }
                break;
        }
    }

    /**
     * 读取
     *
     * @param mBitmap
     */
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/");
        file.mkdirs();// 创建文件夹
        String fileName = file + "icon.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void initView(@NonNull final View itemView) {
        homeLoadData = new HomeLoadData();
        mImageUser = (ImageView) itemView.findViewById(R.id.user_image);
        mWeinituijian = (RecyclerView) itemView.findViewById(R.id.weinituijian);
        mLoginUser = (TextView) itemView.findViewById(R.id.user_login);
        mMyshipping = (LinearLayout) itemView.findViewById(R.id.myshipping);
        mMyshipping.setOnClickListener(this);
        mGetuserMoreinfo = (ImageView) itemView.findViewById(R.id.getuserMoreinfo);
    }

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
        mWeinituijian.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mWeinituijian.setAdapter(tuijianAdapter);
        mWeinituijian.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));

        //点击事件
        tuijianAdapter.setOnItemClickListener(new TuijianAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                //获取详细信息
                int pid = data.get(position).getPid();
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.in, R.anim.out);
            }

        });
    }

    //上传头像
    public void upLoadFile(final String url, final Map<String, Object> map, File file) {
        OkHttpClient client = new OkHttpClient();
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", file.getName(), body);
        }
        if (map != null) {
            // map 里面是请求中所需要的 key 和 value
            for (Map.Entry entry : map.entrySet()) {
                requestBody.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("lfq", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String str = response.body().string();

                } else {
                    Log.i("lfq", response.message() + " error : body " + response.body().string());
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_image:

                break;
            case R.id.user_login:

                break;
            case R.id.myshipping:

                break;
            case R.id.getuserMoreinfo:// TODO 18/03/09
                if (uid.equals("0")) {

                } else {
                    Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }
}
