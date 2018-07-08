package com.flyingupword.membership.manage.service.impl;

import com.flyingupword.common.model.vo.SingleDataResponseVO;
import com.flyingupword.common.utils.UniqueIdUtil;
import com.flyingupword.membership.manage.dto.PayTypeEnum;
import com.flyingupword.membership.manage.request.CreateUnifiedOrderRequest;
import com.flyingupword.membership.manage.request.WxPayUnifiedOrderRequest;
import com.flyingupword.membership.manage.response.WxAppPaySyncResponse;
import com.flyingupword.membership.manage.service.PayService;
import com.flyingupword.membership.manage.service.WxAppPayApi;
import com.flyingupword.membership.manage.service.config.WxAppPayConfig;
import com.flyingupword.membership.manage.vo.pay.CreateUnifiedOrderRequestVO;
import com.flyingupword.membership.manage.vo.pay.PayResponseVO;
import com.lly835.bestpay.constants.WxPayConstants;
import com.lly835.bestpay.service.impl.WxPaySignature;
import com.lly835.bestpay.utils.MapUtil;
import com.lly835.bestpay.utils.MoneyUtil;
import com.lly835.bestpay.utils.RandomUtil;
import com.lly835.bestpay.utils.XmlUtil;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by killer9527 on 2018/7/2.
 */
@Service
public class PayServiceImpl implements PayService {
    @Autowired
    private WxAppPayConfig wxAppPayConfig;

    @Override
    public SingleDataResponseVO<PayResponseVO> createUnifiedOrder(CreateUnifiedOrderRequest createUnifiedOrderRequest) {
        //1. 根据临时登录凭证code，调用登录凭证后端校验接口（HTTPS 接口）获取openid
        String openId = getOpenId(createUnifiedOrderRequest.getCode());
        //2. 生成商户订单
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setOutTradeNo(UniqueIdUtil.genItemId() + "");
        wxPayUnifiedOrderRequest.setTotalFee(MoneyUtil.Yuan2Fen(createUnifiedOrderRequest.getAmountOfMoney()));
        wxPayUnifiedOrderRequest.setBody("小程序支付body");
        wxPayUnifiedOrderRequest.setOpenid(openId);
        wxPayUnifiedOrderRequest.setTradeType(switchTradeType(PayTypeEnum.WXPAY_APP));

        wxPayUnifiedOrderRequest.setAppid(this.wxAppPayConfig.getAppId());
        wxPayUnifiedOrderRequest.setMchId(this.wxAppPayConfig.getMchId());
        wxPayUnifiedOrderRequest.setNotifyUrl(this.wxAppPayConfig.getNotifyUrl());
        wxPayUnifiedOrderRequest.setNonceStr(RandomUtil.getRandomStr());
        wxPayUnifiedOrderRequest.setSpbillCreateIp("8.8.8.8");
        wxPayUnifiedOrderRequest.setSign(WxPaySignature.sign(MapUtil.buildMap(wxPayUnifiedOrderRequest), this.wxAppPayConfig.getMchKey()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WxPayConstants.WXPAY_GATEWAY)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor((new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)))
                        .build()
                )
                .build();
        RequestBody body = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), XmlUtil.toString(wxPayUnifiedOrderRequest));
        Call<WxAppPaySyncResponse> call = retrofit.create(WxAppPayApi.class).unifiedorder(body);
        Response<WxAppPaySyncResponse> retrofitResponse  = null;
        try{
            retrofitResponse = call.execute();
        }catch (IOException e) {
            e.printStackTrace();
        }
        if (!retrofitResponse.isSuccessful()) {
            throw new RuntimeException("【微信统一支付】发起支付, 网络异常");
        }
        WxAppPaySyncResponse wxAppPaySyncResponse = retrofitResponse.body();

        if(!wxAppPaySyncResponse.getReturnCode().equals(WxPayConstants.SUCCESS)) {
            throw new RuntimeException("【微信统一支付】发起支付, returnCode != SUCCESS, returnMsg = " + wxAppPaySyncResponse.getReturnMsg());
        }
        if (!wxAppPaySyncResponse.getResultCode().equals(WxPayConstants.SUCCESS)) {
            throw new RuntimeException("【微信统一支付】发起支付, resultCode != SUCCESS, err_code = " + wxAppPaySyncResponse.getErrCode() + " err_code_des=" + wxAppPaySyncResponse.getErrCodeDes());
        }
        PayResponseVO payResponse = this.buildPayResponse(wxAppPaySyncResponse);
        SingleDataResponseVO<PayResponseVO> response = new SingleDataResponseVO<>(payResponse);
        return response;
    }

    /**
     * 获取openid
     * @param code
     * @return
     */
    private String getOpenId(String code){
        //todo:调用接口实现
        return "oTgZpwYYZoGfK2JmaahfpIz1tquQ";
    }

    /**
     * H5支付交易类型选择
     */
    private String switchTradeType(PayTypeEnum payTypeEnum){
        String tradeType = "APP";
        switch (payTypeEnum){
            case WXPAY_H5:
                tradeType = "JSAPI";
                break;
            case WXPAY_MWEB:
                tradeType = "MWEB";
                break;
            case WXPAY_APP:
                tradeType = "JSAPI";
                break;
        }
        return tradeType;
    }

    /**
     * 返回给h5的参数
     * @param response
     * @return
     */
    private PayResponseVO buildPayResponse(WxAppPaySyncResponse response) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = RandomUtil.getRandomStr();
        String packAge = "prepay_id=" + response.getPrepayId();
        String signType = "MD5";

        //先构造要签名的map
        Map<String, String> map = new HashMap<>();
        map.put("appId", response.getAppid());
        map.put("timeStamp", timeStamp);
        map.put("nonceStr", nonceStr);
        map.put("package", packAge);
        map.put("signType", signType);

        PayResponseVO payResponse = new PayResponseVO();
        payResponse.setAppId(response.getAppid());
        payResponse.setTimeStamp(timeStamp);
        payResponse.setNonceStr(nonceStr);
        payResponse.setPackAge(packAge);
        payResponse.setSignType(signType);
        payResponse.setPaySign(WxPaySignature.sign(map, this.wxAppPayConfig.getMchKey()));
        payResponse.setMwebUrl(response.getMwebUrl());

        return payResponse;
    }
}
