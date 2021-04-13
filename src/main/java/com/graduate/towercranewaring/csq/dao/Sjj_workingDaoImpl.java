package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.sjj_working;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: Sjj_workingDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/13
 * @Version 1.0
 **/
@Repository
public class Sjj_workingDaoImpl implements Sjj_workingDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<sjj_working> getAllSjj_Working() {
        String sql="select * from sjj_working";
        try{
            List<sjj_working> sjj_workings=jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(sjj_working.class));
            return  sjj_workings;
        }catch (Exception e){
            return null;
        }
    }
}
