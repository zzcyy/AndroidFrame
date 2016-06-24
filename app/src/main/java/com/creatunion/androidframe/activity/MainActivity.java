package com.creatunion.androidframe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.creatunion.androidframe.R;
import com.path.android.jobqueue.JobManager;

import de.greenrobot.event.EventBus;

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
        
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,FullscreenActivity.class));
            }
        });
    }
    

    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
