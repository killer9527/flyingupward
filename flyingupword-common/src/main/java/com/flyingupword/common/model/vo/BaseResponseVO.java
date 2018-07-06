package com.flyingupword.common.model.vo;

/**
 * Created by killer9527 on 2018/5/14.
 * 响应基类
 */
public class BaseResponseVO {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    public BaseResponseVO(){
        this.status = 200;
        this.msg = "OK";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
