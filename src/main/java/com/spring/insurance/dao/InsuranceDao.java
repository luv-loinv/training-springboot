package com.spring.insurance.dao;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.spring.insurance.bo.InsuranceBO;

@Service
public class InsuranceDao {
   public ArrayList<InsuranceBO> getDetailById(int insuranceId) {
	   ArrayList<InsuranceBO> list = new ArrayList<>();
	   list.add(
			   new InsuranceBO(
					   123,
					   "0988719747",
					   "19/04/1993",
					   "19/04/1993",
					   "deo co"
			   )
	   );
	   return list;
   }
} 