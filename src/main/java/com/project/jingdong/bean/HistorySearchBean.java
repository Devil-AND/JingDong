package com.project.jingdong.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */
@Entity
public class HistorySearchBean {
    private String searchName;

    @Generated(hash = 2073760500)
    public HistorySearchBean(String searchName) {
        this.searchName = searchName;
    }

    @Generated(hash = 954352461)
    public HistorySearchBean() {
    }

    public String getSearchName() {
        return this.searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

   

}
