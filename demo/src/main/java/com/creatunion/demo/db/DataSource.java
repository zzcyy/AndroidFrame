package com.creatunion.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/27
 * @version: 1.0
 * @Description:
 */
public interface  DataSource {

    public SQLiteDatabase openSQLiteDatabase(Context context);

    public void askCloseDatabase(SQLiteDatabase db);
}
