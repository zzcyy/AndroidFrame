package com.creatunion.demo.jobqueue.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class BaseInfo {

    @SerializedName("status")
    private String mStatus;

    @SerializedName("msg")
    private String mMsg=null;

    public BaseInfo(){

    }

    public BaseInfo(String status,String msg){
        mStatus=status;
        mMsg=msg;
    }

    public void setStatus(String status){
        mStatus=status;
    }

    public  String getStatus(){
        return mStatus;
    }

    public void setMsg(String msg){
        mMsg=msg;
    }

    public String getMsg(){
        return mMsg;
    }

}
