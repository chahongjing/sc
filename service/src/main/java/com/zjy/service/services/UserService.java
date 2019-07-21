package com.zjy.service.services;

import com.zjy.service.po.User;

import java.sql.SQLException;

public interface UserService {
    User get(Long id);

    void update() throws SQLException;
}
