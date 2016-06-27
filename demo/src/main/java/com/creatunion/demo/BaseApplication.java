package com.creatunion.demo;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
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
        initUniversalImageLoader();
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

    private void initUniversalImageLoader() {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(getApplicationContext());
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for releaseAllVideos app
        config.defaultDisplayImageOptions(getDefaultDisplayImageOption());
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public static DisplayImageOptions getDefaultDisplayImageOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(new ColorDrawable(Color.parseColor("#f0f0f0")))
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(500)) // 设置图片渐显的时间
//                .delayBeforeLoading(300)  // 下载前的延迟时间
                .build();
        return options;
    }
}
