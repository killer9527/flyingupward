package com.flyingupword.membership.manage.service;

import com.flyingupword.common.model.vo.SingleDataResponseVO;
import com.flyingupword.membership.manage.request.CreateUnifiedOrderRequest;
import com.flyingupword.membership.manage.vo.pay.CreateUnifiedOrderRequestVO;
import com.flyingupword.membership.manage.vo.pay.PayResponseVO;

/**
 * Created by killer9527 on 2018/7/2.
 */
public interface PayService {
    /**
     * 支付统一下单
     * @param payRequest
     * @return
     */
    SingleDataResponseVO<PayResponseVO> createUnifiedOrder(CreateUnifiedOrderRequest payRequest);
}
