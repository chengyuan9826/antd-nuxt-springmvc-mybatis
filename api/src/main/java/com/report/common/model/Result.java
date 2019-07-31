package com.report.common.model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private String msg = "成功";
    private int state = 0;
    private Map<String,Object> result = new HashMap<String,Object>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
