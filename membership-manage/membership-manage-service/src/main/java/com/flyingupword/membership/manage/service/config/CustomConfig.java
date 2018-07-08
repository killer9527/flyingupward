package com.flyingupword.membership.manage.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by killer9527 on 2018/7/2.
 */
@Component
public class CustomConfig {
    @Autowired
    private WxAppConfig wxAppConfig;

    @Bean
    public WxAppPayConfig wxAppPayConfig(){
        WxAppPayConfig wxAppPayConfig = new WxAppPayConfig();
        wxAppPayConfig.setAppId(this.wxAppConfig.getAppId());
        wxAppPayConfig.setMchId(this.wxAppConfig.getMchId());
        wxAppPayConfig.setMchKey(this.wxAppConfig.getMchKey());
        wxAppPayConfig.setKeyPath(this.wxAppConfig.getKeyPath());
        wxAppPayConfig.setNotifyUrl(this.wxAppConfig.getNotifyUrl());
        return wxAppPayConfig;
    }
}
