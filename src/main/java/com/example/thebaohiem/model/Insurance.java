package com.example.thebaohiem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@ToString
@Table(name="tbl_insurance")
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "insurance_internal_id")
    private  int insuranceInternalId;

    @Getter
    @Setter
    @NotNull
    @Column(name = "insurance_number")
    private  String insuranceNumber;

    @Getter
    @Setter
    @NotNull
    @Column(name ="insurance_start_date")
    private String insuranceStartDate;

    @Getter
    @Setter
    @NotNull
    @Column(name ="insurance_end_date")
    private  String insuranceEndDate;

    @Getter
    @Setter
    @NotNull
    @Column(name ="place_of_register")
    private  String placeOfRegister;

    @Getter
    @Setter
    @OneToOne(mappedBy = "insurance")
    private User user;
    public Insurance(String insuranceNumber, String insuranceStartDate, String insuranceEndDate, String placeOfRegister)
    {
        this.insuranceEndDate = insuranceEndDate;
        this.insuranceNumber = insuranceNumber;
        this.insuranceStartDate = insuranceStartDate;
        this.placeOfRegister = placeOfRegister;
    }
}
