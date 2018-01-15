package com.example.thebaohiem.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.thebaohiem.model.Company;

public interface CompanyDao extends JpaRepository<Company ,Integer> {
}
