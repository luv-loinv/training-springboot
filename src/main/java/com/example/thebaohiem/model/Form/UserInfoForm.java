package com.example.thebaohiem.model.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserInfoForm {

    @NotNull
    @Getter
    @Setter
    private int userInternalID;

    @NotNull
    @Pattern(regexp="^$|[0-9]{10}")
    @Getter
    @Setter
    private String insuranceNumber;

    @Getter
    @Setter
    @NotNull
    private String fullName;

    @NotNull
    @Getter
    @Setter
    private String sex;

    @Getter
    @Setter
    private String birthday;

    @Getter
    @Setter
    private String companyName;

    @Getter
    @Setter
    private String companyAddress;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String telephone;

    @Getter
    @Setter
    @NotNull
    private String placeOfRegister;

    @Getter
    @Setter
    @NotNull
    private String insuranceStartDate;

    @Getter
    @Setter
    @NotNull
    private String insuranceEndDate;

    @Getter
    @Setter
    private int companyInternalID;

    @Setter
    @Getter
    private int insuranceInternalId;
}
