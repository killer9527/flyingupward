package com.flyingupword.membership.manage.service;

import com.flyingupword.membership.manage.vo.user.AddUserRequestVO;

/**
 * Created by killer9527 on 2018/6/1.
 * 用户信息相关服务
 */
public interface UserService {
    boolean addUser(AddUserRequestVO user);
}
