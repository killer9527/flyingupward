package com.flyingupword.membership.manage.controller;

import com.flyingupword.common.model.vo.BaseResponseVO;
import com.flyingupword.membership.manage.vo.PrintRequestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by killer9527 on 2018/5/13.
 */
@RestController
@Api(value = "Hello", description = "测试")
public class Hello {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ApiOperation(value = "hello")
    public String hello(){
        LoggerFactory.getLogger(Hello.class).info("test");
        return "hello world";
    }

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    @ApiOperation(value = "打印测试")
    public BaseResponseVO print(@RequestBody PrintRequestVO request){
        LoggerFactory.getLogger(Hello.class).info(request.getMessage());
        return new BaseResponseVO();
    }
}
