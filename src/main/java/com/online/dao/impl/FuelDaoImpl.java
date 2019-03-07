package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.FuelDao;
import com.online.entity.online.Fuel;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 * dao-锅炉信息实现
 *
 */
@Repository("fuelDaoImpl")
public class FuelDaoImpl extends BaseDaoImpl<Fuel, Integer> implements FuelDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Fuel> findSourceNameById(Integer id) {
        Integer projectId = SpringUtils.getProjectId();
       String sql="SELECT olm.* FROM  `ol_fuel` olm  "
               + "WHERE olm.`enterprise`=?1 AND olm.`project`=?2";
       Query createNativeQuery = entityManager.createNativeQuery(sql,Fuel.class);
       createNativeQuery.setParameter(1,id);
       createNativeQuery.setParameter(2, projectId);
       List<Fuel> resultList = createNativeQuery.getResultList();
        return resultList;
    }



}