package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.driver;
import com.graduate.towercranewaring.csq.pojo.equipment;
import com.graduate.towercranewaring.csq.pojo.sjj_working;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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


    //    返回sjj_working的预警种类，如果正常，则返回null
    @Override
    public String judgeIfAlert(sjj_working sjj_working) {
        String alert_information="";
        if(sjj_working.getHeight()>sjj_working.getTop_limit()){
            alert_information+="超过上限位/";
        }else if(sjj_working.getHeight()<sjj_working.getLow_limit()){
            alert_information+="超过下限位/";
        }else if(sjj_working.getLock_condition()==0){
            alert_information+="未上锁/";
        }else if(sjj_working.getTilt_range()>15){
            alert_information+="过度倾斜/";
        }


        return alert_information;
    }

    @Override
    public sjj_working getSjj_workingById(String id) {
        String sql="select * from sjj_working where id = ?";
        try{
            return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<sjj_working>(sjj_working.class),id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updateSjj_working(sjj_working sjj_working) {
        System.out.println("sjjworking是"+sjj_working);
        String sql="update sjj_working set sn=?,top_limit=?,low_limit=?,tilt_range=?,lock_condition=?," +
                "height=?,driver_id=?,working_time=?,if_include=? where id =?";
        int row=jdbcTemplate.update(sql,sjj_working.getSn(),sjj_working.getTop_limit(),sjj_working.getLow_limit(),sjj_working.getTilt_range(),
                sjj_working.getLock_condition(),sjj_working.getHeight(),sjj_working.getDriver_id(),sjj_working.getWorking_time(),sjj_working.getIf_include(),sjj_working.getId());
        return row!=0;
    }


    @Override
    public HashMap<sjj_working, String> getAlertSjj_working() {
        HashMap<sjj_working,String> sjj_workingStringHashMap=new HashMap<>();
        List<sjj_working> sjj_workings=getAllSjj_Working();
        for(int i=0;i<sjj_workings.size();i++){
            if(judgeIfAlert(sjj_workings.get(i))!=""){
                sjj_workingStringHashMap.put(sjj_workings.get(i),judgeIfAlert(sjj_workings.get(i)));
            }
        }


        return sjj_workingStringHashMap;
    }



}
