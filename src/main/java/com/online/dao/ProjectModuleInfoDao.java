package com.online.dao;

import com.online.entity.online.ProjectModuleInfo;

/**
 * 
 * @author zuozhiping
 * 
 * dao-项目模块信息
 *
 */
public interface ProjectModuleInfoDao extends BaseDao<ProjectModuleInfo, Integer> {

    void delteModuleInfoByTemplateId(Integer ids);

    public ProjectModuleInfo findName(String name);
}