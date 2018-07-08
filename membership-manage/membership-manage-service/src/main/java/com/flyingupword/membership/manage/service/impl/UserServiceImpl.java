package com.flyingupword.membership.manage.service.impl;

import com.flyingupword.membership.manage.entity.MsUserDO;
import com.flyingupword.membership.manage.mapper.MsUserMapper;
import com.flyingupword.membership.manage.service.UserService;
import com.flyingupword.membership.manage.vo.user.AddUserRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by killer9527 on 2018/6/1.
 * 用户相关服务实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MsUserMapper userMapper;

    @Override
    public boolean addUser(AddUserRequestVO user) {
        MsUserDO userDO = new MsUserDO();
        userDO.setLoginName(user.getLoginName());
        userDO.setPassword(user.getPassword());
        userDO.setEmail(user.getEmail());
        userDO.setPhoneNumber(user.getPhoneNumber());
        userDO.setGender(user.getGender());
        userDO.setAddress(user.getAddress());
        userDO.setStatus("1");

        int result = this.userMapper.insertSelective(userDO);
        return result > 0;
    }
}
