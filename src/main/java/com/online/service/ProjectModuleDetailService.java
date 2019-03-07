package com.online.service;

import com.online.entity.online.ProjectModuleDetail;
/**
 * 
 * @author zuozhiping
 * 
 * 项目模块明细服务接口
 *
 */
public interface ProjectModuleDetailService extends BaseService<ProjectModuleDetail, Integer> {

    void deleteModeleDetailByInfo(Integer id);

}