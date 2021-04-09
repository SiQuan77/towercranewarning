package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.DriverDao;
import com.graduate.towercranewaring.csq.dao.DriverDaoImpl;
import com.graduate.towercranewaring.csq.pojo.driver;
import com.graduate.towercranewaring.csq.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @ClassName: DriverServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/4/9
 * @Version 1.0
 **/
@Service
public class DriverServiceImpl implements DriverService{
    @Autowired
    private DriverDaoImpl driverDao;


    @Override
    public List<driver> getAllDriver() {

        /*
         * @Author:csq
         * @Description 因为springboot提交图片后，没法及时显示，需要重启idea才行。所以对于本地文件，直接覆盖即可，在进行展示的时候，现将图片转换为
         * base64格式，html直接渲染base64格式的图片即可！（转图片至base64的操作在service进行，本地数据库直接存储图片的绝对路径即可！）
         * @Date 22:55 2021/4/9
         * @Param []
         * @return java.util.List<com.graduate.towercranewaring.csq.pojo.driver>
         **/

//        将图片各个文件图片转换为base64
        List<driver> allDriver = driverDao.getAllDriver();
        for(int i=0;i<allDriver.size();i++){
            allDriver.get(i).setPhoto("data:image/jpeg;base64,"+FileUtils.ImageToBase64ByLocal(allDriver.get(i).getPhoto()));
        }

        return allDriver;
    }

    @Override
    public boolean updateDriver(driver driver) {
        return driverDao.updateDriver(driver);
    }

    @Override
    public driver getDriverById(String id) {
        return driverDao.getDriverById(id);
    }
}
