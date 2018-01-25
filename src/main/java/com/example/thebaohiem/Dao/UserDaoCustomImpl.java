package com.example.thebaohiem.Dao;

import com.example.thebaohiem.Dao.custom.UserDaoCustom;
import com.example.thebaohiem.model.Company;
import com.example.thebaohiem.model.Insurance;
import com.example.thebaohiem.model.User;
import com.example.thebaohiem.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoCustomImpl implements UserDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private InsuranceDao insuranceDao;

    /**
     * insert data into database matching with table
     *
     * @param user      : User
     * @param insurance : Insurance
     * @param company   : Company
     * @return true : insert success , false : insert false
     */
    @Override
    @Transactional
    public boolean insertUserInfo(User user, Insurance insurance, Company company) {
        try {
            if (company.getCompanyInternalId() == 0) {
                companyDao.save(company);
            }
            insuranceDao.save(insurance);
            userDao.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<UserInfo> getListUserInfo(int internalCompanyId, String fullName, String insuranceNumber, String placeOfRegister, int offset, int limit, String sortType) {
        List<UserInfo> userInfoList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT new " + UserInfo.class.getName() + "(u.userInternalId, u.userFullName, u.userSexDivision, u.birthdate, u.insurance.insuranceNumber, u.insurance.insuranceStartDate, u.insurance.insuranceEndDate, u.insurance.placeOfRegister) ");
        sql.append("FROM ");
        sql.append(User.class.getName() + " u ");
        sql.append(" WHERE  u.company.companyInternalId = ? ");
        Map<String, Object> mapAttri = new HashMap<>();
        Set set = mapAttri.entrySet();
        Iterator iterator;
        if (fullName.length() > 0) {
            mapAttri.put("u.userFullName", fullName);
        }
        if (insuranceNumber.length() > 0) {
            mapAttri.put("u.insurance.insuranceNumber", insuranceNumber);
        }
        if (placeOfRegister.length() > 0) {
            mapAttri.put("u.insurance.placeOfRegister", placeOfRegister);

        }
        if (mapAttri.size() > 0) {
            iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                sql.append(" AND ");
                sql.append(mapEntry.getKey() + " LIKE ? ");
            }
        }
        sql.append(" ORDER BY u.userFullName " + sortType);
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        int i = 1;
        query.setParameter(i++, internalCompanyId);
        if (mapAttri.size() > 0) {
            iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                if (mapEntry.getKey().equals("u.insurance.insuranceNumber")) {
                    query.setParameter(i++, mapEntry.getValue());
                } else {
                    query.setParameter(i++, "%" + mapEntry.getValue() + "%");
                }
            }
        }
        userInfoList = query.getResultList();

        return userInfoList;
    }

    @Override
    public int getTotalUser(int internalCompanyId, String fullName, String insuranceNumber, String placeOfRegister) {
        int count = 0;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT count(*) ");
        sql.append("FROM ");
        sql.append(User.class.getName() + " u ");
        sql.append(" WHERE  u.company.companyInternalId = ? ");
        Map<String, Object> attributeMap = new HashMap<>();
        Set set = attributeMap.entrySet();
        Iterator iterator;
        if (fullName.length() > 0) {
            attributeMap.put("u.userFullName", fullName);
        }
        if (insuranceNumber.length() > 0) {
            attributeMap.put("u.insurance.insuranceNumber", insuranceNumber);
        }
        if (placeOfRegister.length() > 0) {
            attributeMap.put("u.insurance.placeOfRegister", placeOfRegister);

        }
        if (attributeMap.size() > 0) {
            iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                sql.append(" AND ");
                sql.append(mapEntry.getKey() + " LIKE ? ");
            }
        }
        Query query = entityManager.createQuery(sql.toString());
        int i = 1;
        query.setParameter(i++, internalCompanyId);
        if (attributeMap.size() > 0) {
            iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = (Map.Entry) iterator.next();
                if (mapEntry.getKey().equals("u.insurance.insuranceNumber")) {
                    query.setParameter(i++, mapEntry.getValue());
                } else {
                    query.setParameter(i++, "%" + mapEntry.getValue() + "%");
                }
            }
        }
        count = (int)(long) query.getSingleResult();

        return count;
    }

    @Override
    public List<Company> getListCompany() {
        List<Company> companyList = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT new " + Company.class.getName() + "(c.companyInternalId ,c.companyName) ");
        sql.append("FROM ");
        sql.append(Company.class.getName() + " c ");
        sql.append(" order by c.companyName ASC");
        Query query = entityManager.createQuery(sql.toString());
        companyList = query.getResultList();
        return companyList;
    }
}
