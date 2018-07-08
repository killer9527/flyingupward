package com.flyingupword.membership.manage.dto;

/**
 * Created by killer9527 on 2018/7/2.
 */
public enum PayTypeEnum {
    ALIPAY_APP("alipay_app", "支付宝app"),

    ALIPAY_PC("alipay_pc", "支付宝pc"),

    ALIPAY_WAP("alipay_wap", "支付宝wap"),

    WXPAY_H5("wxpay_h5", "微信公众账号支付"),

    WXPAY_MWEB("MWEB", "微信公众账号支付"),

    WXPAY_APP("wxpay_app", "微信小程序支付")
    ;

    private String code;

    private String name;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    PayTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PayTypeEnum getByCode(String code) {
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()) {
            if (payTypeEnum.getCode().equals(code)) {
                return payTypeEnum;
            }
        }
        throw new PayException(PayResultEnum.PAY_TYPE_ERROR);
    }
}
