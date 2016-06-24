package com.creatunion.demo.jobqueue.job;

import android.util.Log;

import com.creatunion.demo.BaseApplication;
import com.creatunion.demo.jobqueue.bean.LoginInfo;
import com.creatunion.demo.jobqueue.constants.Priority;
import com.creatunion.demo.jobqueue.event.BaseResponseEvent;
import com.creatunion.demo.jobqueue.request.BaseProtocol;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/24
 * @version: 1.0
 * @Description:
 */
public class BaseRequestJob<T extends BaseProtocol, R extends BaseResponseEvent> extends Job {

    private BaseProtocol mRequest;

    private BaseResponseEvent mResponseEvent = null;

    private static final String TAG = "BaseRequestJob";

    protected BaseRequestJob(Params params, T request) {
        super(params);
        this.mRequest = request;
    }

    protected BaseRequestJob(T request, BaseResponseEvent responseEvent) {
        super(new Params(Priority.HIGH).requireNetwork().persist());
        this.mRequest = request;
        this.mResponseEvent = responseEvent;
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        if (mResponseEvent == null) {
            mResponseEvent = ((Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]).newInstance();
        }

        if (!BaseApplication.getInstance().isConnected()) {
            mResponseEvent.setState(BaseResponseEvent.STATE_FAIL);
            mResponseEvent.setMessage(BaseApplication.getInstance().getString(com.creatunion.demo.R.string.network_error));
            EventBus.getDefault().post(mResponseEvent);
            return;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://wthrcdn.etouch.cn/weather_mini?city=bj").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mResponseEvent.setState(BaseResponseEvent.STATE_FAIL);
                mResponseEvent.setMessage(BaseApplication.getInstance().getString(com.creatunion.demo.R.string.network_error));
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setStatus("失败");
                mResponseEvent.setResponse(loginInfo);
                EventBus.getDefault().post(mResponseEvent);
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                mResponseEvent.setResponse((BaseInfo) JSONUtils.fromJson(resContent, mResponseEvent.getResponseClass()));
                mResponseEvent.setState(BaseResponseEvent.STATE_SUCCESS);
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setStatus("成功");
                mResponseEvent.setResponse(loginInfo);
                EventBus.getDefault().post(mResponseEvent);
                Log.d(TAG, "onResponse: ");
            }
        });
    }
    
    @Override
    protected void onCancel() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }
}
