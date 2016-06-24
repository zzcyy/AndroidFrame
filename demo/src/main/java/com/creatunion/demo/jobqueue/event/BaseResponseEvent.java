package com.creatunion.demo.jobqueue.event;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.creatunion.demo.jobqueue.bean.BaseInfo;

import java.lang.reflect.ParameterizedType;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class BaseResponseEvent<T extends BaseInfo> {

    private String mMessage;
    public static final int STATE_SUCCESS = 0;
    public static final int STATE_FAIL = 1;
    public static final int STATE_CANCEL = 3;

    @IntDef(flag = true, value = {
            STATE_SUCCESS,
            STATE_FAIL,
            STATE_CANCEL})
    public @interface State {
    }

    private int mState;
    private T mResponse;
    private Object mTag;

    public BaseResponseEvent() {
    }

    protected BaseResponseEvent(@State int state) {
        mState = state;
    }

    public BaseResponseEvent setMessage(@Nullable String message) {
        this.mMessage = message;
        return this;
    }

    public BaseResponseEvent setResponse(@Nullable T response) {
        this.mResponse = response;
        return this;
    }

    public BaseResponseEvent setTag(@Nullable Object tag) {
        this.mTag = tag;
        return this;
    }

    @State
    public int getState() {
        return mState;
    }


    public Object getTag() {
        return mTag;
    }

    @Nullable
    public T getResponse() {
        return mResponse;
    }

    @Nullable
    public String getMessage() {
        return mMessage;
    }

    public BaseResponseEvent setState(int state) {
        mState = state;
        return this;
    }

    public Class<? extends BaseInfo> getResponseClass() {
        return  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}