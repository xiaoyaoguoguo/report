package com.panda.project.system.user.controller;


import com.panda.common.utils.auth.AuthUtils;
import com.panda.framework.aspectj.lang.annotation.Auth;
import com.panda.framework.domain.JsonReturn;
import com.panda.framework.domain.UserDto;
import com.panda.framework.web.controller.BaseController;
import com.panda.project.system.user.domain.User;
import com.panda.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author jamie
 * @ClassName: UserController
 * @Description: 后台用户
 * @data 2019-01-04 12:01
 **/
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * admin用户登录
     * @param username
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonReturn login(String username,String password,String code){
        User u = userService.login(username,password,code);
        //登录
        UserDto userDto = new UserDto();
        userDto.setId(u.getId());
        userDto.setUsername(u.getUsername());
        AuthUtils.setAdminUser(userDto);
        return new JsonReturn(userDto);
    }

    /**
     * 管理员基本信息
     * @return
     */
    @GetMapping("/info")
    @ResponseBody
    @Auth(isAuth = true,userType = AuthUtils.ADMIN_USER)
    public JsonReturn userInfo(){
        return new JsonReturn(AuthUtils.getAdminUser());
    }

    /**
     * 管理员注销
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    @Auth(isAuth = true,userType = AuthUtils.ADMIN_USER)
    public JsonReturn logout(){
        AuthUtils.removeAdminUser();
        return new JsonReturn();
    }




}
