package com.creatunion.androidframe;

import android.app.Application;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class BaseApplication extends Application {


    private static BaseApplication mInstance;

    public static BaseApplication getInstance() {

        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
