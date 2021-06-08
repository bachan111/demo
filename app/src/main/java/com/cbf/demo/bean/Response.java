package com.cbf.demo.bean;

/**
 * @author：practicing
 * @date:2021/6/8 0008 17:29
 * @description:
 */
public class Response<T> {
    private int code;
    private String msg;
    private String token;
    private T testeeLoginDTO;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getTesteeLoginDTO() {
        return testeeLoginDTO;
    }

    public void setTesteeLoginDTO(T testeeLoginDTO) {
        this.testeeLoginDTO = testeeLoginDTO;
    }

    public static class DataBean {

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
            return "DataBean{" +
                    "testeeUid='" + testeeUid + '\'' +
                    ", testeeName='" + testeeName + '\'' +
                    ", serialNo='" + serialNo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", token='" + token + '\'' +
                ", testeeLoginDTO=" + testeeLoginDTO +
                '}';
    }
}
