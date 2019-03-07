package com.online.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.online.dao.ModuleDetailDao;
import com.online.entity.online.ModuleDetail;

/**
 * 
 * @author 左志平
 * 
 * dao-模块明细实现
 *
 */
@Repository("moduleDetailDaoImpl")
public class ModuleDetailDaoImpl extends BaseDaoImpl<ModuleDetail, Integer> implements ModuleDetailDao {

    @Override
    public void deleteDetails(Integer ids) {
        String sql="delete from ol_module_detail where module_info =?1";
        Query setParameter = entityManager.createNativeQuery(sql).setParameter("1", ids);
        setParameter.executeUpdate();
        
    }



}