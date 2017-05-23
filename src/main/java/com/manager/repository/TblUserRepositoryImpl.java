package com.manager.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.manager.entities.TblUser;

public class TblUserRepositoryImpl implements TblUserRepositoryCustom {
	@Autowired
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<TblUser> getListUser(int companyID, String fullName, String insNumber, String placeReg, String sortBy,
			int offset, int limit) {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select user from TblUser user ");
		sqlString.append(" where user.tblCompany.companyInternalId = :companyID ");
		sqlString.append(" and ( :fullName = '' or user.userFullName like :fullName )");
		sqlString.append(" and ( :insNumber = '' or user.tblInsurance.insuranceNumber = :insNumber )");
		sqlString.append(" and ( :placeReg = '' or user.tblInsurance.placeOfRegister like :placeReg )");
		sqlString.append(" order by user.userFullName " + sortBy);

		Query query = em.createQuery(sqlString.toString());
		query.setParameter("companyID", companyID);
		query.setParameter("fullName", "%" + fullName + "%");
		query.setParameter("insNumber", insNumber);
		query.setParameter("placeReg", "%" + placeReg + "%");
		query.setFirstResult(offset);
		if (limit > 0) {
			query.setMaxResults(limit);
		}

		return query.getResultList();
	}

	public int getTotalUser(int companyID, String fullName, String insNumber, String placeReg) {
		StringBuilder sqlString = new StringBuilder();
		sqlString.append(" select count(*) from TblUser user ");
		sqlString.append(" where user.tblCompany.companyInternalId = :companyID ");
		sqlString.append(" and ( :fullName = '' or user.userFullName like :fullName )");
		sqlString.append(" and ( :insNumber = '' or user.tblInsurance.insuranceNumber = :insNumber )");
		sqlString.append(" and ( :placeReg = '' or user.tblInsurance.placeOfRegister like :placeReg )");

		Query query = em.createQuery(sqlString.toString());
		query.setParameter("companyID", companyID);
		query.setParameter("fullName", "%" + fullName + "%");
		query.setParameter("insNumber", insNumber);
		query.setParameter("placeReg", "%" + placeReg + "%");

		return ((Long) query.getSingleResult()).intValue();
	}
}
