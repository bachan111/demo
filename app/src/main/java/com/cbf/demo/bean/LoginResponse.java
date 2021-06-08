package com.cbf.demo.bean;

/**
 * @author：practicing
 * @date:2021/6/8 0008 18:34
 * @description:
 */
public class LoginResponse {
    private String testeeUid;
    private String testeeName;//昵称
    private String serialNo;//登录账号

    public String getTesteeUid() {
        return testeeUid;
    }

    public void setTesteeUid(String testeeUid) {
        this.testeeUid = testeeUid;
    }

    public String getTesteeName() {
        return testeeName;
    }

    public void setTesteeName(String testeeName) {
        this.testeeName = testeeName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "testeeUid='" + testeeUid + '\'' +
                ", testeeName='" + testeeName + '\'' +
                ", serialNo='" + serialNo + '\'' +
                '}';
    }
}
