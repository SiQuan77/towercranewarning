package com.graduate.towercranewaring.csq.dao;


import com.graduate.towercranewaring.csq.pojo.equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @ClassName: EquipmentDaoImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/7
 * @Version 1.0
 **/
@Repository
public class EquipmentDaoImpl implements EquipmentDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<equipment> getAllEquip() {
        String sql="select * from equipment";

        try{
            List<equipment> equipmentList=jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(equipment.class));
            return  equipmentList;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public equipment getEquipmentBySn(String equip_sn) {
        String sql="select * from equipment where sn =?";
        try{
            return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<equipment>(equipment.class),equip_sn);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean deleteEquipmentBySn(String equip_sn) {
        String sql="delete from equipment where sn = ?";
        try {
            int row=jdbcTemplate.update(sql,equip_sn);
            if(row!=0){//
                return true;
            }else {//若返回的是0，说明删除失败，没有任何行收到影响
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean updateEquipmentByEquip(equipment equipment) {
        String sql="update equipment set name=?,xinxihao=?,type=?,property_contractor=?,property_contractor_id=?" +
                ",installation_contractor=?,installing_time=?,dismantle_contractor=?,dismantle_time=?,position_left = ?,position_top=? where sn = ?";


        int row=jdbcTemplate.update(sql,equipment.getName(),equipment.getXinxihao(),equipment.getType(),equipment.getProperty_contractor(),equipment.getProperty_contractor_id(),
                equipment.getInstallation_contractor(),equipment.getInstalling_time(),equipment.getDismantle_contractor(),equipment.getDismantle_time(),equipment.getPosition_left(),equipment.getPosition_top(),
                equipment.getSn());

        return row != 0;

    }

    @Override
    public boolean addEquipment(equipment equipment) {
        String sql="insert into equipment VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        int row=jdbcTemplate.update(sql,equipment.getSn(),equipment.getName(),equipment.getXinxihao(),equipment.getType(),equipment.getProperty_contractor(),equipment.getProperty_contractor_id(),
                equipment.getInstallation_contractor(),equipment.getInstalling_time(),equipment.getDismantle_contractor(),equipment.getDismantle_time(),equipment.getPosition_left(),equipment.getPosition_top());
        return row!=0;
    }

    @Override
    public List<equipment> searchEquipment(String equip_name, String equip_sn, String equip_type, String equip_cqdw, String equip_azdw) {
        String sql="select * from equipment where 1=1";
        if(equip_name!=null){
            sql+=" and name like '%"+equip_name+"%'";
        }
        if(equip_sn!=null){
            sql+=" and sn like '%"+equip_sn+"%'";
        }

        if(equip_type!=null){
            sql+=" and type like '%"+equip_type+"%'";
        }

        if(equip_cqdw!=null){
            sql+=" and property_contractor like '%"+equip_cqdw+"%'";
        }

        if(equip_azdw!=null){
            sql+=" and installation_contractor like '%"+equip_azdw+"%'";
        }

        try{
            return jdbcTemplate.query(sql,
                    new BeanPropertyRowMapper<>(equipment.class));
        }catch (Exception e){
            return null;
        }


    }

    @Override
    public boolean dingweiEquip(String equip_sn, int x, int y) {
        String sql="update equipment set position_left = ?,position_top = ? where sn = ?";
        int row=jdbcTemplate.update(sql,x,y,equip_sn);
        return row != 0;
    }
}
