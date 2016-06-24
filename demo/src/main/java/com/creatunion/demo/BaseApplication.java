package com.creatunion.demo;

import android.app.Application;
import android.util.Log;

import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import com.path.android.jobqueue.network.NetworkUtil;
import com.path.android.jobqueue.network.NetworkUtilImpl;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/24
 * @version: 1.0
 * @Description:
 */
public class BaseApplication extends Application {

    private static BaseApplication mInstance;
    private JobManager mJobManager;
    private NetworkUtil mNetworkUtil;
    
    public static BaseApplication getInstance(){
        return mInstance;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        configureJobManager();
        mNetworkUtil = new NetworkUtilImpl(this);
    }

    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {

                    private static final String TAG = "JOBS";

                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive  
                .maxConsumerCount(3)//up to 3 consumers at a time  
                .loadFactor(3)//3 jobs per consumer  
                .consumerKeepAlive(120)//wait 2 minute  
                .build();
        mJobManager = new JobManager(this, configuration);
    }

    public JobManager getJobManager() {
        return mJobManager;
    }

    public boolean isConnected() {
        return mNetworkUtil.isConnected(this);
    }
}
