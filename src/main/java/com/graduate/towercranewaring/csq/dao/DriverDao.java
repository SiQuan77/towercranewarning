package com.graduate.towercranewaring.csq.dao;

import com.graduate.towercranewaring.csq.pojo.driver;

import java.util.List;

public interface DriverDao {
    List<driver> getAllDriver();
    boolean updateDriver(driver driver);
    driver getDriverById(String id);
}
