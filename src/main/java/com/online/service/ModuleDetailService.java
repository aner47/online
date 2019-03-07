package com.online.service;

import java.util.List;

import com.online.entity.online.ModuleDetail;
/**
 * 
 * @author zuozhiping
 * 
 * 模块明细服务接口
 *
 */
public interface ModuleDetailService extends BaseService<ModuleDetail, Integer> {

    void deleteDetails(Integer ids);
    
    public List<ModuleDetail> getListByModuleId(int moduleId);

}