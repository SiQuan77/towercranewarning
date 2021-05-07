package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.project_information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: project_informationDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/5/7
 * @Version 1.0
 **/
@Repository
public class project_informationDaoImpl implements project_informationDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public project_information GetProject() {
        String sql="select * from project_information";
        try{
            List<project_information> projectList=jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(project_information.class));
            return  projectList.get(0);
        }catch (Exception e){
            return null;
        }
    }
}
