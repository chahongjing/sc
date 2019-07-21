package com.zjy.service.services.impl;

import com.zjy.service.dao.UserDao;
import com.zjy.service.po.User;
import com.zjy.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User get(Long id) {
        return userDao.get(id);
    }

    @Override
    @Transactional
    public void update() throws SQLException {
        User user = new User();
        user.setUserid(2L);
        user.setName("abdggg");
        userDao.update(user);
        if (user.getUserid().equals(2L)) {
            throw new SQLException("没找到");
        }
    }
}
