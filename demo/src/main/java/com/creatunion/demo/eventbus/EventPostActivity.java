package com.creatunion.demo.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.creatunion.demo.R;

import de.greenrobot.event.EventBus;

public class EventPostActivity extends AppCompatActivity {

    private Button btn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_post);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new InfoEvent("EventPost的接收到了"));
                finish();
            }
        });
    }
}
