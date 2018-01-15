package com.example.thebaohiem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@ToString
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_internal_id")
    private int userInternalId;

    @Getter
    @Setter
    @NotNull
    @Column(name = "username")
    private String userName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "password")
    private String password;

    @Getter
    @Setter
    @NotNull
    @Column(name = "user_full_name")
    private String userFullName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "user_sex_division")
    private String userSexDivision;

    @Getter
    @Setter
    @Column(name = "birthdate")
    private String birthdate;

//    @Getter
//    @Setter
//    @Column(name = "insurance_internal_id")
//    private int insuranceInternalId;
//
//    @Getter
//    @Setter
//    @Column(name = "company_internal_id")
//    private int companyInternalId;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_internal_id")
    private Company company;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_internal_id")
    @Getter
    @Setter
    private Insurance insurance;

}
