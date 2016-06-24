package com.creatunion.demo.jobqueue.request;

import com.google.gson.Gson;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class BaseProtocol {

    private String mId;
    private String mMobile;
    private String mToken;

    public BaseProtocol() {
        
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
