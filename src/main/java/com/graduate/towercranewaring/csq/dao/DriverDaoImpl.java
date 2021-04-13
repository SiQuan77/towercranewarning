package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.driver;
import com.graduate.towercranewaring.csq.pojo.equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: DriverDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/9
 * @Version 1.0
 **/
@Repository
public class DriverDaoImpl implements DriverDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<driver> getAllDriver() {
        String sql="select * from driver";
//        System.out.println("进来啦！");
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(driver.class));
        }catch (Exception e){
            System.out.println("返回了空指针呜呜呜！");
            return null;
        }
    }

    @Override
    public boolean updateDriver(driver driver) {
        String sql1="update driver set driver_name=?,age=?,gender=?,tel_number=?" +
                ",photo=? where driver_id = ?";
        String sql2="update driver set driver_name=?,age=?,gender=?,tel_number=? where driver_id = ?";

        //如果修改员工传的的photo是null，则对原值不进行修改

        int row;
        if(driver.getPhoto()==null){
            row=jdbcTemplate.update(sql2,driver.getDriver_name(),driver.getAge(),driver.getGender(),
                    driver.getTel_number(),driver.getDriver_id());
        }else {
            row=jdbcTemplate.update(sql1,driver.getDriver_name(),driver.getAge(),driver.getGender(),
                    driver.getTel_number(),driver.getPhoto(),driver.getDriver_id());
        }


        return row != 0;
    }

    @Override
    public driver getDriverById(String id) {
        String sql="select * from driver where driver_id = ?";
        try{
            return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<driver>(driver.class),id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean addDriver(driver driver) {
        String sql="insert into driver VALUES (?,?,?,?,?,?,?)";
        int row=jdbcTemplate.update(sql,driver.getDriver_id(),driver.getDriver_name(),driver.getAge(),driver.getGender()
                ,driver.getId_card_number(),driver.getTel_number(),driver.getPhoto());

        return row!=1;
    }

    @Override
    public boolean deleteDriverById(String driver_id) {
        String sql="delete from driver where driver_id = ?";
        int row=jdbcTemplate.update(sql,driver_id);
        return row!=1;
    }
}
