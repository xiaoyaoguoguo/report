package com.panda.project.system.user.controller;

import com.panda.common.utils.auth.AuthUtils;
import com.panda.framework.aspectj.lang.annotation.Auth;
import com.panda.framework.domain.JsonReturn;
import com.panda.framework.web.controller.BaseController;
import com.panda.framework.web.page.TableDataInfo;
import com.panda.project.system.user.domain.Message;
import com.panda.project.system.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jamie
 * @ClassName: MessageController
 * @Description: 案件信息
 * @data 2019-04-11 15:26
 **/
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/list")
    @Auth(isAuth = true,userType = AuthUtils.ADMIN_USER)
    @ResponseBody
    public JsonReturn list(){
        startPage();
        List<Message> messageList = messageService.list();
        return new JsonReturn(getDataTable(messageList));
    }


}
