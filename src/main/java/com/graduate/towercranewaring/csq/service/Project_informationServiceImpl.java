package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.dao.project_informationDao;
import com.graduate.towercranewaring.csq.pojo.project_information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: Project_informationServiceImpl
 * @Description:
 * @Author:csq
 * @Date 2021/5/7
 * @Version 1.0
 **/
@Service
public class Project_informationServiceImpl implements Project_informationService{

    @Autowired
    private project_informationDao project_informationDao;

    @Override
    public project_information GetProject() {
        return project_informationDao.GetProject();
    }
}
