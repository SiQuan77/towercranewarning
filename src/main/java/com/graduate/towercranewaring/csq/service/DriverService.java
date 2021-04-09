package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.pojo.driver;

import java.util.List;

public interface DriverService {
    List<driver> getAllDriver();
    boolean updateDriver(driver driver);
    driver getDriverById(String id);
}
