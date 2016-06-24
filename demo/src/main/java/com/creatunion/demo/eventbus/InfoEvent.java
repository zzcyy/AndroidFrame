package com.creatunion.demo.eventbus;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/24
 * @version: 1.0
 * @Description:
 */
public class InfoEvent {
    
    private String msg;

    public InfoEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
