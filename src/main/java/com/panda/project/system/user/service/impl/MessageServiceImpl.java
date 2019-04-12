package com.panda.project.system.user.service.impl;

import com.panda.project.system.user.domain.Message;
import com.panda.project.system.user.mapper.MessageMapper;
import com.panda.project.system.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jamie
 * @ClassName: MessageServiceImpl
 * @Description: 案件信息
 * @data 2019-04-11 15:32
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;


    @Override
    public List<Message> list() {
        return messageMapper.selectAll();
    }
}
