package com.graduate.towercranewaring.csq.service;

import com.graduate.towercranewaring.csq.pojo.alert_information;
import com.graduate.towercranewaring.csq.pojo.alert_information_packing;

import java.util.ArrayList;
import java.util.List;

public interface Alert_informationService {
    List<alert_information_packing> getAllAlertList();
    alert_information_packing returnPackingAlert(alert_information alert_information);
    ArrayList<Integer> returnAlert();
}
