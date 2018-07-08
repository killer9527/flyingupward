package com.flyingupword.membership.manage.vo.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by killer9527 on 2018/7/1.
 */
@ApiModel(value = "CreateUnifiedOrderRequestVO", description = "请求下单支付请求")
public class CreateUnifiedOrderRequestVO {
    private Double amountOfMoney;
    private String code;

    @ApiModelProperty(value = "支付金额")
    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @ApiModelProperty(value = "临时登录凭证：小程序前端调用wx.login(OBJECT)")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
