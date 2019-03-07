package com.online.dao;

import com.online.entity.online.ProjectModuleDetail;

/**
 * 
 * @author zuozhiping
 * 
 * dao-项目模块明细
 *
 */
public interface ProjectModuleDetailDao extends BaseDao<ProjectModuleDetail, Integer> {

    void deleteModeleDetailByInfo(Integer id);


}