package com.online.service;

import com.online.entity.online.ProjectModuleInfo;
/**
 * 
 * @author zuozhiping
 * 
 * 项目模块信息服务接口
 *
 */
public interface ProjectModuleInfoService extends BaseService<ProjectModuleInfo, Integer> {

    void saveModule(String moduleClassification, Integer moduleInfo, Integer templateId);
    
    public ProjectModuleInfo findName(String name);

}