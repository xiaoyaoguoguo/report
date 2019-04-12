package com.panda.project.system.user.service;

import com.panda.project.system.user.domain.User;


public interface UserService {

    User login(String username, String password, String code);
}
