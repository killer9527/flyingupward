package com.flyingupword.membership.manage.controller;

import com.flyingupword.common.model.vo.BaseResponseVO;
import com.flyingupword.membership.manage.service.UserService;
import com.flyingupword.membership.manage.vo.user.AddUserRequestVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by killer9527 on 2018/6/1.
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController", description = "用户相关api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户")
    public BaseResponseVO addUser(@RequestBody AddUserRequestVO request){
        boolean isSuccess = this.userService.addUser(request);
        BaseResponseVO response = new BaseResponseVO();
        response.setStatus(isSuccess ? 200 : 501);
        response.setMsg(isSuccess ? "添加用户成功" : "添加用户失败");
        return response;
    }
}
