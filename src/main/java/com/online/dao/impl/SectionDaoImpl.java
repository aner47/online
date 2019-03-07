package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.SectionDao;
import com.online.entity.online.Section;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 * dao-工段实现
 *
 */
@Repository("sectionDaoImpl")
public class SectionDaoImpl extends BaseDaoImpl<Section, Integer> implements SectionDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Section> findSourceNameById(Integer id) {
        Integer projectId = SpringUtils.getProjectId();
        String sql="SELECT olm.* FROM  `ol_section` olm  "
                + "WHERE olm.`enterprise`=?1 AND olm.`project`=?2";
        Query createNativeQuery = entityManager.createNativeQuery(sql,Section.class);
        createNativeQuery.setParameter(1,id);
        createNativeQuery.setParameter(2, projectId);
        List<Section> resultList = createNativeQuery.getResultList();
         return resultList;
    }



}