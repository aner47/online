package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.PowerPlantDao;
import com.online.entity.online.PowerPlant;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 * dao-电厂实现
 *
 */
@Repository("powerPlantDaoImpl")
public class PowerPlantDaoImpl extends BaseDaoImpl<PowerPlant, Integer> implements PowerPlantDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<PowerPlant> findSourceNameById(Integer id) {
        Integer projectId = SpringUtils.getProjectId();
        String sql="SELECT olm.* FROM  `ol_power_plant` olm  "
                + "WHERE olm.`enterprise`=?1 AND olm.`project`=?2";
        Query createNativeQuery = entityManager.createNativeQuery(sql,PowerPlant.class);
        createNativeQuery.setParameter(1,id);
        createNativeQuery.setParameter(2, projectId);
        List<PowerPlant> resultList = createNativeQuery.getResultList();
         return resultList;
    }



}