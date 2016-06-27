package com.creatunion.demo.db.sample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/27
 * @version: 1.0
 * @Description:
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "PhodevDatabaseHelper";
    public static final String DATABASE_NAME = "phodev_demo_db_module.db";
    public static final int DATABASE_VERSION = 1;
    private static DBHelper instance = null;
    
    public static synchronized DBHelper getInstance(Context context){
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }
    
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
}
