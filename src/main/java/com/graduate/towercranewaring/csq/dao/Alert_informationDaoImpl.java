package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.alert_information;
import com.graduate.towercranewaring.csq.pojo.driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: Alert_informationDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/15
 * @Version 1.0
 **/
@Repository
public class Alert_informationDaoImpl implements Alert_informationDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<alert_information> getAllAlertInformation() {
        String sql="select * from alert_information";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(alert_information.class));
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public Boolean InsertAlertInformation(alert_information alert_information) {
        String sql="insert into alert_information VALUES (?,?,?)";
        int row=jdbcTemplate.update(sql,alert_information.getId(),alert_information.getId_of_working()
                ,alert_information.getType());
        return row!=0;
    }

    @Override
    public Boolean deleteAlertInformationByWorkId(String work_id) {
        String sql="delete from alert_information where id_of_working = ?";
        int row=jdbcTemplate.update(sql,work_id);
        return row!=1;
    }
}
