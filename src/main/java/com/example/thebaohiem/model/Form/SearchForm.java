package com.example.thebaohiem.model.Form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchForm {
    private String companyId  ="1";
    private String fullName = "";
    private String insuranceNumber = "";
    private String placeOfRegister = "";
    private String sortType = "ASC";
    private String currentPage ="1";

    public SearchForm(String fullName, String insuranceNumber, String placeOfRegister) {
        this.fullName = fullName;
        this.insuranceNumber = insuranceNumber;
        this.placeOfRegister = placeOfRegister;
    }
}
