package com.example.thebaohiem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Table(name = "tbl_company")
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_internal_id")
    private int companyInternalId;

    @Getter
    @Setter
    @NotNull
    @Column(name = "company_name")
    private String companyName;

    @Getter
    @Setter
    @NotNull
    @Column(name = "address")
    private String address;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    @Getter
    @Setter
    private Set<User> userSet;

    public Company(String companyName, String companyAddress) {
        this.companyName = companyName;
        this.address = companyAddress;
    }

    public Company(String companyName, String companyAddress, String email, String telephone) {
        this.companyName = companyName;
        this.address = companyAddress;
        this.email = email;
        this.telephone = telephone;
    }

    public Company(int companyInternalId, String companyName) {
        this.companyName = companyName;
        this.companyInternalId = companyInternalId;
    }
}
