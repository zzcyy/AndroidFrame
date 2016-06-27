package com.creatunion.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.creatunion.demo.eventbus.EventBusActivity;
import com.creatunion.demo.flowlayout.FlowLayoutActivity;
import com.creatunion.demo.jobqueue.JobQueueActivity;
import com.creatunion.demo.update.UpdateVersionActivity;
import com.creatunion.demo.videoplayer.VideoMainActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private SimpleAdapter simppleAdapter;

    private Class[] claszz = new Class[]{
            JobQueueActivity.class,
            EventBusActivity.class,
            FlowLayoutActivity.class,
            VideoMainActivity.class,
            UpdateVersionActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        simppleAdapter = new SimpleAdapter(this,R.layout.item_category,claszz);
        mListView.setAdapter(simppleAdapter);
        mListView.setOnItemClickListener(this);
    }

    class SimpleAdapter extends ArrayAdapter<Class> {

        private int resource;

        public SimpleAdapter(Context context, int resource, Class[] objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String title = getItem(position).getSimpleName();
            if(convertView == null){
                convertView = getLayoutInflater().inflate(resource,null);
            }
            TextView textView = (TextView) convertView.findViewById(R.id.id_title);
            textView.setText(title);
            return convertView;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,claszz[i]);
        startActivity(intent);
    }
}