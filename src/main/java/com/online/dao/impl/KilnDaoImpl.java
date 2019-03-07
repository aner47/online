package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.KilnDao;
import com.online.entity.online.Kiln;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 * dao-窑炉实现
 *
 */
@Repository("kilnDaoImpl")
public class KilnDaoImpl extends BaseDaoImpl<Kiln, Integer> implements KilnDao {

    @SuppressWarnings({ "all" })
    @Override
    public List<Kiln> findSourceNameById(Integer id) {
        Integer projectId = SpringUtils.getProjectId();
        String sql="SELECT olm.* FROM  `ol_kilnn` olm  "
                + "WHERE olm.`enterprise`=?1 AND olm.`project`=?2";
        Query createNativeQuery = entityManager.createNativeQuery(sql,Kiln.class);
        createNativeQuery.setParameter(1,id);
        createNativeQuery.setParameter(2, projectId);
        List<Kiln> resultList = createNativeQuery.getResultList();
         return resultList;
    }



}