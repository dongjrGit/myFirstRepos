package com.yinlian.Extended;

import com.google.gson.Gson;

public class JsonStatus_Base_Model {
    private int    Code;
    private Object Desc;
    private Object Data;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public Object getDesc() {
        return Desc;
    }

    public void setDesc(Object desc) {
        Desc = desc;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String ToJson() {
        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
