package com.flyingupword.membership.manage.dto;

/**
 * Created by killer9527 on 2018/7/2.
 */
public class PayException extends RuntimeException {

    private Integer code;

    public PayException(PayResultEnum resultEnum) {
        super(resultEnum.getMsg());
        code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
