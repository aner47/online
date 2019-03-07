package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.BoilerInformationDao;
import com.online.entity.online.BoilerInformation;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 * dao-锅炉信息实现
 *
 */
@Repository("boilerInformationDaoImpl")
public class BoilerInformationDaoImpl extends BaseDaoImpl<BoilerInformation, Integer> implements BoilerInformationDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<BoilerInformation> findSourceNameById(Integer id) {
        Integer projectId = SpringUtils.getProjectId();
       String sql="SELECT olm.* FROM  `ol_boiler_information` olm  "
               + "WHERE olm.`enterprise`=?1 AND olm.`project`=?2";
       Query createNativeQuery = entityManager.createNativeQuery(sql,BoilerInformation.class);
       createNativeQuery.setParameter(1,id);
       createNativeQuery.setParameter(2, projectId);
       List<BoilerInformation> resultList = createNativeQuery.getResultList();
        return resultList;
    }



}