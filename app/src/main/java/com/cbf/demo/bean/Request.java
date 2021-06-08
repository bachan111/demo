package com.cbf.demo.bean;

/**
 * @author：practicing
 * @date:2021/6/8 0008 17:58
 * @description:
 */
public class Request {
    private String serialNo;
    private String password;
    private String projectKey;

    public Request(String serialNo, String password, String projectKey) {
        this.serialNo = serialNo;
        this.password = password;
        this.projectKey = projectKey;
    }
}
