package com.creatunion.androidframe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.creatunion.androidframe.BaseApplication;
import com.creatunion.androidframe.R;
import com.creatunion.androidframe.bean.EventType;
import com.path.android.jobqueue.JobManager;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "laopai";
    private TextView tv;
    private JobManager jobManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        EventBus.getDefault().register(this);
        jobManager = BaseApplication.getInstance().getJobManager();
        
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,FullscreenActivity.class));
            }
        });
    }
    
    @Subscribe
    public void onEventMainThread(EventType eventType){
        Log.d(TAG, "run onEventMainThread : "+Thread.currentThread().getId());
        tv.setText(eventType.getMsg());
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
