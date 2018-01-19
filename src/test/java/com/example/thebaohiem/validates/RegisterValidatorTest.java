package com.example.thebaohiem.validates;

import com.example.thebaohiem.logic.Impl.CompanyLogicImpl;
import com.example.thebaohiem.logic.Impl.InsuranceLogicImpl;
import com.example.thebaohiem.model.Form.UserInfoForm;
import com.example.thebaohiem.utils.ErrorMessageProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegisterValidatorTest {
    @Mock
    InsuranceLogicImpl insuranceLogicImpl;
    @Mock
    CompanyLogicImpl companyLogicImpl;

    @InjectMocks
    RegisterValidator sut;

    /**
     * test function registerValidate with insuranceNumber is empty.
     * IN
     *  fullName = Le Thi Ha
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceNumber").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateInsuranceNumberEmpty() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceNumber");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceNumber not enough 10 number.
     * IN
     * insuranceNumber = "123456789"
     * fullName = Le Thi Ha
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2018'
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * actual = actual = ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateLengthInsuranceNumber() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("123456789");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);

    }

    /**
     * test function registerValidate with insuranceNumber enough 10 digit include both char and number.
     * IN
     * insuranceNumber = "a123456789"
     * fullName = Le Thi Ha
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2018'
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * actual = ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateInsuranceNumberFormat() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("a123456789");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        List<String> actStringList = new ArrayList<>();
        actStringList.add(ErrorMessageProperties.getMessage("Pattern.userInfoForm.insuranceNumber"));
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList, actStringList);
    }

    /**
     * test function registerValidate with insuranceNumber enough 10 number but existed in database.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Ha
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2018'
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * actual = ErrorMessageProperties.getMessage("userInfoForm.validate.ExistedNumber").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateExistInsuranceNumber() {
        when(insuranceLogicImpl.checkExistInsuranceNumber(anyString(), anyInt())).thenReturn(true);
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), ErrorMessageProperties.getMessage("userInfoForm.validate.ExistedNumber"));
    }

    /**
     * test function registerValidate with insuranceNumber enough 10 number and not existed in database.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = Le Thi Ha
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     *  OUT
     * actual = ErrorMessageProperties.getMessage("userInfoForm.validate.ExistedNumber").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateNotExistInsuranceNumber() {
        when(insuranceLogicImpl.checkExistInsuranceNumber(anyString(), anyInt())).thenReturn(false);
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    /**
     * test function registerValidate with userFullName is empty.
     * IN
     *  insuranceNumber = "1234567891"
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.fullName").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateEmptyFullName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        //       userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.fullName");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with userFullName is Exceed length.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = a aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.fullName").
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateExceedLengthFullName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("a aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.usrInfoForm.fullName");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with userFullName has length =50.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  not message error
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateMaxLengthFullName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    /**
     * test function registerValidate with userFullName has length < 50.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = Le Thi Ha
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  not message error
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateLengthFullName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    /**
     * test function registerValidate with insuranceStartDate is right.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = Le Thi Hang
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20/12/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  not message error.
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateStartDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    /**
     * test function registerValidate with insuranceStartDate is not format dd/mm/yyyy.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = Le Thi Hang
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="20-12-2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     *  ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateFormatStartDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20-12-2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceStartDate is word.
     * IN
     *  insuranceNumber = "1234567891"
     *  fullName = Le Thi Hang
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="abc'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateFormatStartDateWord() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("abd");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceStartDate does not exist.
     * IN
     * insuranceNumber = "1234567891"
     *  fullName = Le Thi Hang
     *  Sex = 01
     *  placeOfRegister = " Ha Noi"
     *  insuranceStartDate="30/2/2018'
     *  insuranceEndDate = "20/1/2019"
     *  companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateFormatStartDate2() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("30/2/2016");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceStartDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceStartDate is empty.
     * IN
     *  insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceStartDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateEmptyStartDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceStartDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceEndDate is empty.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="30/2/2018'
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceEndDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateEmptyEndDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.insuranceEndDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceEndDate is not format dd/mm/yyyy.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2018'
     * insuranceEndDate = "20/1/2019"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceEndDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateFormatEndDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("20-1-2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceEndDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
        ;
    }

    /**
     * test function registerValidate with insuranceEndDate is word.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2018'
     * insuranceEndDate = "ABC"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceEndDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateFormatEndDateWord() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("ABC");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.insuranceEndDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceEndDate and insuranceStartDate are right but insuranceEndDate < insuranceStartDate.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2019"
     * insuranceEndDate = "19/01/2019"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("userInfoForm.validate.InvalidDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateCompareEndDateWithStartDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("19/01/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("userInfoForm.validate.InvalidDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate with insuranceEndDate ,insuranceStartDate are right and insuranceEndDate = insuranceStartDate.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/12/2019'
     * insuranceEndDate = "20/12/2019"
     * companyId =1
     * OUT
     * ErrorMessageProperties.getMessage("userInfoForm.validate.InvalidDate")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateEndDateEqualStartDate() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("20/01/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("userInfoForm.validate.InvalidDate");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    /**
     * test function registerValidate create company with companyName is empty.
     * IN
     * insuranceNumber = "1234567891"
     * fullName = Le Thi Hang
     * Sex = 01
     * placeOfRegister = " Ha Noi"
     * insuranceStartDate="20/1/2019'
     * insuranceEndDate = "21/1/2019"
     * companyAddress = 'Ha Noi
     * OUT
     * ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.companyName")
     *
     * @since 2018-01-18
     */
    @Test
    public void testRegisterValidateEmptyCompanyName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.companyName");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    @Test
    public void testRegisterValidateMaxLengthCompanyName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setCompanyAddress("Ha Noi");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateExceedLengthCompanyName() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setCompanyAddress("Ha Noi");
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.companyName");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateEmptyCompanyAdress() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.companyAddress");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    @Test
    public void testRegisterValidateMaxLengthCompanyAddress() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        userInfoForm.setCompanyAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa11111111111111111111111111111111111111111111111111");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateExceedLengthCompanyAddress() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa111111111111111111111111111111111111111111111111111");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.companyAddress");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateEmptyEmail() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        userInfoForm.setCompanyAddress("Ha Noi");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateMaxLengthCompanyEmail() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@example.com");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateExceedLengthEmail() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setEmail("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@example.com");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.email");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateExistEmail() {
        //setup
        when(companyLogicImpl.checkExistEmail(anyString(), anyInt())).thenReturn(true);
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setEmail("aaaaaaa@example.com");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Existed.userInfoForm.email");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateFormatEmail() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setEmail("aaa@gmail.c");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Email.userInfoForm.email");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateEmptyTelephone() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        userInfoForm.setCompanyAddress("TP HCM");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateMaxLengthTel() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyName("LUVINA");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setTelephone("123456789876543");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateExceedLengthTel() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setTelephone("1234567898765431");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.tel");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateExistTel() {
        //setup
        when(companyLogicImpl.checkExistTel(anyString(), anyInt())).thenReturn(true);
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setTelephone("12345678");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Existed.userInfoForm.telephone");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateFormatTel() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/01/2019");
        userInfoForm.setCompanyAddress("Ha Noi");
        userInfoForm.setTelephone("a12345678");
        userInfoForm.setCompanyName("Luvina");
        String actual = ErrorMessageProperties.getMessage("Pattern.userInfoForm.telephone");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateEmptyBirthday() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("22/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateFormatBirthday() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("21/1/2019");
        userInfoForm.setBirthday("abs");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.birthday");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);

    }

    @Test
    public void testRegisterValidateFormatBirthday1() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("23/1/2019");
        userInfoForm.setBirthday("20-02-1994");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.birthday");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateBirthday() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Hang ");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/1/2019");
        userInfoForm.setInsuranceEndDate("23/1/2019");
        userInfoForm.setBirthday("20/02/1994");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }

    @Test
    public void testRegisterValidateEmptyRegisterOfPlace() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setFullName("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("NotEmpty.userInfoForm.placeOfRegister");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.get(0), actual);
        assertEquals(acStringList.size(), 1);
    }

    @Test
    public void testRegisterValidateExceedLengthRegisterOfPlace() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setPlaceOfRegister("a aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setFullName("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        String actual = ErrorMessageProperties.getMessage("Invalid.userInfoForm.registerOfPlace");
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 1);
        assertEquals(acStringList.get(0), actual);
    }

    @Test
    public void testRegisterValidateMaxLengthRegisterOfPlace() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setPlaceOfRegister("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setFullName("Ha Noi");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        List<String> actStringList = new ArrayList<>();
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList, actStringList);
    }

    @Test
    public void testRegisterValidateRegisterOfPlace() {
        //setup
        UserInfoForm userInfoForm = new UserInfoForm();
        userInfoForm.setFullName("Le Thi Ha");
        userInfoForm.setSex("01");
        userInfoForm.setInsuranceNumber("1234567891");
        userInfoForm.setPlaceOfRegister("TH HCM");
        userInfoForm.setInsuranceStartDate("20/12/2018");
        userInfoForm.setInsuranceEndDate("20/1/2019");
        userInfoForm.setCompanyInternalID(1);
        // exercise
        List<String> acStringList = sut.validate(userInfoForm);
        //verify
        assertEquals(acStringList.size(), 0);
    }


}

