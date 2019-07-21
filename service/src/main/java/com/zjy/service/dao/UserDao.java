package com.zjy.service.dao;

import com.zjy.service.po.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User get(Long id);

    void update(User user);
}
