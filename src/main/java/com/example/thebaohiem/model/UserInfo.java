package com.example.thebaohiem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    @Getter
    @Setter
    private String userFullName;

    @Getter
    @Setter
    private String userSexDivision;

    @Getter
    @Setter
    private String birthdate;

    @Getter
    @Setter
    private String insuranceNumber;

    @Getter
    @Setter
    private String insuranceStartDate;

    @Getter
    @Setter
    private String insuranceEndDate;

    @Getter
    @Setter
    private String placeOfRegister;

    @Getter
    @Setter
    private String companyName;

    @Getter
    @Setter
    private int userInternalId;

    @Getter
    @Setter
    private int companyInternalId;

    @Setter
    @Getter
    private int insuranceId;

    public UserInfo(int userInternalId, String userFullName, String userSexDivision, String birthdate, String insuranceNumber, String insuranceStartDate, String insuranceEndDate, String placeOfRegister) {
        this.userInternalId = userInternalId;
        this.userFullName = userFullName;
        this.userSexDivision = userSexDivision;
        this.birthdate = birthdate;
        this.insuranceNumber = insuranceNumber;
        this.insuranceStartDate = insuranceStartDate;
        this.insuranceEndDate = insuranceEndDate;
        this.placeOfRegister = placeOfRegister;
    }

    public UserInfo(int userInternalId, String fullName, String insuranceNumber, String placeOfRegister) {
        this.userFullName = fullName;
        this.insuranceNumber = insuranceNumber;
        this.userInternalId = userInternalId;
        this.placeOfRegister = placeOfRegister;
    }

}
