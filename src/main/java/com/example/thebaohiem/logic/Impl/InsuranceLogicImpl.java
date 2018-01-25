package com.example.thebaohiem.logic.Impl;

import com.example.thebaohiem.Dao.InsuranceDao;
import com.example.thebaohiem.model.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceLogicImpl {
    @Autowired
    private InsuranceDao insuranceDao;

    public boolean checkExistInsuranceNumber(String insuranceNumber, int insuranceId) {
        boolean check = false;
        Insurance insurance = insuranceDao.findByInsuranceNumber(insuranceNumber);
        if (insurance != null) {
            if (insuranceId != insurance.getInsuranceInternalId()) {
                check = true;
            }
        }
        return check;
    }
}
