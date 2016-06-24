package com.creatunion.demo.jobqueue.request;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/23
 * @version: 1.0
 * @Description:
 */
public class LoginRequest extends BaseProtocol {
    
    private String username;
    
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
