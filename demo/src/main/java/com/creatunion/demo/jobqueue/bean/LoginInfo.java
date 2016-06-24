package com.creatunion.demo.jobqueue.bean;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class LoginInfo extends BaseInfo{
    
    private String mToken;
    private String mUserId;
    private String mMobile;

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }
}
