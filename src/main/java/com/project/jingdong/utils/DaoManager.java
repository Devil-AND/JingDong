package com.project.jingdong.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.project.jingdong.greendao.DaoMaster;

/**
 * Author:AND
 * Time:2018/2/11.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class DaoManager {
    private final static String dbName = "historynote";
    private static DaoManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    private DaoManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DaoManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager(context);
                }
            }
        }
        return mInstance;
    }

    public SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }
}
