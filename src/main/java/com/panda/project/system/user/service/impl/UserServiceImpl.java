package com.panda.project.system.user.service.impl;

import com.google.code.kaptcha.Constants;
import com.panda.common.exception.user.UserException;
import com.panda.common.utils.Md5Utils;
import com.panda.common.utils.ServletUtils;
import com.panda.common.utils.StringUtils;
import com.panda.project.system.user.domain.User;
import com.panda.project.system.user.mapper.UserMapper;
import com.panda.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author jamie
 * @ClassName: UserServiceImpl
 * @Description:
 * @data 2019-01-04 12:10
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password, String code) {
        ServletUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(StringUtils.isEmpty(username)){
            throw new UserException("username.not.null",null);
        }
        if(StringUtils.isEmpty(password)){
            throw new UserException("password.not.null",null);
        }
        if(StringUtils.isEmpty(code)){
            throw new UserException("code.not.null",null);
        }
        if(ServletUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY)==null){
            throw new UserException("user.code.error",null);
        }
        if(!code.equals(ServletUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            throw new UserException("user.code.error",null);
        }

        User user = userMapper.selectByUsername(username);
        if(user==null){
            throw new UserException("user.not.exist",null);
        }
        if(!Md5Utils.encode(password).equals(user.getPassword())){
            throw new UserException("user.password.error",null);
        }
        return user;
    }
}
