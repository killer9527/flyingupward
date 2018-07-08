package com.flyingupword.membership.manage.controller;

import com.flyingupword.common.model.vo.SingleDataResponseVO;
import com.flyingupword.membership.manage.request.CreateUnifiedOrderRequest;
import com.flyingupword.membership.manage.service.PayService;
import com.flyingupword.membership.manage.vo.pay.CreateUnifiedOrderRequestVO;
import com.flyingupword.membership.manage.vo.pay.PayResponseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by killer9527 on 2018/7/2.
 */
@RestController
@RequestMapping(value = "/wxPay")
@ApiModel(value = "WxPayController", description = "微信支付相关接口")
public class WxPayController {
    @Autowired
    private PayService payService;

    @RequestMapping(value = "createUnifiedOrder", method = RequestMethod.POST)
    @ApiOperation(value = "支付统一下单")
    public SingleDataResponseVO<PayResponseVO> createUnifiedOrder(CreateUnifiedOrderRequestVO request){
        CreateUnifiedOrderRequest createUnifiedOrderRequest = new CreateUnifiedOrderRequest();
        createUnifiedOrderRequest.setCode(request.getCode());
        createUnifiedOrderRequest.setAmountOfMoney(request.getAmountOfMoney());
        SingleDataResponseVO<PayResponseVO> unifiedOrder = this.payService.createUnifiedOrder(createUnifiedOrderRequest);
        return unifiedOrder;
    }
}
