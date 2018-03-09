package com.project.jingdong.presenter;

import com.project.jingdong.bean.DisplayNewsBean;
import com.project.jingdong.model.DisplayDataModel;
import com.project.jingdong.constant.Constant;
import com.project.jingdong.constant.LoadNetDataListener;
import com.project.jingdong.view.DisplayView;

/**
 * Author:AND
 * Time:2018/2/10.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class DisplayPresenter {
    private static final String TAG = "发现presenter";

    public void getAndroidNewsData(int page, DisplayDataModel displayDataModel, final DisplayView displayView) {
        displayDataModel.getAndridNewsData(Constant.Display, page, new LoadNetDataListener<DisplayNewsBean>() {
            @Override
            public void loadSuccess(DisplayNewsBean data) {
                displayView.showDisplayData(data);
            }

            @Override
            public void loadFailed(DisplayNewsBean data) {

            }
        });
    }
}
