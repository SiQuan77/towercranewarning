package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.driver;
import com.graduate.towercranewaring.csq.pojo.sjj_working;
import com.graduate.towercranewaring.csq.pojo.taji_working;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: Taji_workingDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/14
 * @Version 1.0
 **/
@Repository
public class Taji_workingDaoImpl implements Taji_workingDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<taji_working> getAllTajiworking() {
        String sql="select * from taji_working";
        try{
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(taji_working.class));
        }catch (Exception e){
            return null;
        }
    }


    @Override
    public String judgeIfAlert(taji_working taji_working) {
        String alert_information="";
        if(taji_working.getLoading_payload()>taji_working.getMaximum_payload()){
            alert_information+="超重/";
        }
        if(taji_working.getMoment()>10){
            alert_information+="力矩报警/";
        }

        return alert_information;
    }

    @Override
    public taji_working getTaji_workingById(String id) {
        String sql="select * from taji_working where id = ?";
        try{
            return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<taji_working>(taji_working.class),id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean updateTaji_working(taji_working taji_working) {
        String sql="update taji_working set sn=?,starting_time=?,ending_time=?,maximum_payload=?," +
                "height=?,loading_payload=?,range_size=?,moment=?,driver_id=?,if_include=? where id=?";
        int row=jdbcTemplate.update(sql,taji_working.getSn(),taji_working.getStarting_time(),taji_working.getEnding_time(),taji_working.getMaximum_payload(),taji_working.getHeight()
                ,taji_working.getLoading_payload(),taji_working.getRange_size(),taji_working.getMoment(),taji_working.getDriver_id(),taji_working.getIf_include(),taji_working.getId());
        return row!=0;
    }

    @Override
    public boolean insertTaji_working(taji_working taji_working) {
        String sql="insert into taji_working VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        int row=jdbcTemplate.update(sql,taji_working.getId(),taji_working.getSn(),taji_working.getStarting_time(),taji_working.getEnding_time(),
                taji_working.getMaximum_payload(),taji_working.getHeight(),taji_working.getLoading_payload(),taji_working.getRange_size(),
                taji_working.getMoment(),taji_working.getDriver_id(),taji_working.getIf_include());
        return row!=0;
    }

    @Override
    public boolean deleteTaji_working(String tajiworking_id) {
        String sql="delete from taji_working where id = ?";
        int row=jdbcTemplate.update(sql,tajiworking_id);
        return row!=1;
    }

    @Override
    public List<taji_working> getTaji_workingBySn(String sn) {
        String sql="select * from taji_working where sn = ?";
        try{
           return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(taji_working.class),sn);
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @Override
    public HashMap<taji_working, String> getAlertTaji_working() {
        List<taji_working> allTajiworking = getAllTajiworking();
        HashMap<taji_working,String> taji_workingStringHashMap=new HashMap<>();
        for(int i=0;i<allTajiworking.size();i++){
            if(judgeIfAlert(allTajiworking.get(i))!=""){
                taji_workingStringHashMap.put(allTajiworking.get(i),judgeIfAlert(allTajiworking.get(i)));
            }
        }

        return taji_workingStringHashMap;
    }

}
