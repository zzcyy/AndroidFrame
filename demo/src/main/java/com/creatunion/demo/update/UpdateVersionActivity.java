package com.creatunion.demo.update;

import android.app.Notification;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.creatunion.demo.R;
import com.creatunion.utils.UpdateService;

public class UpdateVersionActivity extends AppCompatActivity {

    private static final String URL = "http://store.yunzhi120.com/app/apk/down?f=1464852617_4693.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version);
    }

    public void update(View view){
        UpdateService.Builder.create(URL).build(this);
    }

    public void updateFlag(View view){
        UpdateService.Builder.create(URL)
                .setStoreDir("update/flag")
                .setDownloadSuccessNotificationFlag(Notification.DEFAULT_ALL)
                .setDownloadErrorNotificationFlag(Notification.DEFAULT_ALL)
                .build(this);
    }

    public void updateStore(View view){
        UpdateService.Builder.create(URL)
                .setStoreDir("update/store")
                .build(this);
    }
}
