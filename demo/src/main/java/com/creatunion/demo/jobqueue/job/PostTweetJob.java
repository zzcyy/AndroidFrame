package com.creatunion.demo.jobqueue.job;

import com.creatunion.demo.jobqueue.constants.Priority;
import com.creatunion.demo.jobqueue.event.LoginResultEvent;
import com.creatunion.demo.jobqueue.request.LoginRequest;
import com.path.android.jobqueue.Params;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/24
 * @version: 1.0
 * @Description:
 */
public class PostTweetJob extends BaseRequestJob<LoginRequest,LoginResultEvent> {

    
    public PostTweetJob(LoginRequest request) {
        super(new Params(Priority.HIGH).requireNetwork().persist(),request);
    }
}
