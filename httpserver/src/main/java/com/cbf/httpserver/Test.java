package com.cbf.httpserver;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author：practicing
 * @date:2021/4/22 0022 20:36
 * @description:
 */
@Entity
public class Test {
    @Id
    private Long id;//必须为Long类型,大写
    private String date;
    @Unique
    private String key;
    private int step;
    @Generated(hash = 1933514200)
    public Test(Long id, String date, String key, int step) {
        this.id = id;
        this.date = date;
        this.key = key;
        this.step = step;
    }
    @Generated(hash = 372557997)
    public Test() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public int getStep() {
        return this.step;
    }
    public void setStep(int step) {
        this.step = step;
    }
}
