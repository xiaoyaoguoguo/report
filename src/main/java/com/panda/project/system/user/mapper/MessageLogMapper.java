package com.panda.project.system.user.mapper;

import com.panda.project.system.user.domain.MessageLog;

public interface MessageLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageLog record);

    int insertSelective(MessageLog record);

    MessageLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageLog record);

    int updateByPrimaryKey(MessageLog record);
}