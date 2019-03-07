package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ProjectModuleDetailDao;
import com.online.entity.online.ProjectModuleDetail;

/**
 * 
 * @author 左志平
 * 
 * dao-项目模块明细实现
 *
 */
@Repository("projectModuleDetailDaoImpl")
public class ProjectModuleDetailDaoImpl extends BaseDaoImpl<ProjectModuleDetail, Integer> implements ProjectModuleDetailDao {

    @Override
    public void deleteModeleDetailByInfo(Integer id) {
       String sql="delete from ol_project_module_detail where project_module_info=?1 ";
        entityManager.createNativeQuery(sql).setParameter(1, id).executeUpdate();
    }



}