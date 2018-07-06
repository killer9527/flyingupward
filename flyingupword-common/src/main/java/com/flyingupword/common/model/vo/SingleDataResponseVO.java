package com.flyingupword.common.model.vo;

/**
 * Created by killer9527 on 2018/5/14.
 * 单值响应
 */
public class SingleDataResponseVO<T> extends BaseResponseVO {
    private T data;

    public SingleDataResponseVO(T data) {
        this.data = data;
        this.setStatus(200);
        this.setMsg("OK");
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
