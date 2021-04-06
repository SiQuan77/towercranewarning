package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.UserDaoImpl;
import com.graduate.towercranewaring.csq.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDaoImpl userDao;

    @Override
    public User getUserById(String user_id) {
        return userDao.getUserById(user_id);
    }
}
