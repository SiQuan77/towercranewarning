package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: UserDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public User getUserById(String user_id) {
        String sql="select * from user where id = ?";
        //必须用try，catch包裹起来，因为jdbcTemplate.queryForObject查不到数据或者查到多列数据就会报异常！
        try{
            User user=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user_id);
            return user;
        }catch (Exception e){
            return null;
        }

    }
}
