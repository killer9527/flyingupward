package com.flyingupword.membership.manage.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by killer9527 on 2018/7/2.
 */
public class CreateUnifiedOrderRequest {
    /**
     * 支付金额
     */
    private Double amountOfMoney;
    private String code;
    private String spBillCreateIp;

    @ApiModelProperty(value = "支付金额")
    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    /**
     * 临时登录凭证：小程序前端调用wx.login(OBJECT)
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSpBillCreateIp() {
        return spBillCreateIp;
    }

    public void setSpBillCreateIp(String spBillCreateIp) {
        this.spBillCreateIp = spBillCreateIp;
    }
}
