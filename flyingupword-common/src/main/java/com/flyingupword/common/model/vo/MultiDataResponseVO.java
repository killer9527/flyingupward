package com.flyingupword.common.model.vo;

import java.util.List;

/**
 * Created by killer9527 on 2018/5/14.
 * 多值响应
 */
public class MultiDataResponseVO<T> extends BaseResponseVO {
    private List<T> data;

    public MultiDataResponseVO(List<T> data) {
        this.data = data;
        this.setStatus(200);
        this.setMsg("OK");
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
