package com.flyingupword.membership.manage.service;

import com.flyingupword.membership.manage.response.WxAppPaySyncResponse;
import com.lly835.bestpay.model.wxpay.response.WxPayRefundResponse;
import com.lly835.bestpay.model.wxpay.response.WxPaySandboxKeyResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by killer9527 on 2018/7/2.
 */
public interface WxAppPayApi {
    /**
     * 统一下单
     * @param body
     * @return
     */
    @POST("/pay/unifiedorder")
    Call<WxAppPaySyncResponse> unifiedorder(@Body RequestBody body);

    /**
     * 申请退款
     * @param body
     * @return
     */
    @POST("/secapi/pay/refund")
    Call<WxPayRefundResponse> refund(@Body RequestBody body);

    /**
     * 申请沙箱密钥
     * @param body
     * @return
     */
    @POST("/sandboxnew/pay/getsignkey")
    Call<WxPaySandboxKeyResponse> getsignkey(@Body RequestBody body);
}
