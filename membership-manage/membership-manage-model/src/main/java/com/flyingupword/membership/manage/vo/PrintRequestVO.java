package com.flyingupword.membership.manage.vo;

import io.swagger.annotations.ApiModel;

/**
 * Created by killer9527 on 2018/5/14.
 */
@ApiModel(value = "PrintRequestVO")
public class PrintRequestVO {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
