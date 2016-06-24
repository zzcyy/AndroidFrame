package com.creatunion.demo.jobqueue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.creatunion.demo.BaseApplication;
import com.creatunion.demo.R;
import com.creatunion.demo.jobqueue.event.BaseResponseEvent;
import com.creatunion.demo.jobqueue.event.LoginResultEvent;
import com.creatunion.demo.jobqueue.job.PostTweetJob;
import com.creatunion.demo.jobqueue.request.LoginRequest;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class JobQueueActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobqueue);
        EventBus.getDefault().register(this);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername("laopai");
                loginRequest.setPassword("");
                PostTweetJob postTweetJob = new PostTweetJob(loginRequest);
                BaseApplication.getInstance().getJobManager().addJobInBackground(postTweetJob);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void LoginResultEvent(LoginResultEvent event) {
        if (event.getState() == BaseResponseEvent.STATE_SUCCESS) {
            Toast.makeText(JobQueueActivity.this, event.getResponse().getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(JobQueueActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
