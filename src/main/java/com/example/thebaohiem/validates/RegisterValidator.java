package com.example.thebaohiem.validates;

import com.example.thebaohiem.logic.Impl.CompanyLogicImpl;
import com.example.thebaohiem.logic.Impl.InsuranceLogicImpl;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.utils.Common;
import com.example.thebaohiem.utils.ErrorMessageProperties;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RegisterValidator {

    @Autowired
    private CompanyLogicImpl companyLogic;
    @Autowired
    private InsuranceLogicImpl insuranceLogicImpl;

    /**
     * validate information to insert data into database.
     * @param userInfoForm : userInfo
     * @return List<String>
     */
    public List<String> validate(UserInfoForm userInfoForm) {

        List<String> errList = new ArrayList<>();
        //validate insuranceNumber
        //check empty
        String insuranceNumber = userInfoForm.getInsuranceNumber();
        if (Common.isEmpty(insuranceNumber)) {
            errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceNumber"));
        }
        //check check length
        else if (Common.checkLength(insuranceNumber, 10, 10) == false) {
            errList.add(ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber"));
        }
        //check format
        else if (Common.checkFormat(insuranceNumber, "^$|[0-9]{10}") == false) {
            errList.add(ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber"));
        }
        //check exist insuranceNumber
        else if (insuranceLogicImpl.checkExistInsuranceNumber(insuranceNumber, userInfoForm.getInsuranceInternalId()) == true) {
            errList.add(ErrorMessageProperties.getMessage("userInfoForm.validate.ExistedNumber"));
        }
        // validate fullName
        //check empty
        String fullName = userInfoForm.getFullName();
        if (Common.isEmpty(fullName)) {
            errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.fullName"));
        } else if (Common.checkLength(fullName, 1, 50) == false) {
            errList.add(ErrorMessageProperties.getMessage("Invalid.usrInfoForm.fullName"));
        }
        // validate birthday
        // check empty birthday
        String birthday = userInfoForm.getBirthday();
        if (Common.isEmpty(birthday) == false) {
            // check date
            if (Common.checkDate(birthday) == false) {
                errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.birthday"));
            }
        }
        //validate registerOfPlace
        // check empty
        String registerOfPlace = userInfoForm.getPlaceOfRegister();
        if (Common.isEmpty(registerOfPlace) == true) {
            errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.placeOfRegister"));
        } else if (Common.checkLength(registerOfPlace, 1, 50) == false) {
            errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.registerOfPlace"));
        }
        // validate startdate
        // check empty
        String startDate = userInfoForm.getInsuranceStartDate();
        if (Common.isEmpty(startDate) == true) {
            errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceStartDate"));
        }
        //check format
        else if (Common.checkDate(startDate) == false) {
            errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate"));
        }
        // validate end date
        //check empty
        String endDate = userInfoForm.getInsuranceEndDate();
        if (Common.isEmpty(endDate) == true) {
            errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceEndDate"));
        } else if (Common.checkDate(endDate) == false) {
            errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceEndDate"));
        } else if (Common.checkDate(startDate) == true && Common.compareEndDateWithStartDate(endDate, startDate) == false) {
            errList.add(ErrorMessageProperties.getMessage("userInfoForm.validate.InvalidDate"));
        }
        //checl length
        //validate information company
        int companyId = userInfoForm.getCompanyInternalID();
        if (companyId == 0) {
            String companyName = userInfoForm.getCompanyName();
            // validate companyName
            // check empty
            if (Common.isEmpty(companyName) == true) {
                errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.companyName"));
            }//check length
            else if (Common.checkLength(companyName, 1, 50) == false) {
                errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.companyName"));
            }
            //validate address
            //check empty
            String address = userInfoForm.getCompanyAddress();

            if (Common.isEmpty(address) == true) {
                errList.add(ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.companyAddress"));
            } else if (Common.checkLength(address, 1, 100) == false) {
                errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.companyAddress"));
            }
            String email = userInfoForm.getEmail();
            String pattern = "^$|[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
            // validate email
            //check emmpty
            if (Common.isEmpty(email) == false) {
                //check length
                if (Common.checkLength(email, 1, 50) == false) {
                    errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.email"));
                }
                // check format
                else if (Common.checkFormat(email, pattern) == false) {
                    errList.add(ErrorMessageProperties.getMessage("Email.userInfoForm.email"));
                }
                //check exist
                else if (companyLogic.checkExistEmail(email, companyId) == true) {
                    errList.add(ErrorMessageProperties.getMessage("Existed.userInfoForm.email"));
                }
            }
            String tel = userInfoForm.getTelephone();
            if (Common.isEmpty(tel) == false) {
                String patternNumber = "^\\d+$";
                //check length
                if (Common.checkLength(tel, 8, 15) == false) {
                    errList.add(ErrorMessageProperties.getMessage("Invalid.userInfoForm.tel"));
                } else if (Common.checkFormat(tel, patternNumber) == false) {
                    errList.add(ErrorMessageProperties.getMessage("Pattern.userInfoForm.telephone"));
                } else if (companyLogic.checkExistTel(tel, companyId) == true) {
                    errList.add(ErrorMessageProperties.getMessage("Existed.userInfoForm.telephone"));
                }
            }
        }
        return errList;
    }
}
